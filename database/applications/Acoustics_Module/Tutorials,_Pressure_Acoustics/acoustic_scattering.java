/*
 * acoustic_scattering.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class acoustic_scattering {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ri", "1[m]", "\u5185\u7403\u4f53\u534a\u5f84");
    model.param().set("Rext", "10[m]", "\u5916\u573a\u8ba1\u7b97\u8ddd\u79bb");
    model.param().set("f0", "1000[Hz]", "\u9a71\u52a8\u9891\u7387");
    model.param().set("c0", "1500[m/s]", "\u58f0\u901f");
    model.param().set("lambda_min", "c0/f0", "\u6ce2\u957f");
    model.param().set("A", "0.5[m]", "\u692d\u7403\u7684 x \u534a\u8f74");
    model.param().set("B", "0.25[m]", "\u692d\u7403\u7684 y \u534a\u8f74");
    model.param().set("C", "0.25[m]", "\u692d\u7403\u7684 z \u534a\u8f74");

    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new String[]{"A", "B", "C"});
    model.component("comp1").geom("geom1").run("elp1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "Ri");
    model.component("comp1").geom("geom1").run("sph1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("elp1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 2, 3, 4, 9, 10, 13, 16);
    model.component("comp1").selection("sel1").label("\u5916\u573a");

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

    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 3);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(1);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new int[]{1, 0, 1});
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("sel1");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp1").physics("acpr").feature("pmb1").selection().named("sel1");

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 6);

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(2);
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").hideEntities().clear();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u603b\u573a");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6563\u5c04\u573a");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("expr", "acpr.p_s");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u80cc\u666f\u573a");
    model.result("pg3").run();
    model.result("pg3").feature("mslc1").set("expr", "acpr.p_b");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\u7ea7");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "acpr.Lp_s");
    model.result("pg4").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg4").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("\u5916\u573a\u538b\u529b\uff0cxy \u5e73\u9762");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rp1").set("linewidth", "preference");
    model.result("pg5").feature("rp1").set("expr", "acpr.efc1.pext");
    model.result("pg5").feature("rp1").set("radius", "Rext");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5916\u573a\u58f0\u538b\u7ea7\uff0cxy \u5e73\u9762");
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("expr", "acpr.efc1.Lp_pext");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result("pg7").run();
    model.result("pg7").label("\u5916\u573a\u538b\u529b\uff0cyz \u5e73\u9762");
    model.result("pg7").run();
    model.result("pg7").feature("rp1").set("refdir", new int[]{0, 1, 0});
    model.result("pg7").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg7").run();
    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");
    model.result("pg8").run();
    model.result("pg8").label("\u5916\u573a\u58f0\u538b\u7ea7\uff0cyz \u5e73\u9762");
    model.result("pg8").run();
    model.result("pg8").feature("rp1").set("refdir", new int[]{0, 1, 0});
    model.result("pg8").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").set("edges", "off");
    model.result("pg9").set("view", "new");
    model.result("pg9").create("rp1", "RadiationPattern");
    model.result("pg9").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg9").feature("rp1").set("thetadisc", 40);
    model.result("pg9").feature("rp1").set("phidisc", 60);
    model.result("pg9").feature("rp1").set("grid", "fine");
    model.result("pg9").feature("rp1").set("colortable", "Rainbow");
    model.result("pg9").feature("rp1").set("colorscalemode", "linear");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg9").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg9").run();
    model.result("pg9").set("view", "new");
    model.result("pg9").run();
    model.result("pg9").feature("rp1").set("sphere", "manual");
    model.result("pg9").feature("rp1").set("radius", "Rext");
    model.result("pg9").run();
    model.result("pg9").feature("rp1").set("expr", "acpr.efc1.Lp_pext-66");
    model.result("pg9").feature("rp1").set("descractive", true);
    model.result("pg9").feature("rp1").set("descr", "\u5916\u573a\u58f0\u538b\u7ea7");
    model.result("pg9").feature("rp1").set("useradiusascolor", false);
    model.result("pg9").run();
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmin1", -10);
    model.result().dataset("grid1").set("parmax1", 10);
    model.result().dataset("grid1").set("parmin2", -10);
    model.result().dataset("grid1").set("parmax2", 10);
    model.result().dataset("grid1").set("parmax3", 0);
    model.result().dataset("grid1").set("res1", 300);
    model.result().dataset("grid1").set("res2", 300);
    model.result().dataset("grid1").set("res3", 2);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u5916\u90e8\u58f0\u538b\u573a");
    model.result("pg10").set("view", "view3");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("data", "grid1");
    model.result("pg10").feature("surf1").set("expr", "acpr.efc1.pext");
    model.result("pg10").feature("surf1").create("filt1", "Filter");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("filt1").set("expr", "sqrt(x^2+y^2+z^2)>Ri*1.05");
    model.result("pg10").run();
    model.result("pg10").run();

    model.title("\u692d\u7403\u4f53\u58f0\u6563\u5c04");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u80cc\u666f\u573a\u548c\u5916\u573a\u8ba1\u7b97\u6765\u6c42\u89e3\u4e09\u7ef4\u58f0\u6563\u5c04\u95ee\u9898\uff0c\u8be5 App \u53ef\u7528\u4e8e\u9884\u6d4b\u4ece\u56fa\u4f53\u692d\u7403\u53d1\u51fa\u7684\u5165\u5c04\u5e73\u9762\u6ce2\u6563\u5c04\u3002\u901a\u8fc7\u5916\u573a\u8ba1\u7b97\u7279\u5f81\u53ef\u4ee5\u5728\u8ba1\u7b97\u57df\u5916\u786e\u5b9a\u4ea7\u751f\u7684\u6563\u5c04\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("acoustic_scattering.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
