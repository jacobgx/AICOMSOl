/*
 * thermal_decomposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:23 by COMSOL 6.3.0.290. */
public class thermal_decomposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("H1", "1[cm]", "\u53cd\u5e94\u5668\u9ad8\u5ea6");
    model.param().set("W1", "12[cm]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("H2", "3[mm]", "\u53f0\u9636\u9ad8\u5ea6");
    model.param().set("W2", "3[cm]", "\u53f0\u9636\u957f\u5ea6");
    model.param().set("R1", "2[mm]", "\u7b52\u534a\u5f84");
    model.param().set("xpos", "6[cm]", "\u7b52 x \u5750\u6807");
    model.param().set("ypos", "5[mm]", "\u7b52 y \u5750\u6807");
    model.param().set("v_inlet", "5e-4[m/s]", "\u6d41\u5165\u901f\u5ea6");
    model.param().set("DA", "2e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cA");
    model.param().set("E", "72[kJ/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("H", "-100[kJ/mol]", "\u53cd\u5e94\u70ed");
    model.param().set("A", "1e10[1/s]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("cA_inlet", "1000[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cA");
    model.param()
         .set("c_solv", "(998[kg/m^3]-cA_inlet*Mn_A)/Mn_solv", "\u6d53\u5ea6\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param().set("Mn_A", "0.034[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cA");
    model.param().set("Mn_F", "0.025[kg/mol]", "\u5e73\u5747\u6469\u5c14\u8d28\u91cf\uff0cF");
    model.param().set("Mn_solv", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param().set("T_inlet", "300[K]", "\u6e29\u5ea6\uff0c\u5165\u53e3");
    model.param().set("T_cyl", "325[K]", "\u6e29\u5ea6\uff0c\u52a0\u70ed\u7b52");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W1", "H1"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W2", "H2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R1");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"xpos", "ypos"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("c1", 3);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"xpos+10*R1", "ypos"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 6);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic ");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(6);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u52a0\u70ed\u5668");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(7, 8, 9);

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_inlet");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 3, 4, 5, 7, 8, 9, 10, 11);
    model.component("comp1").mesh("mesh1").feature("bl1").feature().duplicate("blp2", "blp1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blhminfact", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7b49\u6e29\u6d41");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").label("\u6d41\u573a");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Acanthaster");
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("ynumber", 11);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 5);
    model.result("pg1").run();

    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);

    model.component("comp1").physics("ht").label("\u6d41\u4f53\u4f20\u70ed\uff08\u7814\u7a76 2\uff09");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_inlet");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().named("sel3");
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_cyl");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel2");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").label("\u975e\u7b49\u6e29\u6d41\u52a8\uff08\u7814\u7a76 2\uff09");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");

    model.component("comp1").physics("spf").create("init2", "init", 2);
    model.component("comp1").physics("spf").feature("init2").selection().all();
    model.component("comp1").physics("spf").feature("init2").set("u_init", new String[]{"u", "v", "0"});
    model.component("comp1").physics("spf").feature("init2").set("p_init", "p");

    model.study("std2").label("\u975e\u7b49\u6e29\u6d41\u52a8");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg3").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std2").feature("stat").setSolveFor("/physics/chem", false);

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);

    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA"});
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp1.T");
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "A=>F");
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "E");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("H", "H");
    model.component("comp1").physics("chem").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("chem").feature("F").set("M", "Mn_F");
    model.component("comp1").physics("chem").feature("F").set("cLock", true);
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("chem").feature("H2O").set("M", "Mn_solv");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "c_solv", 2, 0);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().named("sel1");
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cA_inlet", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().named("sel2");
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cA_src", "root.comp1.chem.R_A", 0);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0_src", "root.comp1.chem.Qtot");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std3").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std3").feature("stat").set("useinitsol", true);
    model.study("std3").feature("stat").set("initmethod", "sol");
    model.study("std3").feature("stat").set("initstudy", "std2");
    model.study("std3").label("\u5168\u8026\u5408");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "cA");
    model.result("pg4").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccA");
    model.result("pg4").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendstitle", true);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "tds.R_cA");
    model.result("pg5").feature("surf1").set("descr", "\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result("pg5").feature("surf1").set("colortable", "Viridis");
    model.result("pg5").feature("surf1").set("legendtitle", "R_cA");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u53cd\u5e94\u901f\u7387");

    model.title("\u70ed\u5206\u89e3");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u548c\u6c42\u89e3\u6d89\u53ca\u8d28\u91cf\u4f20\u9012\u3001\u80fd\u91cf\u4f20\u9012\u548c\u52a8\u91cf\u4f20\u9012\u7684\u8026\u5408\u65b9\u7a0b\u7684\u591a\u7269\u7406\u573a\u95ee\u9898\u3002\n\n\u672c\u4f8b\u5f88\u597d\u5730\u9610\u8ff0\u4e86\u5982\u4f55\u5faa\u5e8f\u6e10\u8fdb\u5730\u5efa\u7acb\u6a21\u578b\uff0c\u5373\u5148\u6c42\u89e3\u6d41\u573a\uff0c\u7136\u540e\u6c42\u89e3\u6d41\u573a\u548c\u4f20\u70ed\uff0c\u6700\u540e\u6c42\u89e3\u6d41\u573a\u3001\u4f20\u70ed\u53ca\u5316\u5b66\u653e\u70ed\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("thermal_decomposition.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
