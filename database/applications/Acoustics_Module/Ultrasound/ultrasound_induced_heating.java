/*
 * ultrasound_induced_heating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class ultrasound_induced_heating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("ht", "BioHeat", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", true);

    model.param().set("d0", "3.8[nm]");
    model.param().descr("d0", "\u6362\u80fd\u5668\u7684\u4f4d\u79fb\u5927\u5c0f");
    model.param().set("z_tissue", "24.6[mm]");
    model.param().descr("z_tissue", "\u751f\u4f53\u7ec4\u7ec7\u7684\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("T0", "293.7[K]");
    model.param().descr("T0", "\u6e29\u5ea6\u521d\u59cb\u503c");
    model.param().set("alpha_water", "0.025[1/m]");
    model.param().descr("alpha_water", "\u6c34\u7684\u5438\u58f0\u7cfb\u6570");
    model.param().set("alpha_tissue", "8.55[1/m]");
    model.param().descr("alpha_tissue", "\u751f\u4f53\u7ec4\u7ec7\u7684\u5438\u58f0\u7cfb\u6570");
    model.param().set("f0", "1[MHz]");
    model.param().descr("f0", "\u6e90\u9891\u7387");

    model.func().create("step1", "Step");
    model.func("step1").set("from", 1);
    model.func("step1").set("to", 0);
    model.func("step1").set("smooth", 0.005);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 62.64);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0, 62.64});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{35, 10.69});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{48.6, 75.5});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "z_tissue"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"48.6", "z_tissue-10.69"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 10.69});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").feature("r3").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r3").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 15);
    model.component("comp1").geom("geom1").feature("c2").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new double[]{0, 0.80336});
    model.component("comp1").geom("geom1").feature("c2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c2", "int1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 10);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new double[]{7.5, 1.5});
    model.component("comp1").geom("geom1").feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("e1").set("pos", new String[]{"0", "z_tissue+35"});
    model.component("comp1").geom("geom1").feature("e1").set("rot", 270);
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(7, 9, 10);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp1").coordSystem().duplicate("pml2", "pml1");
    model.component("comp1").coordSystem("pml2").selection().set(8);
    model.component("comp1").coordSystem().create("pml3", "PML");
    model.component("comp1").coordSystem("pml3").selection().set(1);
    model.component("comp1").coordSystem("pml3").set("stretchingType", "rational");

    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "Attenuation");
    model.component("comp1").physics("acpr").feature("fpam1").set("alpha1", "alpha_water");
    model.component("comp1").physics("acpr").create("fpam2", "FrequencyPressureAcousticsModel", 2);
    model.component("comp1").physics("acpr").feature("fpam2").selection().set(5, 6, 7, 9, 10);
    model.component("comp1").physics("acpr").feature("fpam2").set("FluidModel", "Attenuation");
    model.component("comp1").physics("acpr").feature("fpam2").set("alpha1", "alpha_tissue");
    model.component("comp1").physics("acpr").create("ndisp1", "NormalDisplacement", 1);
    model.component("comp1").physics("acpr").feature("ndisp1").selection().set(32);
    model.component("comp1").physics("acpr").feature("ndisp1").set("ndisp", "d0");
    model.component("comp1").physics("ht").selection().set(5, 6);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(5, 6);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "acpr.Q_pw*step1(t[1/s]-1)");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(9, 14, 20);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u751f\u4f53\u7ec4\u7ec7");
    model.component("comp1").material("mat2").selection().set(5, 6, 7, 9, 10);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1044"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"1568"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"0.59"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"3710"});

    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("fpam2").set("minput_temperature", "T0");

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c 1 - \u58f0\u5b66");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 3, 4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1568[m/s]/f0/30");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1483[m/s]/f0/5");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 7, 8, 9, 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 22, 23);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c 2 - \u751f\u7269\u4f20\u70ed");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("ftri1").selection().set(5, 6);
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").selection().set(6);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "0.18[mm]");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", 5);
    model.component("comp1").mesh("mesh2").feature("size").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").label("\u7814\u7a76 1 - \u58f0\u5b66");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.2,5)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").label("\u7814\u7a76 2 - \u751f\u7269\u4f20\u70ed");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("maxorder", 5);
    model.sol("sol2").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol2").feature("t1").set("maxstepbdf", 0.02);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 26, 0);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "r \u5750\u6807 (mm)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "z \u5750\u6807 (mm)");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 3, 4, 5, 6);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u5f3a\u573a");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "r \u5750\u6807 (mm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "z \u5750\u6807 (mm)");
    model.result("pg6").set("edges", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "acpr.I_mag");
    model.result("pg6").feature("surf1").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6cbf z \u8f74\u8f74\u5411\u7684\u58f0\u538b\u5e45\u503c");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().set(3, 4, 6, 8, 10, 11, 12);
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "z");
    model.result("pg7").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 59.6, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 5, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 59.6, 1, 1);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6cbf\u7126\u5e73\u9762\u5f84\u5411\u8f74\u7684\u58f0\u538b\u5e45\u503c");
    model.result("pg8").set("data", "cln1");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "r \u5750\u6807 (mm)");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "r");
    model.result("pg8").feature("lngr1").set("resolution", "finer");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("xdataexpr", "-r");
    model.result("pg8").feature("lngr2").set("linecolor", "blue");
    model.result("pg8").run();
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("t = 1 s \u65f6\u7684\u6e29\u5347");
    model.result("pg9").set("data", "mir2");
    model.result("pg9").setIndex("looplevel", 6, 0);
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "t = 1 s \u65f6\u7684\u6e29\u5347");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "r \u5750\u6807 (mm)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "z \u5750\u6807 (mm)");
    model.result("pg9").set("edges", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "T-T0");
    model.result("pg9").feature("surf1").set("colortable", "Thermal");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("t = 1 s \u65f6\u7684\u7b49\u6e29\u7ebf");
    model.result("pg10").set("data", "mir2");
    model.result("pg10").setIndex("looplevel", 6, 0);
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "t = 1 s \u65f6\u7684\u6e29\u5347\u4e91\u56fe");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "r \u5750\u6807 (mm)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "z \u5750\u6807 (mm)");
    model.result("pg10").set("edges", false);
    model.result("pg10").create("con1", "Contour");
    model.result("pg10").feature("con1").set("expr", "T-T0");
    model.result("pg10").feature("con1").set("number", 50);
    model.result("pg10").feature("con1").set("colortable", "Thermal");
    model.result("pg10").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 59.6);
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").set("pointx", 0.5);
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11")
         .label("\u7126\u70b9\u5904\u4ee5\u53ca\u79bb\u7126\u70b9 0.5 mm \u5904\u6e29\u5347\u4e0e\u65f6\u95f4\u7684\u5173\u7cfb");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u6e29\u5347 (K)");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").set("data", "cpt1");
    model.result("pg11").feature("ptgr1").set("solutionparams", "parent");
    model.result("pg11").feature("ptgr1").set("expr", "T-T0");
    model.result("pg11").feature("ptgr1").set("linecolor", "red");
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u58f0\u5b66\u7126\u70b9\u5904", 0);
    model.result("pg11").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg11").run();
    model.result("pg11").feature("ptgr2").set("data", "cpt2");
    model.result("pg11").feature("ptgr2").set("linecolor", "blue");
    model.result("pg11").feature("ptgr2").setIndex("legends", "\u79bb\u58f0\u5b66\u7126\u70b9 0.5 mm \u5904", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg11").run();
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u5f52\u4e00\u5316\u6e29\u5ea6\u4e0e\u58f0\u5f3a\u66f2\u7ebf");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u79bb\u7126\u70b9\u7684\u5f84\u5411\u8ddd\u79bb (mm)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u5f52\u4e00\u5316\u7684\u6e29\u5347\u548c\u58f0\u5f3a");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("data", "cln2");
    model.result("pg12").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg12").feature("lngr1").setIndex("looplevel", new int[]{6}, 0);
    model.result("pg12").feature("lngr1").set("expr", "(T-T0)/1.018");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "r");
    model.result("pg12").feature("lngr1").set("linecolor", "red");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "t = 1 s \u65f6\u7684\u6e29\u5ea6\u66f2\u7ebf", 0);
    model.result("pg12").feature().duplicate("lngr2", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").set("xdataexpr", "-r");
    model.result("pg12").feature("lngr2").set("legend", false);
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("lngr3", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr3").setIndex("looplevel", new int[]{11}, 0);
    model.result("pg12").feature("lngr3").set("expr", "(T-T0)/0.5751");
    model.result("pg12").feature("lngr3").set("linecolor", "magenta");
    model.result("pg12").feature("lngr3").setIndex("legends", "t = 2 s \u65f6\u7684\u6e29\u5ea6\u66f2\u7ebf", 0);
    model.result("pg12").feature().duplicate("lngr4", "lngr3");
    model.result("pg12").run();
    model.result("pg12").feature("lngr4").set("xdataexpr", "-r");
    model.result("pg12").feature("lngr4").set("legend", false);
    model.result("pg12").run();
    model.result("pg12").feature().duplicate("lngr5", "lngr1");
    model.result("pg12").run();
    model.result("pg12").feature("lngr5").set("data", "cln1");
    model.result("pg12").feature("lngr5").set("expr", "acpr.I_mag");
    model.result("pg12").feature("lngr5").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg12").feature("lngr5").set("expr", "acpr.I_mag/3.376e5");
    model.result("pg12").feature("lngr5").set("linecolor", "blue");
    model.result("pg12").feature("lngr5").set("linestyle", "dotted");
    model.result("pg12").feature("lngr5").setIndex("legends", "\u58f0\u5f3a\u66f2\u7ebf", 0);
    model.result("pg12").feature().duplicate("lngr6", "lngr5");
    model.result("pg12").run();
    model.result("pg12").feature("lngr6").set("xdataexpr", "-r");
    model.result("pg12").feature("lngr6").set("legend", false);
    model.result("pg12").run();

    model.title("\u7ec4\u7ec7\u4f53\u6a21\u4e2d\u7684\u805a\u7126\u8d85\u58f0\u53d1\u70ed");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u805a\u7126\u8d85\u58f0\u5f15\u8d77\u7684\u4eba\u4f53\u7ec4\u7ec7\u53d1\u70ed\u73b0\u8c61\u5efa\u6a21\u3002\u9996\u5148\u5bf9\u6c34\u548c\u7ec4\u7ec7\u4e2d\u7684\u9759\u6001\u58f0\u573a\u5efa\u6a21\uff0c\u5f97\u5230\u7ec4\u7ec7\u4e2d\u7684\u58f0\u5b66\u5f3a\u5ea6\u5206\u5e03\u3002\u8ba1\u7b97\u5f97\u5230\u5438\u6536\u7684\u58f0\u80fd\uff0c\u7528\u4f5c\u7ec4\u7ec7\u57df\u4e2d\u7684\u201c\u751f\u7269\u4f20\u70ed\u201d\u63a5\u53e3\u4e2d\u7684\u70ed\u6e90\uff0c\u901a\u8fc7\u77ac\u6001\u7814\u7a76\u6a21\u62df\u7ec4\u7ec7\u7f6e\u4e8e\u8d85\u58f0\u573a 1\u00a0s \u5185\u7684\u52a0\u70ed\u548c\u51b7\u5374\u8fc7\u7a0b\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u58f0\u5b66\u6a21\u5757\u201d\u548c\u201c\u4f20\u70ed\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ultrasound_induced_heating.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
