## 大业主对外API入参设计
### 1.错误码定义
| 状态码  | `含 义` |
| -----  | ---- |
| 500000 | `身份验证失败` |
| 500001 | `缺少参数` |
| 500002 | `服务已关闭或超时` |
| 500003 | `未知错误` |
| 500100 | `大业主手机号参数格式错误` |
| 500101 | `房源id参数格式错误` |
| 500102 | `房源图片规格错误,eg:像素大于等于400*315px且小于2M` |
| 500103 | `房源图片格式错误,eg:jpeg,png,jpg,bmp,gif` |
| 500104 | `小区名称多余30字` |
| 500105 | `出租方式类型参数错误` |
| 500106 | `整套户型类型参数错误,eg:室[1-9]取整,厅&卫[0-10]取整` |
| 500107 | `楼层类型参数错误:eg:[1-99]取整且层>=楼` |
| 500108 | `房屋情况参数范围错误` |
| 500109 | `面积参数范围错误,eg:[1-9999]取整` |
| 500110 | `租金参数范围错误,eg:[100,20000]取整` |
| 500111 | `付款方式参数范围错误` |
| 500112 | `配置参数范围错误` |
| 500113 | `房源标题字数范围错误,eg:[5-30]字` |
| 500114 | `房源描述字数范围错误,eg:[1-1000]字` |
| 500115 | `单间出租参数错误:eg:[0-2]` |
| 500201 | `账号余额不足` |
| 500202 | `房源不属于经纪人操作受限` |
| 500203 | `房源已下架不可推荐操作受限` |
| 500204 | `房源已删除不可推荐操作受限` |
| 500205 | `大业主不存在` |
| 500206 | `房源没有找到` |
| 500207 | `图片没有找到` |

### 2.入参判断流程
![入参流程](get_arguments_process.png)
	
### 3.详细步骤	
	a.身份合法性验证--参考《拦截器设计》
	b.参数大类划分
		* 发房API为1
		* 管房API为2
		* 获取房源信息API为3
		* 图片API为4
	c.伪代码
		1.基础接口
		interface Sublessor_Api_Argument_Base {
			 public function identity($params)
			 public function missing($params) //根据参数大类划分,特例
			 public function premission($params)
			 public function layout($params)
			 public function argScope($params)
		}
		2.业务实例化
		class Sublessor_Api_Argument_Instance implements Sublessor_Api_Argument_Base{
			
			public $status;
			public $params;
			$key = $this->params['key'];
			public function identity($key,$sign){
				//$this->biz;
				$this->status;exit();
			}
			
			public function missing($flag){
				switch($flag){
					case 1:
						//发房API
					case 2:
						//管房API
					case 3:
						//获取房源信息API
					case 4:
						//图片API
					default:
						//$this->status;exit();
				}
			}
			
			public function premission($user_id,$props){
				//$this->status;exit();
			}
			
			public function layout($params){
				//$this->status;exit();
			}
			
			public function argScop($params){
				//$this->status;exit();
			}
			
			public function argument_process($params){
				$this->identity($key,$sign);
				$this->missing($flag);
				$this->premission($user_id,$props);
				$this->layout($key,$params);
				$this->argScorp($params);
			}
			
		}
		



