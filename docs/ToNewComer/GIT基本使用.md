## 基本开发上线流程

*	我们的代码开发、上线基本都是使用git
*	仓库 : http://gitlab.corp.anjuke.com/groups/_site

### 开发前的准备

为方便输入git命令，我们一般都会对常用的命令设置缩写。git缩写的配置放在`~/.gitconfig`中。具体命令如下：

  * 编辑 `~/.gitconfig` 文件

```
vim ~/.gitconfig
```

  * 输入以下内容：

```
[user]
    name=你的名字                 # 换成你的名字
    email=yourname@anjuke.com   # 换成你的email

[alias]
   br = branch
   ci = commit
   co = checkout
   lg = log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr %ci) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative
   rd = reset --hard
   st = status
```

  * clone对应的仓库

```
git clone git@gitlab.corp.anjuke.com:_site/anjuke.git anjuke-site
```

  * 增加user-dev仓库

```
cd anjuke-site
git remote add user-dev git@gitlab.corp.anjuke.com:_site/anjuke.git
```

  * 检查remote是否正确，执行 `git remote -v`，返回结果应该如下：

```
origin	git@gitlab.corp.anjuke.com:_site/anjuke.git (fetch)
origin	git@gitlab.corp.anjuke.com:_site/anjuke.git (push)
```

 以上返回结果中的user-dev与origin为仓库的别名。如果有一天分支仓库的地址变更了，但是你已经习惯用别名user-dev来操作git，此时请执行以下命令来修改对应仓库的地址。

```
git remote set-url user-dev git@git.corp.anjuke.com:anjuke/v2-feature-newurl
```

### 在user-dev仓库创建对应的开发分支

现在分支都是用deploy工具中的新建项目来生成的，它的实现方式是：

分支是从master仓库里拉出来，再以pmt-xxx-site-anjuke的名字push到user-dev仓库中，此时该分支的代码和master仓库里面的master分支是一致的。

###  在开发分支上完成相应的修改

假设此时你有一个项目为12345，并且已经用deploy建好了二手房分支，那么请执行一下命令：

切换到git工作目录：

    cd anjuke-site

更新开发分支的代码

    git fetch fetature pmt-12345-site-anjuke:pmt-12345-site-anjuke 
    # 前面的pmt-12345-site-anjuke为git服务器上的分支名，后面的为该分支拉倒本地之后的命名）

查看本地分支

    git branch

切到所要开发的分支

    git checkout pmt-12345-site-anjuke

开始改代码

    vim xxx

修改完成查看文件状态

    git status

在提交前查看diff

    git diff

添加新增文件/修改文件到 Git 代码仓库的索引中

    git add .

把修改/删除的提交到本地仓库

    git commit -am '注释'

查看最后一次修改的点

    git show

更新开发分支，获得其他同事的修改，

    git pull --rebase user-dev pmt-12345-site-anjuke
    # 注意：一定要 --rebase 来保证开发分支的线是直的
    # rebase 的时候可能会出现冲突，需要依次解决，具体参考下面的冲突解决

更新到user-dev仓库

    git push user-dev pmt-12345-site-anjuke


###  开发分支rebase最新线上分支

做这一步的目的是确保我们分支的修改在最新的线上代码的基础上。同时需要注意的是，操作这一步时请确保其他同事不会更新代码到你即将要做rebase的分支。具体操作步骤如下：

更新本地最新的master仓库代码

    git fetch origin

rebase master仓库中的master分支

    git rebase origin/master
    # rebase 的时候可能会出现冲突，需要依次解决，具体参考下面的冲突解决

更新到开发分支

    git push user-dev pmt-12345-site-anjuke -f


<h4 id='title5''>将线上分支的代码更新至线上环境。</h4>

 这一步交由QA操作，感兴趣的同学可以看下面的命令

```
git checkout master

git pull --rebase origin master

git fetch user-dev pmt-12345-site-anjuke：pmt-12345-site-anjuke

git checkout pmt-12345-site-anjuke

git rebase master

判断分支是否存在

git rebase master 并且判断是否有冲突

没有冲突：

git push user-dev pmt-12345-site-anjuke -f

git checkout master

git merge pmt-12345-site-anjuke --no-ff

git push origin master:master

有冲突的话

git rebase --abort

git checkout master

git branch -D pmt-12345-site-anjuke
```
**举个merge的例子，假设merge的分支为pmt-19482-site-anjuke**

merge前master的log

```
*   d706707 -  Merge branch 'pmt-19612-site-anjuke' (28 hours ago 2014-03-31 11:27:51 +0800) <app10-089>
|\  
| * 81a9456 - update community page css (28 hours ago 2014-03-31 11:27:45 +0800) <陈翠卓>
| * 080edfe - update community page css (28 hours ago 2014-03-31 11:27:45 +0800) <陈翠卓>
|/ 
```

merge后master的log

```
*   6e3b6a4 - Merge branch 'pmt-19482-site-anjuke' (26 hours ago 2014-03-31 13:27:28 +0800) <app10-089>
|\  
| * 35562c0 - _black (26 hours ago 2014-03-31 13:27:22 +0800) <霍本林>
| * b5fa231 - update link (26 hours ago 2014-03-31 13:27:22 +0800) <刘祺>
| * 8f63c73 - update link (26 hours ago 2014-03-31 13:27:21 +0800) <刘祺>
| * e3e4d7b - update link (26 hours ago 2014-03-31 13:27:21 +0800) <刘祺>
| * bacf18c - update link (26 hours ago 2014-03-31 13:27:21 +0800) <刘祺>
| * 57a0f49 - height - 2 (26 hours ago 2014-03-31 13:27:21 +0800) <霍本林>
| * 1253f49 - css change (26 hours ago 2014-03-31 13:27:20 +0800) <霍本林>
| * 7bbcfc8 - view2 (26 hours ago 2014-03-31 13:27:20 +0800) <霍本林>
| * faf5b2f - update -f (26 hours ago 2014-03-31 13:27:20 +0800) <刘祺>
| * 1d645e3 - update (26 hours ago 2014-03-31 13:27:20 +0800) <刘祺>
| * 4ecdf43 - 代码修改完毕，待前端修改图片 (26 hours ago 2014-03-31 13:27:20 +0800) <刘祺>
|/  
*   d706707 -  Merge branch 'pmt-19612-site-anjuke' (28 hours ago 2014-03-31 11:27:51 +0800) <app10-089>
|\  
| * 81a9456 - update community page css (28 hours ago 2014-03-31 11:27:45 +0800) <陈翠卓>
| * 080edfe - update community page css (28 hours ago 2014-03-31 11:27:45 +0800) <陈翠卓>
|/ 
```

