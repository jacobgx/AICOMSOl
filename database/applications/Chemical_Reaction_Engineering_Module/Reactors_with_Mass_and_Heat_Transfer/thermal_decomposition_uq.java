/*
 * thermal_decomposition_uq.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:24 by COMSOL 6.3.0.290. */
public class thermal_decomposition_uq {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cBetaC");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cBetaC", "cByprod"});
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);
    model.component("comp1").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("rfd1").set("Species_physics", "tds");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfd1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T_cyl", "(273.15 + 92)[K]", "\u6e29\u5ea6\uff0c\u52a0\u70ed\u7b52");
    model.param()
         .set("E", "110[kJ/mol]", "\u6d3b\u5316\u80fd\uff08C. Dhuique-Mayer \u7b49\u4eba\uff0c2007 \u5e74\uff09");
    model.param()
         .set("A", "9.4e13[1/s]", "\u9891\u7387\u56e0\u5b50\uff08C. Dhuique-Mayer \u7b49\u4eba\uff0c2007 \u5e74\uff09");
    model.param().set("H", "8.4[kJ/mol]", "\u53cd\u5e94\u7113\uff08W. Doering \u7b49\u4eba\uff0c1995 \u5e74\uff09");
    model.param()
         .set("D_BetaC_ref", "2.07e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u03b2 \u80e1\u841d\u535c\u7d20");
    model.param().set("dDdT", "(7.5e-9/100)[m^2/s/K]", "\u6269\u6563\u7cfb\u6570\u7684\u6e29\u5ea6\u5bfc\u6570");
    model.param().set("xpos", "6[cm]", "\u7b52 x \u5750\u6807");
    model.param().set("ypos", "5[mm]", "\u7b52 y \u5750\u6807");
    model.param().set("R1", "2[mm]", "\u7b52\u534a\u5f84");
    model.param().set("H1", "1[cm]", "\u53cd\u5e94\u5668\u9ad8\u5ea6");
    model.param().set("W1", "12[cm]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("H2", "3[mm]", "\u53f0\u9636\u9ad8\u5ea6");
    model.param().set("W2", "3[cm]", "\u53f0\u9636\u957f\u5ea6");
    model.param().set("v_inlet", "5e-4[m/s]", "\u6d41\u5165\u901f\u5ea6");
    model.param()
         .set("cBetaC_inlet", "1.3e-6[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6\uff0c\u03b2 \u80e1\u841d\u535c\u7d20");
    model.param()
         .set("Mn_BetaC", "0.5368726[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u03b2 \u80e1\u841d\u535c\u7d20");
    model.param().set("Mn_solv", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param()
         .set("c_solv", "(998[kg/m^3]-cBetaC_inlet*Mn_BetaC)/Mn_solv", "\u6d53\u5ea6\uff0c\u6eb6\u5242\uff08\u6c34\uff09");
    model.param().set("T_inlet", "(273.15+60)[K]", "\u6e29\u5ea6\uff0c\u5165\u53e3");

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
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 6);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").set(6);
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3").label("\u52a0\u70ed\u5668");
    model.component("comp1").selection("sel3").set(7, 8, 9);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size2", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature().duplicate("blp2", "blp1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blhminfact", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("D_BetaC", "D_BetaC_ref + dDdT*(T-298.15[K])");
    model.component("comp1").variable("var1").descr("D_BetaC", "\u6269\u6563\u7cfb\u6570");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(6);
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(1);
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(6);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_inlet");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(7, 8, 9);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_cyl");
    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);

    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "betaCarotene=>byproduct");
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "E");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("H", "H");
    model.component("comp1").physics("chem").feature("betaCarotene").set("M", "Mn_BetaC");
    model.component("comp1").physics("chem").feature("byproduct").set("M", "Mn_BetaC");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cBetaC", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cByprod", 1, 0);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").setIndex("materialType", "nonSolid", 0);
    model.component("comp1").physics("ht").feature("hs1").set("Q0_src", "root.comp1.chem.Qtot");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);

    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cBetaC", new String[]{"D_BetaC", "0", "0", "0", "D_BetaC", "0", "0", "0", "D_BetaC"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cBetaC_inlet", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(6);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1")
         .setIndex("R_cBetaC_src", "root.comp1.chem.R_betaCarotene", 0);
    model.component("comp1").physics("tds").feature("reac1")
         .setIndex("R_cByprod_src", "root.comp1.chem.R_byproduct", 0);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_inlet");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.study("std1").feature().duplicate("stat1", "stat");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, BetaC (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cBetaC"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tds.tflux_cBetaCx", "tds.tflux_cBetaCy"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, Byprod (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cByprod"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cByprodx", "tds.tflux_cByprody"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1);
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
    model.result("pg6").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "nitf1.T");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg6").feature().create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("solutionparams", "parent");
    model.result("pg6").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg6").feature("arws1").set("xnumber", 30);
    model.result("pg6").feature("arws1").set("ynumber", 30);
    model.result("pg6").feature("arws1").set("arrowtype", "cone");
    model.result("pg6").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("data", "parent");
    model.result("pg6").feature("arws1").feature().create("col1", "Color");
    model.result("pg6").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg6").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg6").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl("intop1").label("\u5728\u51fa\u53e3\u5904\u8fdb\u884c\u79ef\u5206");
    model.component("comp1").cpl("intop1").set("opname", "intop_exit");

    model.component("comp1").variable("var1").set("molar_outflow_rate", "intop_exit(cBetaC*u)");
    model.component("comp1").variable("var1")
         .descr("molar_outflow_rate", "\u03b2-\u80e1\u841d\u535c\u7d20\u6d41\u51fa");

    model.study().create("std2");
    model.study("std2").create("ref", "StudyReference");
    model.study("std2").feature("ref").set("studyref", "std1");
    model.study("std2").create("uq", "UncertaintyQuantification");
    model.study("std2").label("\u7814\u7a76 2\uff1aUQ \u7b5b\u9009");
    model.study("std2").feature("uq").setIndex("funcname", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std2").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std2").feature("uq").setIndex("failif", "larger", 0);
    model.study("std2").feature("uq").setIndex("threshold", "", 0);
    model.study("std2").feature("uq").setIndex("funcname", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std2").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std2").feature("uq").setIndex("failif", "larger", 0);
    model.study("std2").feature("uq").setIndex("threshold", "", 0);
    model.study("std2").feature("uq").setIndex("qoiexpression", "comp1.molar_outflow_rate", 0);
    model.study("std2").feature("uq").setIndex("pname", "T_cyl", 0);
    model.study("std2").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "K", 0);
    model.study("std2").feature("uq").setIndex("pname", "T_cyl", 0);
    model.study("std2").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "K", 0);
    model.study("std2").feature("uq").setIndex("pname", "E", 1);
    model.study("std2").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "J/mol", 1);
    model.study("std2").feature("uq").setIndex("pname", "E", 1);
    model.study("std2").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "J/mol", 1);
    model.study("std2").feature("uq").setIndex("pname", "A", 2);
    model.study("std2").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "1/s", 2);
    model.study("std2").feature("uq").setIndex("pname", "A", 2);
    model.study("std2").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "1/s", 2);
    model.study("std2").feature("uq").setIndex("pname", "H", 3);
    model.study("std2").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "J/mol", 3);
    model.study("std2").feature("uq").setIndex("pname", "H", 3);
    model.study("std2").feature("uq").setEntry("sourceType", "col4", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "J/mol", 3);
    model.study("std2").feature("uq").setIndex("pname", "D_BetaC_ref", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m^2/s", 4);
    model.study("std2").feature("uq").setIndex("pname", "D_BetaC_ref", 4);
    model.study("std2").feature("uq").setEntry("sourceType", "col5", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m^2/s", 4);
    model.study("std2").feature("uq").setIndex("pname", "dDdT", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m^2/(s*K)", 5);
    model.study("std2").feature("uq").setIndex("pname", "dDdT", 5);
    model.study("std2").feature("uq").setEntry("sourceType", "col6", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m^2/(s*K)", 5);
    model.study("std2").feature("uq").setIndex("pname", "xpos", 6);
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "xpos", 6);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("uq").setEntry("sourceType", "col7", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 6);
    model.study("std2").feature("uq").setIndex("pname", "ypos", 7);
    model.study("std2").feature("uq").setEntry("sourceType", "col8", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 7);
    model.study("std2").feature("uq").setIndex("pname", "ypos", 7);
    model.study("std2").feature("uq").setEntry("sourceType", "col8", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 7);
    model.study("std2").feature("uq").setIndex("pname", "R1", 8);
    model.study("std2").feature("uq").setEntry("sourceType", "col9", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 8);
    model.study("std2").feature("uq").setIndex("pname", "R1", 8);
    model.study("std2").feature("uq").setEntry("sourceType", "col9", "analytic");
    model.study("std2").feature("uq").setIndex("paramDescription", "m", 8);
    model.study("std2").feature("uq").setEntry("lboundselection", "col1", "T_cyl-5[K]");
    model.study("std2").feature("uq").setEntry("uboundselection", "col1", "T_cyl+5[K]");
    model.study("std2").feature("uq").setEntry("distributionselection", "col2", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col2", "E");
    model.study("std2").feature("uq").setEntry("s2selection", "col2", "0.003*E");
    model.study("std2").feature("uq").setEntry("distributionselection", "col3", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col3", "A");
    model.study("std2").feature("uq").setEntry("s2selection", "col3", "0.1*A");
    model.study("std2").feature("uq").setEntry("lboundselection", "col4", "0.97*H");
    model.study("std2").feature("uq").setEntry("uboundselection", "col4", "1.03*H");
    model.study("std2").feature("uq").setEntry("distributionselection", "col5", "normal");
    model.study("std2").feature("uq").setEntry("s1selection", "col5", "D_BetaC_ref");
    model.study("std2").feature("uq").setEntry("s2selection", "col5", "0.2*D_BetaC_ref");
    model.study("std2").feature("uq").setEntry("lboundselection", "col6", "0.5*dDdT");
    model.study("std2").feature("uq").setEntry("uboundselection", "col6", "1.5*dDdT");
    model.study("std2").feature("uq").setEntry("lboundselection", "col7", "4[cm]");
    model.study("std2").feature("uq").setEntry("uboundselection", "col7", "10[cm]");
    model.study("std2").feature("uq").setEntry("lboundselection", "col8", "2.75[mm]");
    model.study("std2").feature("uq").setEntry("uboundselection", "col8", "7.25[mm]");
    model.study("std2").feature("uq").setEntry("lboundselection", "col9", "1[mm]");
    model.study("std2").feature("uq").setEntry("uboundselection", "col9", "2.5[mm]");
    model.study("std2").feature("uq").set("plot", true);
    model.study("std2").feature("uq").set("plotgroup", "pg3");
    model.study("std2").feature("uq").set("errorhandling", "later");
    model.study("std2").feature("uq").set("keepsol", "all");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so2").set("psol", "sol4");
    model.batch("uq1").run("compute");

    model.result("pg7").run();

    model.study().create("std3");
    model.study("std3").feature().copy("uq", "std2/uq", "");
    model.study("std3").feature().copy("ref", "std2/ref", "");
    model.study("std3").feature("uq").set("uqtype", "screening");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "inverseuq");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std3").feature("uq").set("computeaction", "recompute");
    model.study("std3").feature("uq").set("designtable", "new");
    model.study("std3").feature("uq").set("verifyaction", "recompute");
    model.study("std3").feature("uq").set("tablegraphgrp", "new");
    model.study("std3").label("\u7814\u7a76 3\uff1aUQ \u7075\u654f\u5ea6\u5206\u6790");
    model.study("std3").feature("uq").set("selected", new String[]{"3"});
    model.study("std3").feature("uq")
         .set("distributionselection", new String[]{"col1", "uniform", "col2", "normal", "col3", "normal", "col4", "normal", "col5", "uniform", 
         "col6", "uniform", "col7", "uniform", "col8", "uniform"});
    model.study("std3").feature("uq")
         .set("s1selection", new String[]{"col1", "", "col2", "E", "col3", "A", "col4", "D_BetaC_ref", "col5", "", 
         "col6", "", "col7", "", "col8", ""});
    model.study("std3").feature("uq")
         .set("s2selection", new String[]{"col1", "", "col2", "0.003*E", "col3", "0.1*A", "col4", "0.2*D_BetaC_ref", "col5", "", 
         "col6", "", "col7", "", "col8", ""});
    model.study("std3").feature("uq")
         .set("lcdfselection", new String[]{"col1", "0.001", "col2", "0.001", "col3", "0.001", "col4", "0.001", "col5", "0.001", 
         "col6", "0.001", "col7", "0.001", "col8", "0.001"});
    model.study("std3").feature("uq")
         .set("lboundselection", new String[]{"col1", "T_cyl-5[K]", "col2", "1.0898E5", "col3", "6.4952E13", "col4", "7.9064E-10", "col5", "0.5*dDdT", 
         "col6", "4[cm]", "col7", "2.75[mm]", "col8", "1[mm]"});
    model.study("std3").feature("uq")
         .set("ucdfselection", new String[]{"col1", "0.999", "col2", "0.999", "col3", "0.999", "col4", "0.999", "col5", "0.999", 
         "col6", "0.999", "col7", "0.999", "col8", "0.999"});
    model.study("std3").feature("uq")
         .set("uboundselection", new String[]{"col1", "T_cyl+5[K]", "col2", "1.1102E5", "col3", "1.2305E14", "col4", "3.3494E-9", "col5", "1.5*dDdT", 
         "col6", "10[cm]", "col7", "7.25[mm]", "col8", "2.5[mm]"});
    model.study("std3").feature("uq")
         .set("punitselection", new String[]{"col1", "K", "col2", "J/mol", "col3", "1/s", "col4", "m^2/s", "col5", "m^2/(s*K)", 
         "col6", "m", "col7", "m", "col8", "m"});
    model.study("std3").feature("uq")
         .set("inputdatasource", new String[]{"col1", "specified", "col2", "specified", "col3", "specified", "col4", "specified", "col5", "specified", 
         "col6", "specified", "col7", "specified", "col8", "specified"});
    model.study("std3").feature("uq")
         .set("tablecolumnselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", "", "col8", ""});
    model.study("std3").feature("uq")
         .set("plistarrselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", "", "col8", ""});
    model.study("std3").feature("uq")
         .set("plistarrsizeselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", "", "col8", ""});
    model.study("std3").feature("uq").remove("sourceType", 3);
    model.study("std3").feature("uq").remove("paramDescription", 3);
    model.study("std3").feature("uq").remove("pname", new int[]{3});
    model.study("std3").feature("uq").set("selected", new String[]{"3"});
    model.study("std3").feature("uq")
         .set("distributionselection", new String[]{"col1", "uniform", "col2", "normal", "col3", "normal", "col4", "uniform", "col5", "uniform", 
         "col6", "uniform", "col7", "uniform"});
    model.study("std3").feature("uq")
         .set("s1selection", new String[]{"col1", "", "col2", "E", "col3", "A", "col4", "", "col5", "", 
         "col6", "", "col7", ""});
    model.study("std3").feature("uq")
         .set("s2selection", new String[]{"col1", "", "col2", "0.003*E", "col3", "0.1*A", "col4", "", "col5", "", 
         "col6", "", "col7", ""});
    model.study("std3").feature("uq")
         .set("lcdfselection", new String[]{"col1", "0.001", "col2", "0.001", "col3", "0.001", "col4", "0.001", "col5", "0.001", 
         "col6", "0.001", "col7", "0.001"});
    model.study("std3").feature("uq")
         .set("lboundselection", new String[]{"col1", "T_cyl-5[K]", "col2", "1.0898E5", "col3", "6.4952E13", "col4", "0.5*dDdT", "col5", "4[cm]", 
         "col6", "2.75[mm]", "col7", "1[mm]"});
    model.study("std3").feature("uq")
         .set("ucdfselection", new String[]{"col1", "0.999", "col2", "0.999", "col3", "0.999", "col4", "0.999", "col5", "0.999", 
         "col6", "0.999", "col7", "0.999"});
    model.study("std3").feature("uq")
         .set("uboundselection", new String[]{"col1", "T_cyl+5[K]", "col2", "1.1102E5", "col3", "1.2305E14", "col4", "1.5*dDdT", "col5", "10[cm]", 
         "col6", "7.25[mm]", "col7", "2.5[mm]"});
    model.study("std3").feature("uq")
         .set("punitselection", new String[]{"col1", "K", "col2", "J/mol", "col3", "1/s", "col4", "m^2/(s*K)", "col5", "m", 
         "col6", "m", "col7", "m"});
    model.study("std3").feature("uq")
         .set("inputdatasource", new String[]{"col1", "specified", "col2", "specified", "col3", "specified", "col4", "specified", "col5", "specified", 
         "col6", "specified", "col7", "specified"});
    model.study("std3").feature("uq")
         .set("tablecolumnselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", ""});
    model.study("std3").feature("uq")
         .set("plistarrselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", ""});
    model.study("std3").feature("uq")
         .set("plistarrsizeselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", "", "col7", ""});
    model.study("std3").feature("uq").remove("sourceType", 3);
    model.study("std3").feature("uq").remove("paramDescription", 3);
    model.study("std3").feature("uq").remove("pname", new int[]{3});
    model.study("std3").feature("uq").set("selected", new String[]{"3"});
    model.study("std3").feature("uq")
         .set("distributionselection", new String[]{"col1", "uniform", "col2", "normal", "col3", "normal", "col4", "uniform", "col5", "uniform", 
         "col6", "uniform"});
    model.study("std3").feature("uq")
         .set("s1selection", new String[]{"col1", "", "col2", "E", "col3", "A", "col4", "", "col5", "", 
         "col6", ""});
    model.study("std3").feature("uq")
         .set("s2selection", new String[]{"col1", "", "col2", "0.003*E", "col3", "0.1*A", "col4", "", "col5", "", 
         "col6", ""});
    model.study("std3").feature("uq")
         .set("lcdfselection", new String[]{"col1", "0.001", "col2", "0.001", "col3", "0.001", "col4", "0.001", "col5", "0.001", 
         "col6", "0.001"});
    model.study("std3").feature("uq")
         .set("lboundselection", new String[]{"col1", "T_cyl-5[K]", "col2", "1.0898E5", "col3", "6.4952E13", "col4", "4[cm]", "col5", "2.75[mm]", 
         "col6", "1[mm]"});
    model.study("std3").feature("uq")
         .set("ucdfselection", new String[]{"col1", "0.999", "col2", "0.999", "col3", "0.999", "col4", "0.999", "col5", "0.999", 
         "col6", "0.999"});
    model.study("std3").feature("uq")
         .set("uboundselection", new String[]{"col1", "T_cyl+5[K]", "col2", "1.1102E5", "col3", "1.2305E14", "col4", "10[cm]", "col5", "7.25[mm]", 
         "col6", "2.5[mm]"});
    model.study("std3").feature("uq")
         .set("punitselection", new String[]{"col1", "K", "col2", "J/mol", "col3", "1/s", "col4", "m", "col5", "m", 
         "col6", "m"});
    model.study("std3").feature("uq")
         .set("inputdatasource", new String[]{"col1", "specified", "col2", "specified", "col3", "specified", "col4", "specified", "col5", "specified", 
         "col6", "specified"});
    model.study("std3").feature("uq")
         .set("tablecolumnselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", ""});
    model.study("std3").feature("uq")
         .set("plistarrselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", ""});
    model.study("std3").feature("uq")
         .set("plistarrsizeselection", new String[]{"col1", "", "col2", "", "col3", "", "col4", "", "col5", "", 
         "col6", ""});
    model.study("std3").feature("uq").remove("sourceType", 3);
    model.study("std3").feature("uq").remove("paramDescription", 3);
    model.study("std3").feature("uq").remove("pname", new int[]{3});
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol46");
    model.sol("sol46").study("std3");
    model.sol("sol46").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pd2").feature("so2").set("psol", "sol46");
    model.batch("uq2").run("compute");

    model.result("pg8").run();
    model.result("pg8").run();

    model.study().create("std4");
    model.study("std4").feature().copy("uq", "std3/uq", "");
    model.study("std4").feature().copy("ref", "std3/ref", "");
    model.study("std4").feature("uq").set("uqtype", "screening");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "inverseuq");
    model.study("std4").feature("uq").set("uqresultgrp", "new");
    model.study("std4").feature("uq").set("uqtype", "uncertaintypropagation");

    model.func().create("gpm1", "GaussianProcess");
    model.func("gpm1").active(false);

    model.study("std4").feature("uq").set("globalgpfunction", "gpm1");

    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8")
         .addRows(new double[][]{{365.67765637322333, 110071.76263854766, 1.0114054004382633E14, 0.09905359981852663, 0.003720276087946072, 0.002215257920566165, 3.3138484601839294E-12}, {363.03596957949554, 110147.90387913912, 9.994829277610731E13, 0.09457627350259723, 0.003356657748547805, 0.0012299708098074632, 3.5245619718878295E-12}, {367.513311747571, 110264.99487807103, 9.503127522731467E13, 0.043756304710144134, 0.0057188013970484244, 0.001604797550839731, 2.762203381854356E-12}, {370.07485299385644, 109883.24310882168, 8.654882519933075E13, 0.07446134967381335, 0.0053633024932461535, 0.0019482118551187623, 2.7723959026253717E-12}, {367.1946895495363, 109772.13422470137, 1.0514374684093186E14, 0.04614191530622325, 0.005293935833408418, 0.0014756296287184631, 2.488267463692658E-12}, {366.5058262859554, 109811.31620400879, 1.0404800225130794E14, 0.08980297964792265, 0.00677081730298126, 0.002490431919554883, 3.243809064592022E-12}, {360.2933297406593, 110085.61265942338, 9.738512187345478E13, 0.07988310980336094, 0.005592941625385984, 0.0014078079877343692, 3.4057984165729927E-12}, {364.93126173780234, 110209.74238724158, 9.836456701388547E13, 0.051780469158013036, 0.00672305604085484, 0.002418638223273229, 3.0933578447405203E-12}, {369.3189325430588, 110303.40885307179, 8.848455176008964E13, 0.06233326419938977, 0.003571432783044898, 0.0016540355287377293, 3.098440046104185E-12}, {368.80806892196006, 110283.4443778372, 1.0333277377826911E14, 0.08176859749423598, 0.003210168471897784, 0.0015571401927222967, 3.2633616311322833E-12}, {362.966939199309, 110065.59316310241, 7.236526474229212E13, 0.07065466160195331, 0.002865873223700474, 0.0020113250117030574, 3.630094337633866E-12}, {361.1955608274707, 110103.92131511419, 1.0163165729585886E14, 0.08542949563281041, 0.006047567055663383, 0.002396933126474858, 3.217965409218754E-12}, {365.13908641396097, 110583.65218390217, 9.283267505333645E13, 0.055659483949004315, 0.004315697024552967, 0.002320300111702821, 2.8359746197025446E-12}, {369.5591940914537, 109780.94933676392, 9.534753664424048E13, 0.05317648656760661, 0.0031104761923353348, 0.0012874638764546792, 2.948387385668216E-12}, {367.4802152591158, 109357.95328562958, 9.222602530965478E13, 0.06922264054554216, 0.005488785344911285, 0.0013189343835025387, 2.8540064234632715E-12}, {368.58626884976246, 109937.82438751095, 9.447826163871973E13, 0.048525778196723865, 0.0038488053538802457, 0.0022857008222657467, 2.4721187299857223E-12}, {365.53872611270623, 109985.35803452594, 8.019596085884694E13, 0.06345844569141451, 0.003281724464508303, 0.0010378833531101545, 3.4389242950942516E-12}, {360.3691293535056, 110137.36915377194, 9.941947675090433E13, 0.04177216706251684, 0.0037924767895034157, 0.0017049919664466665, 3.025537409551794E-12}, {368.0192774359596, 110477.88155551684, 1.138907205712955E14, 0.0645068401585657, 0.006940219072581173, 0.0015817772190936845, 3.145087253582509E-12}, {362.0018266235772, 109630.38938377541, 8.360043092446323E13, 0.049326605424144215, 0.00588934333121004, 0.0015265970858535727, 3.0233614317257807E-12}, {364.38168824915306, 109812.3102981764, 8.563603467350034E13, 0.05048596679082133, 0.007089452335618899, 0.002429857906802631, 3.209012357521851E-12}, {368.4035506138951, 110510.42447398978, 9.851038829368569E13, 0.08784750052053286, 0.005934276272126443, 0.002335129143654903, 3.1092925929295233E-12}, {368.2448014319791, 110239.04887086186, 9.563485057868198E13, 0.07785456454160583, 0.00541806829400019, 0.0010698378266104213, 3.360821919719779E-12}, {365.4566404753192, 110035.4226006553, 9.334158392055606E13, 0.09887538075422407, 0.006058848325399352, 0.0018264891752649138, 3.3971221652994417E-12}, {361.9060976973726, 109545.17533962222, 8.750595444148555E13, 0.07847842721909934, 0.004332658643161827, 0.0011354960132254916, 3.347698680048009E-12}, {361.67888847657696, 110374.89486747241, 7.81150967423348E13, 0.08325831443965466, 0.005152311960854902, 0.0023716894554453947, 3.4326404335880396E-12}, {366.34022314560093, 109896.69826532222, 9.908311136368533E13, 0.06805873415652332, 0.007112103640595146, 0.001775035591666977, 3.206944279435263E-12}, {362.3467915784584, 110402.6967138952, 8.496892103378966E13, 0.04718681450862895, 0.004221799433070906, 0.0016469337696970712, 3.216951678302834E-12}, {364.1409534308499, 110627.87114245108, 8.984712735884347E13, 0.07312392977743606, 0.00619122615647505, 0.0017438698921028455, 3.416756801355441E-12}, {360.5223009443795, 110042.03071377685, 9.078914994436077E13, 0.0562794182659928, 0.0056515559242536215, 0.0021248875377407055, 2.977384170731575E-12}, {363.3572586457732, 110254.41421558853, 9.777918888489539E13, 0.06077293520222743, 0.007184052756117401, 0.0011215515740745565, 3.4261340322593915E-12}, {367.1063284539058, 110004.11592777296, 8.444505086662158E13, 0.06515935845755531, 0.0069527873336871545, 0.0012541351396815003, 3.346627249590519E-12}, {365.9819945522756, 110198.97407274379, 1.0215267090296525E14, 0.052090979792268975, 0.003482713477366268, 0.0011838587238480437, 3.1021798483725164E-12}, {368.92384397030116, 109727.78236729089, 9.166598445574988E13, 0.04077309158501027, 0.0065281213523279485, 0.001961262213892895, 2.5907078583870774E-12}, {361.3550809471376, 109866.77414819598, 8.7747117840876E13, 0.09783767856200393, 0.004074605320686931, 0.001975550094881837, 3.4697873044231572E-12}, {367.8594432445431, 109756.22956992718, 7.958336180685842E13, 0.09611209595399306, 0.004686773126749329, 0.0013430516990935845, 3.5048841549209672E-12}, {361.5754642971066, 110160.01427677034, 1.11799373463373E14, 0.04515169610543739, 0.006440992251095202, 0.0017578905567659062, 2.9220204905782965E-12}, {362.23388231279483, 109962.47430505518, 7.64809018726093E13, 0.08654468862608945, 0.006217062430708757, 0.0014286405328094837, 3.591752840781436E-12}, {363.2905231708558, 109707.49189824249, 9.618851952422316E13, 0.08806839095771839, 0.006846521465352347, 0.0011664381361597375, 3.38981805576709E-12}, {363.84700020471445, 109991.56858005715, 9.261650870412627E13, 0.04461796350258334, 0.005087766799567949, 0.00101877654028576, 3.1577544750160433E-12}, {360.65412237614095, 109088.51987649442, 9.161323994477773E13, 0.067539754319144, 0.004167920920630589, 0.0020791257525886574, 2.7275356397678762E-12}, {366.757028449133, 110332.67315585072, 8.357857841912088E13, 0.09383161390214079, 0.004495517672183628, 0.0015005712810785851, 3.5848704594835988E-12}, {366.2252320319296, 109851.70557723426, 9.390479600427212E13, 0.08032902517995688, 0.002769417046669832, 0.0016812724854036584, 3.374992460883319E-12}, {369.15137037066046, 109910.5940507705, 8.187357388912725E13, 0.04203877286538555, 0.004954019405715298, 0.0013730910437226755, 2.815440201433313E-12}, {363.6232957447993, 110779.77588214584, 9.09261812279782E13, 0.07147926787839898, 0.0036249935391252214, 0.0010753680640462962, 3.635825067307208E-12}, {364.7912500936017, 109586.39348056499, 1.060117447422054E14, 0.05884756736235367, 0.0039373356394848975, 0.002146908348141961, 2.488216245954741E-12}, {363.6730249393835, 109471.89470597495, 8.933385370881975E13, 0.08214204504119099, 0.006592452702012475, 0.0020716681001169924, 3.1943050269721746E-12}, {369.03272527426094, 110364.16629944745, 8.709225612772738E13, 0.061226861289591955, 0.006332086573456807, 0.0022562242653648844, 3.0045173163749674E-12}, {360.8706633096632, 109644.67351728692, 1.0040516864157853E14, 0.0596274298859412, 0.006355544282482873, 0.0018160586426388076, 2.958443781403629E-12}, {364.16920115703505, 110174.02457621267, 1.0975431712478919E14, 0.06698708832320803, 0.004469970801920988, 0.0019242121910580357, 2.8146414420883755E-12}, {365.16121017126926, 109958.3385333204, 9.00603683967718E13, 0.0767998276675079, 0.00472277805095646, 0.0024509592853853477, 2.7962489701297513E-12}, {364.4863613717936, 110445.8063262247, 1.0739697874243511E14, 0.09285048029036445, 0.005768697606968276, 0.0014682265928624666, 3.443182958318261E-12}, {366.85661549598217, 109838.5762828827, 1.0845522403717258E14, 0.08438696217702712, 0.004565588708738435, 0.0012170812130828806, 3.1315347524787606E-12}, {362.68020472659845, 109673.73210959775, 1.025498720379625E14, 0.09010547746749714, 0.004834538199145929, 0.0018700375342073157, 3.117107566690958E-12}, {361.0146941864763, 110019.70449912292, 9.69145577961796E13, 0.07571347898165509, 0.0029478661304216188, 0.002225945765993948, 3.4379600362395052E-12}, {369.93948783317376, 109927.13314573743, 1.055661240687739E14, 0.07278980859292272, 0.005051687620219472, 0.0020322562715061245, 2.4306645558830683E-12}, {367.7923865452147, 109405.55092599489, 9.419643649000002E13, 0.09559751465901829, 0.004905732149918327, 0.002152368239905337, 2.912128731724752E-12}, {362.63284831365786, 109694.86056749902, 9.639100306395588E13, 0.05427364671949318, 0.0030497539566340047, 0.00137531349605277, 3.1419988398452883E-12}, {366.05195803123075, 109572.14898640323, 8.272449018439036E13, 0.05714937969164362, 0.003951992171294393, 0.0018987725297345338, 2.7843721408764703E-12}, {369.69900446958445, 110122.56187465198, 8.871602420909619E13, 0.09178533150440515, 0.003164960748001891, 0.0021952378291504506, 3.4389388743677384E-12}, {369.99877758571625, 110243.87528923266, 9.88203938953887E13, 0.0745458269089902, 0.00500115054304932, 0.00119214711255752, 3.1827403244635657E-12}, {367.7199649177712, 110528.23073997795, 8.395957532105423E13, 0.08711588887453095, 0.005691424113812137, 0.002249289759247555, 3.2797943123224843E-12}, {368.1441761719682, 110189.10511601625, 1.1652485827104081E14, 0.0838760309139306, 0.004136255143006544, 0.0013040897892810147, 3.106963925957063E-12}, {360.56741750397276, 110027.48624884975, 1.1285913865581847E14, 0.04307825633997615, 0.006806078578903794, 0.0011105759568434234, 3.1097291583650475E-12}, {369.1143290564266, 109395.01823921602, 1.0018869828932322E14, 0.0723222246320733, 0.0028265142685299453, 0.0018510246931426882, 2.99875640025895E-12}, {365.84080256621513, 110434.76294714933, 1.05611984207241E14, 0.08856389417264773, 0.004199106515407885, 0.001623758943081225, 3.3196090746074225E-12}, {367.9292468729111, 109918.38386908236, 9.408487170662478E13, 0.08069420389209611, 0.0031576327444361916, 0.001418127252547784, 3.274124691627047E-12}, {365.27080233443235, 109994.46906197393, 9.109969800478902E13, 0.045592396762782206, 0.0038950801826752213, 0.002292595468315322, 2.672049402463268E-12}, {361.25629462372166, 110733.81098992532, 1.0469035184602425E14, 0.07391571303180437, 0.005995249019653101, 0.0017699512905409304, 3.38489464175242E-12}, {361.5280471412333, 110050.3149347184, 8.718768178874072E13, 0.04842668201949756, 0.004048784310052791, 0.0015882129213390362, 3.140089517044968E-12}, {364.30650495214985, 110275.280714893, 8.146899882762028E13, 0.086472395917264, 0.0034231315765817557, 0.0018436451393928064, 3.5553945950052953E-12}, {363.1975594413255, 109907.22161404786, 1.008394083611128E14, 0.09214675288436994, 0.0054589955001262385, 0.002386807832213798, 3.076593654451674E-12}, {368.8488429818444, 110117.76389393503, 1.0791790456945283E14, 0.07604033338721071, 0.0043673750945302705, 0.0012739280367980571, 3.0517464668674342E-12}, {366.9897672546219, 110413.16702952106, 7.56741099394775E13, 0.06864729884079021, 0.003349996588469931, 0.00108918346243215, 3.5953273635063107E-12}, {367.5836757982096, 109721.12453803414, 8.834494608192472E13, 0.0513392797609036, 0.007024474759284197, 0.001138115201750058, 3.1290370826109305E-12}, {367.3500535656156, 109684.37604756924, 9.823547148769052E13, 0.0636868149336752, 0.0030761955336896943, 0.0019964059022426666, 3.009150955178848E-12}, {362.19518896267067, 109976.75545766158, 7.779319336978544E13, 0.09718389136718919, 0.0030091903417301972, 0.002345995204616064, 3.71489941421951E-12}, {365.0195497993159, 110458.07269966377, 1.0923164046896198E14, 0.055307400926321686, 0.0065653976184894425, 0.0011556351856347857, 3.2024134543098734E-12}, {366.07300631352115, 109950.19255380257, 8.583488140954103E13, 0.05834311103733092, 0.0035326209981013826, 0.0024760129852436858, 3.0437702821810584E-12}, {360.93502617158333, 109786.83058648609, 8.623201638749372E13, 0.0792005231621988, 0.006744428764438131, 0.002304883977042204, 3.428525798456008E-12}, {368.5018146883497, 110685.74652441285, 1.040926627523731E14, 0.07049947482713323, 0.0036605244208474455, 0.0017267851739232029, 3.1423934848517456E-12}, {369.7588229796373, 109966.60789201049, 8.9272515351804E13, 0.06274470193799958, 0.006671362122407297, 0.002200133731810996, 3.011005682027516E-12}, {366.2622373683218, 110060.8739051991, 9.927039250147047E13, 0.06439742372349257, 0.004538436067718607, 0.0014549101732268576, 2.9866265859239017E-12}, {368.3341821878165, 109737.78905226404, 9.977681364171022E13, 0.05673494990511607, 0.00624275601778955, 0.001691846238884626, 2.634155090250859E-12}, {360.7628652765416, 109613.13405336783, 9.699483446274652E13, 0.08494588485087617, 0.006638047202489667, 0.0016255242080277141, 3.3006417604055163E-12}, {368.1976133982768, 109277.45897233428, 9.211318065908986E13, 0.09139822029473668, 0.004985003145839235, 0.002354686042686559, 2.6515128858944262E-12}, {363.4249297842282, 110029.288809195, 1.0660165650993E14, 0.0547068502099546, 0.005536774668617458, 0.0015395082727394514, 2.8584839763451678E-12}, {360.2037692770009, 110348.81970402478, 9.127267155938108E13, 0.04773495396800181, 0.006290760145200164, 0.002404079717006841, 3.186019881845965E-12}, {361.7895531850497, 109747.44682208433, 8.29182698115767E13, 0.04669441440228099, 0.005201511650826703, 0.00105489007351252, 3.251777546555959E-12}, {364.70937922833883, 109455.40082518134, 9.788571763635225E13, 0.07868882093730523, 0.0034293696834598307, 0.0018819024902175492, 3.0250514456101015E-12}, {363.5498805648354, 109830.18036975256, 9.550827392858719E13, 0.05393266429685028, 0.004022759646798693, 0.0018021724419673428, 2.8237431345568644E-12}, {362.46360053389435, 110320.61280150975, 1.0240267618016694E14, 0.09819307335131956, 0.004775321448836462, 0.001493706702000207, 3.5616461203110092E-12}, {362.87799905135586, 110129.97493959496, 9.18830038543096E13, 0.07521049257794674, 0.007155589139278656, 0.001449293958511103, 3.5019062665518374E-12}, {361.47227945645204, 110154.34289188255, 9.596508779932156E13, 0.06737877795067795, 0.004618488102108283, 0.0012379815266224936, 3.351927640512918E-12}, {369.2945439322175, 110229.57590969121, 9.602756866555414E13, 0.042830661381081346, 0.005843219121326165, 0.001967557331874689, 2.4252143340370423E-12}, {365.61500870266167, 109800.86285226957, 8.24040947743771E13, 0.08581356682374955, 0.00530767379378478, 0.0013966905816614507, 3.394677569647502E-12}, {363.90160994322173, 110216.48670936178, 8.807328578556962E13, 0.07733298383817991, 0.003818445474717889, 0.0017916070211605505, 3.3357778023285333E-12}, {369.64518072035133, 110555.55098082282, 1.015747830398604E14, 0.0696733484260838, 0.005128548851718831, 0.0015164014118598812, 3.0555647802716793E-12}, {364.6082460346012, 109767.03339930401, 9.471755447567923E13, 0.07191563737484033, 0.006105721914265628, 0.002469066678007939, 2.8698190416926766E-12}, {366.98137399565024, 109517.34808457148, 9.280433691845158E13, 0.040095129638992644, 0.006153129006088446, 0.0020962795440046264, 2.3113627565982848E-12}, {369.4751953692978, 109844.65228423142, 9.494456099617181E13, 0.08132187227701637, 0.006424378611526733, 0.0015747178883052937, 3.072519230648624E-12}, {367.2388236602692, 109862.7018831085, 8.095535136122539E13, 0.06154298974692023, 0.004633675213706934, 0.002100422412953486, 2.7070678972767394E-12}, {366.72551525451877, 109552.37297567553, 1.0282310408487884E14, 0.057541886242958036, 0.007039513954712612, 0.0013312628646745758, 2.9403947917729877E-12}, {364.40057512770335, 110187.56493646085, 8.690608293824447E13, 0.04999747661395523, 0.00690777469645313, 0.0012881816521150592, 3.341695583241176E-12}, {362.5211028162347, 110079.47101488088, 8.905340340634756E13, 0.09661185283770157, 0.006487019518545689, 0.0022685044194148962, 3.5285462422801348E-12}, {366.42127908683995, 110378.8351004704, 8.490062907001795E13, 0.08296421950888541, 0.005231669141755885, 0.0020473406922976514, 3.268429241772576E-12}, {368.7171485281574, 109874.31843774149, 9.346565658997512E13, 0.09529165967976, 0.0032482163264086466, 0.002447812068130304, 3.370797094071457E-12}, {369.85945087919805, 109501.54904959546, 9.379099556242747E13, 0.09425742672522475, 0.004752715944399839, 0.0016661838187246817, 3.0461273589889577E-12}, {365.3678594548813, 110170.69254490171, 9.745984735248347E13, 0.09331295239349388, 0.0027952532873747778, 0.0021779261277910127, 3.5511687323212562E-12}, {363.80376118878667, 109601.50315593607, 7.901872443221828E13, 0.06596367010909712, 0.004871804453921621, 0.002061430229605754, 2.896035366904386E-12}, {360.46825852830284, 109822.06004106188, 8.547952439035016E13, 0.09975116592284182, 0.004418736209481615, 0.002135525679878212, 3.49969418058944E-12}, {363.1469284162993, 110110.17995092992, 8.961778894645581E13, 0.05916831501893123, 0.007244192146109111, 0.001008218018751842, 3.479377881566825E-12}, {361.1185767104429, 109935.331945758, 9.671628926292186E13, 0.08911735291526168, 0.0037473556653445012, 0.002172748329855429, 3.324382241009027E-12}, {364.8474085885714, 110313.71952301402, 6.998465970083248E13, 0.06622273847336468, 0.005806667125992404, 0.0010257911032527085, 3.659982283831287E-12}, {364.0573860964469, 110007.78667420226, 1.0365717738010198E14, 0.05082463344265646, 0.005392761740307884, 0.001937327965115909, 2.5753626778958474E-12}, {365.77056626939645, 110292.48859428476, 1.1020100224549897E14, 0.05298164014683271, 0.0029062919301186506, 0.001909459683331324, 3.1328187817045473E-12}, {362.78388571631604, 110092.91740910173, 9.034878736991881E13, 0.04416705299689902, 0.005627868827619018, 0.0017232714630924543, 2.9269776956093596E-12}, {362.09753039618766, 109892.66340874405, 9.31196222621516E13, 0.09091645788606512, 0.0035933404759345812, 0.0013559136898716319, 3.463975766902948E-12}, {361.85321194835205, 109655.91722748175, 1.0069748122161947E14, 0.041080431762410524, 0.004264819852650434, 0.0012036718355370264, 2.877379899779098E-12}, {366.5674386398672, 109662.41592172357, 9.05601152400499E13, 0.06048525932629581, 0.005955912834195331, 0.002013713416972965, 2.6515899725698536E-12}});

    return model;
  }

  public static Model run3(Model model) {

    model.func("gpm1").set("source", "resultTable");
    model.func("gpm1").set("resultTable", "tbl8");
    model.func("gpm1").set("ignorenaninf", true);

    model.study("std4").feature("uq").set("designtable", "tbl8");

    model.func("gpm1").set("covfunction", "matern32");
    model.func("gpm1").set("meanfunction", "const");
    model.func("gpm1").set("improvementfunction", "entropy");
    model.func("gpm1").set("lastinternalseed", 1014);
    model.func("gpm1").set("gpadpoptmethod", "direct");
    model.func("gpm1").set("maxgpevals", 10000);
    model.func("gpm1").set("maxgpiters", 500);
    model.func("gpm1").set("adpevals", 10000);
    model.func("gpm1").set("setupfromstudy", "on");
    model.func("gpm1").setEntry("lboundselection", "col1", "360.15");
    model.func("gpm1").setEntry("uboundselection", "col1", "370.15");
    model.func("gpm1").setEntry("lcdfselection", "col1", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col1", "manual");
    model.func("gpm1").setEntry("distributionselection", "col2", "normal");
    model.func("gpm1").setEntry("s1selection", "col2", "110000.0");
    model.func("gpm1").setEntry("s2selection", "col2", "330.0");
    model.func("gpm1").setEntry("lboundselection", "col2", "108980.0");
    model.func("gpm1").setEntry("uboundselection", "col2", "111020.0");
    model.func("gpm1").setEntry("distributionselection", "col3", "normal");
    model.func("gpm1").setEntry("s1selection", "col3", "9.4E13");
    model.func("gpm1").setEntry("s2selection", "col3", "9.4E12");
    model.func("gpm1").setEntry("lboundselection", "col3", "6.4952E13");
    model.func("gpm1").setEntry("uboundselection", "col3", "1.2305E14");
    model.func("gpm1").setEntry("lboundselection", "col4", "0.04");
    model.func("gpm1").setEntry("uboundselection", "col4", "0.1");
    model.func("gpm1").setEntry("lcdfselection", "col4", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col4", "manual");
    model.func("gpm1").setEntry("lboundselection", "col5", "0.00275");
    model.func("gpm1").setEntry("uboundselection", "col5", "0.00725");
    model.func("gpm1").setEntry("lcdfselection", "col5", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col5", "manual");
    model.func("gpm1").setEntry("lboundselection", "col6", "0.001");
    model.func("gpm1").setEntry("uboundselection", "col6", "0.0025");
    model.func("gpm1").setEntry("lcdfselection", "col6", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col6", "manual");
    model.func("gpm1").setEntry("args", "col1", "T_cyl");
    model.func("gpm1").setEntry("unit", "col1", "K");
    model.func("gpm1").setEntry("columnType", "col2", "arg");
    model.func("gpm1").setEntry("args", "col2", "E");
    model.func("gpm1").setEntry("unit", "col2", "J/mol");
    model.func("gpm1").setEntry("columnType", "col3", "arg");
    model.func("gpm1").setEntry("args", "col3", "A");
    model.func("gpm1").setEntry("unit", "col3", "1/s");
    model.func("gpm1").setEntry("columnType", "col4", "arg");
    model.func("gpm1").setEntry("args", "col4", "xpos");
    model.func("gpm1").setEntry("unit", "col4", "m");
    model.func("gpm1").setEntry("columnType", "col5", "arg");
    model.func("gpm1").setEntry("args", "col5", "ypos");
    model.func("gpm1").setEntry("unit", "col5", "m");
    model.func("gpm1").setEntry("columnType", "col6", "arg");
    model.func("gpm1").setEntry("args", "col6", "R1");
    model.func("gpm1").setEntry("unit", "col6", "m");
    model.func("gpm1").setEntry("funcs", "col7", "gpm1_1");

    model.study("std4").feature("uq").set("computeaction", "append");
    model.study("std4").feature("uq").set("tablegraphgrp", "new");
    model.study("std4").label("\u7814\u7a76 4\uff1aUQ \u4e0d\u786e\u5b9a\u6027\u4f20\u64ad");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol168");
    model.sol("sol168").study("std4");
    model.sol("sol168").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("pd3").feature("so2").set("psol", "sol168");
    model.batch("uq3").run("compute");

    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "Kde/1e9/3600");
    model.result("pg9").feature("lngr1").set("descr", "KDE\uff0c\u6469\u5c14\u6d41\u51fa\u7387 (h/nmol)");
    model.result("pg9").feature("lngr1").set("xdataexpr", "predicted*1e9*3600");
    model.result("pg9").feature("lngr1")
         .set("xdatadescr", "\u9884\u6d4b\u7684\u6469\u5c14\u6d41\u51fa\u7387 (nmol/h)");
    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result("pg9").run();

    model.study().create("std5");
    model.study("std5").feature().copy("uq", "std4/uq", "");
    model.study("std5").feature().copy("ref", "std4/ref", "");
    model.study("std5").feature("uq").set("uqtype", "screening");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "inverseuq");
    model.study("std5").feature("uq").set("uqresultgrp", "new");
    model.study("std5").feature("uq").set("uqtype", "reliabilityanalysis");

    model.func().duplicate("gpm2", "gpm1");

    model.study("std5").feature("uq").set("globalgpfunction", "gpm2");

    model.result().table().create("tbl17", "Table");
    model.result().table("tbl17")
         .addRows(new double[][]{{365.67765637322333, 110071.76263854766, 1.0114054004382633E14, 0.09905359981852663, 0.003720276087946072, 0.002215257920566165, 3.3138484601839294E-12}, {363.03596957949554, 110147.90387913912, 9.994829277610731E13, 0.09457627350259723, 0.003356657748547805, 0.0012299708098074632, 3.5245619718878295E-12}, {367.513311747571, 110264.99487807103, 9.503127522731467E13, 0.043756304710144134, 0.0057188013970484244, 0.001604797550839731, 2.762203381854356E-12}, {370.07485299385644, 109883.24310882168, 8.654882519933075E13, 0.07446134967381335, 0.0053633024932461535, 0.0019482118551187623, 2.7723959026253717E-12}, {367.1946895495363, 109772.13422470137, 1.0514374684093186E14, 0.04614191530622325, 0.005293935833408418, 0.0014756296287184631, 2.488267463692658E-12}, {366.5058262859554, 109811.31620400879, 1.0404800225130794E14, 0.08980297964792265, 0.00677081730298126, 0.002490431919554883, 3.243809064592022E-12}, {360.2933297406593, 110085.61265942338, 9.738512187345478E13, 0.07988310980336094, 0.005592941625385984, 0.0014078079877343692, 3.4057984165729927E-12}, {364.93126173780234, 110209.74238724158, 9.836456701388547E13, 0.051780469158013036, 0.00672305604085484, 0.002418638223273229, 3.0933578447405203E-12}, {369.3189325430588, 110303.40885307179, 8.848455176008964E13, 0.06233326419938977, 0.003571432783044898, 0.0016540355287377293, 3.098440046104185E-12}, {368.80806892196006, 110283.4443778372, 1.0333277377826911E14, 0.08176859749423598, 0.003210168471897784, 0.0015571401927222967, 3.2633616311322833E-12}, {362.966939199309, 110065.59316310241, 7.236526474229212E13, 0.07065466160195331, 0.002865873223700474, 0.0020113250117030574, 3.630094337633866E-12}, {361.1955608274707, 110103.92131511419, 1.0163165729585886E14, 0.08542949563281041, 0.006047567055663383, 0.002396933126474858, 3.217965409218754E-12}, {365.13908641396097, 110583.65218390217, 9.283267505333645E13, 0.055659483949004315, 0.004315697024552967, 0.002320300111702821, 2.8359746197025446E-12}, {369.5591940914537, 109780.94933676392, 9.534753664424048E13, 0.05317648656760661, 0.0031104761923353348, 0.0012874638764546792, 2.948387385668216E-12}, {367.4802152591158, 109357.95328562958, 9.222602530965478E13, 0.06922264054554216, 0.005488785344911285, 0.0013189343835025387, 2.8540064234632715E-12}, {368.58626884976246, 109937.82438751095, 9.447826163871973E13, 0.048525778196723865, 0.0038488053538802457, 0.0022857008222657467, 2.4721187299857223E-12}, {365.53872611270623, 109985.35803452594, 8.019596085884694E13, 0.06345844569141451, 0.003281724464508303, 0.0010378833531101545, 3.4389242950942516E-12}, {360.3691293535056, 110137.36915377194, 9.941947675090433E13, 0.04177216706251684, 0.0037924767895034157, 0.0017049919664466665, 3.025537409551794E-12}, {368.0192774359596, 110477.88155551684, 1.138907205712955E14, 0.0645068401585657, 0.006940219072581173, 0.0015817772190936845, 3.145087253582509E-12}, {362.0018266235772, 109630.38938377541, 8.360043092446323E13, 0.049326605424144215, 0.00588934333121004, 0.0015265970858535727, 3.0233614317257807E-12}, {364.38168824915306, 109812.3102981764, 8.563603467350034E13, 0.05048596679082133, 0.007089452335618899, 0.002429857906802631, 3.209012357521851E-12}, {368.4035506138951, 110510.42447398978, 9.851038829368569E13, 0.08784750052053286, 0.005934276272126443, 0.002335129143654903, 3.1092925929295233E-12}, {368.2448014319791, 110239.04887086186, 9.563485057868198E13, 0.07785456454160583, 0.00541806829400019, 0.0010698378266104213, 3.360821919719779E-12}, {365.4566404753192, 110035.4226006553, 9.334158392055606E13, 0.09887538075422407, 0.006058848325399352, 0.0018264891752649138, 3.3971221652994417E-12}, {361.9060976973726, 109545.17533962222, 8.750595444148555E13, 0.07847842721909934, 0.004332658643161827, 0.0011354960132254916, 3.347698680048009E-12}, {361.67888847657696, 110374.89486747241, 7.81150967423348E13, 0.08325831443965466, 0.005152311960854902, 0.0023716894554453947, 3.4326404335880396E-12}, {366.34022314560093, 109896.69826532222, 9.908311136368533E13, 0.06805873415652332, 0.007112103640595146, 0.001775035591666977, 3.206944279435263E-12}, {362.3467915784584, 110402.6967138952, 8.496892103378966E13, 0.04718681450862895, 0.004221799433070906, 0.0016469337696970712, 3.216951678302834E-12}, {364.1409534308499, 110627.87114245108, 8.984712735884347E13, 0.07312392977743606, 0.00619122615647505, 0.0017438698921028455, 3.416756801355441E-12}, {360.5223009443795, 110042.03071377685, 9.078914994436077E13, 0.0562794182659928, 0.0056515559242536215, 0.0021248875377407055, 2.977384170731575E-12}, {363.3572586457732, 110254.41421558853, 9.777918888489539E13, 0.06077293520222743, 0.007184052756117401, 0.0011215515740745565, 3.4261340322593915E-12}, {367.1063284539058, 110004.11592777296, 8.444505086662158E13, 0.06515935845755531, 0.0069527873336871545, 0.0012541351396815003, 3.346627249590519E-12}, {365.9819945522756, 110198.97407274379, 1.0215267090296525E14, 0.052090979792268975, 0.003482713477366268, 0.0011838587238480437, 3.1021798483725164E-12}, {368.92384397030116, 109727.78236729089, 9.166598445574988E13, 0.04077309158501027, 0.0065281213523279485, 0.001961262213892895, 2.5907078583870774E-12}, {361.3550809471376, 109866.77414819598, 8.7747117840876E13, 0.09783767856200393, 0.004074605320686931, 0.001975550094881837, 3.4697873044231572E-12}, {367.8594432445431, 109756.22956992718, 7.958336180685842E13, 0.09611209595399306, 0.004686773126749329, 0.0013430516990935845, 3.5048841549209672E-12}, {361.5754642971066, 110160.01427677034, 1.11799373463373E14, 0.04515169610543739, 0.006440992251095202, 0.0017578905567659062, 2.9220204905782965E-12}, {362.23388231279483, 109962.47430505518, 7.64809018726093E13, 0.08654468862608945, 0.006217062430708757, 0.0014286405328094837, 3.591752840781436E-12}, {363.2905231708558, 109707.49189824249, 9.618851952422316E13, 0.08806839095771839, 0.006846521465352347, 0.0011664381361597375, 3.38981805576709E-12}, {363.84700020471445, 109991.56858005715, 9.261650870412627E13, 0.04461796350258334, 0.005087766799567949, 0.00101877654028576, 3.1577544750160433E-12}, {360.65412237614095, 109088.51987649442, 9.161323994477773E13, 0.067539754319144, 0.004167920920630589, 0.0020791257525886574, 2.7275356397678762E-12}, {366.757028449133, 110332.67315585072, 8.357857841912088E13, 0.09383161390214079, 0.004495517672183628, 0.0015005712810785851, 3.5848704594835988E-12}, {366.2252320319296, 109851.70557723426, 9.390479600427212E13, 0.08032902517995688, 0.002769417046669832, 0.0016812724854036584, 3.374992460883319E-12}, {369.15137037066046, 109910.5940507705, 8.187357388912725E13, 0.04203877286538555, 0.004954019405715298, 0.0013730910437226755, 2.815440201433313E-12}, {363.6232957447993, 110779.77588214584, 9.09261812279782E13, 0.07147926787839898, 0.0036249935391252214, 0.0010753680640462962, 3.635825067307208E-12}, {364.7912500936017, 109586.39348056499, 1.060117447422054E14, 0.05884756736235367, 0.0039373356394848975, 0.002146908348141961, 2.488216245954741E-12}, {363.6730249393835, 109471.89470597495, 8.933385370881975E13, 0.08214204504119099, 0.006592452702012475, 0.0020716681001169924, 3.1943050269721746E-12}, {369.03272527426094, 110364.16629944745, 8.709225612772738E13, 0.061226861289591955, 0.006332086573456807, 0.0022562242653648844, 3.0045173163749674E-12}, {360.8706633096632, 109644.67351728692, 1.0040516864157853E14, 0.0596274298859412, 0.006355544282482873, 0.0018160586426388076, 2.958443781403629E-12}, {364.16920115703505, 110174.02457621267, 1.0975431712478919E14, 0.06698708832320803, 0.004469970801920988, 0.0019242121910580357, 2.8146414420883755E-12}, {365.16121017126926, 109958.3385333204, 9.00603683967718E13, 0.0767998276675079, 0.00472277805095646, 0.0024509592853853477, 2.7962489701297513E-12}, {364.4863613717936, 110445.8063262247, 1.0739697874243511E14, 0.09285048029036445, 0.005768697606968276, 0.0014682265928624666, 3.443182958318261E-12}, {366.85661549598217, 109838.5762828827, 1.0845522403717258E14, 0.08438696217702712, 0.004565588708738435, 0.0012170812130828806, 3.1315347524787606E-12}, {362.68020472659845, 109673.73210959775, 1.025498720379625E14, 0.09010547746749714, 0.004834538199145929, 0.0018700375342073157, 3.117107566690958E-12}, {361.0146941864763, 110019.70449912292, 9.69145577961796E13, 0.07571347898165509, 0.0029478661304216188, 0.002225945765993948, 3.4379600362395052E-12}, {369.93948783317376, 109927.13314573743, 1.055661240687739E14, 0.07278980859292272, 0.005051687620219472, 0.0020322562715061245, 2.4306645558830683E-12}, {367.7923865452147, 109405.55092599489, 9.419643649000002E13, 0.09559751465901829, 0.004905732149918327, 0.002152368239905337, 2.912128731724752E-12}, {362.63284831365786, 109694.86056749902, 9.639100306395588E13, 0.05427364671949318, 0.0030497539566340047, 0.00137531349605277, 3.1419988398452883E-12}, {366.05195803123075, 109572.14898640323, 8.272449018439036E13, 0.05714937969164362, 0.003951992171294393, 0.0018987725297345338, 2.7843721408764703E-12}, {369.69900446958445, 110122.56187465198, 8.871602420909619E13, 0.09178533150440515, 0.003164960748001891, 0.0021952378291504506, 3.4389388743677384E-12}, {369.99877758571625, 110243.87528923266, 9.88203938953887E13, 0.0745458269089902, 0.00500115054304932, 0.00119214711255752, 3.1827403244635657E-12}, {367.7199649177712, 110528.23073997795, 8.395957532105423E13, 0.08711588887453095, 0.005691424113812137, 0.002249289759247555, 3.2797943123224843E-12}, {368.1441761719682, 110189.10511601625, 1.1652485827104081E14, 0.0838760309139306, 0.004136255143006544, 0.0013040897892810147, 3.106963925957063E-12}, {360.56741750397276, 110027.48624884975, 1.1285913865581847E14, 0.04307825633997615, 0.006806078578903794, 0.0011105759568434234, 3.1097291583650475E-12}, {369.1143290564266, 109395.01823921602, 1.0018869828932322E14, 0.0723222246320733, 0.0028265142685299453, 0.0018510246931426882, 2.99875640025895E-12}, {365.84080256621513, 110434.76294714933, 1.05611984207241E14, 0.08856389417264773, 0.004199106515407885, 0.001623758943081225, 3.3196090746074225E-12}, {367.9292468729111, 109918.38386908236, 9.408487170662478E13, 0.08069420389209611, 0.0031576327444361916, 0.001418127252547784, 3.274124691627047E-12}, {365.27080233443235, 109994.46906197393, 9.109969800478902E13, 0.045592396762782206, 0.0038950801826752213, 0.002292595468315322, 2.672049402463268E-12}, {361.25629462372166, 110733.81098992532, 1.0469035184602425E14, 0.07391571303180437, 0.005995249019653101, 0.0017699512905409304, 3.38489464175242E-12}, {361.5280471412333, 110050.3149347184, 8.718768178874072E13, 0.04842668201949756, 0.004048784310052791, 0.0015882129213390362, 3.140089517044968E-12}, {364.30650495214985, 110275.280714893, 8.146899882762028E13, 0.086472395917264, 0.0034231315765817557, 0.0018436451393928064, 3.5553945950052953E-12}, {363.1975594413255, 109907.22161404786, 1.008394083611128E14, 0.09214675288436994, 0.0054589955001262385, 0.002386807832213798, 3.076593654451674E-12}, {368.8488429818444, 110117.76389393503, 1.0791790456945283E14, 0.07604033338721071, 0.0043673750945302705, 0.0012739280367980571, 3.0517464668674342E-12}, {366.9897672546219, 110413.16702952106, 7.56741099394775E13, 0.06864729884079021, 0.003349996588469931, 0.00108918346243215, 3.5953273635063107E-12}, {367.5836757982096, 109721.12453803414, 8.834494608192472E13, 0.0513392797609036, 0.007024474759284197, 0.001138115201750058, 3.1290370826109305E-12}, {367.3500535656156, 109684.37604756924, 9.823547148769052E13, 0.0636868149336752, 0.0030761955336896943, 0.0019964059022426666, 3.009150955178848E-12}, {362.19518896267067, 109976.75545766158, 7.779319336978544E13, 0.09718389136718919, 0.0030091903417301972, 0.002345995204616064, 3.71489941421951E-12}, {365.0195497993159, 110458.07269966377, 1.0923164046896198E14, 0.055307400926321686, 0.0065653976184894425, 0.0011556351856347857, 3.2024134543098734E-12}, {366.07300631352115, 109950.19255380257, 8.583488140954103E13, 0.05834311103733092, 0.0035326209981013826, 0.0024760129852436858, 3.0437702821810584E-12}, {360.93502617158333, 109786.83058648609, 8.623201638749372E13, 0.0792005231621988, 0.006744428764438131, 0.002304883977042204, 3.428525798456008E-12}, {368.5018146883497, 110685.74652441285, 1.040926627523731E14, 0.07049947482713323, 0.0036605244208474455, 0.0017267851739232029, 3.1423934848517456E-12}, {369.7588229796373, 109966.60789201049, 8.9272515351804E13, 0.06274470193799958, 0.006671362122407297, 0.002200133731810996, 3.011005682027516E-12}, {366.2622373683218, 110060.8739051991, 9.927039250147047E13, 0.06439742372349257, 0.004538436067718607, 0.0014549101732268576, 2.9866265859239017E-12}, {368.3341821878165, 109737.78905226404, 9.977681364171022E13, 0.05673494990511607, 0.00624275601778955, 0.001691846238884626, 2.634155090250859E-12}, {360.7628652765416, 109613.13405336783, 9.699483446274652E13, 0.08494588485087617, 0.006638047202489667, 0.0016255242080277141, 3.3006417604055163E-12}, {368.1976133982768, 109277.45897233428, 9.211318065908986E13, 0.09139822029473668, 0.004985003145839235, 0.002354686042686559, 2.6515128858944262E-12}, {363.4249297842282, 110029.288809195, 1.0660165650993E14, 0.0547068502099546, 0.005536774668617458, 0.0015395082727394514, 2.8584839763451678E-12}, {360.2037692770009, 110348.81970402478, 9.127267155938108E13, 0.04773495396800181, 0.006290760145200164, 0.002404079717006841, 3.186019881845965E-12}, {361.7895531850497, 109747.44682208433, 8.29182698115767E13, 0.04669441440228099, 0.005201511650826703, 0.00105489007351252, 3.251777546555959E-12}, {364.70937922833883, 109455.40082518134, 9.788571763635225E13, 0.07868882093730523, 0.0034293696834598307, 0.0018819024902175492, 3.0250514456101015E-12}, {363.5498805648354, 109830.18036975256, 9.550827392858719E13, 0.05393266429685028, 0.004022759646798693, 0.0018021724419673428, 2.8237431345568644E-12}, {362.46360053389435, 110320.61280150975, 1.0240267618016694E14, 0.09819307335131956, 0.004775321448836462, 0.001493706702000207, 3.5616461203110092E-12}, {362.87799905135586, 110129.97493959496, 9.18830038543096E13, 0.07521049257794674, 0.007155589139278656, 0.001449293958511103, 3.5019062665518374E-12}, {361.47227945645204, 110154.34289188255, 9.596508779932156E13, 0.06737877795067795, 0.004618488102108283, 0.0012379815266224936, 3.351927640512918E-12}, {369.2945439322175, 110229.57590969121, 9.602756866555414E13, 0.042830661381081346, 0.005843219121326165, 0.001967557331874689, 2.4252143340370423E-12}, {365.61500870266167, 109800.86285226957, 8.24040947743771E13, 0.08581356682374955, 0.00530767379378478, 0.0013966905816614507, 3.394677569647502E-12}, {363.90160994322173, 110216.48670936178, 8.807328578556962E13, 0.07733298383817991, 0.003818445474717889, 0.0017916070211605505, 3.3357778023285333E-12}, {369.64518072035133, 110555.55098082282, 1.015747830398604E14, 0.0696733484260838, 0.005128548851718831, 0.0015164014118598812, 3.0555647802716793E-12}, {364.6082460346012, 109767.03339930401, 9.471755447567923E13, 0.07191563737484033, 0.006105721914265628, 0.002469066678007939, 2.8698190416926766E-12}, {366.98137399565024, 109517.34808457148, 9.280433691845158E13, 0.040095129638992644, 0.006153129006088446, 0.0020962795440046264, 2.3113627565982848E-12}, {369.4751953692978, 109844.65228423142, 9.494456099617181E13, 0.08132187227701637, 0.006424378611526733, 0.0015747178883052937, 3.072519230648624E-12}, {367.2388236602692, 109862.7018831085, 8.095535136122539E13, 0.06154298974692023, 0.004633675213706934, 0.002100422412953486, 2.7070678972767394E-12}, {366.72551525451877, 109552.37297567553, 1.0282310408487884E14, 0.057541886242958036, 0.007039513954712612, 0.0013312628646745758, 2.9403947917729877E-12}, {364.40057512770335, 110187.56493646085, 8.690608293824447E13, 0.04999747661395523, 0.00690777469645313, 0.0012881816521150592, 3.341695583241176E-12}, {362.5211028162347, 110079.47101488088, 8.905340340634756E13, 0.09661185283770157, 0.006487019518545689, 0.0022685044194148962, 3.5285462422801348E-12}, {366.42127908683995, 110378.8351004704, 8.490062907001795E13, 0.08296421950888541, 0.005231669141755885, 0.0020473406922976514, 3.268429241772576E-12}, {368.7171485281574, 109874.31843774149, 9.346565658997512E13, 0.09529165967976, 0.0032482163264086466, 0.002447812068130304, 3.370797094071457E-12}, {369.85945087919805, 109501.54904959546, 9.379099556242747E13, 0.09425742672522475, 0.004752715944399839, 0.0016661838187246817, 3.0461273589889577E-12}, {365.3678594548813, 110170.69254490171, 9.745984735248347E13, 0.09331295239349388, 0.0027952532873747778, 0.0021779261277910127, 3.5511687323212562E-12}, {363.80376118878667, 109601.50315593607, 7.901872443221828E13, 0.06596367010909712, 0.004871804453921621, 0.002061430229605754, 2.896035366904386E-12}, {360.46825852830284, 109822.06004106188, 8.547952439035016E13, 0.09975116592284182, 0.004418736209481615, 0.002135525679878212, 3.49969418058944E-12}, {363.1469284162993, 110110.17995092992, 8.961778894645581E13, 0.05916831501893123, 0.007244192146109111, 0.001008218018751842, 3.479377881566825E-12}, {361.1185767104429, 109935.331945758, 9.671628926292186E13, 0.08911735291526168, 0.0037473556653445012, 0.002172748329855429, 3.324382241009027E-12}, {364.8474085885714, 110313.71952301402, 6.998465970083248E13, 0.06622273847336468, 0.005806667125992404, 0.0010257911032527085, 3.659982283831287E-12}, {364.0573860964469, 110007.78667420226, 1.0365717738010198E14, 0.05082463344265646, 0.005392761740307884, 0.001937327965115909, 2.5753626778958474E-12}, {365.77056626939645, 110292.48859428476, 1.1020100224549897E14, 0.05298164014683271, 0.0029062919301186506, 0.001909459683331324, 3.1328187817045473E-12}, {362.78388571631604, 110092.91740910173, 9.034878736991881E13, 0.04416705299689902, 0.005627868827619018, 0.0017232714630924543, 2.9269776956093596E-12}, {362.09753039618766, 109892.66340874405, 9.31196222621516E13, 0.09091645788606512, 0.0035933404759345812, 0.0013559136898716319, 3.463975766902948E-12}, {361.85321194835205, 109655.91722748175, 1.0069748122161947E14, 0.041080431762410524, 0.004264819852650434, 0.0012036718355370264, 2.877379899779098E-12}, {366.5674386398672, 109662.41592172357, 9.05601152400499E13, 0.06048525932629581, 0.005955912834195331, 0.002013713416972965, 2.6515899725698536E-12}, {365.04041410807093, 109847.12187762803, 8.641002690328181E13, 0.0842318880649994, 0.003862646920277153, 0.0022954134977361254, 3.1701550951449574E-12}, {368.3114493083909, 110201.57586852703, 9.666842013771739E13, 0.08091794830618046, 0.004441226712233995, 0.0019554166745224654, 2.983244005806093E-12}, {363.4632197494385, 110047.34787462822, 9.762656477705123E13, 0.0992656704132997, 0.006164240574414568, 0.0017113620271535027, 3.4469263804772516E-12}, {365.9210034698812, 109921.12467853668, 7.591820585334934E13, 0.05807584189862981, 0.004862800784687638, 0.0015509817476247168, 3.1336849007753017E-12}, {369.1046216574982, 109484.50292404128, 9.488000435457523E13, 0.07483989280696193, 0.006998816431217431, 0.0018313762082912352, 3.054545176817553E-12}, {361.977226981767, 109751.58043251399, 9.270019535546406E13, 0.08622114597574451, 0.0059791501605171715, 0.0019258102563792807, 3.2254065181667063E-12}, {368.6218775594903, 109877.06393205347, 1.0426469632491992E14, 0.044874957704606803, 0.0035140297123659693, 0.0016770360127889984, 2.5896525070211634E-12}, {361.727194790104, 109970.75427260756, 9.361394651378983E13, 0.06977929485601995, 0.007213028334440078, 0.0023093107917997882, 3.41716864443676E-12}, {367.66676791030983, 109763.84657202227, 8.173069370562798E13, 0.05679865138119093, 0.005071336579834808, 0.0014408204689434485, 2.950042162302332E-12}, {367.3124103015533, 109598.28714372274, 7.976156087711753E13, 0.047922649755313386, 0.0034600155216204403, 0.0010600876839516513, 3.1133977918317235E-12}, {360.25584040652086, 110420.36114906301, 1.0631302448754095E14, 0.07265100340863531, 0.00532130711161851, 0.0018914000203493638, 3.212814567027951E-12}, {365.2183656591856, 110280.0730617054, 9.615266747786022E13, 0.08948586194401735, 0.005566462227849643, 0.0024429445730084092, 3.1212099336178194E-12}, {362.83544081414897, 109826.47931929847, 8.53366773189405E13, 0.06403409824162393, 0.006600317473840755, 0.0023762467547387483, 3.2321438594196374E-12}, {370.03715788835643, 109365.9523507141, 1.133079783733229E14, 0.04338632210892475, 0.006444538475133185, 0.0011174065755475125, 2.3771180453883558E-12}, {367.049866752757, 110705.4112109698, 9.866305805723512E13, 0.06128848371260768, 0.003978182524933874, 0.002066598087805126, 2.980717484599696E-12}, {365.4843984339656, 110023.52606555154, 1.1071255909686764E14, 0.04879483650801483, 0.00687526629318573, 0.0013582154208433548, 2.962041554955939E-12}, {360.85077884170846, 110095.55077417768, 8.422361298703266E13, 0.06647800705544267, 0.006274179036039643, 0.0020229191098707026, 3.3246853171775716E-12}, {366.3161495784679, 110591.59398840432, 8.788906888807078E13, 0.08230117902750028, 0.005776014341463983, 0.001498282542509886, 3.4963785204150486E-12}, {362.1565443892072, 109637.67451328672, 9.120220786435792E13, 0.0620563865309795, 0.004303436823712644, 0.001344129768700839, 3.104769627015667E-12}, {360.5654771246887, 110164.10449953337, 8.890191190403122E13, 0.0976130589307721, 0.005262494486543619, 0.0012371194888537303, 3.698914564930688E-12}, {367.8275115729866, 109986.36177709402, 1.0097078776130012E14, 0.07812182636050724, 0.005711956827852033, 0.0012940812420795403, 3.126503585682246E-12}, {363.57885291460565, 110483.7193836947, 9.214010397137753E13, 0.05478148407321987, 0.0029587592198416154, 0.0010129859380187598, 3.5083712680136276E-12}, {362.59364147448804, 109716.46273191622, 1.0022179213601314E14, 0.09167309015479722, 0.004759164397226084, 0.001546530647900484, 3.2816601301985632E-12}, {364.63754764088304, 110207.76074732956, 1.0455094267849525E14, 0.040730811114829485, 0.003685457082021139, 0.0024828882418550225, 2.7203353801619752E-12}, {364.10615956761274, 110337.255355379, 9.047486003432106E13, 0.09372193125400531, 0.0028224497162197834, 0.0017990315411967495, 3.661898500698837E-12}, {361.1590432952565, 110269.39461469022, 9.966098419965942E13, 0.07000592005954423, 0.004664391726951149, 0.0022099634364637476, 3.033724416538536E-12}, {364.2116203021538, 109905.23513712328, 1.0294877939922455E14, 0.09421023744298308, 0.006689864518334202, 0.0011727179641790875, 3.4085922572020764E-12}, {369.51446600657357, 110115.84492945264, 8.990989585940948E13, 0.07656208470668678, 0.00313022615023635, 0.0016177628938006312, 3.289185740581909E-12}, {366.54734587249084, 110068.85791127109, 8.248033985256472E13, 0.05231890224056106, 0.004108675645896805, 0.0021579540159085584, 2.7692033742238492E-12}, {369.2216235288597, 109533.91912468492, 9.418828445121823E13, 0.051168402998351376, 0.0032579190344796806, 0.0021386492511081523, 2.7147140028252036E-12}, {360.1502540263171, 108981.04836742475, 1.2302468286729455E14, 0.040001524157902764, 0.002750038103947569, 0.002499961896052431, 2.5227356452694992E-12}, {370.14974597368285, 111018.95163257522, 6.497531713270503E13, 0.09999847584209723, 0.00724996189605243, 0.002499961896052431, 3.972930597378295E-12}, {360.1502540263171, 108981.04836742475, 1.2302468286729455E14, 0.040001524157902764, 0.005083219021490626, 0.002499961896052431, 1.5108825972622386E-12}, {370.14974597368285, 111018.95163257522, 6.497531713270503E13, 0.040001524157902764, 0.005249885688157293, 0.002499961896052431, 2.7228321059355423E-12}, {360.1502540263171, 111018.95163257522, 9.804087594142748E13, 0.040001524157902764, 0.002750038103947569, 0.0024999872986841438, 3.5586442638520973E-12}, {370.14974597368285, 108981.04836742475, 1.2302468286729455E14, 0.09999847584209723, 0.002750038103947569, 0.001000038103947569, 2.929019310995121E-12}, {370.14974597368285, 111018.95163257522, 6.497531713270503E13, 0.09999847584209723, 0.00724996189605243, 0.001000038103947569, 3.993063974245146E-12}, {360.1502540263171, 111018.95163257522, 1.2302468286729455E14, 0.05333180917543058, 0.0033047553726566073, 0.002499961896052431, 3.322887283768031E-12}, {370.14974597368285, 111018.95163257522, 1.2302468286729455E14, 0.040001524157902764, 0.0055832190214906265, 0.002499961896052431, 1.899310060959371E-12}, {370.14974597368285, 108981.04836742475, 6.497531713270503E13, 0.09999847584209723, 0.002750038103947569, 0.001000038103947569, 3.5382297709769734E-12}, {370.14974597368285, 108981.04836742475, 6.497531713270503E13, 0.09999847584209723, 0.0061943301326017365, 0.0010000127013158563, 3.510358584047276E-12}, {360.1502540263171, 108981.04836742475, 1.2302468286729455E14, 0.07333485749123611, 0.002750038103947569, 0.001000038103947569, 2.8949574724122436E-12}, {360.1502540263171, 111018.95163257522, 6.497531713270503E13, 0.09999847584209723, 0.007083447645176041, 0.001000038103947569, 4.086551923734426E-12}, {370.14974597368285, 108981.04836742475, 1.2302468286729455E14, 0.040001524157902764, 0.00724996189605243, 0.002499961896052431, 2.0550052371302436E-12}, {370.14974597368285, 111018.95163257522, 1.2302468286729455E14, 0.09999847584209723, 0.00724996189605243, 0.002111073007163542, 3.5929579587235712E-12}, {360.1502540263171, 108981.04836742475, 1.2302468286729455E14, 0.09999847584209723, 0.00611408321902149, 0.0010000127013158563, 3.0432061246015406E-12}, {360.1502540263171, 111018.95163257522, 6.497531713270503E13, 0.040001524157902764, 0.002750038103947569, 0.0011667047706142358, 3.909608651178507E-12}, {370.14974597368285, 111018.95163257522, 6.497531713270503E13, 0.040001524157902764, 0.002750038103947569, 0.002499961896052431, 3.6263344758805654E-12}, {360.1502540263171, 111018.95163257522, 6.497531713270503E13, 0.09999847584209723, 0.007249885688157293, 0.001904625819234873, 4.068471698746101E-12}, {370.14974597368285, 111018.95163257522, 1.2302468286729455E14, 0.09999847584209723, 0.002750038103947569, 0.001000038103947569, 3.601589902334683E-12}, {370.14974597368285, 108981.04836742475, 1.2302468286729455E14, 0.09999847584209723, 0.00724996189605243, 0.001000038103947569, 2.9263480277743557E-12}, {360.15076207895135, 111012.57073833275, 1.2284292406159998E14, 0.040004572473708276, 0.007249657064471879, 0.002203589391860997, 3.36344231165534E-12}, {370.14974597368285, 111017.32043501391, 1.2297821845191175E14, 0.040001524157902764, 0.00447233653406493, 0.002499961896052431, 1.898505607620235E-12}, {360.1502540263171, 108981.04836742475, 6.497531713270503E13, 0.040001524157902764, 0.0027501143118427067, 0.002499961896052431, 3.278832369081615E-12}, {370.14974597368285, 111017.32043501391, 6.502178154808799E13, 0.09999847584209723, 0.006750114311842708, 0.0016913961286389272, 3.8877069205354155E-12}, {360.1502540263171, 108982.67956498607, 6.497531713270503E13, 0.040001524157902764, 0.004249885688157293, 0.002499961896052431, 2.6782343575359406E-12}, {370.14974597368285, 111018.95163257522, 8.682845340207202E13, 0.09999847584209723, 0.0061451760402377684, 0.001000038103947569, 3.834866912582882E-12}, {370.14974597368285, 108981.04836742475, 1.0322213694156528E14, 0.09999847584209723, 0.003898033836305441, 0.001000038103947569, 3.0485122766979405E-12}, {360.1502540263171, 111017.32043501391, 6.497531713270503E13, 0.040001524157902764, 0.00341655235482396, 0.002333295229385764, 3.748666218321599E-12}, {360.1502540263171, 108981.04836742475, 1.2302468286729455E14, 0.040001524157902764, 0.007028349336991312, 0.002499961896052431, 2.4376862769691727E-12}});

    return model;
  }

  public static Model run4(Model model) {

    model.func("gpm2").set("source", "resultTable");
    model.func("gpm2").set("resultTable", "tbl17");
    model.func("gpm2").set("ignorenaninf", true);

    model.study("std5").feature("uq").set("designtable", "tbl17");

    model.func("gpm2").setEntry("funcs", "col7", "gpm2_1");

    model.study("std5").feature("uq").set("computeaction", "append");
    model.study("std5").feature("uq").set("tablegraphgrp", "new");
    model.study("std5").feature("uq").setIndex("failif", "smaller", 0);
    model.study("std5").feature("uq").setIndex("threshold", "8[nmol/h]", 0);
    model.study("std5").label("\u7814\u7a76 5\uff1aUQ \u53ef\u9760\u6027\u5206\u6790");
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol230");
    model.sol("sol230").study("std5");
    model.sol("sol230").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("pd4").feature("so2").set("psol", "sol230");
    model.batch("uq4").run("compute");

    model.result("pg8").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("\u53cd\u5e94\u901f\u7387");
    model.result("pg10").set("data", "none");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10")
         .set("title", "\u7b5b\u9009\u7814\u7a76\u5f97\u51fa\u7684\u53cd\u5e94\u901f\u7387 (mol/(m<sup>3</sup>*s))");
    model.result("pg10").set("plotarrayenable", true);
    model.result("pg10").set("arrayaxis", "y");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("arraydim", "1");
    model.result("pg10").feature("surf1").set("data", "dset4");
    model.result("pg10").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg10").feature("surf1").set("expr", "-tds.R_cBetaC");
    model.result("pg10").feature("surf1").set("colortable", "PrismDark");
    model.result("pg10").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg10").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf1").set("manualindexing", true);
    model.result("pg10").feature().duplicate("surf2", "surf1");
    model.result("pg10").feature("surf2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").setIndex("looplevel", 10, 0);
    model.result("pg10").feature("surf2").set("inheritplot", "surf1");
    model.result("pg10").feature("surf2").set("arrayindex", 1);
    model.result("pg10").feature().duplicate("surf3", "surf2");
    model.result("pg10").feature("surf3").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf3").setIndex("looplevel", 20, 0);
    model.result("pg10").feature("surf3").set("arrayindex", 2);
    model.result("pg10").feature().duplicate("surf4", "surf3");
    model.result("pg10").feature("surf4").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf4").setIndex("looplevel", 30, 0);
    model.result("pg10").feature("surf4").set("arrayindex", 3);
    model.result("pg10").feature().duplicate("surf5", "surf4");
    model.result("pg10").feature("surf5").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("surf5").setIndex("looplevel", 40, 0);
    model.result("pg10").feature("surf5").set("arrayindex", 4);
    model.result("pg10").run();
    model.result("pg10").create("str1", "Streamline");

    model.component("comp1").geom("geom1").run();

    model.result("pg10").feature("str1").set("arraydim", "1");
    model.result("pg10").feature("str1").set("data", "dset4");
    model.result("pg10").feature("str1").setIndex("looplevel", 1, 0);
    model.result("pg10").feature("str1").set("startmethod", "coord");
    model.result("pg10").feature("str1").set("xcoord", "0 0 0 0 0 0");
    model.result("pg10").feature("str1").set("ycoord", "0.004 0.005 0.006 0.007 0.008 0.009");
    model.result("pg10").feature("str1").set("color", "custom");
    model.result("pg10").feature("str1")
         .set("customcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("manualindexing", true);
    model.result("pg10").feature().duplicate("str2", "str1");
    model.result("pg10").feature("str2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("str2").setIndex("looplevel", 10, 0);
    model.result("pg10").feature("str2").set("arrayindex", 1);
    model.result("pg10").feature().duplicate("str3", "str2");
    model.result("pg10").feature("str3").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("str3").setIndex("looplevel", 20, 0);
    model.result("pg10").feature("str3").set("arrayindex", 2);
    model.result("pg10").feature().duplicate("str4", "str3");
    model.result("pg10").feature("str4").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("str4").setIndex("looplevel", 30, 0);
    model.result("pg10").feature("str4").set("arrayindex", 3);
    model.result("pg10").feature().duplicate("str5", "str4");
    model.result("pg10").feature("str5").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("str5").setIndex("looplevel", 40, 0);
    model.result("pg10").feature("str5").set("arrayindex", 4);
    model.result("pg10").run();
    model.result("pg10").create("ann1", "Annotation");
    model.result("pg10").feature("ann1").set("arraydim", "1");
    model.result("pg10").feature("ann1").set("data", "dset4");
    model.result("pg10").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg10").feature("ann1")
         .set("text", "$\\phi$ = eval(comp1.molar_outflow_rate, nmol/m/h) $\\mathrm{\\frac{nmol}{m\\cdot h}}$");
    model.result("pg10").feature("ann1").set("latexmarkup", true);
    model.result("pg10").feature("ann1").set("posxexpr", "W1");
    model.result("pg10").feature("ann1").set("posyexpr", "H1/2");
    model.result("pg10").feature("ann1").set("showpoint", false);
    model.result("pg10").feature("ann1").set("anchorpoint", "middleleft");
    model.result("pg10").feature("ann1").set("exprprecision", 3);
    model.result("pg10").feature("ann1").set("manualindexing", true);
    model.result("pg10").feature().duplicate("ann2", "ann1");
    model.result("pg10").feature("ann2").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann2").setIndex("looplevel", 10, 0);
    model.result("pg10").feature("ann2").set("arrayindex", 1);
    model.result("pg10").feature().duplicate("ann3", "ann2");
    model.result("pg10").feature("ann3").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann3").setIndex("looplevel", 20, 0);
    model.result("pg10").feature("ann3").set("arrayindex", 2);
    model.result("pg10").feature().duplicate("ann4", "ann3");
    model.result("pg10").feature("ann4").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann4").setIndex("looplevel", 30, 0);
    model.result("pg10").feature("ann4").set("arrayindex", 3);
    model.result("pg10").feature().duplicate("ann5", "ann4");
    model.result("pg10").feature("ann5").set("arraydim", "1");
    model.result("pg10").run();
    model.result("pg10").feature("ann5").setIndex("looplevel", 40, 0);
    model.result("pg10").feature("ann5").set("arrayindex", 4);
    model.result("pg10").run();

    model.title("\u03b2-\u80e1\u841d\u535c\u7d20\u5728\u6d41\u52a8\u53cd\u5e94\u5668\u4e2d\u7684\u70ed\u5206\u89e3");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u201d(UQ) \u7814\u7a76\u7c7b\u578b\u6765\u7814\u7a76\u6d41\u7ecf\u70ed\u5706\u67f1\u4f53\u7684\u679c\u6c41\u4e2d \u03b2-\u80e1\u841d\u535c\u7d20\u7684\u7a33\u5b9a\u6027\uff0c\u786e\u5b9a\u4e86\u5f71\u54cd\u8be5\u8fc7\u7a0b\u7684\u5173\u952e\u53c2\u6570\uff0c\u5e76\u5bf9\u5b83\u4eec\u5404\u81ea\u4e0d\u786e\u5b9a\u6027\u7684\u91cd\u8981\u6027\u8fdb\u884c\u4e86\u91cf\u5316\u3002\u672c\u4f8b\u786e\u5b9a\u4e86 \u03b2-\u80e1\u841d\u535c\u7d20\u6d41\u51fa\u91cf\uff08\u5373\u201c\u5173\u6ce8\u91cf\u201d(QoI)\uff09\u6700\u53ef\u80fd\u7684\u7ed3\u679c\uff0c\u4ee5\u53ca\u5176\u4f4e\u4e8e\u4e0b\u9650\u7684\u53ef\u80fd\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
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
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();
    model.sol("sol87").clearSolutionData();
    model.sol("sol88").clearSolutionData();
    model.sol("sol89").clearSolutionData();
    model.sol("sol90").clearSolutionData();
    model.sol("sol91").clearSolutionData();
    model.sol("sol92").clearSolutionData();
    model.sol("sol93").clearSolutionData();
    model.sol("sol94").clearSolutionData();
    model.sol("sol95").clearSolutionData();
    model.sol("sol96").clearSolutionData();
    model.sol("sol97").clearSolutionData();
    model.sol("sol98").clearSolutionData();
    model.sol("sol99").clearSolutionData();
    model.sol("sol100").clearSolutionData();
    model.sol("sol101").clearSolutionData();
    model.sol("sol102").clearSolutionData();
    model.sol("sol103").clearSolutionData();
    model.sol("sol104").clearSolutionData();
    model.sol("sol105").clearSolutionData();
    model.sol("sol106").clearSolutionData();
    model.sol("sol107").clearSolutionData();
    model.sol("sol108").clearSolutionData();
    model.sol("sol109").clearSolutionData();
    model.sol("sol110").clearSolutionData();
    model.sol("sol111").clearSolutionData();
    model.sol("sol112").clearSolutionData();
    model.sol("sol113").clearSolutionData();
    model.sol("sol114").clearSolutionData();
    model.sol("sol115").clearSolutionData();
    model.sol("sol116").clearSolutionData();
    model.sol("sol117").clearSolutionData();
    model.sol("sol118").clearSolutionData();
    model.sol("sol119").clearSolutionData();
    model.sol("sol120").clearSolutionData();
    model.sol("sol121").clearSolutionData();
    model.sol("sol122").clearSolutionData();
    model.sol("sol123").clearSolutionData();
    model.sol("sol124").clearSolutionData();
    model.sol("sol125").clearSolutionData();
    model.sol("sol126").clearSolutionData();
    model.sol("sol127").clearSolutionData();
    model.sol("sol128").clearSolutionData();
    model.sol("sol129").clearSolutionData();
    model.sol("sol130").clearSolutionData();
    model.sol("sol131").clearSolutionData();
    model.sol("sol132").clearSolutionData();
    model.sol("sol133").clearSolutionData();
    model.sol("sol134").clearSolutionData();
    model.sol("sol135").clearSolutionData();
    model.sol("sol136").clearSolutionData();
    model.sol("sol137").clearSolutionData();
    model.sol("sol138").clearSolutionData();
    model.sol("sol139").clearSolutionData();
    model.sol("sol140").clearSolutionData();
    model.sol("sol141").clearSolutionData();
    model.sol("sol142").clearSolutionData();
    model.sol("sol143").clearSolutionData();
    model.sol("sol144").clearSolutionData();
    model.sol("sol145").clearSolutionData();
    model.sol("sol146").clearSolutionData();
    model.sol("sol147").clearSolutionData();
    model.sol("sol148").clearSolutionData();
    model.sol("sol149").clearSolutionData();
    model.sol("sol150").clearSolutionData();
    model.sol("sol151").clearSolutionData();
    model.sol("sol152").clearSolutionData();
    model.sol("sol153").clearSolutionData();
    model.sol("sol154").clearSolutionData();
    model.sol("sol155").clearSolutionData();
    model.sol("sol156").clearSolutionData();
    model.sol("sol157").clearSolutionData();
    model.sol("sol158").clearSolutionData();
    model.sol("sol159").clearSolutionData();
    model.sol("sol160").clearSolutionData();
    model.sol("sol161").clearSolutionData();
    model.sol("sol162").clearSolutionData();
    model.sol("sol163").clearSolutionData();
    model.sol("sol164").clearSolutionData();
    model.sol("sol165").clearSolutionData();
    model.sol("sol166").clearSolutionData();
    model.sol("sol167").clearSolutionData();
    model.sol("sol168").clearSolutionData();
    model.sol("sol169").clearSolutionData();
    model.sol("sol170").clearSolutionData();
    model.sol("sol171").clearSolutionData();
    model.sol("sol172").clearSolutionData();
    model.sol("sol173").clearSolutionData();
    model.sol("sol174").clearSolutionData();
    model.sol("sol175").clearSolutionData();
    model.sol("sol176").clearSolutionData();
    model.sol("sol177").clearSolutionData();
    model.sol("sol178").clearSolutionData();
    model.sol("sol179").clearSolutionData();
    model.sol("sol180").clearSolutionData();
    model.sol("sol181").clearSolutionData();
    model.sol("sol182").clearSolutionData();
    model.sol("sol183").clearSolutionData();
    model.sol("sol184").clearSolutionData();
    model.sol("sol185").clearSolutionData();
    model.sol("sol186").clearSolutionData();
    model.sol("sol187").clearSolutionData();
    model.sol("sol188").clearSolutionData();
    model.sol("sol189").clearSolutionData();
    model.sol("sol190").clearSolutionData();
    model.sol("sol191").clearSolutionData();
    model.sol("sol192").clearSolutionData();
    model.sol("sol193").clearSolutionData();
    model.sol("sol194").clearSolutionData();
    model.sol("sol195").clearSolutionData();
    model.sol("sol196").clearSolutionData();
    model.sol("sol197").clearSolutionData();
    model.sol("sol198").clearSolutionData();
    model.sol("sol199").clearSolutionData();
    model.sol("sol200").clearSolutionData();
    model.sol("sol201").clearSolutionData();
    model.sol("sol202").clearSolutionData();
    model.sol("sol203").clearSolutionData();
    model.sol("sol204").clearSolutionData();
    model.sol("sol205").clearSolutionData();
    model.sol("sol206").clearSolutionData();
    model.sol("sol207").clearSolutionData();
    model.sol("sol208").clearSolutionData();
    model.sol("sol209").clearSolutionData();
    model.sol("sol210").clearSolutionData();
    model.sol("sol211").clearSolutionData();
    model.sol("sol212").clearSolutionData();
    model.sol("sol213").clearSolutionData();
    model.sol("sol214").clearSolutionData();
    model.sol("sol215").clearSolutionData();
    model.sol("sol216").clearSolutionData();
    model.sol("sol217").clearSolutionData();
    model.sol("sol218").clearSolutionData();
    model.sol("sol219").clearSolutionData();
    model.sol("sol220").clearSolutionData();
    model.sol("sol221").clearSolutionData();
    model.sol("sol222").clearSolutionData();
    model.sol("sol223").clearSolutionData();
    model.sol("sol224").clearSolutionData();
    model.sol("sol225").clearSolutionData();
    model.sol("sol226").clearSolutionData();
    model.sol("sol227").clearSolutionData();
    model.sol("sol228").clearSolutionData();
    model.sol("sol229").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol230").clearSolutionData();
    model.sol("sol231").clearSolutionData();
    model.sol("sol232").clearSolutionData();
    model.sol("sol233").clearSolutionData();
    model.sol("sol234").clearSolutionData();
    model.sol("sol235").clearSolutionData();
    model.sol("sol236").clearSolutionData();
    model.sol("sol237").clearSolutionData();
    model.sol("sol238").clearSolutionData();
    model.sol("sol239").clearSolutionData();
    model.sol("sol240").clearSolutionData();
    model.sol("sol241").clearSolutionData();
    model.sol("sol242").clearSolutionData();
    model.sol("sol243").clearSolutionData();
    model.sol("sol244").clearSolutionData();
    model.sol("sol245").clearSolutionData();
    model.sol("sol246").clearSolutionData();
    model.sol("sol247").clearSolutionData();
    model.sol("sol248").clearSolutionData();
    model.sol("sol249").clearSolutionData();
    model.sol("sol250").clearSolutionData();
    model.sol("sol251").clearSolutionData();
    model.sol("sol252").clearSolutionData();
    model.sol("sol253").clearSolutionData();
    model.sol("sol254").clearSolutionData();
    model.sol("sol255").clearSolutionData();
    model.sol("sol256").clearSolutionData();
    model.sol("sol257").clearSolutionData();
    model.sol("sol258").clearSolutionData();
    model.sol("sol259").clearSolutionData();
    model.sol("sol260").clearSolutionData();
    model.sol("sol261").clearSolutionData();
    model.sol("sol262").clearSolutionData();
    model.sol("sol263").clearSolutionData();
    model.sol("sol264").clearSolutionData();
    model.sol("sol265").clearSolutionData();
    model.sol("sol266").clearSolutionData();
    model.sol("sol267").clearSolutionData();
    model.sol("sol268").clearSolutionData();
    model.sol("sol269").clearSolutionData();
    model.sol("sol270").clearSolutionData();
    model.sol("sol271").clearSolutionData();
    model.sol("sol272").clearSolutionData();
    model.sol("sol273").clearSolutionData();
    model.sol("sol274").clearSolutionData();
    model.sol("sol275").clearSolutionData();
    model.sol("sol276").clearSolutionData();
    model.sol("sol277").clearSolutionData();
    model.sol("sol278").clearSolutionData();
    model.sol("sol279").clearSolutionData();
    model.sol("sol280").clearSolutionData();
    model.sol("sol281").clearSolutionData();
    model.sol("sol282").clearSolutionData();
    model.sol("sol283").clearSolutionData();
    model.sol("sol284").clearSolutionData();
    model.sol("sol285").clearSolutionData();
    model.sol("sol286").clearSolutionData();
    model.sol("sol287").clearSolutionData();
    model.sol("sol288").clearSolutionData();
    model.sol("sol289").clearSolutionData();
    model.sol("sol290").clearSolutionData();

    model.label("thermal_decomposition_uq.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
