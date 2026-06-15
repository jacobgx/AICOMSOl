/*
 * optimal_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:22 by COMSOL 6.3.0.290. */
public class optimal_cooling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA", "cB", "cC"});
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("ht2", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht2").field("temperature").field("Tj");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_r", "2[m]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("D", "1e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("u", "0.0042[m/s]", "\u6d41\u4f53\u901f\u5ea6\uff0c\u53cd\u5e94\u5668");
    model.param().set("uj", "0.01[m/s]", "\u6d41\u4f53\u901f\u5ea6\uff0c\u5939\u5957");
    model.param().set("cA_in", "700[mol/m^3]", "A \u7684\u5165\u53e3\u6d53\u5ea6\uff0c\u53cd\u5e94\u5668");
    model.param().set("Mn_A", "0.030[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cA");
    model.param().set("Mn_B", "0.048[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cB");
    model.param().set("Mn_C", "0.056[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cC");
    model.param().set("Mn_H2O", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242");
    model.param().set("c_solv", "(941[kg/m^3]-cA_in*Mn_A)/Mn_H2O", "\u6d53\u5ea6\uff0c\u6eb6\u5242");
    model.param().set("T_in", "400[K]", "\u5165\u53e3\u6e29\u5ea6\uff0c\u53cd\u5e94\u5668");
    model.param().set("Tj_in", "400[K]", "\u5165\u53e3\u6e29\u5ea6\uff0c\u5939\u5957");
    model.param().set("UA", "10000[W/m^3/K]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("H1", "200e3[J/mol]", "\u53cd\u5e94\u70ed 1");
    model.param().set("H2", "100e3[J/mol]", "\u53cd\u5e94\u70ed 2");
    model.param().set("Rg", "8.314[J/mol/K]", "\u901a\u7528\u6c14\u4f53\u5e38\u6570");
    model.param().set("A1", "1.6e8[1/s]", "\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 1");
    model.param().set("A2", "1e15[1/s]", "\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 2");
    model.param().set("E1", "75e3[J/mol]", "\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 1");
    model.param().set("E2", "125e3[J/mol]", "\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 2");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_r", 1);
    model.component("comp1").geom("geom1").run("i1");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("cB_out", "intop1(cB)");
    model.component("comp1").variable("var1").descr("cB_out", "\u51fa\u53e3\u6d53\u5ea6");

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

    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp1.T");
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "A=>B");
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A1");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "E1");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("H", "H1");
    model.component("comp1").physics("chem").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("chem").feature("B").set("M", "Mn_B");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 1);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "B=>C");
    model.component("comp1").physics("chem").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch2").set("Af", "A2");
    model.component("comp1").physics("chem").feature("rch2").set("Ef", "E2");
    model.component("comp1").physics("chem").feature("rch2").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch2").set("H", "H2");
    model.component("comp1").physics("chem").feature("C").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("chem").feature("C").set("M", "Mn_C");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 1);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("chem").feature("H2O").set("M", "Mn_H2O");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cB", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cC", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "c_solv", 3, 0);
    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"u", "0", "0"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cB", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cC", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 0);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cA_in", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 0);
    model.component("comp1").physics("tds").feature("out1").selection().set(2);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 1);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cA_src", "root.comp1.chem.R_A", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cB_src", "root.comp1.chem.R_B", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cC_src", "root.comp1.chem.R_C", 0);
    model.component("comp1").physics("ht").label("\u6d41\u4f53\u4f20\u70ed - \u53cd\u5e94\u5668");
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"u", "0", "0"});
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 0);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(2);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 1);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "-UA*(T-Tj)+chem.Qtot");
    model.component("comp1").physics("ht2").label("\u6d41\u4f53\u4f20\u70ed - \u51b7\u5374\u5957");
    model.component("comp1").physics("ht2").feature("fluid1").set("u", new String[]{"uj", "0", "0"});
    model.component("comp1").physics("ht2").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht2").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht2").feature("temp1").set("T0", "Tj_in");
    model.component("comp1").physics("ht2").create("ofl1", "ConvectiveOutflow", 0);
    model.component("comp1").physics("ht2").feature("ofl1").selection().set(2);
    model.component("comp1").physics("ht2").create("hs1", "HeatSource", 1);
    model.component("comp1").physics("ht2").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht2").feature("hs1").set("Q0", "UA*(T-Tj)");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg1").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tds)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"cA"});
    model.result("pg1").feature("lngr1").label("\u7269\u8d28 A");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoexpr", false);
    model.result("pg1").feature("lngr1").set("autodescr", false);
    model.result("pg1").feature("lngr1").set("legendprefix", "A ");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", new String[]{"cB"});
    model.result("pg1").feature("lngr2").label("\u7269\u8d28 B");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("autosolution", false);
    model.result("pg1").feature("lngr2").set("autoexpr", false);
    model.result("pg1").feature("lngr2").set("autodescr", false);
    model.result("pg1").feature("lngr2").set("legendprefix", "B ");
    model.result("pg1").feature("lngr2").set("descractive", true);
    model.result("pg1").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "x");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature("lngr3").set("expr", new String[]{"cC"});
    model.result("pg1").feature("lngr3").label("\u7269\u8d28 C");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("autosolution", false);
    model.result("pg1").feature("lngr3").set("autoexpr", false);
    model.result("pg1").feature("lngr3").set("autodescr", false);
    model.result("pg1").feature("lngr3").set("legendprefix", "C ");
    model.result("pg1").feature("lngr3").set("descractive", true);
    model.result("pg1").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u6d53\u5ea6, A (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"cA"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, B (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"cB"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, C (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cC"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").feature().create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("expr", "T");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("data", "parent");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u6e29\u5ea6 (ht2)");
    model.result("pg6").feature().create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("expr", "Tj");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("data", "parent");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("Tj_in=400K");

    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().configuration("gsty1").set("linecolor", "cycle");
    model.result().configuration("gsty1").set("autoplotlabel", true);
    model.result().configuration("gsty1").set("autopoint", false);
    model.result().configuration("gsty1").set("autosolution", false);
    model.result().configuration("gsty1").set("autoheaders", false);
    model.result("pg1").run();
    model.result("pg1").label("Tj_in=400K \u7684\u6d53\u5ea6");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("styleconfig", "gsty1");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").label("Tj_in=400K \u7684\u6e29\u5ea6");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("styleconfig", "gsty1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").label("\u53cd\u5e94\u5668");
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").label("\u51b7\u5374\u5957");
    model.result("pg5").feature("lngr2").set("expr", "Tj");
    model.result("pg5").feature("lngr2").set("descr", "\u6e29\u5ea6");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("Tj_in=400K \u7684\u4ea7\u7387");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("styleconfig", "gsty1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").label("\u53cd\u5e94 1");
    model.result("pg6").feature("lngr1").set("titletype", "none");
    model.result("pg6").feature("lngr1").set("expr", "chem.r_1");
    model.result("pg6").feature("lngr1").set("descr", "\u53cd\u5e94\u901f\u7387");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").label("\u53cd\u5e94 2");
    model.result("pg6").feature("lngr2").set("expr", "chem.r_2");
    model.result("pg6").feature("lngr2").set("descr", "\u53cd\u5e94\u901f\u7387");
    model.result("pg6").run();

    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "bobyqa");
    model.study("std1").feature("opt").setIndex("optobj", "comp1.cB_out", 0);
    model.study("std1").feature("opt").set("objectivetype", "maximization");
    model.study("std1").feature("opt").setIndex("pname", "L_r", 0);
    model.study("std1").feature("opt").setIndex("initval", "2[m]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "L_r", 0);
    model.study("std1").feature("opt").setIndex("initval", "2[m]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "Tj_in", 0);
    model.study("std1").feature("opt").setIndex("scale", "400[K]", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("o1").run("compute");

    model.result("pg2").run();

    model.study("std1").feature("opt").set("probewindow", "");

    model.result("pg1").run();
    model.result().duplicate("pg7", "pg1");
    model.result("pg7").run();
    model.result("pg7").label("\u4f18\u5316\u7684 Tj_in \u7684\u6d53\u5ea6");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").run();
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").label("\u4f18\u5316\u7684 Tj_in \u7684\u6e29\u5ea6 Tj_in");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").run();
    model.result("pg6").run();
    model.result().duplicate("pg9", "pg6");
    model.result("pg9").run();
    model.result("pg9").label("\u4f18\u5316\u7684 Tj_in \u7684\u4ea7\u7387");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result("pg5").run();

    model.title("\u7ba1\u5f0f\u53cd\u5e94\u5668\u7684\u4f18\u5316\u51b7\u5374");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u975e\u7b49\u6e29\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u53d1\u751f\u7684\u4e24\u4e2a\u8fde\u7eed\u53cd\u5e94\u3002\u901a\u8fc7\u4f18\u5316\u7b97\u6cd5\u786e\u5b9a\u53cd\u5e94\u5668\u7684\u6700\u4f73\u6e29\u5ea6\u6761\u4ef6\uff0c\u5f97\u5230\u6700\u5927\u4ea7\u7387\uff0c\u540c\u65f6\u5c3d\u91cf\u51cf\u5c11\u4e2d\u95f4\u53cd\u5e94\u4ea7\u7269\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u4f18\u5316\u6a21\u5757\u201d\u3002");

    model.label("optimal_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
