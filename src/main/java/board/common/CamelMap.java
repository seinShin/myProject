package board.common;

import java.util.HashMap;

import board.common.util.CamelUtil;

@SuppressWarnings("rawtypes")
public class CamelMap extends HashMap {

	static final long serialVersionUID = -3144044422514529794L;

    @SuppressWarnings("unchecked")
	public Object put(Object key, Object value) {
        return super.put(CamelUtil.convert2CamelCase((String) key), value);
    }
    
    public String getString(Object key){		
		if(this.get(key) == null){
			return "";
		}else{
			return String.valueOf(this.get(key));
		}
	}
	
	public int getInt(Object key){		
		return Integer.parseInt(this.getString(key));
	}
}
