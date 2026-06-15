/*
 * geothermal_heat_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:56 by COMSOL 6.3.0.290. */
public class geothermal_heat_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Applications");

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
    model.param().descr("pattern", "Parameter for selecting the pattern");
    model.param().set("d_pipe", "36[mm]");
    model.param().descr("d_pipe", "Pipe diameter in soil");
    model.param().set("flowrate_pipe", "1[l/s]");
    model.param().descr("flowrate_pipe", "Flow rate inside pipes");
    model.param().set("heat_demand", "30[kW*h]");
    model.param().descr("heat_demand", "Daily heat demand");
    model.param().set("power", "4[kW]");
    model.param().descr("power", "Heat pump power");
    model.param().set("dt", "30[s]");
    model.param().descr("dt", "Smoothed heater state transition zone");
    model.param().set("depth", "4[m]");
    model.param().descr("depth", "Depth of heat exchanger");
    model.param().set("Tz_depth", "0.5[K/m]");
    model.param().descr("Tz_depth", "Temperature gradient");
    model.param().set("month", "1");
    model.param().descr("month", "Month index");
    model.param().set("humidity", "1");
    model.param().descr("humidity", "Soil humidity");
    model.param().set("k_soil", "0.18[W/(m*K)]+(1.5-0.18)*humidity");
    model.param().descr("k_soil", "Soil thermal conductivity");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-depth");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Pipes");
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
    model.component("comp1").geom("geom1").selection("csel2").label("Ground");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideEntities().create("hide1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 4);

    model.func().create("int1", "Interpolation");
    model.func("int1").label("Yearly Temperature Profile");
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
    model.result("pg1").label("Temperature Profile");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Month");
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("xdataexpr", "t*12");
    model.result("pg1").feature("plot1").set("xdatadescr", "t (Month)");
    model.result("pg1").feature("plot1").set("lowerbound", 1);
    model.result("pg1").feature("plot1").set("upperbound", 12);
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
    model.func("an1").label("Depth Temperature Gradient");
    model.func("an1").set("funcname", "T0");
    model.func("an1").set("expr", "T_z0(month[a]/12)[1/degC]+Tz_depth[m/K]*(-z)");
    model.func("an1").set("args", "z");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "degC");
    model.func("an1").setIndex("plotargs", -10, 0, 1);
    model.func("an1").setIndex("plotargs", 0, 0, 2);
    model.func("an1").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("Temperature Gradient");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Depth (m)");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("linewidth", 3);
    model.result("pg2").run();

    model.func().create("step1", "Step");
    model.func("step1").label("Smoothed Heaviside Function");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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
    model.component("comp1").material("mat2").label("Soil");
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
    model.component("comp1").variable("var1").descr("heater_state_smoothed", "Smoothed heater state");
    model.component("comp1").variable("var1").set("T_out", "intop1(T2)");
    model.component("comp1").variable("var1").descr("T_out", "Water temperature at pipe outlet");
    model.component("comp1").variable("var1")
         .set("T_in", "nojac(T_out-power*heater_state_smoothed/(intop1(htp.rho)*intop1(htp.Cp)*flowrate_pipe))");
    model.component("comp1").variable("var1").descr("T_in", "Inlet temperature for pipes");

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
    model.component("comp1").probe("var1").label("Outlet Temperature");
    model.component("comp1").probe("var1").set("expr", "T_out");
    model.component("comp1").probe("var1").set("descr", "Water temperature at pipe outlet");
    model.component("comp1").probe("var1").set("unit", "degC");
    model.component("comp1").probe("var1").set("table", "new");
    model.component("comp1").probe("var1").set("window", "new");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").label("Heat Production");
    model.component("comp1").probe("var2").set("unit", "kWh");
    model.component("comp1").probe("var2").set("table", "new");
    model.component("comp1").probe("var2").set("window", "new");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").label("Heater State");
    model.component("comp1").probe("var3").set("expr", "heater_state_smoothed");
    model.component("comp1").probe("var3").set("descr", "Smoothed heater state");
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
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.batch("p1").run("compute");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Temperature (ht)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 17, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Temperature (htp)");
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

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("gev4").set("data", "dset2");
    model.result().numerical("gev4").set("expr", new String[]{"heat_prod", "t_stop"});
    model.result().numerical("gev4").set("descr", new String[]{"State variable heat_prod", "State variable t_stop"});
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"heat_prod", "t_stop"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"State variable heat_prod", "State variable t_stop"});
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("Temperature (pwhtc1)");
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
         .setIndex("quantityunits", new String[]{"temperature", "Temperature", "K", "K"}, 0);
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
    model.result("pg3").set("windowtitle", "Probe Plot 2");
    model.result("pg3").run();
    model.result("pg3").label("Outlet Temperature");
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "Probe Plot 2");
    model.result("pg3").run();
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "Probe Plot 3");
    model.result("pg4").run();
    model.result("pg4").label("Heat Production");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Heat Production (kWh)");
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "Probe Plot 3");
    model.result("pg4").run();
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "Probe Plot 4");
    model.result("pg5").run();
    model.result("pg5").label("Heater State");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Heater State");
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "Probe Plot 4");
    model.result("pg5").run();
    model.result("pg7").run();

    model.title("\u8f90\u5c04\u5730\u677f\u52a0\u70ed\u7cfb\u7edf\u7684\u5730\u70ed\u56de\u6536");

    model
         .description("\u5730\u70ed\u4f9b\u6696\u662f\u4e00\u79cd\u53ef\u7528\u4e8e\u5bf9\u9694\u70ed\u6548\u679c\u826f\u597d\u7684\u73b0\u4ee3\u623f\u5c4b\u8fdb\u884c\u4f9b\u6696\u7684\u9ad8\u6548\u8282\u80fd\u3001\u73af\u4fdd\u7684\u65b9\u6cd5\u3002\u4e3a\u964d\u4f4e\u4f9b\u6696\u6210\u672c\uff0c\u5c31\u9700\u8981\u7814\u7a76\u70ed\u6536\u96c6\u5668\u5728\u5730\u8868\u4e0b\u7684\u6392\u5217\u65b9\u5f0f\u3002\n\n\u6b64\u6a21\u578b\u4f7f\u7528\u201c\u7ba1\u9053\u6d41\u6a21\u5757\u201d\u6bd4\u8f83\u4e86\u6536\u96c6\u5668\u5d4c\u4e8e\u82b1\u56ed\u5730\u8868\u571f\u5c42\u4e2d\u7684\u4e09\u79cd\u4e0d\u540c\u6392\u5217\u65b9\u5f0f\u3002\u571f\u58e4\u5c42\u7684\u5178\u578b\u70ed\u5c5e\u6027\u901a\u8fc7\u5bfc\u5165\u6d4b\u91cf\u6570\u636e\u83b7\u53d6\u3002");

    model.label("ground_heat_recovery.mph");

    model.result("pg7").run();

    model.param().set("t_step", "3[h]");
    model.param().descr("t_step", "\u65f6\u6b65\u95f4\u9694");
    model.param().set("t_end", "2[d]");
    model.param().descr("t_end", "\u4eff\u771f\u7ed3\u675f\u65f6\u95f4");
    model.param().set("T_min", "-5[degC]");
    model.param().descr("T_min", "\u7ba1\u6d41\u4f53\u6700\u4f4e\u8bb8\u7528\u6e29\u5ea6");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").create("edg1", "Edge");
    model.component("comp1").mesh("mesh2").feature("edg1").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh2").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").selection().set(7, 11);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh2").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("hmax", 0.7);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh2").feature("edg1").feature("size2").set("hmin", 0.03);
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh().create("mesh3");
    model.component("comp1").mesh("mesh3").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh3").create("edg1", "Edge");
    model.component("comp1").mesh("mesh3").feature("edg1").selection().named("geom1_csel1_edg");
    model.component("comp1").mesh("mesh3").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").selection().set(7, 11);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh3").feature("edg1").create("size2", "Size");
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("hmax", 0.3);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh3").feature("edg1").feature("size2").set("hmin", 0.01);
    model.component("comp1").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh3").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh3").run();

    model.study("std1").feature("time").set("tlist", "range(0,t_step,t_end)");
    model.study("std1").feature("param").active(false);

    model.result().numerical().create("min1", "MinLine");
    model.result().numerical("min1").label("\u6700\u4f4e\u6e29\u5ea6");
    model.result().numerical("min1").selection().named("geom1_csel1_edg");
    model.result().numerical("min1").set("expr", new String[]{"T2"});
    model.result().numerical("min1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result().numerical("min1").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("min1").setIndex("unit", "degC", 0);
    model.result().numerical("min1").set("dataseries", "minimum");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u6700\u4f4e\u6e29\u5ea6");
    model.result().numerical("min1").set("table", "tbl4");
    model.result().numerical("min1").setResult();
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("\u65e5\u4f9b\u70ed\u9700\u6c42");
    model.result().numerical("gev5").setIndex("expr", "heat_demand", 0);
    model.result().numerical("gev5").setIndex("unit", "kW*h", 0);
    model.result().numerical("gev5").setIndex("descr", "\u65e5\u4f9b\u70ed\u9700\u6c42", 0);
    model.result().numerical("gev5").setIndex("unit", "", 1);
    model.result().numerical("gev5").remove("unit", 1);
    model.result().numerical("gev5").remove("descr", 1);
    model.result().numerical("gev5").remove("expr", new int[]{1});
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u65e5\u4f9b\u70ed\u9700\u6c42");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();
    model.result("pg9").run();
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg9").set("data", "dset1");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "dset1");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("data", "dset1");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").run();
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "Probe Plot 2");
    model.result("pg3").run();
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "Probe Plot 2");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("linecolor", "red");
    model.result("pg3").feature("tblp1").set("linewidth", 3);
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "Probe Plot 2");
    model.result("pg3").run();
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "Probe Plot 3");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("linecolor", "black");
    model.result("pg4").feature("tblp1").set("linewidth", 3);
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "Probe Plot 3");
    model.result("pg4").run();
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "Probe Plot 4");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("linecolor", "blue");
    model.result("pg5").feature("tblp1").set("linewidth", 3);
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "Probe Plot 4");
    model.result("pg5").run();

    model.title("\u5730\u6e90\u70ed\u6cf5\u70ed\u56de\u6536\u7cfb\u7edf");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u901a\u8fc7\u4f7f\u7528\u5e26\u6709\u9884\u5b9a\u4e49\u9009\u9879\u7684\u7ec4\u5408\u6846\u6765\u66f4\u6539\u8bbe\u8ba1\n\u2022 \u7528\u4e8e\u521b\u5efa\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u7f16\u8f91\u548c\u7ed8\u5236\u6bcf\u6708\u6570\u636e\u8f93\u5165\n\u2022 \u8bbe\u7f6e\u77ac\u6001\u4eff\u771f\u7684\u7ed3\u675f\u65f6\u95f4\u548c\u65f6\u6b65\u5927\u5c0f\n\u2022 \u53ef\u89c6\u5316\u77ac\u6001\u4eff\u771f\u7684\u521d\u59cb\u503c\n\u2022 \u5305\u542b\u4e00\u4e2a\u7b80\u5355\u7684\u63a7\u5236\u7cfb\u7edf\u6765\u7ba1\u7406\u6e29\u5ea6\n\n\u5730\u70ed\u4f9b\u6696\u662f\u4e00\u79cd\u7528\u4e8e\u5bf9\u9694\u70ed\u6548\u679c\u826f\u597d\u7684\u73b0\u4ee3\u623f\u5c4b\u8fdb\u884c\u4f9b\u6696\u7684\u9ad8\u6548\u3001\u8282\u80fd\u3001\u73af\u4fdd\u7684\u65b9\u6cd5\u3002\u6362\u70ed\u5668\u653e\u7f6e\u5728\u623f\u5c4b\u4e0b\u65b9\u8db3\u591f\u6df1\u7684\u5730\u4e0b\uff0c\u56e0\u6b64\u53ef\u4ee5\u5229\u7528\u5730\u4e0b\u7684\u70ed\u91cf\uff0c\u4f7f\u8fd9\u91cc\u7684\u6e29\u5ea6\u51e0\u4e4e\u5168\u5e74\u4e0d\u53d8\u3002\n\n\u8be5 App \u7814\u7a76\u5730\u57cb\u7ba1\u6362\u70ed\u5668\u7684\u4e0d\u540c\u7ba1\u9053\u914d\u7f6e\uff0c\u63d0\u4f9b\u4e86\u5730\u57cb\u7ba1\u6362\u70ed\u5668\u5728\u4e0d\u540c\u6280\u672f\u53c2\u6570\uff08\u6df1\u5ea6\u3001\u6a21\u5f0f\u3001\u7ba1\u9053\u914d\u7f6e\u548c\u91c7\u6696\u6761\u4ef6\uff09\u3001\u6e29\u5ea6\u6761\u4ef6\u3001\u571f\u58e4\u5bfc\u70ed\u7cfb\u6570\u548c\u6e29\u5ea6\u68af\u5ea6\u4e0b\u7684\u6027\u80fd\u76f8\u5173\u4fe1\u606f\u3002\n\n\u5982\u679c\u8fbe\u5230\u6bcf\u65e5\u70ed\u91cf\u9700\u6c42\uff0c\u4e5f\u53ef\u4ee5\u5173\u95ed\u52a0\u70ed\u5668\uff0c24\u00a0\u5c0f\u65f6\u540e\u518d\u6253\u5f00\u3002\u60a8\u53ef\u4ee5\u63a7\u5236\u7ba1\u9053\u51fa\u53e3\u7684\u6e29\u5ea6\uff0c\u5e76\u5c06\u5176\u4e0e\u6362\u70ed\u5668\u6280\u672f\u53c2\u6570\u4e2d\u8981\u6c42\u7684\u6700\u4f4e\u6e29\u5ea6\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.setExpectedComputationTime("10 \u5206\u949f");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///geothermal_heat_pump.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature("tp1").set("logo", "none");
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature("toc1").set("levels", "1");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u8f6f\u4ef6\u5c5e\u6027");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature().create("func1", "Functions");
    model.result().report("rpt1").feature("sec2").feature("func1").set("includesettings", false);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u6a21\u578b");
    model.result().report("rpt1").feature("sec3").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec3").feature("geom1").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec3").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature().create("mesh2", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh2").set("noderef", "mesh2");
    model.result().report("rpt1").feature("sec3").feature("mesh2").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh2").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh2").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh2").active(false);
    model.result().report("rpt1").feature("sec3").feature().create("mesh3", "Mesh");
    model.result().report("rpt1").feature("sec3").feature("mesh3").set("noderef", "mesh3");
    model.result().report("rpt1").feature("sec3").feature("mesh3").set("includestats", true);
    model.result().report("rpt1").feature("sec3").feature("mesh3").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh3").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh3").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec3").feature("mesh3").active(false);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg1").label("\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("noderef", "pg9");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg1").set("customcaption", "\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg2").label("\u51fa\u53e3\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg2").set("customcaption", "\u51fa\u53e3\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").label("\u6700\u4f4e\u6e29\u5ea6");
    model.result().report("rpt1").feature("sec4").feature("mtbl1").set("noderef", "tbl4");
    model.result().report("rpt1").feature("sec4").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg3").label("\u4ea7\u70ed\u91cf");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg3").set("customcaption", "\u4ea7\u70ed\u91cf");
    model.result().report("rpt1").feature("sec4").feature().create("pg4", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("pg4").label("\u52a0\u70ed\u5668\u72b6\u6001");
    model.result().report("rpt1").feature("sec4").feature("pg4").set("noderef", "pg5");
    model.result().report("rpt1").feature("sec4").feature("pg4").set("caption", "custom");
    model.result().report("rpt1").feature("sec4").feature("pg4")
         .set("customcaption", "\u52a0\u70ed\u5668\u72b6\u6001");
    model.result("pg7").run();

    model.title("\u5730\u6e90\u70ed\u6cf5\u70ed\u56de\u6536\u7cfb\u7edf");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u901a\u8fc7\u4f7f\u7528\u5e26\u6709\u9884\u5b9a\u4e49\u9009\u9879\u7684\u7ec4\u5408\u6846\u6765\u66f4\u6539\u8bbe\u8ba1\n\u2022 \u7528\u4e8e\u521b\u5efa\u4e0d\u540c\u7f51\u683c\u5927\u5c0f\u7684\u9009\u9879\n\u2022 \u7f16\u8f91\u548c\u7ed8\u5236\u6bcf\u6708\u6570\u636e\u8f93\u5165\n\u2022 \u8bbe\u7f6e\u77ac\u6001\u4eff\u771f\u7684\u7ed3\u675f\u65f6\u95f4\u548c\u65f6\u6b65\u5927\u5c0f\n\u2022 \u53ef\u89c6\u5316\u77ac\u6001\u4eff\u771f\u7684\u521d\u59cb\u503c\n\u2022 \u5305\u542b\u4e00\u4e2a\u7b80\u5355\u7684\u63a7\u5236\u7cfb\u7edf\u6765\u7ba1\u7406\u6e29\u5ea6\n\n\u5730\u70ed\u4f9b\u6696\u662f\u4e00\u79cd\u7528\u4e8e\u5bf9\u9694\u70ed\u6548\u679c\u826f\u597d\u7684\u73b0\u4ee3\u623f\u5c4b\u8fdb\u884c\u4f9b\u6696\u7684\u9ad8\u6548\u3001\u8282\u80fd\u3001\u73af\u4fdd\u7684\u65b9\u6cd5\u3002\u6362\u70ed\u5668\u653e\u7f6e\u5728\u623f\u5c4b\u4e0b\u65b9\u8db3\u591f\u6df1\u7684\u5730\u4e0b\uff0c\u56e0\u6b64\u53ef\u4ee5\u5229\u7528\u5730\u4e0b\u7684\u70ed\u91cf\uff0c\u4f7f\u8fd9\u91cc\u7684\u6e29\u5ea6\u51e0\u4e4e\u5168\u5e74\u4e0d\u53d8\u3002\n\n\u8be5 App \u7814\u7a76\u5730\u57cb\u7ba1\u6362\u70ed\u5668\u7684\u4e0d\u540c\u7ba1\u9053\u914d\u7f6e\uff0c\u63d0\u4f9b\u4e86\u5730\u57cb\u7ba1\u6362\u70ed\u5668\u5728\u4e0d\u540c\u6280\u672f\u53c2\u6570\uff08\u6df1\u5ea6\u3001\u6a21\u5f0f\u3001\u7ba1\u9053\u914d\u7f6e\u548c\u91c7\u6696\u6761\u4ef6\uff09\u3001\u6e29\u5ea6\u6761\u4ef6\u3001\u571f\u58e4\u5bfc\u70ed\u7cfb\u6570\u548c\u6e29\u5ea6\u68af\u5ea6\u4e0b\u7684\u6027\u80fd\u76f8\u5173\u4fe1\u606f\u3002\n\n\u5982\u679c\u8fbe\u5230\u6bcf\u65e5\u70ed\u91cf\u9700\u6c42\uff0c\u4e5f\u53ef\u4ee5\u5173\u95ed\u52a0\u70ed\u5668\uff0c24\u00a0\u5c0f\u65f6\u540e\u518d\u6253\u5f00\u3002\u60a8\u53ef\u4ee5\u63a7\u5236\u7ba1\u9053\u51fa\u53e3\u7684\u6e29\u5ea6\uff0c\u5e76\u5c06\u5176\u4e0e\u6362\u70ed\u5668\u6280\u672f\u53c2\u6570\u4e2d\u8981\u6c42\u7684\u6700\u4f4e\u6e29\u5ea6\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("geothermal_heat_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
