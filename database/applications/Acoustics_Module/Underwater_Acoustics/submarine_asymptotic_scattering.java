/*
 * submarine_asymptotic_scattering.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class submarine_asymptotic_scattering {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Underwater_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("paas", "PressureAcousticsAsymptoticScattering", "geom1");
    model.component("comp1").physics("paas").create("so1", "ScatteringObject");
    model.component("comp1").physics("paas").feature("so1").selection().all();
    model.component("comp1").physics("paas").create("efc1", "ExteriorFieldCalculation");
    model.component("comp1").physics("paas").feature("efc1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/paas", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c0", "1480[m/s]", "\u6c34\u4e2d\u7684\u58f0\u901f");
    model.param().set("f0", "2[kHz]", "\u9891\u7387");
    model.param().set("depth", "100[m]", "\u6df1\u5ea6");
    model.param().set("phi", "30[deg]", "\u6e90\u4f4d\u7f6e\u89d2\u5ea6");
    model.param().set("lam0", "c0/f0", "\u6ce2\u957f");
    model.param().set("d_source", "1000[m]", "\u5230\u6e90\u7684\u8ddd\u79bb");
    model.param().set("p_ref", "1[Pa]", "\u53c2\u8003\u538b\u529b");
    model.param().set("k0", "2*pi/lam0", "\u6ce2\u6570");
    model.param().set("L", "62[m]", "\u6f5c\u8247\u957f\u5ea6");
    model.param().set("kL", "k0*L", "\u58f0\u5b66\u5c3a\u5ea6 k*L");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "submarine_asymptotic_scattering_alpha.txt");
    model.func("int1").importData();
    model.func("int1").set("funcname", "alpha");
    model.func("int1").set("interp", "cubicspline");
    model.func("int1").setIndex("fununit", 1, 0);
    model.func("int1").setIndex("argunit", "deg", 0);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "submarine_asymptotic_scattering.mphbin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input")
         .set("fin", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 69, 70, 71, 72, 73, 79, 80, 81, 82, 83);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input")
         .set("cmf1", 4, 5, 6, 7, 13, 14, 15, 16, 24, 25, 26, 27, 49, 50, 62, 63, 65, 66, 84, 85, 86, 87);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").run("cmf2");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("p_in", "aveop1(abs(paas.p_b))", "\u5165\u5c04\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("TS", "20*log10((abs(paas.p_s)/p_in)*d_source/1[m])", "\u76ee\u6807\u5f3a\u5ea6");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
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
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();

    model.component("comp1").physics("paas").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("paas").feature("epam1").set("FluidModel", "OceanAttenuation");
    model.component("comp1").physics("paas").feature("bpf1").set("PressureFieldType", "SphericalWave");
    model.component("comp1").physics("paas").feature("bpf1").set("PressureAmplitudeSpherical", "p_ref");
    model.component("comp1").physics("paas").feature("bpf1")
         .set("SourcePointSpherical", new String[]{"-d_source*cos(phi)+L/2", "-d_source*sin(phi)", "0"});
    model.component("comp1").physics("paas").feature("so1").feature("wall1").set("Type", "AbsorptionCoefficient");
    model.component("comp1").physics("paas").feature("so1").feature("wall1").set("alpha", "alpha(paas.theta)");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/4");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "50[mm]");
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 24, 25, 26, 27, 32, 33, 34, 35, 38, 39, 42, 43, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 62, 63, 64, 65, 66, 67, 68, 69);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().remaining();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("edges", "off");
    model.result("pg1").set("view", "new");
    model.result("pg1").create("rp1", "RadiationPattern");
    model.result("pg1").feature("rp1").set("expr", new String[]{"paas.Lp_s"});
    model.result("pg1").feature("rp1").set("thetadisc", 40);
    model.result("pg1").feature("rp1").set("phidisc", 60);
    model.result("pg1").feature("rp1").set("grid", "fine");
    model.result("pg1").feature("rp1").set("colortable", "Rainbow");
    model.result("pg1").feature("rp1").set("colorscalemode", "linear");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u5916\u573a\u58f0\u538b\u7ea7 (paas)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("rp1", "RadiationPattern");
    model.result("pg2").feature("rp1").set("data", "dset1");
    model.result("pg2").feature("rp1").set("expr", new String[]{"paas.p_s"});
    model.result("pg2").feature("rp1").set("thetadisc", 40);
    model.result("pg2").feature("rp1").set("phidisc", 60);
    model.result("pg2").feature("rp1").set("colortable", "Cividis");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u5916\u573a\u538b\u529b (paas)");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("expr", new String[]{"paas.Lp_s"});
    model.result("pg3").feature("rp1").set("legend", true);
    model.result("pg3").feature("rp1").set("phidisc", 180);
    model.result("pg3").label("\u5916\u573a\u58f0\u538b\u7ea7 xy \u5e73\u9762 (paas)");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("parmin1", -62.00000000000002);
    model.result().dataset("grid1").set("parmax1", 124.00000000000004);
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("parmin2", -10.500000000000014);
    model.result().dataset("grid1").set("parmax2", 10.500000000000014);
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin3", -14.500000000000014);
    model.result().dataset("grid1").set("parmax3", 18.500000000000014);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "grid1");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("colortable", "Wave");
    model.result("pg4").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("mslc1").set("expr", new String[]{"paas.p_t"});
    model.result("pg4").label("\u58f0\u538b (paas)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"1"});
    model.result("pg4").feature("line1").set("data", "dset1");
    model.result("pg4").feature("line1").set("titletype", "none");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").set("solutionparams", "parent");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"paas.p_t"});
    model.result("pg4").feature("surf1").set("data", "dset1");
    model.result("pg4").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg1")
         .label("100 m \u5904\u7684\u4e09\u7ef4\u6563\u5c04\u58f0\u538b\u7ea7\u8f90\u5c04\u65b9\u5411\u56fe");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").feature("rp1").set("thetadisc", 90);
    model.result("pg1").feature("rp1").set("phidisc", 180);
    model.result("pg1").feature("rp1").set("sphere", "manual");
    model.result("pg1").feature("rp1").set("center", new String[]{"L/2", "0", "0"});
    model.result("pg1").feature("rp1").set("radius", "100[m]");
    model.result("pg1").feature("rp1").set("grid", "finer");
    model.result("pg1").feature("rp1").create("tran1", "Transparency");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("data", "dset1");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").run();
    model.result("pg2").label("100 m \u5904\u7684\u5916\u573a\u538b\u529b");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").feature("rp1").set("thetadisc", 90);
    model.result("pg2").feature("rp1").set("phidisc", 180);
    model.result("pg2").feature("rp1").set("sphere", "manual");
    model.result("pg2").feature("rp1").set("center", new String[]{"L/2", "0", "0"});
    model.result("pg2").feature("rp1").set("radius", "100[m]");
    model.result("pg2").run();
    model.result("pg3")
         .label("xy \u5e73\u9762\u4e2d\u7684\u6563\u5c04\u58f0\u538b\u7ea7\uff08\u5728\u6e90\u8ddd\u79bb\u5904\uff09");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").feature("rp1").set("phidisc", 1800);
    model.result("pg3").feature("rp1").set("center3", new String[]{"L/2", "0", "0"});
    model.result("pg3").feature("rp1").set("radius", "d_source");
    model.result("pg3").feature("rp1").set("refdir", new int[]{-1, 0, 0});
    model.result("pg3").run();
    model.result().dataset("grid1").set("parmin1", -20);
    model.result().dataset("grid1").set("parmax1", 80);
    model.result().dataset("grid1").set("parmin2", -25);
    model.result().dataset("grid1").set("parmax2", 25);
    model.result().dataset("grid1").set("parmin3", -25);
    model.result().dataset("grid1").set("parmax3", 25);
    model.result().dataset("grid1").set("res1", 400);
    model.result().dataset("grid1").set("res2", 200);
    model.result().dataset("grid1").set("res3", 2);
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("ynumber", "0");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u53ef\u89c1\u6027");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "paas.bpf1.visibility");
    model.result("pg5").feature("surf1").set("descr", "\u53ef\u89c1\u6027");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u5165\u5c04\u89d2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "paas.bpf1.theta");
    model.result("pg6").feature("surf1").set("descr", "\u5165\u5c04\u89d2");
    model.result("pg6").feature("surf1").set("expr", "if(paas.bpf1.visibility,paas.bpf1.theta,NaN)");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u9762\u6cd5\u5411\u5438\u6536");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "if(paas.bpf1.visibility,alpha(paas.bpf1.theta),NaN)");
    model.result("pg7").run();
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("parmax1", "2*pi");
    model.result().dataset("pc1").set("exprx", "-d_source*cos(s)+L/2");
    model.result().dataset("pc1").set("expry", "-d_source*sin(s)");
    model.result().dataset("pc1").set("global", true);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "grid1");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").label("\u5f39\u9053\u76ee\u6807\u5f3a\u5ea6");
    model.result("pg8").set("data", "pc1");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("expr", "TS");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "s[rad]");
    model.result("pg8").feature("lngr1").set("xdatadescractive", true);
    model.result("pg8").feature("lngr1").set("xdatadescr", "\u63a5\u6536\u5668\u89d2");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6563\u5c04\u58f0\u538b\u7ea7");
    model.result("pg9").set("data", "cpl1");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "paas.Lp_s");
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("data", "dset1");
    model.result("pg9").feature("surf2").set("expr", "1");
    model.result("pg9").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg9").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg9").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("data", "dset1");
    model.result("pg9").feature("line1").set("expr", "1");
    model.result("pg9").feature("line1").set("coloring", "uniform");
    model.result("pg9").feature("line1").set("color", "black");
    model.result("pg9").create("arpt1", "ArrowPoint");
    model.result("pg9").feature("arpt1").set("data", "dset1");
    model.result("pg9").feature("arpt1").set("expr", new String[]{"10*cos(phi)", "", ""});
    model.result("pg9").feature("arpt1").setIndex("expr", "10*sin(phi)", 1);
    model.result("pg9").feature("arpt1").setIndex("expr", 0, 2);
    model.result("pg9").feature("arpt1").set("arrowbase", "head");
    model.result("pg9").feature("arpt1").set("scaleactive", true);
    model.result("pg9").feature("arpt1").create("sel1", "Selection");
    model.result("pg9").feature("arpt1").feature("sel1").selection().set(44);
    model.result("pg9").run();

    model.title("\u6f5c\u8247\u9ad8\u9891\u6e10\u8fd1\u6563\u5c04");

    model
         .description("\u6f5c\u8247\u7684\u4e3b\u8981\u9632\u5fa1\u80fd\u529b\u5728\u4e8e\u5176\u5728\u884c\u52a8\u671f\u95f4\u4fdd\u6301\u9690\u853d\u7684\u80fd\u529b\u3002\u7531\u4e8e\u6d77\u6c34\u5bf9\u65e0\u7ebf\u7535\u6ce2\u7684\u5438\u6536\u80fd\u529b\u5f88\u5f3a\uff0c\u56e0\u6b64\u58f0\u97f3\u5bfc\u822a\u6d4b\u8ddd\uff08\u58f0\u5450\uff09\u662f\u63a2\u6d4b\u6f5c\u8247\u7684\u4e3b\u8981\u65b9\u6cd5\u4e4b\u4e00\u3002\u6b64\u5916\uff0c\u58f0\u5450\u7cfb\u7edf\u8fd8\u7528\u4e8e\u6c34\u4e0b\u52d8\u63a2\u548c\u6e14\u4e1a\u3002\n\n\u8bbe\u8ba1\u4eba\u5458\u901a\u8fc7\u5206\u6790\u58f0\u6ce2\u7684\u53cd\u5c04\u65b9\u5f0f\uff0c\u6700\u5927\u7a0b\u5ea6\u5730\u51cf\u5c0f\u6f5c\u8247\u7684\u7b49\u6548\u53cd\u5c04\u9762\u79ef\u3002\u672c\u6559\u7a0b\u7814\u7a76 BeTTSi \u57fa\u51c6\u6f5c\u8247\u7684\u6563\u5c04\uff08\u57fa\u51c6\u76ee\u6807\u56de\u6ce2\u5f3a\u5ea6\u4eff\u771f\uff09\u3002\n\n\u6a21\u578b\u4e2d\u91c7\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u6e10\u8fd1\u6563\u5c04\u201d\u63a5\u53e3\u7684\u9ad8\u9891\u8fd1\u4f3c\u65b9\u6cd5\uff0c\u5f53\u6ce2\u957f\u8fdc\u5c0f\u4e8e\u6563\u5c04\u7269\u4f53\u65f6\uff0c\u5206\u6790\u901f\u5ea6\u5feb\uff0c\u5e76\u4e14\u5728\u9ad8\u9891\u4e0b\u53ef\u4ee5\u8fdb\u884c\u5f88\u597d\u7684\u8fd1\u4f3c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("submarine_asymptotic_scattering.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
