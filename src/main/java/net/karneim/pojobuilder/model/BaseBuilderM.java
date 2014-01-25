package net.karneim.pojobuilder.model;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseBuilderM extends ClassM {
    private TypeM productType;

    public BaseBuilderM() {
        // Add @Generated to this builder
        this.getAdditionalImports().add(TypeM.get(Generated.class.getName()));
    }

    public TypeM getPojoType() {
        return productType;
    }

    public void setPojoType(TypeM productType) {
        this.productType = productType;
    }

    @Override
    public void exportImportTypes(Set<String> result) {
        super.exportImportTypes(result);
        productType.exportImportTypes(result);
    }

    @Override
    public String toString() {
        return "BaseBuilderM [pojoType=" + productType + ",type=" + getType() + ",superType=" + getSuperType()
                + ",abstract=" + isAbstract() + "]";
    }

}
