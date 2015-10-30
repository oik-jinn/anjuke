# git扩展
## git常见错误处理
   * [git常见错误之git fetch](#title1)
   * [git常见错误之git checkout](#title4)
   * [git常见错误之git pull --rebase](#title2)
   * [git常见错误之git push](#title3)

## git特殊处理
   * [git特殊处理之git reset –hard](#title6)
   * [git特殊处理之git cherry-pick](#title7)
   * [git特殊处理之git revert](#title8)

<h4 id='title1'>git常见错误之git fetch</h4>

```
git fetch feature pmt-xxx-site-anjuke:pmt-xxx-site-anjuke

fatal: Couldn't find remote ref pmt-xxx-site-anjuke
jianqiangni@jianqiangni:/home/evans/release/git/anjuke$ fatal: The remote end hung up unexpectedly

该git仓库里没有该分支存在

fatal: 拒绝获取到非裸版本库的当前分支 refs/heads/pmt-xxx-site-anjuke
fatal: The remote end hung up unexpectedly
```

本地已经存在名字为pmt-xxx-site-anjuke的分支了

PS:为了避免分支重复，所以请大家git fetch的时候本地的分支名与服务器上的分支名一致。

<h4 id='title4'>git常见错误之git chechout</h4>

假设当前开发分支为pmt-xxx-site-anjuke

```
git fetch feature pmt-xxx-site-anjuke:pmt-xxx-site-anjuke （默认fetch成功）
git checkout pmt-xxx-site-anjuke

```
如果失败，那么执行`git status`，会提示有冲突。
解决方法：

```
git rebase --abort
git reset --hard 上面命令解决不了问题再执行该命令
git checkout pmt-xxx-site-anjuke

```
<h4 id='title2'>git常见错误之git pull --rebase</h4>

```
git pull --rebase feature pmt-xxx-site-anjuke

来自 git.corp.anjuke.com:anjuke/v2-feature
 * branch           pmt-xxx-site-anjuke       -> FETCH_HEAD
好像已有一个 rebase-apply 目录，我怀疑您正处于另外一个变基过程中。
如果是这样，请尝试执行
	git rebase (--continue | --abort | --skip)
如果不是这样，请执行
	rm -fr "/home/evans/release/git/anjuke-site/.git/rebase-apply"
然后再重新执行变基。为避免您丢失重要数据，我已经停止当前操作。
```

此时执行 rm -fr "/home/evans/release/git/anjuke-site/.git/rebase-apply"
此情况常见于`git reset --hard`清理冲突之后执行git pull --feature feature pmt-xxx-site-anjuke

<h4 id='title3'>git常见错误之git push</h4>

第一种情况：
由于本地代码不是最新的导致push失败

```
git push feature pmt-xxx-site-anjuke:pmt-xxx-site-anjuke

To git@git.corp.anjuke.com:anjuke/v2-feature
 ! [rejected]        pmt-xxx-site-anjuke-> pmt-xxx-site-anjuke(non-fast-forward)
error: 无法推送一些引用到 'git@git.corp.anjuke.com:anjuke/v2-feature'
提示：更新被拒绝，因为您当前分支的最新提交落后于其对应的远程分支。
提示：再次推送前，先与远程变更合并（如 'git pull'）。详见
提示：'git push --help' 中的 'Note about fast-forwards' 小节。
```

此情况常见于多人开发同一个分支，因为你本地的代码不是最新的，有其他人提交了代码到服务器的本分支上了，此时执行下面的命令。

```
git pull --rebase feature pmt-xxx-site-anjuke 更新代码
再次执行git push feature pmt-xxx-site-anjuke:pmt-xxx-site-anjuke
```
第二种情况：
rebase master所造成的冲突

```
git checkout master
git pull --rebase origin master
git checkout pmt-xxx-site-anjuke
git rebase master
git push feature pmt-xxx-site-anjuke 成功之后，如果准备该项目上线，那么执行以下操作
git checkout master
git pull --renase origin master 更新master代码到最新
git checkout pmt-xxx-site-anjuke
git rebase master 
```
---------------------------------------------------------------------------
如果有冲突
`git status` 红色的为冲突文件 both modify 冲突文件可以vim /<<< 查看冲突位置
解决冲突文件后  `git add` 冲突文件 例如 `git add app-anjuke/classes/bll/qa/Manage.php`
假如所有冲突文件都解决了，那么执行下面操作

```
git add . 
git rebase --continue
git push feature pmt-xxx-site-anjuke -f
```

如果是多人开发分支，不知道冲突文件哪些代码是需要的，那么请把另外几个参与者一起坐到电脑前在解决，此时你也可以`git rebase --abort` 这命令的作用是撤销`git rebase master`操作，让本项目owner解决冲突

如果没有冲突
那么直接`git push feature pmt-xxx-site-anjuke -f`

注意：如果是多人开发的分支，那么做rebase master前请确保所有人都把代码push到pmt-xxx-site-anjuke

###git特殊处理

<h4 id='title6'>git特殊处理之git reset --hard</h4>

开发过程中会遇到一种情况，git add commit之后push的时候push错分支了。
`git reset --hard`是指将所选点之前的内容重置，不会留任何痕迹
此时怎么处理呢?例如A分支的代码push到B分支了

```
git fetch feature B:B
git checkout B
git log -n3

* 327b370 - push to B (2 分钟之前 2014-03-28 15:55:08 +0800) <jianqiangni>
*   dd75275 - (tag: 2014_13_40anjuke, tag: 2014_13_39anjuke) Merge branch 'hotfix-34993-site-anjuke' (3 小时之前 2014-03-28 12:37:18 +0800) <app10-089>
|\  
| * 0e3fc07 - fix (4 小时之前 2014-03-28 11:57:04 +0800) <杨砾>
```

显而易见，最后一个点搞错了

```
git reset --hard dd75275 
git push feature B:B -f
```
当然，以上做法都比较危险，慎重！

<h4 id='title7'>git特殊处理之git cherry-pick</h4>

继上述`git reset --hard`，来说下`git cherry-pick`
`git cherry-pick`用于把另一个本地分支的commit修改应用到当前分支。

```
git fetch feature B:B
git checkout B
git log -n3

* 327b370 - push to B (2 分钟之前 2014-03-28 15:55:08 +0800) <jianqiangni>
*   dd75275 - (tag: 2014_13_40anjuke, tag: 2014_13_39anjuke) Merge branch 'hotfix-34993-site-anjuke' (3 小时之前 2014-03-28 12:37:18 +0800) <app10-089>
|\  
| * 0e3fc07 - fix (4 小时之前 2014-03-28 11:57:04 +0800) <杨砾>
```

此时有一个分支C
```
git fetch feature C:C
git checkout C
git log -n3

*   dd75275 - (tag: 2014_13_40anjuke, tag: 2014_13_39anjuke) Merge branch 'hotfix-34993-site-anjuke' (2 小时之前 2014-03-28 12:37:18 +0800) <app10-089>
|\  
| * 0e3fc07 - fix (3 小时之前 2014-03-28 11:57:04 +0800) <杨砾>
|/  
*   0baf804 - (tag: 2014_13_38anjuke, origin/master, origin/HEAD) Merge branch 'pmt-19947-site-anjuke' (4 小时之前 2014-03-28 11:21:11 +0800) <app10-089>
```

只要执行`git cherry-pick 327b370 `
`git log -n3`
你会发现 B分支被cherry-pick的那个点 C分支上也能看到了
`git push feature C:C`

<h4 id='title8'>git特殊处理之git revert</h4>

`git revert`是撤销某次提交，但是这次撤销也会作为一次提交进行保存(这样就不会丢失原来修改过，但是没有提交的内容)。

```
git log -n3

* 327b370 - push to B (2 分钟之前 2014-03-28 15:55:08 +0800) <jianqiangni>
*   dd75275 - (tag: 2014_13_40anjuke, tag: 2014_13_39anjuke) Merge branch 'hotfix-34993-site-anjuke' (3 小时之前 2014-03-28 12:37:18 +0800) <app10-089>
|\  
| * 0e3fc07 - fix (4 小时之前 2014-03-28 11:57:04 +0800) <杨砾>


git revert 327b370 
git log -n3
* 923359c - (HEAD, rrrr) Revert "push to B" (7 秒钟之前 2014-03-28 16:15:56 +0800) <jianqiangni>
* 327b370 - (branch/rrrr) push to B (21 分钟之前 2014-03-28 15:55:08 +0800) <jianqiangni>
* 2fb255f - 44343 (83 分钟之前 2014-03-28 14:52:42 +0800) <jianqiangn>
```
