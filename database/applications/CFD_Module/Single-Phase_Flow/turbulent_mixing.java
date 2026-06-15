/*
 * turbulent_mixing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class turbulent_mixing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("TIME", "0[s]");
    model.param().set("rpm", "20[1/min]", "\u6405\u62cc\u5668\u8f6c\u6570");
    model.param().set("frev_out0", "7[1]", "\u5f00\u59cb\u65cb\u8f6c\uff0c\u5b58\u50a8\u6d41\u573a");
    model.param().set("frev_outEnd", "10[1]", "\u7ed3\u675f\u65cb\u8f6c\uff0c\u5b58\u50a8\u6d41\u573a");
    model.param().set("rev_t", "1/rpm", "\u65cb\u8f6c\u65f6\u95f4");
    model.param().set("frev_t0", "frev_out0/rpm", "\u5f00\u59cb\u65f6\u95f4\uff0c\u5b58\u50a8\u6d41\u573a");
    model.param().set("frev_tEnd", "frev_outEnd/rpm", "\u7ed3\u675f\u65f6\u95f4\uff0c\u5b58\u50a8\u6d41\u573a");
    model.param().set("rev_n", "20", "\u6bcf\u8f6c\u8981\u5b58\u50a8\u7684\u65f6\u6b65\u6570");
    model.param().set("rev_dt", "rev_t/rev_n", "\u5b58\u50a8\u7684\u65f6\u6b65\u4e4b\u95f4\u7684\u65f6\u6b65");
    model.param().set("inject_x", "0.3[m]", "\u6ce8\u5c04\u70b9\uff0cx \u5206\u91cf");
    model.param().set("inject_y", "inject_x", "\u6ce8\u5c04\u70b9\uff0cy \u5206\u91cf");
    model.param().set("tank_R", "0.5[m]", "\u91dc\u534a\u5f84");
    model.param().set("blade_R", "0.25[m]", "\u53f6\u7247\u534a\u5f84");
    model.param().set("baffle_L", "0.1[m]", "\u6321\u677f\u957f\u5ea6");
    model.param().set("tank_A", "pi*tank_R^2", "\u91dc\u6a2a\u622a\u9762\u79ef");
    model.param().set("blade_Utip", "2*pi*blade_R*rpm", "\u53f6\u7247\u5c16\u7aef\u901f\u5ea6");
    model.param().set("mix_revs", "40[1]", "\u8981\u6a21\u62df\u7684\u6df7\u5408\u65cb\u8f6c");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "tank_R");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "0.7*tank_R");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-tank_R", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"-tank_R+baffle_L", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(90,90,270)");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "inject_x", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "inject_y", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "-tank_R*0.97", 0);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("pt2");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", -10);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("dif1", "ls1", "pt1", "rot1", "rot2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{-0.25, 0});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new double[]{0.25, 0});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new double[]{0, -0.25});
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new double[]{0, 0.25});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("c2", "ls2", "ls3");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 2, 3, 4, 13, 14, 15, 16);
    model.component("comp1").selection("sel1").label("\u53f6\u8f6e\u548c\u6321\u677f");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "tankAv");
    model.component("comp1").cpl("aveop1").selection().all();

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

    model.component("comp1").common("rot1").selection().set(2);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "-rpm");

    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 1);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("sel1");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(10);

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 1);
    model.component("comp1").view("view1").hideEntities("hide1").set(7, 8, 10, 11, 17, 18, 19, 20);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "3e-3");
    model.component("comp1").mesh("mesh1").run();

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
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "none");
    model.result().dataset().create("edg2", "Edge2D");
    model.result().dataset("edg2").label("\u5185\u58c1");
    model.result().dataset("edg2").set("data", "dset1");
    model.result().dataset("edg2").selection().geom("geom1", 1);
    model.result().dataset("edg2").selection().set(1, 2, 3, 4, 13, 14, 15, 16);
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(5, 6, 9, 12);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg3").feature("line1").feature().create("hght1", "Height");
    model.result("pg3").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg3").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg3").feature("line1").feature().create("def1", "Deform");
    model.result("pg3").feature("line1").feature("def1").label("\u53d8\u5f62");
    model.result("pg3").feature("line1").feature("def1").set("expr", new String[]{"0", "0"});
    model.result("pg3").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature().create("line2", "Line");
    model.result("pg3").feature("line2").label("\u58c1\u5206\u8fa8\u7387\uff0c\u4e0a\u4fa7");
    model.result("pg3").feature("line2").set("showsolutionparams", "on");
    model.result("pg3").feature("line2").set("expr", "spf.Delta_wPlus_u");
    model.result("pg3").feature("line2").set("titletype", "none");
    model.result("pg3").feature("line2").set("linetype", "tube");
    model.result("pg3").feature("line2").set("smooth", "internal");
    model.result("pg3").feature("line2").set("showsolutionparams", "on");
    model.result("pg3").feature("line2").set("data", "parent");
    model.result("pg3").feature("line2").set("inheritplot", "line1");
    model.result("pg3").feature("line2").feature().create("hght1", "Height");
    model.result("pg3").feature("line2").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line2").feature("hght1").set("expr", "spf.WRHeightExpr_u");
    model.result("pg3").feature("line2").feature().create("def1", "Deform");
    model.result("pg3").feature("line2").feature("def1").label("\u53d8\u5f62");
    model.result("pg3").feature("line2").feature("def1")
         .set("expr", new String[]{"spf.WRDeform_ux", "spf.WRDeform_uy"});
    model.result("pg3").feature().create("line3", "Line");
    model.result("pg3").feature("line3").label("\u58c1\u5206\u8fa8\u7387\uff0c\u4e0b\u4fa7");
    model.result("pg3").feature("line3").set("showsolutionparams", "on");
    model.result("pg3").feature("line3").set("expr", "spf.Delta_wPlus_d");
    model.result("pg3").feature("line3").set("titletype", "none");
    model.result("pg3").feature("line3").set("linetype", "tube");
    model.result("pg3").feature("line3").set("smooth", "internal");
    model.result("pg3").feature("line3").set("showsolutionparams", "on");
    model.result("pg3").feature("line3").set("data", "parent");
    model.result("pg3").feature("line3").set("inheritplot", "line1");
    model.result("pg3").feature("line3").feature().create("hght1", "Height");
    model.result("pg3").feature("line3").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line3").feature("hght1").set("expr", "spf.WRHeightExpr_d");
    model.result("pg3").feature("line3").feature().create("def1", "Deform");
    model.result("pg3").feature("line3").feature("def1").label("\u53d8\u5f62");
    model.result("pg3").feature("line3").feature("def1")
         .set("expr", new String[]{"spf.WRDeform_dx", "spf.WRDeform_dy"});
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u6d41\u4f53\u6d41\u52a8\uff0c\u51bb\u7ed3\u8f6c\u5b50");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("U0", "withsol('sol1',tankAv(spf.U))");
    model.component("comp1").variable("var1").descr("U0", "\u5e73\u5747\u901f\u5ea6\uff0c\u51bb\u7ed3\u8f6c\u5b50");
    model.component("comp1").variable("var1").set("muT0", "withsol('sol1',tankAv(spf.muT))");
    model.component("comp1").variable("var1")
         .descr("muT0", "\u5e73\u5747\u6e4d\u6d41\u52a8\u529b\u9ecf\u5ea6\uff0c\u51bb\u7ed3\u8f6c\u5b50");

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u57df\u63a2\u9488\uff1aU");
    model.component("comp1").probe("dom1").set("expr", "spf.U/U0");
    model.component("comp1").probe().duplicate("dom2", "dom1");
    model.component("comp1").probe("dom2").label("\u57df\u63a2\u9488\uff1amuT");
    model.component("comp1").probe("dom2").set("expr", "spf.muT/muT0");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u901f\u5ea6 (spf) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u538b\u529b (spf) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("expr", "p");
    model.result("pg6").feature("con1").set("number", 40);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("smooth", "internal");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("data", "parent");
    model.result().dataset().create("edg3", "Edge2D");
    model.result().dataset("edg3").label("\u5916\u58c1 1");
    model.result().dataset("edg3").set("data", "none");
    model.result().dataset().create("edg4", "Edge2D");
    model.result().dataset("edg4").label("\u5185\u58c1 1");
    model.result().dataset("edg4").set("data", "dset2");
    model.result().dataset("edg4").selection().geom("geom1", 1);
    model.result().dataset("edg4").selection().set(1, 2, 3, 4, 13, 14, 15, 16);
    model.result().dataset("edg3").set("data", "dset2");
    model.result().dataset("edg3").selection().geom("geom1", 1);
    model.result().dataset("edg3").selection().set(5, 6, 9, 12);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u58c1\u5206\u8fa8\u7387 (spf) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("line1", "Line");
    model.result("pg7").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("smooth", "internal");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("data", "parent");
    model.result("pg7").feature("line1").feature().create("hght1", "Height");
    model.result("pg7").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg7").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg7").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg7").feature("line1").feature().create("def1", "Deform");
    model.result("pg7").feature("line1").feature("def1").label("\u53d8\u5f62");
    model.result("pg7").feature("line1").feature("def1").set("expr", new String[]{"0", "0"});
    model.result("pg7").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature().create("line2", "Line");
    model.result("pg7").feature("line2").label("\u58c1\u5206\u8fa8\u7387\uff0c\u4e0a\u4fa7");
    model.result("pg7").feature("line2").set("showsolutionparams", "on");
    model.result("pg7").feature("line2").set("expr", "spf.Delta_wPlus_u");
    model.result("pg7").feature("line2").set("titletype", "none");
    model.result("pg7").feature("line2").set("linetype", "tube");
    model.result("pg7").feature("line2").set("smooth", "internal");
    model.result("pg7").feature("line2").set("showsolutionparams", "on");
    model.result("pg7").feature("line2").set("data", "parent");
    model.result("pg7").feature("line2").set("inheritplot", "line1");
    model.result("pg7").feature("line2").feature().create("hght1", "Height");
    model.result("pg7").feature("line2").feature("hght1").set("heightdata", "expr");
    model.result("pg7").feature("line2").feature("hght1").set("expr", "spf.WRHeightExpr_u");
    model.result("pg7").feature("line2").feature().create("def1", "Deform");
    model.result("pg7").feature("line2").feature("def1").label("\u53d8\u5f62");
    model.result("pg7").feature("line2").feature("def1")
         .set("expr", new String[]{"spf.WRDeform_ux", "spf.WRDeform_uy"});
    model.result("pg7").feature().create("line3", "Line");
    model.result("pg7").feature("line3").label("\u58c1\u5206\u8fa8\u7387\uff0c\u4e0b\u4fa7");
    model.result("pg7").feature("line3").set("showsolutionparams", "on");
    model.result("pg7").feature("line3").set("expr", "spf.Delta_wPlus_d");
    model.result("pg7").feature("line3").set("titletype", "none");
    model.result("pg7").feature("line3").set("linetype", "tube");
    model.result("pg7").feature("line3").set("smooth", "internal");
    model.result("pg7").feature("line3").set("showsolutionparams", "on");
    model.result("pg7").feature("line3").set("data", "parent");
    model.result("pg7").feature("line3").set("inheritplot", "line1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("line3").feature().create("hght1", "Height");
    model.result("pg7").feature("line3").feature("hght1").set("heightdata", "expr");
    model.result("pg7").feature("line3").feature("hght1").set("expr", "spf.WRHeightExpr_d");
    model.result("pg7").feature("line3").feature().create("def1", "Deform");
    model.result("pg7").feature("line3").feature("def1").label("\u53d8\u5f62");
    model.result("pg7").feature("line3").feature("def1")
         .set("expr", new String[]{"spf.WRDeform_dx", "spf.WRDeform_dy"});
    model.result("pg5").run();

    model.study("std2").feature("time").set("tlist", "0 range(frev_t0,rev_dt,frev_tEnd)");
    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg4");
    model.study("std2").feature("time").set("plotfreq", "tsteps");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");

    model.sol("sol2").runAll();

    model.result("pg5").run();
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").label("\u6d41\u4f53\u6d41\u52a8\uff0c\u77ac\u6001");

    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("preprocx", "linear");
    model.result("pg4").feature("tblp1").set("scalingx", "rpm");
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").label("\u63a2\u9488\u56fe\uff1a\u6d41\u4f53\u6d41\u52a8\u7684\u53d1\u5c55");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8f6c\u901f");
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1").setIndex("legends", "spf.U/U0", 0);
    model.result("pg4").feature("tblp1").setIndex("legends", "spf.muT/muT0", 1);
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").feature("str1").set("color", "white");
    model.result("pg5").run();

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", 0.1);
    model.component("comp1").func("rect1").set("upper", 1.1);
    model.component("comp1").func("rect1").set("smooth", 0.2);

    model.component("comp1").probe().create("dom3", "Domain");
    model.component("comp1").probe("dom3").set("intsurface", true);
    model.component("comp1").probe("dom3").set("intvolume", true);
    model.component("comp1").probe("dom3").label("\u57df\u63a2\u9488\uff1ac_av");
    model.component("comp1").probe("dom3").set("expr", "c");
    model.component("comp1").probe("dom3").set("table", "new");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("dom3").set("table", "tbl2");
    model.component("comp1").probe("dom3").set("window", "new");

    model.result("pg7").set("windowtitle", "\u56fe\u5f62");
    model.result("pg6").set("windowtitle", "\u56fe\u5f62");

    model.component("comp1").probe("dom3").set("window", "window2");
    model.component("comp1").probe("dom3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").label("\u70b9\u63a2\u9488\uff1ac");
    model.component("comp1").probe("point1").set("type", "integral");
    model.component("comp1").probe("point1").selection().set(2);
    model.component("comp1").probe("point1").set("expr", "c");
    model.component("comp1").probe("point1").set("table", "tbl2");
    model.component("comp1").probe("point1").set("window", "window2");

    model.result().table("tbl2").label("\u63a2\u9488\u8868 2");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("frrot").setSolveFor("/physics/tds", false);
    model.study("std2").feature("time").setSolveFor("/physics/tds", false);

    model.component("comp1").physics("tds").create("tib1", "ThinImpermeableBarrier", 1);
    model.component("comp1").physics("tds").feature("tib1").selection().named("sel1");
    model.component("comp1").physics("tds").create("lms1", "MassSourceLine", 0);
    model.component("comp1").physics("tds").feature("lms1").selection().set(11);
    model.component("comp1").physics("tds").feature("lms1").setIndex("qs", "rect1(t[1/s])", 0);

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 2);

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/tds", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/rfd1", true);
    model.study("std3").feature("time").set("tlist", "range(0,rev_t/3,rev_t*mix_revs)");
    model.study("std3").feature("time").set("plot", true);
    model.study("std3").feature("time").set("plotfreq", "tsteps");
    model.study("std3").feature("time").set("probesel", "manual");
    model.study("std3").feature("time").set("probes", new String[]{"dom3", "point1"});
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").feature("time").set("notsolnum", "all");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("assumeperiodic", true);
    model.sol("sol3").feature("t1").set("starttime", "frev_tEnd-rev_t");
    model.sol("sol3").feature("t1").set("solutionperiod", "rev_t");

    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.component("comp1").probe("dom3").genResult("none");
    model.component("comp1").probe("point1").genResult("none");

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").label("\u6d53\u5ea6 (tds)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy"});
    model.result("pg9").feature("arws1").set("xnumber", 10);
    model.result("pg9").feature("arws1").set("ynumber", 10);
    model.result("pg9").feature("arws1").set("color", "black");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(1, 2);
    model.result("pg9").run();
    model.result("pg9").set("frametype", "spatial");

    model.study("std3").feature("time").set("plotgroup", "pg9");
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("dom3").genResult("none");
    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg9").run();
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");
    model.nodeGroup("grp3").label("\u7269\u8d28\u6df7\u5408\uff0c\u77ac\u6001");

    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("preprocx", "linear");
    model.result("pg8").feature("tblp1").set("scalingx", "rpm");
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg8").label("\u63a2\u9488\u56fe\uff1a\u6df7\u5408");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u8f6c\u901f");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg8").set("legendpos", "lowerright");
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("legendmethod", "manual");
    model.result("pg8").feature("tblp1").setIndex("legends", "\u5e73\u5747\u503c", 0);
    model.result("pg8").feature("tblp1").setIndex("legends", "\u6d4b\u5b9a\u503c", 1);
    model.result("pg8").set("window", "window2");
    model.result("pg8").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "c/max(tankAv(c),1e-6)");
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("rangecolormin", 0);
    model.result("pg9").feature("surf1").set("rangecolormax", 2);
    model.result("pg9").feature("surf1").set("colortable", "Wave");
    model.result("pg9").run();
    model.result("pg9").feature("arws1").active(false);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 3, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 9, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 19, 0);
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 39, 0);
    model.result("pg9").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg9");
    model.result().export("anim1").set("frametime", 0.15);
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 21, 0);
    model.result("pg9").run();

    model.title("\u6405\u62cc\u91dc\u4e2d\u7684\u6e4d\u6d41\u6df7\u5408");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8bc4\u4f30\u6405\u62cc\u5bb9\u5668\u7684\u6df7\u5408\u80fd\u529b\u3002\u4e3a\u4e86\u6709\u6548\u8fbe\u5230\u7a33\u5b9a\u7684\u5de5\u4f5c\u6761\u4ef6\uff0c\u5176\u4e2d\u4f7f\u7528\u51bb\u7ed3\u8f6c\u5b50\u5206\u6790\uff0c\u5e76\u8fdb\u884c\u540e\u7eed\u7684\u77ac\u6001\u4eff\u771f\u6765\u6c42\u89e3\u6e4d\u6d41\u95ee\u9898\u3002\u5f53\u51c6\u7a33\u6001\u6d41\u573a\u53d1\u5c55\u5230\u4e00\u5b9a\u7a0b\u5ea6\u65f6\uff0c\u6a21\u62df\u75d5\u91cf\u7269\u8d28\u7684\u6e4d\u6d41\u6df7\u5408\uff0c\u5e76\u8ba1\u7b97\u5176\u8fbe\u5230\u5b8c\u5168\u6df7\u5408\u6240\u9700\u7684\u6df7\u5408\u65f6\u95f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("turbulent_mixing.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
