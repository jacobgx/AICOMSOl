/*
 * plate_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:22 by COMSOL 6.3.0.290. */
public class plate_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA", "cB", "cD", "cU"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "300[K]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("D", "1e-7[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("U1", "2e-3[m/s]", "\u901f\u5ea6\uff0c\u5165\u53e3 1");
    model.param().set("U2", "1e-3[m/s]", "\u901f\u5ea6\uff0c\u5165\u53e3 2");
    model.param().set("cA1", "2[mol/m^3]", "A \u7684\u6d53\u5ea6\uff0c\u5165\u53e3 1");
    model.param().set("cB1", "2[mol/m^3]", "B \u7684\u6d53\u5ea6\uff0c\u5165\u53e3 1");
    model.param().set("cB2", "2[mol/m^3]", "B \u7684\u6d53\u5ea6\uff0c\u5165\u53e3 2");
    model.param().set("csolv", "55500[mol/m^3]", "\u6eb6\u5242\uff08\u6c34\uff09\u6d53\u5ea6");
    model.param().set("Af1", "1e4[m^3/(mol*s)]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("Ef1", "4e4[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("Af2", "1e7[m^3/(mol*s)]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("Ef2", "6e4[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("H1", "-1.1e5[J/mol]", "\u53cd\u5e94\u7113");
    model.param().set("H2", "-1e6[J/mol]", "\u53cd\u5e94\u7113");
    model.param().set("hx", "1000[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("Mn_A", "0.032[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cA");
    model.param().set("Mn_B", "0.032[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cB");
    model.param().set("Mn_D", "0.064[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cD");
    model.param().set("Mn_U", "0.096[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cU");
    model.param().set("Mn_solv", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242\uff08\u6c34\uff09");

    model.component("comp1").geom("geom1").insertFile("plate_reactor_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", 3);
    model.component("comp1").selection("cyl1").set("bottom", 0.1);
    model.component("comp1").selection("cyl1").set("angle1", 90);
    model.component("comp1").selection("cyl1").set("angle2", 270);
    model.component("comp1").selection("cyl1").set("pos", new int[]{0, 20, 0});
    model.component("comp1").selection("cyl1").set("axistype", "x");
    model.component("comp1").selection().duplicate("cyl2", "cyl1");
    model.component("comp1").selection("cyl2").set("pos", new int[]{0, 20, 18});
    model.component("comp1").selection().duplicate("cyl3", "cyl2");
    model.component("comp1").selection("cyl3").set("pos", new String[]{"6", "20", "18*2"});
    model.component("comp1").selection().duplicate("cyl4", "cyl3");
    model.component("comp1").selection("cyl4").set("pos", new String[]{"6", "20", "18*3"});
    model.component("comp1").selection().duplicate("cyl5", "cyl4");
    model.component("comp1").selection("cyl5").set("r", 0.5);
    model.component("comp1").selection("cyl5").set("top", 10);
    model.component("comp1").selection("cyl5").set("angle1", 1);
    model.component("comp1").selection("cyl5").set("angle2", 360);
    model.component("comp1").selection("cyl5").set("pos", new String[]{"1", "20", "18*2-1"});
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(29, 31, 32, 33, 47, 50, 52, 53, 68, 71, 72, 73, 87, 90, 92, 93, 108, 111, 112, 113, 127, 130, 132, 133, 148, 151, 152, 153, 167, 170, 172, 173, 188, 191, 192, 193, 207, 210);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u58c1\u9009\u62e9");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"cyl1", "cyl2", "cyl3", "cyl4", "cyl5", "sel1"});

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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U1");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().named("geom1_sel2");
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("Uavfdf", "U2");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp1.T");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "A+B=>D");
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "Af1");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "Ef1");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("H", "H1");
    model.component("comp1").physics("chem").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("chem").feature("B").set("M", "Mn_B");
    model.component("comp1").physics("chem").feature("D").set("M", "Mn_D");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "D+B=>U");
    model.component("comp1").physics("chem").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch2").set("Af", "Af2");
    model.component("comp1").physics("chem").feature("rch2").set("Ef", "Ef2");
    model.component("comp1").physics("chem").feature("rch2").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch2").set("H", "H2");
    model.component("comp1").physics("chem").feature("U").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("chem").feature("U").set("M", "Mn_U");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cB", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cD", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "csolv", 3, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cU", 4, 0);
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").set("Q0_src", "root.comp1.chem.Qtot");
    model.component("comp1").physics("ht").feature("hs1").selection().all();
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T0");
    model.component("comp1").physics("ht").feature().duplicate("ifl2", "ifl1");
    model.component("comp1").physics("ht").feature("ifl2").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_sel3");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_difsel1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "hx");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0-5[K]");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_difsel2");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "hx");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T0-20[K]");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cA", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cB", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cD", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cU", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds").feature("reac1").selection().all();
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cA_src", "root.comp1.chem.R_A", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cB_src", "root.comp1.chem.R_B", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cD_src", "root.comp1.chem.R_D", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cU_src", "root.comp1.chem.R_U", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().named("geom1_sel1");
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cA1", 0);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "cB1", 1);
    model.component("comp1").physics("tds").create("in2", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in2").selection().named("geom1_sel2");
    model.component("comp1").physics("tds").feature("in2").setIndex("c0", "cB2", 1);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_sel3");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(10, 21, 219, 242);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3, 5, 6, 9, 10, 11, 16, 17, 18);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").feature().duplicate("ftri2", "ftri1");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(3, 17, 30, 217, 218, 224, 229, 230);
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").label("\u7a33\u6001 - \u5c42\u6d41");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfd1", false);
    model.study("std1").feature("stat2").label("\u7a33\u6001 - \u4f20\u70ed\u548c\u4f20\u8d28");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, A, \u6d41\u7ebf (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tds.tflux_cAx", "tds.tflux_cAy", "tds.tflux_cAz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col", "Color");
    model.result("pg4").feature("str1").feature("col").set("expr", "cA");
    model.result("pg4").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg4").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg4").feature("str1").set("linetype", "ribbon");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, A, \u8868\u9762 (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").label("\u6d53\u5ea6, B, \u6d41\u7ebf (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"tds.tflux_cBx", "tds.tflux_cBy", "tds.tflux_cBz"});
    model.result("pg6").feature("str1").set("posmethod", "start");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col", "Color");
    model.result("pg6").feature("str1").feature("col").set("expr", "cB");
    model.result("pg6").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg6").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg6").feature("str1").set("linetype", "ribbon");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").label("\u6d53\u5ea6, B, \u8868\u9762 (tds)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cB"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").label("\u6d53\u5ea6, D, \u6d41\u7ebf (tds)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tds.tflux_cDx", "tds.tflux_cDy", "tds.tflux_cDz"});
    model.result("pg8").feature("str1").set("posmethod", "start");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg8").feature("str1").create("col", "Color");
    model.result("pg8").feature("str1").feature("col").set("expr", "cD");
    model.result("pg8").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg8").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg8").feature("str1").set("linetype", "ribbon");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").label("\u6d53\u5ea6, D, \u8868\u9762 (tds)");
    model.result("pg9").set("titletype", "custom");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cD"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").label("\u6d53\u5ea6, U, \u6d41\u7ebf (tds)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1")
         .set("expr", new String[]{"tds.tflux_cUx", "tds.tflux_cUy", "tds.tflux_cUz"});
    model.result("pg10").feature("str1").set("posmethod", "start");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result("pg10").feature("str1").create("col", "Color");
    model.result("pg10").feature("str1").feature("col").set("expr", "cU");
    model.result("pg10").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg10").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg10").feature("str1").set("linetype", "ribbon");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").label("\u6d53\u5ea6, U, \u8868\u9762 (tds)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cU"});
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u58c1\u6e29");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("solutionparams", "parent");
    model.result("pg12").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg12").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg12").feature("surf1").feature("sel1").selection()
         .set(1, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result("pg12").feature().create("arwv1", "ArrowVolume");
    model.result("pg12").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg12").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg12").feature("arwv1").set("solutionparams", "parent");
    model.result("pg12").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg12").feature("arwv1").set("xnumber", 30);
    model.result("pg12").feature("arwv1").set("ynumber", 30);
    model.result("pg12").feature("arwv1").set("znumber", 30);
    model.result("pg12").feature("arwv1").set("arrowtype", "cone");
    model.result("pg12").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg12").feature("arwv1").set("data", "parent");
    model.result("pg12").feature("arwv1").feature().create("col1", "Color");
    model.result("pg12").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg12").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg12").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u901f\u5ea6\u573a");
    model.result("pg13").set("edges", false);
    model.result("pg13").set("showlegendsunit", true);
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1").selection().set(23, 24);
    model.result("pg13").feature("str1").set("linetype", "tube");
    model.result("pg13").feature("str1").set("radiusexpr", "5e-4");
    model.result("pg13").feature("str1").create("col1", "Color");
    model.result("pg13").run();
    model.result("pg13").feature("str1").feature("col1").set("expr", "cA");
    model.result("pg13").feature("str1").feature("col1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccA");
    model.result("pg13").feature("str1").feature("col1").set("colortable", "Prism");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("titletype", "none");
    model.result("pg13").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg13").run();
    model.result("pg13").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg13").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg13").run();
    model.result("pg13").feature("surf1").create("sel1", "Selection");
    model.result("pg13").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u6e29\u5ea6");
    model.result("pg14").set("edges", false);
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").create("slc1", "Slice");
    model.result("pg14").feature("slc1").set("expr", "T");
    model.result("pg14").feature("slc1").set("descr", "\u6e29\u5ea6");
    model.result("pg14").feature("slc1").set("quickplane", "xy");
    model.result("pg14").feature("slc1").set("quickznumber", 4);
    model.result("pg14").feature("slc1").set("rangecoloractive", true);
    model.result("pg14").feature("slc1").set("rangecolormin", 280);
    model.result("pg14").feature("slc1").set("rangecolormax", 300);
    model.result("pg14").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg14").run();
    model.result("pg14").create("slc2", "Slice");
    model.result("pg14").feature("slc2").set("titletype", "none");
    model.result("pg14").feature("slc2").set("expr", "T");
    model.result("pg14").feature("slc2").set("descr", "\u6e29\u5ea6");
    model.result("pg14").feature("slc2").set("quickplane", "zx");
    model.result("pg14").feature("slc2").set("quickynumber", 1);
    model.result("pg14").feature("slc2").set("inheritplot", "slc1");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg14").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg14").run();
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result("pg15").label("\u6d53\u5ea6 B");
    model.result("pg15").set("edges", false);
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").create("iso1", "Isosurface");
    model.result("pg15").feature("iso1").set("expr", "cB");
    model.result("pg15").feature("iso1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccB");
    model.result("pg15").feature("iso1").set("number", 20);
    model.result("pg15").feature("iso1").set("colortable", "Prism");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").create("sel1", "Selection");
    model.result("pg15").feature("surf1").feature("sel1").selection().named("uni1");
    model.result("pg15").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").set("edges", false);
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg9").set("edges", false);
    model.result("pg9").run();
    model.result("pg11").run();
    model.result("pg11").set("edges", false);
    model.result("pg11").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().remove("pg6");
    model.result().remove("pg8");
    model.result().remove("pg10");
    model.result().remove("pg12");
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label("\u6469\u5c14\u5e73\u8861, A (tds)");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().all();
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"tds.ntflux_cA"});
    model.result().evaluationGroup("eg1").feature("int1").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg1").feature("int1").selection().set(23, 24);
    model.result().evaluationGroup("eg1").feature("int1").label("\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg1").create("int2", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int2").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int2").selection().all();
    model.result().evaluationGroup("eg1").feature("int2").set("expr", new String[]{"tds.ntflux_cA"});
    model.result().evaluationGroup("eg1").feature("int2").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg1").feature("int2").selection().set(2);
    model.result().evaluationGroup("eg1").feature("int2").label("\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg1").create("int3", "IntVolume");
    model.result().evaluationGroup("eg1").feature("int3").label("\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg1").feature("int3").set("expr", new String[]{"tds.R_cA"});
    model.result().evaluationGroup("eg1").feature("int3").set("descr", "\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg1").feature("int3").selection().set(1, 2, 3);
    model.result().evaluationGroup("eg1").create("int4", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int4").selection().all();
    model.result().evaluationGroup("eg1").feature("int4").set("expr", new String[]{"tds.ntflux_cA"});
    model.result().evaluationGroup("eg1").feature("int4")
         .set("descr", "\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg1").feature("int4").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result().evaluationGroup("eg1").feature("int4").label("\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("generalexpr", "-int4+int3");
    model.result().evaluationGroup("eg1").set("keepchildnodes", true);
    model.result().evaluationGroup("eg1").set("generalheader", "\u6469\u5c14\u5e73\u8861");
    model.result().evaluationGroup("eg1").label("\u6469\u5c14\u5e73\u8861, A (tds)");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset1");
    model.result().evaluationGroup("eg2").label("\u6469\u5c14\u5e73\u8861, B (tds)");
    model.result().evaluationGroup("eg2").create("int1", "IntSurface");
    model.result().evaluationGroup("eg2").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg2").feature("int1").selection().all();
    model.result().evaluationGroup("eg2").feature("int1").set("expr", new String[]{"tds.ntflux_cB"});
    model.result().evaluationGroup("eg2").feature("int1").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg2").feature("int1").selection().set(23, 24);
    model.result().evaluationGroup("eg2").feature("int1").label("\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg2").create("int2", "IntSurface");
    model.result().evaluationGroup("eg2").feature("int2").set("intvolume", true);
    model.result().evaluationGroup("eg2").feature("int2").selection().all();
    model.result().evaluationGroup("eg2").feature("int2").set("expr", new String[]{"tds.ntflux_cB"});
    model.result().evaluationGroup("eg2").feature("int2").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg2").feature("int2").selection().set(2);
    model.result().evaluationGroup("eg2").feature("int2").label("\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg2").create("int3", "IntVolume");
    model.result().evaluationGroup("eg2").feature("int3").label("\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg2").feature("int3").set("expr", new String[]{"tds.R_cB"});
    model.result().evaluationGroup("eg2").feature("int3").set("descr", "\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg2").feature("int3").selection().set(1, 2, 3);
    model.result().evaluationGroup("eg2").create("int4", "IntSurface");
    model.result().evaluationGroup("eg2").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg2").feature("int4").selection().all();
    model.result().evaluationGroup("eg2").feature("int4").set("expr", new String[]{"tds.ntflux_cB"});
    model.result().evaluationGroup("eg2").feature("int4")
         .set("descr", "\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg2").feature("int4").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result().evaluationGroup("eg2").feature("int4").label("\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg2").set("type", "general");
    model.result().evaluationGroup("eg2").set("generalexpr", "-int4+int3");
    model.result().evaluationGroup("eg2").set("keepchildnodes", true);
    model.result().evaluationGroup("eg2").set("generalheader", "\u6469\u5c14\u5e73\u8861");
    model.result().evaluationGroup("eg2").label("\u6469\u5c14\u5e73\u8861, B (tds)");
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset1");
    model.result().evaluationGroup("eg3").label("\u6469\u5c14\u5e73\u8861, D (tds)");
    model.result().evaluationGroup("eg3").create("int1", "IntSurface");
    model.result().evaluationGroup("eg3").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg3").feature("int1").selection().all();
    model.result().evaluationGroup("eg3").feature("int1").set("expr", new String[]{"tds.ntflux_cD"});
    model.result().evaluationGroup("eg3").feature("int1").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg3").feature("int1").selection().set(23, 24);
    model.result().evaluationGroup("eg3").feature("int1").label("\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg3").create("int2", "IntSurface");
    model.result().evaluationGroup("eg3").feature("int2").set("intvolume", true);
    model.result().evaluationGroup("eg3").feature("int2").selection().all();
    model.result().evaluationGroup("eg3").feature("int2").set("expr", new String[]{"tds.ntflux_cD"});
    model.result().evaluationGroup("eg3").feature("int2").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg3").feature("int2").selection().set(2);
    model.result().evaluationGroup("eg3").feature("int2").label("\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg3").create("int3", "IntVolume");
    model.result().evaluationGroup("eg3").feature("int3").label("\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg3").feature("int3").set("expr", new String[]{"tds.R_cD"});
    model.result().evaluationGroup("eg3").feature("int3").set("descr", "\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg3").feature("int3").selection().set(1, 2, 3);
    model.result().evaluationGroup("eg3").create("int4", "IntSurface");
    model.result().evaluationGroup("eg3").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg3").feature("int4").selection().all();
    model.result().evaluationGroup("eg3").feature("int4").set("expr", new String[]{"tds.ntflux_cD"});
    model.result().evaluationGroup("eg3").feature("int4")
         .set("descr", "\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg3").feature("int4").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result().evaluationGroup("eg3").feature("int4").label("\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg3").set("type", "general");
    model.result().evaluationGroup("eg3").set("generalexpr", "-int4+int3");
    model.result().evaluationGroup("eg3").set("keepchildnodes", true);
    model.result().evaluationGroup("eg3").set("generalheader", "\u6469\u5c14\u5e73\u8861");
    model.result().evaluationGroup("eg3").label("\u6469\u5c14\u5e73\u8861, D (tds)");
    model.result().evaluationGroup().create("eg4", "EvaluationGroup");
    model.result().evaluationGroup("eg4").set("data", "dset1");
    model.result().evaluationGroup("eg4").label("\u6469\u5c14\u5e73\u8861, U (tds)");
    model.result().evaluationGroup("eg4").create("int1", "IntSurface");
    model.result().evaluationGroup("eg4").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg4").feature("int1").selection().all();
    model.result().evaluationGroup("eg4").feature("int1").set("expr", new String[]{"tds.ntflux_cU"});
    model.result().evaluationGroup("eg4").feature("int1").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg4").feature("int1").selection().set(23, 24);
    model.result().evaluationGroup("eg4").feature("int1").label("\u603b\u901a\u91cf\uff0c\u6d41\u5165");
    model.result().evaluationGroup("eg4").create("int2", "IntSurface");
    model.result().evaluationGroup("eg4").feature("int2").set("intvolume", true);
    model.result().evaluationGroup("eg4").feature("int2").selection().all();
    model.result().evaluationGroup("eg4").feature("int2").set("expr", new String[]{"tds.ntflux_cU"});
    model.result().evaluationGroup("eg4").feature("int2").set("descr", "\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg4").feature("int2").selection().set(2);
    model.result().evaluationGroup("eg4").feature("int2").label("\u603b\u901a\u91cf\uff0c\u6d41\u51fa");
    model.result().evaluationGroup("eg4").create("int3", "IntVolume");
    model.result().evaluationGroup("eg4").feature("int3").label("\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg4").feature("int3").set("expr", new String[]{"tds.R_cU"});
    model.result().evaluationGroup("eg4").feature("int3").set("descr", "\u603b\u901f\u7387\u8868\u8fbe\u5f0f");
    model.result().evaluationGroup("eg4").feature("int3").selection().set(1, 2, 3);
    model.result().evaluationGroup("eg4").create("int4", "IntSurface");
    model.result().evaluationGroup("eg4").feature("int4").set("intvolume", true);
    model.result().evaluationGroup("eg4").feature("int4").selection().all();
    model.result().evaluationGroup("eg4").feature("int4").set("expr", new String[]{"tds.ntflux_cU"});
    model.result().evaluationGroup("eg4").feature("int4")
         .set("descr", "\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg4").feature("int4").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.result().evaluationGroup("eg4").feature("int4").label("\u603b\u901a\u91cf\uff0c\u6240\u6709\u8fb9\u754c");
    model.result().evaluationGroup("eg4").set("type", "general");
    model.result().evaluationGroup("eg4").set("generalexpr", "-int4+int3");
    model.result().evaluationGroup("eg4").set("keepchildnodes", true);
    model.result().evaluationGroup("eg4").set("generalheader", "\u6469\u5c14\u5e73\u8861");
    model.result().evaluationGroup("eg4").label("\u6469\u5c14\u5e73\u8861, U (tds)");
    model.result().evaluationGroup("eg1").set("generalexpr", "(-int4+int3)/int1");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").set("generalexpr", "(-int4+int3)/int1");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").set("generalexpr", "(-int4+int3)/int2");
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup("eg4").set("generalexpr", "(-int4+int3)/int2");
    model.result().evaluationGroup("eg4").run();
    model.result("pg15").run();

    model.title("\u677f\u5f0f\u53cd\u5e94\u5668\u4e2d\u7684\u7cbe\u7ec6\u5316\u5b66\u54c1\u751f\u4ea7");

    model
         .description("\u5728\u8fde\u7eed\u6761\u4ef6\u4e0b\u8fd0\u884c\u7684\u677f\u5f0f\u53cd\u5e94\u5668\u5df2\u7ecf\u53ef\u4ee5\u5728\u7cbe\u7ec6\u5316\u5b66\u54c1\u751f\u4ea7\u4e2d\u4ee3\u66ff\u95f4\u6b47\u5f0f\u53cd\u5e94\u5668\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u5e76\u6c42\u89e3\u7528\u4e8e\u63cf\u8ff0\u677f\u5f0f\u53cd\u5e94\u5668\u4e2d\u7684\u53cd\u5e94\u6d41\u7684\u6d41\u52a8\u3001\u8d28\u91cf\u548c\u80fd\u91cf\u4f20\u9012\u7684\u8026\u5408\u65b9\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("plate_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
