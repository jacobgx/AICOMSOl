/*
 * piezoelectric_layered.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class piezoelectric_layered {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics("lshell").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("lshell").feature("pzm1").selection().all();
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");
    model.component("comp1").physics("ecis").create("epzml1", "PiezoelectricLayer");
    model.component("comp1").physics("ecis").feature("epzml1").selection().all();

    model.component("comp1").multiphysics().create("pzel1", "PiezoelectricEffectLS", 2);
    model.component("comp1").multiphysics("pzel1").set("Solid_physics", "lshell");
    model.component("comp1").multiphysics("pzel1").set("Electrostatics_physics", "ecis");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/pzel1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{50, 30});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 50, 0});
    model.component("comp1").geom("geom1").run("arr1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").label("Aluminum");
    model.material("mat1").set("family", "aluminum");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.material("mat2").propertyGroup().create("StressCharge", "StressCharge", "Stress-charge form");
    model.material("mat2").label("Lead Zirconate Titanate (PZT-5H)");
    model.material("mat2").set("family", "lead");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.material("mat2").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "1e-4[m]", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("thickness", "1[mm]", 0);
    model.material("lmat1").setIndex("link", "mat2", 1);
    model.material("lmat1").setIndex("thickness", "4[mm]", 1);
    model.material("lmat1").setIndex("meshPoints", 4, 1);
    model.material("lmat1").setIndex("thickness", "1[mm]", 2);
    model.material("lmat1").set("widthRatio", "8/30");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp1").physics("ecis").feature("epzml1").set("allLayers", false);
    model.component("comp1").physics("ecis").feature("epzml1").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("ecis").feature("epzml1").setIndex("shelllist_lCheck", 0, 2, 0);
    model.component("comp1").physics("lshell").feature("pzm1")
         .label("\u538b\u7535\u6750\u6599\uff08Z \u6781\u8f74\uff09");
    model.component("comp1").physics("lshell").feature("pzm1").selection().set(1);
    model.component("comp1").physics("lshell").feature("pzm1").set("allLayers", false);
    model.component("comp1").physics("lshell").feature("pzm1").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("lshell").feature("pzm1").setIndex("shelllist_lCheck", 0, 2, 0);
    model.component("comp1").physics("lshell").feature("pzm1").set("ConstitutiveRelation", "StrainCharge");
    model.component("comp1").physics("lshell").feature().duplicate("pzm2", "pzm1");
    model.component("comp1").physics("lshell").feature("pzm1")
         .comments("In this feature, the orientation of the piezoelectric layer is in the default state, with the pole axis in the z-direction.");
    model.component("comp1").physics("lshell").feature("pzm2")
         .label("\u538b\u7535\u6750\u6599\uff08X \u6781\u8f74\uff09");
    model.component("comp1").physics("lshell").feature("pzm2").selection().set(2);
    model.component("comp1").physics("lshell").feature("pzm2").set("Flip", "Flipn_t1");
    model.component("comp1").physics("lshell").feature("pzm2")
         .comments("In this feature, the orientation of the piezoelectric layer is changed, with the pole axis in the x-direction.");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1, 4);
    model.component("comp1").physics("ecis").feature("csh1").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ecis").feature("csh1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ecis").feature("csh1").feature("pot1").set("applyTo", "bottom");
    model.component("comp1").physics("ecis").feature("csh1").feature("pot1").set("V0", 20);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature("arr1").active(false);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature("arr1").active(true);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("lshell").feature("pzm1").selection().set(1);
    model.component("comp1").physics("lshell").feature("pzm2").selection().set(2);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(1);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u52bf");
    model.result("pg1").set("data", "lshl1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "V");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u5782\u76f4\u4f4d\u79fb\uff08Z \u6781\u8f74\uff09");
    model.result("pg2").set("data", "lshl1");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "w");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().dataset().duplicate("lshl2", "lshl1");
    model.result().dataset("lshl2").selection().set(2);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u5782\u76f4\u4f4d\u79fb\uff08X \u6781\u8f74\uff09");
    model.result("pg3").set("data", "lshl2");
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("colortable", "GrayScale");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("scenelight", true);

    model.result("pg1").feature("surf1").set("expr", "V");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("titletype", "auto");
    model.result("pg1").feature("surf1").set("colorlegend", true);
    model.result("pg1").run();
    model.result("pg2").run();

    model.title("\u542b\u538b\u7535\u6750\u6599\u7684\u591a\u5c42\u58f3");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u591a\u5c42\u58f3\u529f\u80fd\u5bf9\u538b\u7535\u5668\u4ef6\u8fdb\u884c\u5efa\u6a21\u3002\u5176\u4e2d\u7814\u7a76\u6750\u6599\u65b9\u5411\u7684\u4e24\u79cd\u60c5\u51b5\u3002\u5728\u7b2c\u4e00\u79cd\u60c5\u51b5\u4e0b\uff0c\u6781\u8f74\u5782\u76f4\u4e8e\u58f3\u8868\u9762\uff0c\u5bfc\u81f4\u539a\u5ea6\u53d1\u751f\u53d8\u5316\u3002\u5728\u7b2c\u4e8c\u79cd\u60c5\u51b5\u4e0b\uff0c\u6781\u8f74\u4f4d\u4e8e\u58f3\u5e73\u9762\u5185\uff0c\u5bfc\u81f4\u5f2f\u66f2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("piezoelectric_layered.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
