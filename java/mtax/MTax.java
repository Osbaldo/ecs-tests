import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MTax {
    
    public void mTax() {
    	// Do something
    }
	
    public static List<String> validate(List<X_Tax> xTaxList) {
        
        List<String> errorList = new ArrayList<>();
        
        // checks is xTaxList is empty
        if(!xTaxList.isEmpty()) {
        	// Initialize validIds
            List<String> validIds = new ArrayList<>();
            int cont = 0;
            for (X_Tax tax : xTaxList) {
            	// checks for ID existence
                if(tax.getId() != null){
                    validIds.add(tax.getId().toString());
                 // checks for Tax
                    if(tax.getTax() == null) {
                        errorList.add("El impuesto es obligatorio");
                    }
                    if(!tax.isLocal()){
                    	//checks for non-local taxes
                        cont++;
                    }
                    
                }
                
                

            }
            if(cont<=0) errorList.add("Debe de incluir al menos una tasa no local");
            
            if(!validIds.isEmpty()){
                    // checks if validIds is empty
                    List<X_Tax> xt = TaxsByListId(validIds, false);
                    if(xt.size() != validIds.size()){
                        errorList.add("Existen datos no guardados previamente");
                    }else{
                    	HashMap<String, X_Tax> mapTaxs = new HashMap<>();
                        for(X_Tax tax: xt){
                            mapTaxs.put(tax.getId().toString(), tax);
                        }
                        for(int i = 0; i < xTaxList.size(); i++){
                            if(xTaxList.get(i).getId() != null){
                                xTaxList.get(i).setCreated(mapTaxs.get(xTaxList.get(i).getId().toString()).getCreated());
                            }
                        }
                    }
            }
        }
        
        return errorList;
    }
    
}
