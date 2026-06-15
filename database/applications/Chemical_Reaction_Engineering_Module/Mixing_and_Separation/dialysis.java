/*
 * dialysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:20 by COMSOL 6.3.0.290. */
public class dialysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Rhf", "0.2[mm]", "\u5185\u534a\u5f84\uff0c\u4e2d\u7a7a\u7ea4\u7ef4");
    model.param().set("Lm", "0.08[mm]", "\u539a\u5ea6\uff0c\u819c");
    model.param().set("Lpc", "0.42[mm]", "\u5bbd\u5ea6\uff0c\u540c\u5fc3\u6e17\u900f\u901a\u9053");
    model.param().set("H", "21[mm]", "\u957f\u5ea6\uff0c\u7ea4\u7ef4");
    model.param().set("D", "1e-9[m^2/s]", "\u6269\u6563\u5e38\u6570\uff0c\u6db2\u76f8");
    model.param().set("Dm", "1e-9[m^2/s]", "\u6269\u6563\u5e38\u6570\uff0c\u819c");
    model.param().set("K", "0.7", "\u5206\u914d\u7cfb\u6570");
    model.param().set("c0_dia", "1[mol/liter]", "\u5165\u53e3\u6d53\u5ea6\uff0c\u900f\u6790\u6db2");
    model.param().set("c0_per", "c0_dia*0.1", "\u5165\u53e3\u6d53\u5ea6\uff0c\u900f\u8fc7\u6db2");
    model.param().set("c0_mem", "0[mol/liter]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u819c");
    model.param().set("Uave_dia", "0.5[mm/s]", "\u5e73\u5747\u901f\u5ea6\uff0c\u900f\u6790\u6db2");
    model.param().set("Uave_per", "0.8[mm/s]", "\u5e73\u5747\u901f\u5ea6\uff0c\u900f\u8fc7\u6db2");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rhf", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Lm", "H"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Rhf", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Lpc", "H"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"Rhf+Lm", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u900f\u6790\u6db2\u548c\u900f\u8fc7\u6db2");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1, 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u819c");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 2);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tds").feature("cdm1")
         .label("\u4f20\u9012\u5c5e\u6027 - \u900f\u6790\u6db2\u548c\u900f\u8fc7\u6db2");
    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("cdm2", "Fluid", 2);
    model.component("comp1").physics("tds").feature("cdm2").selection().set(2);
    model.component("comp1").physics("tds").feature("cdm2").label("\u4f20\u9012\u5c5e\u6027 - \u819c");
    model.component("comp1").physics("tds").feature("cdm2")
         .set("D_c", new String[]{"Dm", "0", "0", "0", "Dm", "0", "0", "0", "Dm"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c0_dia", 0);
    model.component("comp1").physics("tds").feature().duplicate("init2", "init1");
    model.component("comp1").physics("tds").feature("init2").selection().set(3);
    model.component("comp1").physics("tds").feature("init2").setIndex("initc", "c0_per", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in1").selection().set(2);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c0_dia", 0);
    model.component("comp1").physics("tds").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tds").create("in2", "Inflow", 1);
    model.component("comp1").physics("tds").feature("in2").selection().set(8);
    model.component("comp1").physics("tds").feature("in2").setIndex("c0", "c0_per", 0);
    model.component("comp1").physics("tds").feature("in2").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(3, 9);
    model.component("comp1").physics("tds").create("pac1", "PartitionCondition", 1);
    model.component("comp1").physics("tds").feature("pac1").selection().set(4);
    model.component("comp1").physics("tds").feature("pac1").setIndex("K", "K", 0);
    model.component("comp1").physics("tds").create("pac2", "PartitionCondition", 1);
    model.component("comp1").physics("tds").feature("pac2").selection().set(7);
    model.component("comp1").physics("tds").feature("pac2").set("ReverseDirection", true);
    model.component("comp1").physics("tds").feature("pac2").setIndex("K", "K", 0);

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

    model.component("comp1").physics("spf").selection().named("geom1_sel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uave_dia");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl2").selection().set(8);
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl2").set("Uavfdf", "Uave_per");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3, 9);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4, 7, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 250);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 25);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(8, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("reverse", true);

    model.study("std1").feature("stat").label("\u5c42\u6d41");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u7a00\u7269\u8d28\u4f20\u9012");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u6d53\u5ea6 (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cr", "tds.tflux_cz"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").label("\u6d53\u5ea6, 3D (tds)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("colortable", "Rainbow");
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "spf.U");
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.view().create("view2", 2);
    model.view("view2").axis().set("viewscaletype", "manual");
    model.view("view2").axis().set("xscale", 25);

    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("view", "view2");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "MetasepiaBlue");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").active(false);
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 13);
    model.result("pg1").feature("str1").selection().set(2);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowdistr", "equaltime");
    model.result("pg1").feature("str1").set("arrowscaleactive", true);
    model.result("pg1").feature("str1").set("arrowscale", 2);
    model.result("pg1").feature("str1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").create("sel1", "Selection");
    model.result("pg1").feature("arwl1").feature("sel1").selection().set(2, 8);
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("arwl1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg1").feature("arwl1").set("arrowcount", 39);
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", 1400);
    model.result("pg1").feature("arwl1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\u4e8c\u7ef4\u65cb\u8f6c");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("colortable", "MetasepiaBlue");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "Metasepia");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("con1").create("sel1", "Selection");
    model.result("pg4").feature("con1").feature("sel1").selection().set(3);
    model.result("pg4").run();
    model.result("pg4").feature("con1").set("number", 20);
    model.result("pg4").feature().duplicate("con2", "con1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("con2").feature("sel1").selection().set(1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\u4e8c\u7ef4\u65cb\u8f6c");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("colortable", "Metasepia");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "H/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "Rhf+Lm+Lpc", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H/2", 1, 1);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "H", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "Rhf+Lm+Lpc", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "H", 1, 1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6d53\u5ea6\u7a81\u53d8");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").label("H/2 \u5904");
    model.result("pg6").feature("lngr1").set("data", "cln1");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "r");
    model.result("pg6").feature("lngr1").set("linewidth", 2);
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u4e00\u534a\u7ea4\u7ef4\u957f\u5ea6\u5904", 0);
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").label("H \u5904");
    model.result("pg6").feature("lngr2").set("data", "cln2");
    model.result("pg6").feature("lngr2").set("titletype", "none");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "r");
    model.result("pg6").feature("lngr2").set("linewidth", 2);
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u51fa\u53e3\u5904", 0);
    model.result("pg6").run();

    model.title("\u900f\u6790\u5206\u79bb");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u7ecf\u8fc7\u900f\u6790\u7684\u6c34\u76f8\u6d41\u4e2d\u7684\u6c61\u67d3\u7269\u6d53\u5ea6\u3002\u900f\u6790\u8bbe\u5907\u901a\u5e38\u7531\u7a7a\u5fc3\u7ea4\u7ef4\u6a21\u5757\u6784\u6210\uff0c\u4f17\u591a\u7a7a\u5fc3\u7ea4\u7ef4\u5728\u6b64\u626e\u6f14\u7740\u819c\u7684\u89d2\u8272\u3002\u8fd9\u4e00\u8fc7\u7a0b\u7531\u6269\u6563\u9a71\u52a8\uff0c\u4e5f\u5c31\u662f\u8bf4\uff0c\u819c\u7684\u900f\u6790\u6db2\u4fa7\u4e0e\u900f\u8fc7\u6db2\u4fa7\u4e4b\u95f4\u7684\u6d53\u5ea6\u5dee\u5f02\u4f1a\u5bfc\u81f4\u6c61\u67d3\u7269\u901a\u8fc7\u819c\u8fdb\u884c\u6269\u6563\u3002\n\n\u672c\u4f8b\u6a21\u62df\u5355\u4e2a\u7a7a\u5fc3\u7ea4\u7ef4\u5185\u7684\u6c61\u67d3\u7269\u53bb\u9664\u8fc7\u7a0b\uff0c\u5e76\u5047\u8bbe\u5728\u51e0\u4f55\u5185\u5b58\u5728\u5145\u5206\u53d1\u5c55\u7684\u5c42\u6d41\u3002\u540c\u65f6\uff0c\u8fd8\u8003\u8651\u4e86\u4e0d\u540c\u7ea4\u7ef4\u90e8\u5206\u4e4b\u95f4\u7684\u5206\u914d\u5e73\u8861\uff0c\u8fd9\u79cd\u5e73\u8861\u4f1a\u5bfc\u81f4\u6240\u4f20\u9012\u7269\u8d28\u6d53\u5ea6\u7684\u4e0d\u8fde\u7eed\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("dialysis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
