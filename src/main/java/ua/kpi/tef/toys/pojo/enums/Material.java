package ua.kpi.tef.toys.pojo.enums;

public enum Material {
    SPRICE("metal", "varnish"),
    FIR("metal", "shellac"),
    CEDAR("metal", "oil"),
    METAL("wood", "oil");

    private String additionalMaterial;
    private String coating;

    Material(String additionalMaterial, String coating) {
        this.additionalMaterial = additionalMaterial;
        this.coating = coating;
    }

    public String getAdditionalMaterial() {
        return additionalMaterial;
    }

    public String getCoating() {
        return coating;
    }

    @Override
    public String toString() {
        return " additional material: " + additionalMaterial +
                ", coating: " + coating;
    }
}

