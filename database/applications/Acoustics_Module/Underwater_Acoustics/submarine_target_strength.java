/*
 * submarine_target_strength.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class submarine_target_strength {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Underwater_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/pabe", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "submarine_target_strength.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c0", "1480[m/s]", "\u6c34\u4e2d\u7684\u58f0\u901f");
    model.param().set("f_max", "1.5[kHz]", "\u6700\u5927\u9891\u7387");
    model.param().set("depth", "100[m]", "\u6df1\u5ea6");
    model.param().set("phi", "330[deg]", "\u5165\u5c04\u5e73\u9762\u6ce2\u7684\u89d2\u5ea6");
    model.param().set("lam0", "c0/f_max", "\u6700\u5c0f\u6ce2\u957f");
    model.param().set("d_source", "1000[m]", "\u5230\u6e90\u7684\u8ddd\u79bb");
    model.param().set("p_ref", "1[Pa]", "\u53c2\u8003\u538b\u529b");
    model.param().set("alpha_n", "0.05", "\u6cd5\u5411\u5165\u5c04\u5438\u58f0\u7cfb\u6570");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("TS", "20*log10((abs(pabe.p_s)/p_in)*d_source/1[m])");
    model.component("comp1").variable("var1").descr("TS", "\u76ee\u6807\u5f3a\u5ea6");
    model.component("comp1").variable("var1").set("p_in", "aveop1(abs(pabe.p_b))");
    model.component("comp1").variable("var1").descr("p_in", "\u5165\u5c04\u538b\u529b");

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
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();

    model.component("comp1").physics("pabe").selection().set();
    model.component("comp1").physics("pabe").selection().allVoids();
    model.component("comp1").physics("pabe").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("pabe").prop("Stabilization").set("StabilizedFormulation", true);
    model.component("comp1").physics("pabe").feature("bpam1").set("FluidModel", "OceanAttenuation");
    model.component("comp1").physics("pabe").feature("bpam1").set("minput_depth", "depth");
    model.component("comp1").physics("pabe").create("bpf1", "BackgroundPressureField", 3);
    model.component("comp1").physics("pabe").feature("bpf1").set("PressureFieldType", "SphericalWave");
    model.component("comp1").physics("pabe").feature("bpf1").set("PressureAmplitudeSpherical", "p_ref");
    model.component("comp1").physics("pabe").feature("bpf1")
         .set("SourcePointSpherical", new String[]{"-d_source*cos(phi)", "-d_source*sin(phi)", "0"});
    model.component("comp1").physics("pabe").create("imp1", "Impedance", 2);
    model.component("comp1").physics("pabe").feature("imp1").selection().all();
    model.component("comp1").physics("pabe").feature("imp1").set("ImpedanceModel", "AbsorptionCoefficient");
    model.component("comp1").physics("pabe").feature("imp1").set("alpha_n", "alpha_n");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 41, 42, 43, 44, 49, 50, 51, 52, 55, 56, 59, 60, 61, 64, 65, 66, 91, 92, 93, 94, 95, 96, 97, 98);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(67, 70, 73, 74, 75, 76, 102, 103);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(105, 107);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 65);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection()
         .set(194, 195, 197, 198, 200, 201, 203, 204, 205, 206, 207, 209, 210, 211, 213, 214);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(81, 83);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/4");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "50[mm]");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").feature("ftri1").set("method", "af");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f_max");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmin1", -20);
    model.result().dataset("grid1").set("parmax1", 80);
    model.result().dataset("grid1").set("parmin2", -25);
    model.result().dataset("grid1").set("parmax2", 25);
    model.result().dataset("grid1").set("parmin3", -0.1);
    model.result().dataset("grid1").set("parmax3", 0.1);
    model.result().dataset("grid1").set("res1", 500);
    model.result().dataset("grid1").set("res2", 250);
    model.result().dataset("grid1").set("res3", 3);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").set("data", "grid1");
    model.result().dataset("cpl2").set("quickplane", "xy");
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("parmax1", "2*pi");
    model.result().dataset("pc1").set("exprx", "-d_source*cos(s)");
    model.result().dataset("pc1").set("expry", "-d_source*sin(s)");
    model.result().dataset("pc1").set("global", true);
    model.result().dataset("pc1").set("res1", 10000);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u603b\u538b\uff0c\u8fb9\u754c");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("resolution", "extrafine");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").label("100 m \u5904\u7684\u8f90\u5c04\u65b9\u5411\u56fe");
    model.result("pg2").set("view", "new");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").feature("surf1").set("expr", "0");

    model.view("view2").set("showgrid", false);

    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "carbonforged");
    model.result("pg2").run();
    model.result("pg2").create("rp1", "RadiationPattern");
    model.result("pg2").feature("rp1").set("expr", "100[m]+pabe.Lp_s[m]");
    model.result("pg2").feature("rp1").set("descractive", true);
    model.result("pg2").feature("rp1").set("descr", "100[m] \u5904\u7684\u6563\u5c04\u58f0\u538b\u7ea7");
    model.result("pg2").feature("rp1").set("useradiusascolor", false);
    model.result("pg2").feature("rp1").set("colorexpr", "pabe.Lp_s");
    model.result("pg2").feature("rp1").set("rangecoloractive", true);
    model.result("pg2").feature("rp1").set("rangecolormax", 55);
    model.result("pg2").feature("rp1").set("thetadisc", 120);
    model.result("pg2").feature("rp1").set("phidisc", 1440);
    model.result("pg2").feature("rp1").set("anglerestr", "manual");
    model.result("pg2").feature("rp1").set("thetamin", 75);
    model.result("pg2").feature("rp1").set("thetarange", 30);
    model.result("pg2").feature("rp1").set("sphere", "manual");
    model.result("pg2").feature("rp1").set("center", new String[]{"28[m]", "0", "0"});
    model.result("pg2").feature("rp1").set("radius", "100[m]");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"cos(phi)", "sin(phi)", ""});
    model.result("pg2").feature("arws1").setIndex("expr", 0, 2);
    model.result("pg2").feature("arws1").set("titletype", "none");
    model.result("pg2").feature("arws1").set("placement", "uniformani");
    model.result("pg2").feature("arws1").set("arrowcount", 1);
    model.result("pg2").feature("arws1").set("weight", new double[]{0.1, 1, 1});
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", 50);
    model.result("pg2").feature("arws1").create("sel1", "Selection");
    model.result("pg2").feature("arws1").feature("sel1").selection().set(36);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("z = 0 \u65f6\u7684\u6563\u5c04\u538b\u529b");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "cpl2");
    model.result("pg3").feature("surf1").set("expr", "pabe.p_s");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").label("z = 0 \u65f6\u7684\u6563\u5c04\u58f0\u538b\u7ea7");
    model.result("pg4").feature("surf1").set("expr", "pabe.Lp_s");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").label("\u8f90\u5c04\u65b9\u5411\u56fe - \u6563\u5c04\u58f0\u538b\u7ea7");
    model.result("pg5").set("zeroangle", "left");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rp1").set("linewidth", "preference");
    model.result("pg5").feature("rp1").set("expr", "pabe.Lp_s");
    model.result("pg5").feature("rp1").set("phidisc", 5000);
    model.result("pg5").feature("rp1").set("radius", "d_source");
    model.result("pg5").feature("rp1").set("refdir", new int[]{-1, 0, 0});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u53cc\u57fa\u5730\u76ee\u6807\u5f3a\u5ea6");
    model.result("pg6").set("data", "pc1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "TS");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "s[rad]");
    model.result("pg6").feature("lngr1").set("xdataunit", "\u00b0");
    model.result("pg6").feature("lngr1").set("xdatadescractive", true);
    model.result("pg6").feature("lngr1").set("xdatadescr", "\u63a5\u6536\u5668\u89d2");
    model.result("pg6").run();

    model.title("\u6f5c\u8247\u76ee\u6807\u5f3a\u5ea6");

    model
         .description("\u6f5c\u8247\u7684\u4e3b\u8981\u9632\u5fa1\u80fd\u529b\u5728\u4e8e\u5176\u5728\u4f5c\u6218\u8fc7\u7a0b\u4e2d\u4fdd\u6301\u9690\u853d\u7684\u80fd\u529b\u3002\u7531\u4e8e\u65e0\u7ebf\u7535\u6ce2\u88ab\u6d77\u6c34\u5f3a\u70c8\u5438\u6536\uff0c\u56e0\u6b64\uff0c\u58f0\u97f3\u5bfc\u822a\u6d4b\u8ddd\uff08\u58f0\u5450\uff09\u662f\u63a2\u6d4b\u6f5c\u8247\u7684\u4e3b\u8981\u65b9\u6cd5\u4e4b\u4e00\u3002\u6b64\u5916\uff0c\u58f0\u5450\u7cfb\u7edf\u8fd8\u7528\u4e8e\u6c34\u4e0b\u52d8\u63a2\u548c\u6e14\u4e1a\u3002\n\n\u8bbe\u8ba1\u4eba\u5458\u901a\u8fc7\u5206\u6790\u58f0\u6ce2\u7684\u53cd\u5c04\u65b9\u5f0f\uff0c\u4ee5\u6700\u5927\u7a0b\u5ea6\u5730\u51cf\u5c0f\u6f5c\u8247\u7684\u7b49\u6548\u53cd\u5c04\u9762\u79ef\u3002\u76ee\u6807\u5f3a\u5ea6 (TS) \u662f\u4e00\u79cd\u6d4b\u91cf\u58f0\u5450\u76ee\u6807\u9762\u79ef\u7684\u5ea6\u91cf\u3002\u672c\u6559\u7a0b\u63d0\u4f9b\u4e86\u4e00\u79cd\u5206\u6790 BeTTSi \u57fa\u51c6\u6f5c\u8247\u7684 TS \u7684\u7b80\u5316\u65b9\u6cd5\uff08\u57fa\u51c6\u76ee\u6807\u56de\u6ce2\u5f3a\u5ea6\u4eff\u771f\uff09\u3002\n\n\u6b64\u6a21\u578b\u5177\u6709\u660e\u663e\u7684\u58f0\u5b66\u7279\u6027\uff0c\u56e0\u6b64\u91c7\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u8fb9\u754c\u5143\u201d\u63a5\u53e3 (BEM) \u4e2d\u7684\u7a33\u5b9a\u516c\u5f0f\uff1b\u4ece\u800c\u53ef\u4ee5\u786e\u4fdd\u5927\u6a21\u578b\uff08\u9ad8\u9891\u6216\u5927\u57df\uff09\u6536\u655b\uff0c\u4f46\u9700\u8981\u589e\u52a0\u4e00\u4e9b\u81ea\u7531\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("submarine_target_strength.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
