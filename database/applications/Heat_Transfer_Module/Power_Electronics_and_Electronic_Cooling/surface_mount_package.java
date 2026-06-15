/*
 * surface_mount_package.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:28 by COMSOL 6.3.0.290. */
public class surface_mount_package {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("surface_mount_package_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel2");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat2").set("family", "pcb");
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_blk1_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5851\u6599");
    model.component("comp1").material("mat3").selection().named("geom1_uni1_dom");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"2700[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"900[J/(kg*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.2[W/(m*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat4").label("Silicon");
    model.component("comp1").material("mat4").set("family", "custom");
    model.component("comp1").material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat4").set("diffuse", "custom");
    model.component("comp1").material("mat4")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("ambient", "custom");
    model.component("comp1").material("mat4")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat4").set("noise", true);
    model.component("comp1").material("mat4").set("fresnel", 0.7);
    model.component("comp1").material("mat4").set("roughness", 0.5);
    model.component("comp1").material("mat4").set("diffusewrap", 0);
    model.component("comp1").material("mat4").set("reflectance", 0);
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "170[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat5").label("Copper");
    model.component("comp1").material("mat5").set("family", "copper");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat5").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat5").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat4").selection().named("geom1_blk4_dom");
    model.component("comp1").material("mat5").selection().geom("geom1", 2);
    model.component("comp1").material("mat5").selection().named("geom1_sel2");

    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(11);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "2e8[W/m^3]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_adjsel1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "50[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "30[degC]");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "50[degC]");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().set(7);
    model.component("comp1").physics("ht").feature("sls1").set("lth_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls1").set("UserDefThicknessLayerType", "Conductive");
    model.component("comp1").physics("ht").create("sls2", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls2").selection().named("geom1_wp2_bnd");
    model.component("comp1").physics("ht").feature("sls2").set("lth_mat", "userdef");
    model.component("comp1").physics("ht").feature("sls2").set("lth", "5e-6[m]");
    model.component("comp1").physics("ht").feature("sls2").set("UserDefThicknessLayerType", "Conductive");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(4, 7);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").label("\u57df");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").feature().create("slit1", "SurfaceSlit");
    model.result("pg1").feature("slit1").label("\u975e\u591a\u5c42\u58f3");
    model.result("pg1").feature("slit1").set("showsolutionparams", "on");
    model.result("pg1").feature("slit1").set("solutionparams", "parent");
    model.result("pg1").feature("slit1").set("upexpr", "ht.Tu");
    model.result("pg1").feature("slit1").set("downexpr", "ht.Td");
    model.result("pg1").feature("slit1").set("titletype", "none");
    model.result("pg1").feature("slit1").set("smooth", "internal");
    model.result("pg1").feature("slit1").set("showsolutionparams", "on");
    model.result("pg1").feature("slit1").set("data", "parent");
    model.result("pg1").feature("slit1").feature().create("sel1", "Selection");
    model.result("pg1").feature("slit1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg1").feature("slit1").feature("sel1").selection().set(7, 37);
    model.result("pg1").feature("slit1").set("inheritplot", "vol1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slit1").set("upunit", "\u00b0C");
    model.result("pg1").feature("slit1").set("downunit", "\u00b0C");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("xnumber", "3");
    model.result("pg2").feature("mslc1").set("znumber", "2");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u82af\u7247\u8868\u9762\u6e29\u5ea6");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(195);
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u7845\u82af\u7247\u8868\u9762\u8d34\u88c5\u5c01\u88c5\u7684\u4f20\u70ed");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u96c6\u6210\u7535\u8def\u9760\u8fd1\u53d1\u70ed\u7ec4\u4ef6\u65f6\u7684\u7a33\u6001\u6e29\u5ea6\u5206\u5e03\uff0c\u4f7f\u7528\u4e86\u201c\u4f20\u70ed\u201d\u63a5\u53e3\u53ca\u201c\u8584\u5c42\u201d\u7279\u5f81\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("surface_mount_package.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
