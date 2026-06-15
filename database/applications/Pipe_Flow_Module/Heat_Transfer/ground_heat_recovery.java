/*
 * ground_heat_recovery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:57 by COMSOL 6.3.0.290. */
public class ground_heat_recovery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("htp", "HeatTransferPipes", "geom1");
    model.component("comp1").physics().create("ev", "Events", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/htp", true);
    model.study("std1").feature("time").setSolveFor("/physics/ev", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.geom()
         .load(new String[]{"part1", "part2", "part3"}, "ground_heat_recovery_geom_sequence.mph", new String[]{"part1", "part2", "part3"});

    model.param().set("pattern", "3");
    model.param().descr("pattern", "\u7528\u4e8e\u9009\u62e9\u6a21\u5f0f\u7684\u53c2\u6570");
    model.param().set("d_pipe", "36[mm]");
    model.param().descr("d_pipe", "\u571f\u58e4\u4e2d\u7684\u7ba1\u5f84");
    model.param().set("flowrate_pipe", "1[l/s]");
    model.param().descr("flowrate_pipe", "\u7ba1\u5185\u6d41\u7387");
    model.param().set("heat_demand", "30[kW*h]");
    model.param().descr("heat_demand", "\u6bcf\u65e5\u70ed\u91cf\u9700\u6c42");
    model.param().set("power", "4[kW]");
    model.param().descr("power", "\u70ed\u6cf5\u529f\u7387");
    model.param().set("dt", "30[s]");
    model.param().descr("dt", "\u5e73\u6ed1\u7684\u52a0\u70ed\u5668\u72b6\u6001\u8fc7\u6e21\u533a");
    model.param().set("depth", "4[m]");
    model.param().descr("depth", "\u6362\u70ed\u5668\u7684\u6df1\u5ea6");
    model.param().set("Tz_depth", "0.5[K/m]");
    model.param().descr("Tz_depth", "\u6e29\u5ea6\u68af\u5ea6");
    model.param().set("month", "1");
    model.param().descr("month", "\u6708\u7d22\u5f15");
    model.param().set("humidity", "1");
    model.param().descr("humidity", "\u571f\u58e4\u6e7f\u5ea6");
    model.param().set("k_soil", "0.18[W/(m*K)]+(1.5-0.18)*humidity");
    model.param().descr("k_soil", "\u571f\u58e4\u5bfc\u70ed\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-depth");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7ba1");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("if1", "If");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("if1").set("condition", "pattern==1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("if1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").set("type", "sequence");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").set("sequence", "part1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("imp1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("elseif1").set("condition", "pattern==2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("elseif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp2").set("type", "sequence");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp2").set("sequence", "part2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("imp2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("else1", "Else");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("else1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp3", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp3").set("type", "sequence");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp3").set("sequence", "part3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.5, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 20, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.5, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 20, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-depth", 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 0.5);
    model.component("comp1").geom("geom1").feature("copy1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"15", "22", "depth+3[m]"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-(depth+3[m])"});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5730\u9762");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideEntities().create("hide1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 4);

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u5e74\u5ea6\u6e29\u5ea6\u66f2\u7ebf");
    model.func("int1").set("funcname", "T_z0");
    model.func("int1")
         .set("table", new String[][]{{"1/12", "-1.1"}, 
         {"2/12", "0.1"}, 
         {"3/12", "3"}, 
         {"4/12", "7.4"}, 
         {"5/12", "12.2"}, 
         {"6/12", "15.4"}, 
         {"7/12", "17"}, 
         {"8/12", "16.5"}, 
         {"9/12", "13.1"}, 
         {"10/12", "8.4"}, 
         {"11/12", "3.4"}, 
         {"12/12", "0.2"}});
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("argunit", "a", 0);
    model.func("int1").setIndex("fununit", "degC", 0);
    model.func("int1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u6708");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("xdataexpr", "t*12");
    model.result("pg1").feature("plot1").set("lowerbound", 1);
    model.result("pg1").feature("plot1").set("upperbound", 12);
    model.result("pg1").feature("plot1").set("xdatadescr", "\u6708");
    model.result("pg1").feature("plot1").set("linewidth", 3);
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").set("data", "int1_ds1");
    model.result("pg1").feature("lngr1").set("expr", "T_z0(root.t[a])[1/degC]");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "month");
    model.result("pg1").feature("lngr1").set("linecolor", "black");
    model.result("pg1").feature("lngr1").set("linewidth", 3);
    model.result("pg1").run();

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u6df1\u5ea6\u6e29\u5ea6\u68af\u5ea6");
    model.func("an1").set("funcname", "T0");
    model.func("an1").set("expr", "T_z0(month[a]/12)[1/degC]+Tz_depth[m/K]*(-z)");
    model.func("an1").set("args", "z");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "degC");
    model.func("an1").setIndex("plotargs", -10, 0, 1);
    model.func("an1").setIndex("plotargs", 0, 0, 2);
    model.func("an1").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\u68af\u5ea6");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u6df1\u5ea6 (m)");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("linewidth", 3);
    model.result("pg2").run();

    model.func().create("step1", "Step");
    model.func("step1").label("\u5e73\u6ed1 Heaviside \u51fd\u6570");
    model.func("step1").set("smooth", 1);

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
    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("geom1_csel1_edg");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u571f\u58e4");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_soil"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1742"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"1175"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0(z)");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(4);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_z0(t+month[a]/12)");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(3);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "-ht.k_iso*Tz_depth");
    model.component("comp1").physics("htp").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("htp").feature("ht1")
         .set("u", "flowrate_pipe*(1/10+heater_state_smoothed*9/10)/htp.A");
    model.component("comp1").physics("htp").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("htp").feature("pipe1").set("innerd", "d_pipe");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(11);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(7);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("heater_state_smoothed", "if(heater_state,1,step1((mod(t+dt/2,1[d])-dt)/dt))*if(t_stop>0,1-step1((t-(t_stop+dt/2))/dt),1)");
    model.component("comp1").variable("var1")
         .descr("heater_state_smoothed", "\u5e73\u6ed1\u7684\u52a0\u70ed\u5668\u72b6\u6001");
    model.component("comp1").variable("var1").set("T_out", "intop1(T2)");
    model.component("comp1").variable("var1").descr("T_out", "\u7ba1\u51fa\u53e3\u6c34\u6e29");
    model.component("comp1").variable("var1")
         .set("T_in", "nojac(T_out-power*heater_state_smoothed/(intop1(htp.rho)*intop1(htp.Cp)*flowrate_pipe))");
    model.component("comp1").variable("var1").descr("T_in", "\u7ba1\u5165\u53e3\u6e29\u5ea6");

    model.component("comp1").physics("htp").feature("temp1").set("Tin", "T_in");
    model.component("comp1").physics("htp").feature("init1").set("T2", "T0(z)");
    model.component("comp1").physics("htp").create("hofl1", "HeatOutflow", 0);
    model.component("comp1").physics("htp").feature("hofl1").selection().set(11);
    model.component("comp1").physics("htp").create("wht1", "WallHeatTransfer", 1);
    model.component("comp1").physics("htp").feature("wht1").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("htp").feature("wht1").create("intfilm1", "InternalFilmResistance", 1);
    model.component("comp1").physics("htp").feature("wht1").create("wall1", "WallLayer", 1);
    model.component("comp1").physics("htp").feature("wht1").feature("wall1").set("kChoice", "UserDefined");
    model.component("comp1").physics("htp").feature("wht1").feature("wall1").set("k", 400);
    model.component("comp1").physics("htp").feature("wht1").feature("wall1").set("deltawChoice", "UserDefined");
    model.component("comp1").physics("htp").feature("wht1").feature("wall1").set("item.deltaw", "3.25[mm]");

    model.component("comp1").multiphysics().create("pwhtc1", "PipeWallHeatTransfer", 1);

    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "heater_state", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").create("is1", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "heat_diff", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", "heat_demand-heat_prod", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", "heat_demand", 0, 0);
    model.component("comp1").physics("ev").create("expl1", "ExplicitEvent", -1);
    model.component("comp1").physics("ev").feature("expl1").set("start", "dt");
    model.component("comp1").physics("ev").feature("expl1").set("period", "24[h]");
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitName", "heater_state", 0, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 1, 0, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitName", "t_stop", 1, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 0, 1, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", "t", 1, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitName", "heat_prod", 2, 0);
    model.component("comp1").physics("ev").feature("expl1").setIndex("reInitValue", 0, 2, 0);
    model.component("comp1").physics("ev").create("impl1", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl1").set("condition", "heat_diff<0");
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitName", "heater_state", 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "heat_prod", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "power*heater_state-heat_prodt", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "energy");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "power");
    model.component("comp1").physics("ge").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("ge").feature("ge2").setIndex("name", "t_stop", 0, 0);
    model.component("comp1").physics("ge").feature("ge2").setIndex("equation", "heater_state-t_stopt", 0, 0);
    model.component("comp1").physics("ge").feature("ge2").set("DependentVariableQuantity", "time");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u51fa\u53e3\u6e29\u5ea6");
    model.component("comp1").probe("var1").set("expr", "T_out");
    model.component("comp1").probe("var1").set("descr", "\u7ba1\u51fa\u53e3\u6c34\u6e29");
    model.component("comp1").probe("var1").set("unit", "degC");
    model.component("comp1").probe("var1").set("table", "new");
    model.component("comp1").probe("var1").set("window", "new");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").label("\u4ea7\u70ed\u91cf");
    model.component("comp1").probe("var2").set("unit", "kWh");
    model.component("comp1").probe("var2").set("table", "new");
    model.component("comp1").probe("var2").set("window", "new");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").label("\u52a0\u70ed\u5668\u72b6\u6001");
    model.component("comp1").probe("var3").set("expr", "heater_state_smoothed");
    model.component("comp1").probe("var3").set("descr", "\u5e73\u6ed1\u7684\u52a0\u70ed\u5668\u72b6\u6001");
    model.component("comp1").probe("var3").set("table", "new");
    model.component("comp1").probe("var3").set("window", "new");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(7, 11);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.25);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size2").set("hmin", 0.02);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "pattern", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "pattern", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 3", 0);
    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,3[h],2)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "0.1[s]");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "30[min]");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", "1");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.batch("p1").run("compute");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6 (ht)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 17, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("colortable", "HeatCameraLight");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u6e29\u5ea6 (htp)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 17, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").feature().create("line1", "Line");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("expr", "T2");
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("radiusexpr", "0.5*htp.dh");
    model.result("pg7").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("line1").set("smooth", "internal");
    model.result("pg7").feature("line1").set("showsolutionparams", "on");
    model.result("pg7").feature("line1").set("data", "parent");
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").set("data", "dset2");
    model.result().numerical("gev4").set("expr", new String[]{"heat_prod", "t_stop"});
    model.result().numerical("gev4")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf heat_prod", "\u72b6\u6001\u53d8\u91cf t_stop"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"heat_prod", "t_stop"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf heat_prod", "\u72b6\u6001\u53d8\u91cf t_stop"});
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6e29\u5ea6 (pwhtc1)");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 17, 0);
    model.result("pg9").setIndex("looplevel", 3, 1);
    model.result("pg9").feature().create("line1", "Line");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("expr", "T2");
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("radiusexpr", "pwhtc1.radiusExt");
    model.result("pg9").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg9").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("line1").set("smooth", "internal");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("data", "parent");
    model.result("pg9").feature().create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("smooth", "internal");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("data", "parent");
    model.result("pg9").feature("slc1").set("inheritplot", "line1");
    model.result("pg6").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg6").run();
    model.result("pg6").feature("vol1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").active(false);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("inheritplot", "line1");
    model.result("pg9").run();
    model.result("pg9").feature("line1").set("tuberadiusscale", 5);
    model.result("pg9").run();
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg3").run();
    model.result("pg3").label("\u51fa\u53e3\u6e29\u5ea6");
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg3").run();
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg4").run();
    model.result("pg4").label("\u4ea7\u70ed\u91cf");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u4ea7\u70ed\u91cf (kWh)");
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg4").run();
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c4\u201d");
    model.result("pg5").run();
    model.result("pg5").label("\u52a0\u70ed\u5668\u72b6\u6001");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u52a0\u70ed\u5668\u72b6\u6001");
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c4\u201d");
    model.result("pg5").run();
    model.result("pg7").run();

    model.title("\u8f90\u5c04\u5730\u677f\u52a0\u70ed\u7cfb\u7edf\u7684\u5730\u70ed\u56de\u6536");

    model
         .description("\u5730\u70ed\u4f9b\u6696\u662f\u4e00\u79cd\u53ef\u7528\u4e8e\u5bf9\u9694\u70ed\u6548\u679c\u826f\u597d\u7684\u73b0\u4ee3\u623f\u5c4b\u8fdb\u884c\u4f9b\u6696\u7684\u9ad8\u6548\u8282\u80fd\u3001\u73af\u4fdd\u7684\u65b9\u6cd5\u3002\u4e3a\u964d\u4f4e\u4f9b\u6696\u6210\u672c\uff0c\u5c31\u9700\u8981\u7814\u7a76\u70ed\u6536\u96c6\u5668\u5728\u5730\u8868\u4e0b\u7684\u6392\u5217\u65b9\u5f0f\u3002\n\n\u6b64\u6a21\u578b\u4f7f\u7528\u201c\u7ba1\u9053\u6d41\u6a21\u5757\u201d\u6bd4\u8f83\u4e86\u6536\u96c6\u5668\u5d4c\u4e8e\u82b1\u56ed\u5730\u8868\u571f\u5c42\u4e2d\u7684\u4e09\u79cd\u4e0d\u540c\u6392\u5217\u65b9\u5f0f\u3002\u571f\u58e4\u5c42\u7684\u5178\u578b\u70ed\u5c5e\u6027\u901a\u8fc7\u5bfc\u5165\u6d4b\u91cf\u6570\u636e\u83b7\u53d6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("ground_heat_recovery.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
