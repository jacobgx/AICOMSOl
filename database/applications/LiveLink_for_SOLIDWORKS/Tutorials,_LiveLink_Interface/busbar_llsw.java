/*
 * busbar_llsw.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:48 by COMSOL 6.3.0.290. */
public class busbar_llsw {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_SOLIDWORKS\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").activate("ec", true);
    model.study("std1").feature("stat").activate("ht", true);
    model.study("std1").feature("stat").activate("emh1", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkSOLIDWORKS");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .set("input", new String[]{"cad1_Copper", "cad1_Titanium"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"cad1_Electrolyte_boundary", "cad1_Grounded_boundaries"});

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("mh", "3[mm]", "Minimum mesh element size");
    model.param().set("Jan", "8000[A/m^2]", "Anode current density");
    model.param().set("htca", "5[W/m^2/K]", "Heat transfer coefficient to air");
    model.param().set("Ta", "35[degC]", "Air temperature");
    model.param().set("htce", "3000[W/m^2/K]", "Heat transfer coefficient to electrolyte");
    model.param().set("Te", "100[degC]", "Electrolyte temperature");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").selection().named("geom1_cad1_Copper_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Titanium beta-21S");
    model.component("comp1").material("mat2").set("family", "titanium");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "105e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").set("family", "titanium");
    model.component("comp1").material("mat2").selection().named("geom1_cad1_Titanium_dom");

    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("geom1_cad1_Grounded_boundaries_bnd");
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 2);
    model.component("comp1").physics("ec").feature("ncd1").selection().named("geom1_cad1_Electrolyte_boundary_bnd");
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", "Jan");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_difsel1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "htca");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Ta");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_cad1_Electrolyte_boundary_bnd");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "htce");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "Te");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "mh");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "LL_D1_Sketch2_rod_sldprt", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "LL_D1_Sketch2_rod_sldprt", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(16[mm],2[mm],20[mm])", 0);
    model.study("std1").feature("param").setIndex("pname", "LL_D1_Sketch1_angle_connector_sldprt", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "LL_D1_Sketch1_angle_connector_sldprt", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("punit", "mm", 1);
    model.study("std1").feature("param").setIndex("plistarr", "range(60[mm],10[mm],90[mm])", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "1");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_V"});
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "cg");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "amg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("coarseningmethod", "classic");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").label("\u7535\u6d41");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_T"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", 0.8);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i2").set("prefuntype", "left");
    model.sol("sol1").feature("s1").feature("i2").set("itrestart", 50);
    model.sol("sol1").feature("s1").feature("i2").set("rhob", 20);
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 10000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "i2");
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").label("\u6e29\u5ea6");
    model.sol("sol1").feature("s1").feature("se1").create("ll1", "LowerLimit");
    model.sol("sol1").feature("s1").feature("se1").feature("ll1").set("lowerlimit", "comp1.T 0");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"LL_D1_Sketch2_rod_sldprt", "LL_D1_Sketch1_angle_connector_sldprt"});
    model.batch("p1").set("plistarr", new String[]{"range(16[mm],2[mm],20[mm])", "range(60[mm],10[mm],90[mm])"});
    model.batch("p1").set("sweeptype", "filled");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");

    model.sol("sol1").feature("s1").feature("se1").set("segstabacc", "segaacc");
    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("colortable", "RainbowLight");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "ThermalLight");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").label("\u7b49\u503c\u9762");
    model.result("pg3").feature("iso1").set("expr", "T");
    model.result("pg3").feature("iso1").set("number", 10);
    model.result("pg3").feature("iso1").set("levelrounding", false);
    model.result("pg3").feature("iso1").set("colortable", "ThermalLight");
    model.result("pg3").feature("iso1").set("data", "parent");

    model.batch("p1").run();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg2").set("data", "dset2");
    model.result("pg3").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").set("edgecolor", "gray");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "degC");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").run();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").set("type", "maximum");
    model.component("comp1").probe("dom1").selection().named("geom1_cad1_Copper_dom");
    model.component("comp1").probe("dom1").set("expr", "T");
    model.component("comp1").probe("dom1").set("descr", "\u6e29\u5ea6");
    model.component("comp1").probe("dom1").set("unit", "degC");
    model.component("comp1").probe("dom1").genResult("sol2");

    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").set("innerinput", "all");
    model.result().numerical("pev1").set("outerinput", "all");
    model.result().numerical("pev1").setResult();
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg4").feature("tblp1").set("xaxisdata", "auto");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tbls1", "TableSurface");
    model.result("pg5").feature("tbls1").set("source", "table");
    model.result("pg5").feature("tbls1").set("table", "tbl1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("tbls2", "tbls1");
    model.result("pg5").run();
    model.result("pg5").feature("tbls2").set("titletype", "none");
    model.result("pg5").feature("tbls2").set("rangedataactive", true);
    model.result("pg5").feature("tbls2").set("rangedatamax", 90);
    model.result("pg5").feature("tbls2").set("coloring", "uniform");
    model.result("pg5").feature("tbls2").set("color", "green");
    model.result("pg5").run();
    model.result("pg5").feature("tbls1").set("rangedataactive", true);
    model.result("pg5").feature("tbls1").set("rangedatamin", 90);
    model.result("pg5").feature("tbls1").set("coloring", "uniform");
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("data", "max1");
    model.result("pg5").feature("ann1").set("posxexpr", "16.8[mm]");
    model.result("pg5").feature("ann1").set("posyexpr", "69[mm]");
    model.result("pg5").feature("ann1").set("latexmarkup", true);
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").run();
    model.result("pg5").create("ann2", "Annotation");
    model.result("pg5").feature("ann2").set("data", "max1");
    model.result("pg5").feature("ann2").set("posxexpr", "18.2[mm]");
    model.result("pg5").feature("ann2").set("posyexpr", "79[mm]");
    model.result("pg5").feature("ann2").set("latexmarkup", true);
    model.result("pg5").feature("ann2").set("showpoint", false);
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u6bcd\u7ebf\u677f\u88c5\u914d\u7684\u7126\u8033\u70ed");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790\u6bcd\u7ebf\u677f\u88c5\u914d\u7684\u7535\u963b\u52a0\u70ed\uff08\u7126\u8033\u70ed\uff09\uff0c\u8be5\u88c5\u914d\u7528\u4e8e\u5728\u7535\u89e3\u8fc7\u7a0b\u4e2d\u5c06\u76f4\u6d41\u7535\u4ece\u7535\u6d41\u6e90\u4f20\u5bfc\u81f3\u9633\u6781\u3002\u4e3a\u4e86\u7814\u7a76\u5c3a\u5bf8\u53c2\u6570\u5bf9\u8bbe\u5907\u6e29\u5347\u7684\u5f71\u54cd\uff0c\u5176\u4e2d\u4f7f\u7528\u53c2\u6570\u5316\u6c42\u89e3\u5668\u5bf9\u9009\u5b9a\u5c3a\u5bf8\u7684 SOLIDWORKS\u00ae \u88c5\u914d\u7684\u591a\u79cd\u7ec4\u5408\u8fdb\u884c\u4eff\u771f\u8ba1\u7b97\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("busbar_llsw.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
