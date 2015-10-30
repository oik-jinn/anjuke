## 用户端dev环境
## 10.249.7.25 用户 evans 密码 1 
### `ssh evans@10.249.7.25`

***
### 一、必须之行的操作：增加新用户
#### 1.登陆到该服务器，复制本地电脑上的ssh key,加入至/home/evans/.ssh/authorized_keys中

#### 2.使用方式与原192.168.188.55唯一的差别就是家目录变更，（原/home/www，现/home/evans）

#### 3.`bash /home/evans/add_user.sh`
#### 输入域账号

***
### 二、日常写代码同步方式，有2种：rsync, mount
#### rsync: 本地会存储一份代码，第一次获取全量代码时间较长。后续开发过程，会比较顺畅。 git提交方式不发生变化。不过变更的文件会有几秒延迟。
#### mount：本地不会存储代码，镜像到远程服务器。 后续开发过程，可能会因为网络传输，有点卡。 git提交方式，需要加bash git ***。

#### 1、本地rsync(可选)：
#### `for mac`
#### 1）在本地新建文件:/Library/LaunchAgents/rsync.plist
#### 内容如下:
```xml
 <?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
 <plist version="1.0">
 <dict>
     <key>Label</key>
     <string>logger</string>
     <key>ProgramArguments</key>
     <array>
         <string>/usr/bin/rsync</string>
         <string>-avz</string>
         <string>--delete</string>
         <string>--rsh=ssh</string>
         <string>/home/www/test/</string>
         <string>evans@10.249.7.25:/home/evans/workspace/jianqiangni/</string>
     </array>
     <key>WatchPaths</key>
     <array>
         <string>/home/www/test/</string>
     </array>
     <key>StandardOutPath</key>
     <string>/tmp/rsync_info.log</string>
     <key>StandardErrorPath</key>
     <string>/tmp/rsync_error.log</string>
 </dict>
 </plist>
```
#### 2）修改文件：其中xml中的/home/www/test为本地目录，/home/evans/workspace/jianqiangni为远程目录，需要变更
#### 3）执行rsync --delete-after -avzP -e ssh evans@10.249.7.25:/home/evans/workspace/jianqiangni/*  /home/www/test/(执行前需确定服务器要有你的目录 bash add_user.sh)
#### 4) `执行5之前确认本地代码已经umount了。`
#### 5）最后执行launchctl  load /Library/LaunchAgents/rsync.plist (如果是代码路径是需要root才能修改，则需要把本机root的ssh key加到服务器上)
#### 如果：把load改成unload就取消自动同步了

#### `for ubuntu`

#### 1）执行如下命令
```
cd /tmp
wget http://soft.dev.aifang.com/user-dev/inotify-tools-3.14.tar.gz
tar -xzvf inotify-tools-3.14.tar.gz
cd inotify-tools-3.14
./configure --prefix=/usr/local/inotify
make && make install

wget http://soft.dev.aifang.com/user-dev/rsync.sh
wget http://soft.dev.aifang.com/user-dev/common.sh(该文件需修改)
```
#### 2）执行rsync --delete-after -avzP -e ssh evans@10.249.7.25:/home/evans/workspace/jianqiangni/*  /home/www/test/(执行前需确定服务器要有你的目录 bash add_user.sh)
#### 3）最后执行nohup bash rsync.sh > nohup.out 2>&1 &
#### 如果：想取消自动同步，则kill进程。

***
#### 2、mount 挂载(可选)：
#### for mac

#### `mount_smbfs //evans:1@10.249.7.25/workspace/myname /home/www/test`

#### `mount_smbfs //evans:1@10.249.7.25/workspace/public /home/www/testpublic`

#### for ubuntu

#### `sudo apt-get install smbfs cifs-utils`

#### `sudo mount -t cifs //10.249.7.25/workspace/myname /home/www/test  -o uid=whoami,gid=whoami`  (whoami处需加``)

#### `sudo mount -t cifs //10.249.7.25/workspace/public /home/www/testpublic  -o uid=whoami,gid=whoami`  

***
### 三、nginx执行`/usr/sbin/nginx -s reload` 使本次修改生效

####  另外devel-config下面做修改后 请把修改提交到 master 。
