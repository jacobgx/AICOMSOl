/*
 * isoelectric_separation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:54 by COMSOL 6.3.0.290. */
public class isoelectric_separation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("el", "ElectrophoreticTransport", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "3.5[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("H", "30[mm]", "\u7535\u6781\u9ad8\u5ea6");
    model.param().set("iep_1", "4.7", "\u7b49\u7535\u70b9\uff0c\u86cb\u767d\u8d28 2");
    model.param().set("iep_2", "6.1", "\u7b49\u7535\u70b9\uff0c\u86cb\u767d\u8d28 3");
    model.param().set("iep_3", "7.5", "\u7b49\u7535\u70b9\uff0c\u86cb\u767d\u8d28 4");
    model.param().set("iep_4", "9", "\u7b49\u7535\u70b9\uff0c\u86cb\u767d\u8d28 5");
    model.param().set("D_p", "5e-10[m^2/s]", "\u86cb\u767d\u8d28\u6269\u6563\u7cfb\u6570");
    model.param().set("Uave", "0.15[mm/s]", "\u8f7d\u6d41\u4f53\u5e73\u5747\u901f\u5ea6");
    model.param().set("cp_in", "1[mM]", "\u5165\u53e3\u86cb\u767d\u8d28\u6d53\u5ea6");
    model.param().set("V0", "0.15[V]", "\u7535\u538b");
    model.param().set("cwa_in", "100[mM]", "\u5f31\u9178\u5165\u53e3\u6d53\u5ea6");
    model.param().set("cwb_in", "100[mM]", "\u5f31\u78b1\u5165\u53e3\u6d53\u5ea6");
    model.param().set("pKa_wb", "8", "\u5f31\u9178\u7684 pKa");
    model.param().set("pKa_wa", "6", "\u5f31\u78b1\u7684 pKa");
    model.param().set("mob_wa", "2.4e-13[s*mol/kg]", "\u5f31\u9178\u8fc1\u79fb\u7387");
    model.param().set("mob_wb", "2.5e-13[s*mol/kg]", "\u5f31\u78b1\u8fc1\u79fb\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W", "W"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-W"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "H"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("r2", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("r3", 3);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 6);
    model.component("comp1").geom("geom1").feature("mce1").set("includevtx", false);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("xscale", 3);

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

    model.component("comp1").physics("el").prop("TransportMechanism").set("Convection", true);
    model.component("comp1").physics("el").feature("sol1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("el").create("eip1", "ElectrolytePotential", 1);
    model.component("comp1").physics("el").feature("eip1").selection().set(3);
    model.component("comp1").physics("el").create("eip2", "ElectrolytePotential", 1);
    model.component("comp1").physics("el").feature("eip2").selection().set(7);
    model.component("comp1").physics("el").feature("eip2").set("philbnd", "V0");
    model.component("comp1").physics("el").create("prot1", "Protein", 2);
    model.component("comp1").physics("el").feature("prot1").set("speciesname", "p1");
    model.component("comp1").physics("el").feature("prot1").set("avgz", "iep_1-el.pH");
    model.component("comp1").physics("el").feature("prot1").set("D", "D_p");
    model.component("comp1").physics("el").feature("prot1").create("in1", "Inflow", 1);
    model.component("comp1").physics("el").feature("prot1").feature("in1").selection().named("geom1_sel1");
    model.component("comp1").physics("el").feature("prot1").feature("in1").set("c0", "cp_in");
    model.component("comp1").physics("el").feature("prot1").feature("in1")
         .set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("el").feature("prot1").create("out1", "Outflow", 1);
    model.component("comp1").physics("el").feature("prot1").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("el").feature().duplicate("prot2", "prot1");
    model.component("comp1").physics("el").feature("prot2").set("speciesname", "p2");
    model.component("comp1").physics("el").feature("prot2").set("avgz", "iep_2-el.pH");
    model.component("comp1").physics("el").feature().duplicate("prot3", "prot2");
    model.component("comp1").physics("el").feature("prot3").set("speciesname", "p3");
    model.component("comp1").physics("el").feature("prot3").set("avgz", "iep_3-el.pH");
    model.component("comp1").physics("el").feature().duplicate("prot4", "prot3");
    model.component("comp1").physics("el").feature("prot4").set("speciesname", "p4");
    model.component("comp1").physics("el").feature("prot4").set("avgz", "iep_4-el.pH");
    model.component("comp1").physics("el").create("wa1", "WeakAcid", 2);
    model.component("comp1").physics("el").feature("wa1").set("speciesname", "wa");
    model.component("comp1").physics("el").feature("wa1").set("pKam", "pKa_wa");
    model.component("comp1").physics("el").feature("wa1").set("um", "mob_wa");
    model.component("comp1").physics("el").feature("wa1").feature("initc1").set("initc", "cwa_in");
    model.component("comp1").physics("el").feature("wa1").create("in1", "Inflow", 1);
    model.component("comp1").physics("el").feature("wa1").feature("in1").selection().named("geom1_sel1");
    model.component("comp1").physics("el").feature("wa1").feature("in1").set("c0", "cwa_in");
    model.component("comp1").physics("el").feature("wa1").feature("in1")
         .set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("el").feature("wa1").create("out1", "Outflow", 1);
    model.component("comp1").physics("el").feature("wa1").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("el").create("wb1", "WeakBase", 2);
    model.component("comp1").physics("el").feature("wb1").set("speciesname", "wb");
    model.component("comp1").physics("el").feature("wb1").set("pKam", "pKa_wb");
    model.component("comp1").physics("el").feature("wb1").set("um", "mob_wb");
    model.component("comp1").physics("el").feature("wb1").feature("initc1").set("initc", "cwb_in");
    model.component("comp1").physics("el").feature("wb1").create("in1", "Inflow", 1);
    model.component("comp1").physics("el").feature("wb1").feature("in1").selection().named("geom1_sel1");
    model.component("comp1").physics("el").feature("wb1").feature("in1").set("c0", "cwb_in");
    model.component("comp1").physics("el").feature("wb1").create("out1", "Outflow", 1);
    model.component("comp1").physics("el").feature("wb1").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uave");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 5, 9, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 60);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "5e-4");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/el", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").label("\u7814\u7a76 1 - \u6d41\u52a8\u8ba1\u7b97");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "spf.U");
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
    model.result("pg1").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/el", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/spf", false);
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("ftplistmethod", "manual");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/el", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").label("\u7814\u7a76 2 - \u5206\u79bb\u8ba1\u7b97");
    model.study("std2").feature("cdi").set("usesol", true);
    model.study("std2").feature("cdi").set("notsolmethod", "sol");
    model.study("std2").feature("cdi").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"el.pH"});
    model.result("pg3").label("pH (el)");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"el.sigmal"});
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u5bfc\u7387 (el)");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"el.Ilx", "el.Ily"});
    model.result("pg5").feature("arws1").set("arrowbase", "center");
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (el)");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"el.c_p1"});
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"el.tflux_p1x", "el.tflux_p1y"});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").label("\u6469\u5c14\u6d53\u5ea6 - p1 (el)");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"el.c_p2"});
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"el.tflux_p2x", "el.tflux_p2y"});
    model.result("pg7").feature("arws1").set("arrowbase", "center");
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").label("\u6469\u5c14\u6d53\u5ea6 - p2 (el)");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"el.c_p3"});
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"el.tflux_p3x", "el.tflux_p3y"});
    model.result("pg8").feature("arws1").set("arrowbase", "center");
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").label("\u6469\u5c14\u6d53\u5ea6 - p3 (el)");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"el.c_p4"});
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"el.tflux_p4x", "el.tflux_p4y"});
    model.result("pg9").feature("arws1").set("arrowbase", "center");
    model.result("pg9").feature("arws1").set("color", "black");
    model.result("pg9").label("\u6469\u5c14\u6d53\u5ea6 - p4 (el)");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"el.c_wa"});
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("expr", new String[]{"el.tflux_wax", "el.tflux_way"});
    model.result("pg10").feature("arws1").set("arrowbase", "center");
    model.result("pg10").feature("arws1").set("color", "black");
    model.result("pg10").label("\u6469\u5c14\u6d53\u5ea6 - wa (el)");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"el.c_wb"});
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("expr", new String[]{"el.tflux_wbx", "el.tflux_wby"});
    model.result("pg11").feature("arws1").set("arrowbase", "center");
    model.result("pg11").feature("arws1").set("color", "black");
    model.result("pg11").label("\u6469\u5c14\u6d53\u5ea6 - wb (el)");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().remove("arws1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"u", "v"});
    model.result("pg7").feature("arwl1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg7").feature("arwl1").set("arrowcount", 20);
    model.result("pg7").feature("arwl1").set("color", "custom");
    model.result("pg7").feature("arwl1")
         .set("customcolor", new double[]{0.019607843831181526, 0.5176470875740051, 0.5215686559677124});
    model.result("pg7").run();
    model.result("pg7").feature("arwl1").create("sel1", "Selection");
    model.result("pg7").feature("arwl1").feature("sel1").selection().set(2, 5);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"el.tflux_p2x", "el.tflux_p2y"});
    model.result("pg7").feature("str1").set("descr", "\u603b\u901a\u91cf");
    model.result("pg7").feature("str1").set("selnumber", 10);
    model.result("pg7").feature("str1").selection().set(5);
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowcountactive", true);
    model.result("pg7").feature("str1").set("arrowcount", 90);
    model.result("pg7").feature("str1").set("arrowscaleactive", true);
    model.result("pg7").feature("str1").set("arrowscale", 10);
    model.result("pg7").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u51fa\u53e3\u5904\u7684 pH");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().named("geom1_sel2");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "x");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u51fa\u53e3\u5904\u7684\u86cb\u767d\u8d28\u6d53\u5ea6");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("legendlayout", "outside");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").selection().named("geom1_sel2");
    model.result("pg13").feature("lngr1").set("expr", "el.c_p1");
    model.result("pg13").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "x");
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("legendmethod", "manual");
    model.result("pg13").feature("lngr1").setIndex("legends", "\u86cb\u767d\u8d28 1", 0);
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("lngr2", "lngr1");
    model.result("pg13").run();
    model.result("pg13").feature("lngr2").set("expr", "el.c_p2");
    model.result("pg13").feature("lngr2").setIndex("legends", "\u86cb\u767d\u8d28 2", 0);
    model.result("pg13").feature().duplicate("lngr3", "lngr2");
    model.result("pg13").run();
    model.result("pg13").feature("lngr3").set("expr", "el.c_p3");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg13").feature("lngr3").setIndex("legends", "\u86cb\u767d\u8d28 3", 0);
    model.result("pg13").feature().duplicate("lngr4", "lngr3");
    model.result("pg13").run();
    model.result("pg13").feature("lngr4").set("expr", "el.c_p4");
    model.result("pg13").feature("lngr4").setIndex("legends", "\u86cb\u767d\u8d28 4", 0);
    model.result("pg13").run();
    model.result("pg13").run();

    model.title("\u7b49\u7535\u70b9\u5206\u79bb");

    model
         .description("\u672c\u4f8b\u5e94\u7528\u7535\u6cf3\u8f93\u9001 \u63a5\u53e3\u548c\u5c42\u6d41 \u63a5\u53e3\u5bf9\u81ea\u7531\u6d41\u52a8\u7684\u7535\u6cf3\u88c5\u7f6e\u4e2d\u7684\u7b49\u7535\u70b9\u5206\u79bb\u8fdb\u884c\u5efa\u6a21\u3002\u663e\u793a\u4e86\u901a\u8fc7\u7535\u573a\u4e2d\u7684\u8fc1\u79fb\u4f20\u8f93\u53ef\u4ee5\u5c06\u542b\u6709\u516d\u79cd\u4e0d\u540c\u79bb\u5b50\u7269\u8d28\u7684\u6d41\u4f53\u5206\u79bb\u6210\u7eaf\u7ec4\u5206\u7684\u6d41\u4f53\u3002\n\n\u7531\u4e8e\u86cb\u767d\u8d28\u7b49\u5927\u5206\u5b50\u7684\u8fc1\u79fb\u65b9\u5411\u5782\u76f4\u4e8e\u8f7d\u6d41\u4f53\u7684\u6d41\u52a8\u65b9\u5411\uff0c\u56e0\u6b64\u81ea\u7531\u6d41\u7535\u6cf3\u53ef\u4ee5\u5206\u79bb\u51fa\u8fd9\u4e9b\u5927\u5206\u5b50\u3002\u6b64\u5916\uff0c\u5982\u679c\u8f7d\u6d41\u4f53\u4e0a\u4e2d\u5b58\u5728 pH \u68af\u5ea6\uff0c\u5219\u5206\u5b50\u53ef\u4ee5\u6cbf\u5176\u7b49\u7535\u70b9\u805a\u96c6\u3002\u7b49\u7535\u70b9\u662f\u5206\u5b50\u51c0\u7535\u8377\u4e3a\u96f6\u7684 pH \u503c\u3002\u5e26\u6b63\u51c0\u7535\u8377\u7684\u5206\u5b50\u5c06\u5728\u7535\u573a\u65b9\u5411\u6cbf pH \u68af\u5ea6\u8fd0\u52a8\uff0c\u76f4\u5230\u5b83\u4eec\u5230\u8fbe\u7b49\u7535\u70b9\u3002\u5728\u8fd9\u4e2a\u5b9e\u4f8b\u4e2d\uff0c\u5f53\u5206\u5b50\u51c0\u7535\u8377\u4e3a\u96f6\u65f6\uff0c\u8fc1\u79fb\u4f20\u8f93\u5c31\u7ed3\u675f\u3002\u540c\u6837\u5730\uff0c\u9634\u79bb\u5b50\u7269\u8d28\u6cbf\u7535\u573a\u53cd\u65b9\u5411\u8fd0\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("isoelectric_separation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
