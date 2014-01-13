package iflytek.traffic.bean;

/**
 * 公交路线
 * @author gjyuan
 *
 */
public class BusLine {

	/**
	 * 车次
	 */
	public String name;
	/**
	 * 始发时间
	 */
	public String[] begin ;	
	/**
	 * 车长
	 */
	public String busDuty;
	/**
	 * 路线
	 */
	public String[] line ;
	/**
	 * 停靠
	 */
	public String[] pause ;
	
	public static String Arr2Str(String[] res,String flag){
		
		if (res == null || res.length<1) {
			return null;
		}
		
		StringBuffer result = new StringBuffer(res[0]);		
		for(int i=1;i<res.length;i++){
			result.append(flag+res[i]);
		}
		
		return result.toString();
	}
}
