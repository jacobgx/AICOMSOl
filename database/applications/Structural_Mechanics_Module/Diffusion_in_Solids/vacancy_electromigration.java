/*
 * vacancy_electromigration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:04 by COMSOL 6.3.0.290. */
public class vacancy_electromigration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Diffusion_in_Solids");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ts", "TransportInSolids", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("dode", "DomainODE", "geom1");
    model.component("comp1").physics("dode").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ts", true);
    model.study("std1").feature("time").setSolveFor("/physics/ec", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode", true);

    model.param().set("Height", "15[um]");
    model.param().descr("Height", "\u9ad8\u5ea6");
    model.param().set("Thick", "1[m]");
    model.param().descr("Thick", "\u9762\u5916\u539a\u5ea6");
    model.param().set("Width", "50[um]");
    model.param().descr("Width", "\u5bbd\u5ea6");
    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").set("Cv0", "6020[um^-3]");
    model.param("par2").descr("Cv0", "\u521d\u59cb\u7a7a\u4f4d\u6d53\u5ea6");
    model.param("par2").set("Cv0moles", "Cv0/N_A_const");
    model.param("par2").descr("Cv0moles", "\u521d\u59cb\u7a7a\u4f4d\u6d53\u5ea6 (mol/m^3)");
    model.param("par2").set("Dv", "2.7e-6[cm^2/s]");
    model.param("par2").descr("Dv", "\u6269\u6563\u7cfb\u6570");
    model.param("par2").set("taus", "1.8e-3[s]");
    model.param("par2").descr("taus", "\u7a7a\u4f4d\u5f1b\u8c6b\u65f6\u95f4");
    model.param("par2").set("f", ".6");
    model.param("par2").descr("f", "\u5e73\u5747\u7a7a\u4f4d\u5f1b\u8c6b\u6bd4");
    model.param("par2").label("\u4f20\u9012\u53c2\u6570");
    model.param().create("par3");
    model.param("par3").set("Jden", "0.01[A/um^2]");
    model.param("par3").descr("Jden", "\u7535\u6d41\u5bc6\u5ea6");
    model.param("par3").set("Va", "1.66e7[pm^3]");
    model.param("par3").descr("Va", "\u539f\u5b50\u4f53\u79ef");
    model.param("par3").set("Znum", "4");
    model.param("par3").descr("Znum", "\u6709\u6548\u7535\u8377\u6570");
    model.param("par3").set("Zref", "50[ohm]");
    model.param("par3").descr("Zref", "\u53c2\u8003\u963b\u6297");
    model.param("par3").set("rho_res", "0.03132 [ohm*um]");
    model.param("par3").descr("rho_res", "\u53c2\u8003\u7535\u963b\u7387");
    model.param("par3").set("alpha_res", "0.0036367 [1/K]");
    model.param("par3").descr("alpha_res", "\u7535\u963b\u7387\u6e29\u5ea6\u7cfb\u6570");
    model.param("par3").label("\u7535\u6d41\u53c2\u6570");
    model.param().create("par4");
    model.param("par4").set("Qstar", ".00094[eV]");
    model.param("par4").descr("Qstar", "\u4f20\u9012\u7684\u70ed\u91cf");
    model.param("par4").set("T0", "473[K]");
    model.param("par4").descr("T0", "\u521d\u59cb\u6e29\u5ea6");
    model.param("par4").set("Tref", "293.15[K]");
    model.param("par4").descr("Tref", "\u53c2\u8003\u6e29\u5ea6");
    model.param("par4").label("\u4f20\u70ed\u53c2\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "vacancy_electromigration_hydrostatic_stress.txt");
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("argunit", "um", 0);
    model.func("int1").setIndex("fununit", "N/cm^2", 0);
    model.func("int1").label("t=70[s] \u7684\u6d41\u4f53\u9759\u5e94\u529b");
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "vacancy_electromigration_hydrostatic_stress_steady.txt");
    model.func("int2").set("interp", "piecewisecubic");
    model.func("int2").setIndex("argunit", "um", 0);
    model.func("int2").setIndex("fununit", "N/cm^2", 0);
    model.func("int2").label("\u7a33\u6001\u6d41\u4f53\u9759\u5e94\u529b");
    model.func().create("int3", "Interpolation");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "vacancy_electromigration_anode_stress.txt");
    model.func("int3").set("interp", "piecewisecubic");
    model.func("int3").setIndex("argunit", "s", 0);
    model.func("int3").setIndex("fununit", "N/cm^2", 0);
    model.func("int3").label("\u5e94\u529b vs. \u65f6\u95f4\uff0c\u9633\u6781");
    model.func().create("int4", "Interpolation");
    model.func("int4").set("source", "file");
    model.func("int4").set("filename", "vacancy_electromigration_cathode_stress.txt");
    model.func("int4").set("interp", "piecewisecubic");
    model.func("int4").setIndex("argunit", "s", 0);
    model.func("int4").setIndex("fununit", "N/cm^2", 0);
    model.func("int4").label("\u5e94\u529b vs. \u65f6\u95f4\uff0c\u9634\u6781");
    model.func().create("int5", "Interpolation");
    model.func("int5").set("source", "file");
    model.func("int5").set("filename", "vacancy_electromigration_cathode_concentration.txt");
    model.func("int5").setIndex("argunit", "s", 0);
    model.func("int5").setIndex("fununit", "1", 0);
    model.func("int5").label("\u6d53\u5ea6 vs. \u65f6\u95f4\uff0c\u9634\u6781");
    model.func().create("int6", "Interpolation");
    model.func("int6").set("source", "file");
    model.func("int6").set("filename", "vacancy_electromigration_anode_concentration.txt");
    model.func("int6").setIndex("argunit", "1", 0);
    model.func("int6").setIndex("fununit", "1", 0);
    model.func("int6").label("\u6d53\u5ea6 vs. \u65f6\u95f4\uff0c\u9633\u6781");

    model.nodeGroup().create("grp1", "GlobalDefinitions");
    model.nodeGroup("grp1").set("type", "func");
    model.nodeGroup("grp1").add("func", "int1");
    model.nodeGroup("grp1").add("func", "int2");
    model.nodeGroup("grp1").add("func", "int3");
    model.nodeGroup("grp1").add("func", "int4");
    model.nodeGroup("grp1").add("func", "int5");
    model.nodeGroup("grp1").add("func", "int6");
    model.nodeGroup("grp1").label("\u53c2\u8003\u503c");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Width", "Height"});
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "Thick");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "Tref");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("hstress", "-solid.pm", "\u6d41\u4f53\u9759\u5e94\u529b");
    model.component("comp1").variable("var1")
         .set("Cveq", "Cv0*exp((1-f)*Va*hstress/(k_B_const*T))", "\u5e73\u8861\u7a7a\u4f4d\u6d53\u5ea6");
    model.component("comp1").variable("var1").set("G", "-((c-Cveq)/taus)", "Rosenberg-Ohring \u590d\u5408");
    model.component("comp1").variable("var1").set("strate", "(G-cTIME)*Va*f+(1-f)*G*Va", "\u5e94\u53d8\u7387");
    model.component("comp1").variable("var1")
         .set("PFluxX", "e_const*Znum*(Dv/(k_B_const*T))*ec.Ex*c", "\u7535\u573a\u5f15\u8d77\u7684\u901a\u91cf\uff0cx \u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("PFluxY", "e_const*Znum*(Dv/(k_B_const*T))*ec.Ey*c", "\u7535\u573a\u5f15\u8d77\u7684\u901a\u91cf\uff0cy \u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("SFluxX", "-(Dv/(k_B_const*T))*d(hstress,x)*Va*f*c", "\u5e94\u529b\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf\uff0cx \u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("SFluxY", "-(Dv/(k_B_const*T))*d(hstress,y)*Va*f*c", "\u5e94\u529b\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf\uff0cy \u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("TFluxX", "-(Dv/(k_B_const*T^2))*Tx*Qstar*c", "\u6e29\u5ea6\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf\uff0cx \u65b9\u5411");
    model.component("comp1").variable("var1")
         .set("TFluxY", "-(Dv/(k_B_const*T^2))*Ty*Qstar*c", "\u6e29\u5ea6\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf\uff0cy \u65b9\u5411");

    model.component("comp1").physics("ec").prop("d").set("d", "Thick");
    model.component("comp1").physics("ec").feature("cucn1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("ec").feature("cucn1").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("ec").feature("cucn1").set("rho0_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("rho0", "rho_res");
    model.component("comp1").physics("ec").feature("cucn1").set("Tref_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("Tref", "Tref");
    model.component("comp1").physics("ec").feature("cucn1").set("alpha_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("alpha", "alpha_res");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(1);
    model.component("comp1").physics("ec").feature("gnd1").label("\u63a5\u5730\uff08\u9633\u6781\uff09");
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 1);
    model.component("comp1").physics("ec").feature("ncd1").selection().set(4);
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", "-Jden");
    model.component("comp1").physics("ec").feature("ncd1")
         .label("\u6cd5\u5411\u7535\u6d41\u5bc6\u5ea6\uff08\u9634\u6781\uff09");
    model.component("comp1").physics("ts").prop("dz").set("dz", "Thick");
    model.component("comp1").physics("ts").prop("TransportedQuantity").set("physicalQuantity", "numberdensity");
    model.component("comp1").physics("ts").prop("ShapeProperty").set("order_transportedQuantity", 1);
    model.component("comp1").physics("ts").feature("solid1")
         .set("D_c", new String[]{"Dv", "0", "0", "0", "Dv", "0", "0", "0", "Dv"});
    model.component("comp1").physics("ts").feature("init1").setIndex("initc", "Cv0", 0);
    model.component("comp1").physics("ts").create("src1", "Source", 2);
    model.component("comp1").physics("ts").feature("src1").selection().set(1);
    model.component("comp1").physics("ts").feature("src1").setIndex("S", "G", 0);
    model.component("comp1").physics("ts").feature("solid1").create("extfl1", "ExternalFlux", 2);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl1")
         .setIndex("J0_spatial", new String[]{"PFluxX", "0", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl1")
         .setIndex("J0_spatial", new String[]{"PFluxX", "PFluxY", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl1")
         .label("\u7535\u573a\u5f15\u8d77\u7684\u901a\u91cf");
    model.component("comp1").physics("ts").feature("solid1").create("extfl2", "ExternalFlux", 2);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl2")
         .setIndex("J0_spatial", new String[]{"SFluxX", "0", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl2")
         .setIndex("J0_spatial", new String[]{"SFluxX", "SFluxY", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl2")
         .label("\u5e94\u529b\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf");
    model.component("comp1").physics("ts").feature("solid1").create("extfl3", "ExternalFlux", 2);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl3")
         .setIndex("J0_spatial", new String[]{"TFluxX", "0", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl3")
         .setIndex("J0_spatial", new String[]{"TFluxX", "TFluxY", "0"}, 0);
    model.component("comp1").physics("ts").feature("solid1").feature("extfl3")
         .label("\u6e29\u5ea6\u68af\u5ea6\u5f15\u8d77\u7684\u901a\u91cf");
    model.component("comp1").physics("dode").prop("ShapeProperty").set("shapeFunctionType", "shlag");
    model.component("comp1").physics("dode").field("dimensionless").field("ev");
    model.component("comp1").physics("dode").field("dimensionless").component(1, "ev");
    model.component("comp1").physics("dode").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "strate", 0);
    model.component("comp1").physics("solid").prop("d").set("d", "Thick");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 2, 3, 4);
    model.component("comp1").physics("solid").feature("lemm1").create("eiel1", "ExternalStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("eiel1").set("StrainInput", "StrainTensor");
    model.component("comp1").physics("solid").feature("lemm1").feature("eiel1")
         .set("eext", new String[]{"ev/3", "0", "0", "0", "ev/3", "0", "0", "0", "ev/3"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time")
         .set("tlist", "0 40 50 60 70 80 range(500,500,3500) 24000 25200 26000 range(40000,10000,190000) range(200000,100000,1400000)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 45, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 45, 0);
    model.result("pg2").label("\u4f20\u8f93\u91cf (ts)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"ts.c"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"ts.tflux_cx", "ts.tflux_cy"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "V");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ec.normE");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 45, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b");
    model.result("pg6").feature("surf1").set("expr", "ev");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "c/Cv0");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 7.5, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 100, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 7.5, 1, 1);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{5}, 0);
    model.result("pg7").run();
    model.result("pg7").label("70 s \u7684\u6d41\u4f53\u9759\u5e94\u529b\uff08\u4e2d\u5fc3\u7ebf\uff09");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6d41\u4f53\u9759\u5e94\u529b (N/cm<sup>2</sup>)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "-solid.pm");
    model.result("pg7").feature("lngr1").set("unit", "N/cm^2");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u6a21\u578b\u7ed3\u679c", 0);
    model.result("pg7").feature("lngr1").label("\u6a21\u578b\u7ed3\u679c");
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").set("expr", "int1(x)");
    model.result("pg7").feature("lngr2").set("unit", "N/cm^2");
    model.result("pg7").feature("lngr2").set("linestyle", "dashed");
    model.result("pg7").feature("lngr2").set("linemarker", "circle");
    model.result("pg7").feature("lngr2").set("markerpos", "interp");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").setIndex("legends", "\u53c2\u8003\u6570\u636e", 0);
    model.result("pg7").feature("lngr2").label("\u9a8c\u8bc1\u7ed3\u679c");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").label("\u7a33\u6001\u6d41\u4f53\u9759\u5e94\u529b\uff08\u4e2d\u5fc3\u7ebf\uff09");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "int2(x)");
    model.result("pg8").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 7.5);
    model.result().dataset("cpt1").label("\u4e8c\u7ef4\u622a\u70b9\uff0c\u9633\u6781");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").set("pointx", 50);
    model.result().dataset("cpt2").label("\u4e8c\u7ef4\u622a\u70b9\uff0c\u9634\u6781");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6d41\u4f53\u9759\u5e94\u529b vs. \u65f6\u95f4");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6d41\u4f53\u9759\u5e94\u529b (N/cm<sup>2</sup>)");
    model.result("pg9").set("legendpos", "middleright");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").set("data", "cpt1");
    model.result("pg9").feature("ptgr1").set("expr", "-solid.pm");
    model.result("pg9").feature("ptgr1").set("unit", "N/cm^2");
    model.result("pg9").feature("ptgr1").set("xdataparamunit", "d");
    model.result("pg9").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr1").setIndex("legends", "\u6a21\u578b\u7ed3\u679c\uff0c\u9633\u6781", 0);
    model.result("pg9").feature("ptgr1").set("legend", true);
    model.result("pg9").feature("ptgr1").label("\u6a21\u578b\u7ed3\u679c\u9633\u6781");
    model.result("pg9").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr2").set("data", "cpt2");
    model.result("pg9").feature("ptgr2").setIndex("legends", "\u6a21\u578b\u7ed3\u679c\uff0c\u9634\u6781", 0);
    model.result("pg9").feature("ptgr2").label("\u6a21\u578b\u7ed3\u679c\u9634\u6781");
    model.result("pg9").run();
    model.result("pg9").create("glob1", "Global");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("xdataparamunit", "d");
    model.result("pg9").feature("glob1").setIndex("expr", "int3(t)", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "N/cm^2", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u53c2\u8003\u6570\u636e\uff0c\u9633\u6781", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "int4(t)", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "N/cm^2", 1);
    model.result("pg9").feature("glob1").setIndex("descr", "\u53c2\u8003\u6570\u636e\uff0c\u9634\u6781", 1);
    model.result("pg9").feature("glob1").set("linestyle", "dashed");
    model.result("pg9").feature("glob1").set("linemarker", "circle");
    model.result("pg9").feature("glob1").set("markerpos", "interp");
    model.result("pg9").feature("glob1").label("\u53c2\u8003\u6570\u636e");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u5f52\u4e00\u5316\u6d53\u5ea6 vs. \u65f6\u95f4");
    model.result("pg10").set("ylabel", "\u5f52\u4e00\u5316\u6d53\u5ea6 (1)");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").set("expr", "ts.c/Cv0");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("expr", "ts.c/Cv0");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "int5(t)", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "int6(t)", 1);
    model.result("pg10").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7a33\u6001\u5f52\u4e00\u5316\u6d53\u5ea6");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u7a33\u6001 von Mises \u5e94\u529b (N/m<sup>2</sup>)");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").run();

    model.title("IC \u4e92\u8fde\u7ebf\u4e2d\u7684\u7a7a\u4f4d\u7535\u8fc1\u79fb");

    model
         .description("\u968f\u7740\u96c6\u6210\u7535\u8def (IC) \u6280\u672f\u7684\u4e0d\u65ad\u8fdb\u6b65\uff0c\u7535\u8def\u53d8\u5f97\u66f4\u52a0\u5f3a\u5927\u548c\u7d27\u51d1\uff0c\u56e0\u6b64\u8bc6\u522b\u548c\u9632\u6b62\u7535\u8def\u6545\u969c\u7684\u539f\u56e0\u53d8\u5f97\u8d8a\u6765\u8d8a\u91cd\u8981\u3002\u5bfc\u81f4\u7535\u8def\u6545\u969c\u7684\u4e00\u4e2a\u7279\u522b\u5173\u952e\u7684\u56e0\u7d20\u662f\u7535\u8def\u4e92\u8fde\u7ebf\u4e2d\u7531\u91d1\u5c5e\u5185\u7a7a\u4f4d\u7684\u79ef\u7d2f\u5f15\u8d77\u7684\u7535\u8fc1\u79fb\uff0c\u8fd9\u79cd\u73b0\u8c61\u901a\u5e38\u662f\u6307\u7531\u7535\u573a\u3001\u6d53\u5ea6\u3001\u6d41\u4f53\u9759\u5e94\u529b\u548c\u6e29\u5ea6\u68af\u5ea6\u9a71\u52a8\u7684\u91d1\u5c5e\u5185\u7a7a\u4f4d\u7684\u8fc1\u79fb\u3002\u672c\u4f8b\u6f14\u793a\u4e86\u5982\u4f55\u5728 COMSOL Multiphysics \u4e2d\u5bf9\u8fd9\u79cd\u9ad8\u5ea6\u8026\u5408\u7684\u73b0\u8c61\u8fdb\u884c\u5efa\u6a21\u548c\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("vacancy_electromigration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
