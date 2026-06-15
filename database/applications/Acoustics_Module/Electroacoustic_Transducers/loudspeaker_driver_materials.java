/*
 * loudspeaker_driver_materials.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class loudspeaker_driver_materials {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.label("loudspeaker_driver_materials.mph");

    model.material().create("mat1", "Common", "");
    model.material().create("mat2", "Common", "");
    model.material().create("mat3", "Common", "");
    model.material().create("mat4", "Common", "");
    model.material().create("mat5", "Common", "");
    model.material().create("mat6", "Common", "");
    model.material("mat6").propertyGroup().create("RemanentFluxDensity", "Remanent flux density");
    model.material("mat1").label("Composite");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.material("mat1").propertyGroup("def").set("poissonsratio", "0.42");
    model.material("mat1").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.04");
    model.material("mat2").label("Cloth");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.material("mat2").propertyGroup("def").set("poissonsratio", "0.3");
    model.material("mat2").propertyGroup("def").set("density", "650[kg/m^3]");
    model.material("mat3").label("Foam");
    model.material("mat3").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.material("mat3").propertyGroup("def").set("poissonsratio", "0.4");
    model.material("mat3").propertyGroup("def").set("density", "67[Kg/m^3]");
    model.material("mat4").label("Coil");
    model.material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat4").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.material("mat4").propertyGroup("def").set("poissonsratio", "0.35");
    model.material("mat4").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.material("mat5").label("Glass Fiber");
    model.material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat5").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.material("mat5").propertyGroup("def").set("poissonsratio", "0.33");
    model.material("mat5").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.material("mat5").propertyGroup("def").set("lossfactor", "0.04");
    model.material("mat6").label("Generic Ferrite");
    model.material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat6").propertyGroup("RemanentFluxDensity").set("murec", "");
    model.material("mat6").propertyGroup("RemanentFluxDensity").set("normBr", "");
    model.material("mat6").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat6").propertyGroup("RemanentFluxDensity").set("normBr", "0.4[T]");

    model.label("loudspeaker_driver_materials.mph");

    model.material("mat6").propertyGroup("def").set("poissonsratio", "");
    model.material("mat6").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat6").propertyGroup("def").set("density", "");
    model.material("mat6").propertyGroup("def").set("poissonsratio", new String[]{"0.3"});
    model.material("mat6").propertyGroup("def").set("youngsmodulus", new String[]{"200[GPa]"});
    model.material("mat6").propertyGroup("def").set("density", new String[]{"5000[kg/m^3]"});

    model.label("loudspeaker_driver_materials.mph");

    model.material().create("mat7", "Common", "");
    model.material("mat7").label("Fiberboard");
    model.material("mat7").propertyGroup("def").set("density", "");
    model.material("mat7").propertyGroup("def").set("poissonsratio", "");
    model.material("mat7").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat7").propertyGroup("def").set("density", new String[]{"900[kg/m^3]"});
    model.material("mat7").propertyGroup("def").set("poissonsratio", new String[]{"4[GPa]"});
    model.material("mat7").propertyGroup("def").set("youngsmodulus", new String[]{"0.3"});

    model.label("loudspeaker_driver_materials.mph");

    model.material("mat7").propertyGroup("def").set("poissonsratio", new String[]{});
    model.material("mat7").propertyGroup("def").set("youngsmodulus", new String[]{"4[GPa]"});
    model.material("mat7").propertyGroup("def").set("poissonsratio", new String[]{"0.3"});

    model.label("loudspeaker_driver_materials.mph");

    model.material("mat4").propertyGroup("def").set("lossfactor", new String[]{"0.05"});
    model.material("mat6").propertyGroup("def").set("lossfactor", new String[]{"0.01"});
    model.material("mat7").propertyGroup("def").set("lossfactor", new String[]{"0.07"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"67[kg/m^3]"});

    model.label("loudspeaker_driver_materials.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
