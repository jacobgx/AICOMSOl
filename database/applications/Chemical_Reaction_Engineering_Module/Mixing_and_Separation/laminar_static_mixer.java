/*
 * laminar_static_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:20 by COMSOL 6.3.0.290. */
public class laminar_static_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ra", "3[mm]", "\u7ba1\u534a\u5f84");
    model.param().set("u_av", "10[mm/s]", "\u5e73\u5747\u5165\u53e3\u6d41\u901f");
    model.param().set("c0", "5[mol/m^3]", "\u5165\u53e3\u6d53\u5ea6");
    model.param().set("D", "5e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570");

    model.func().create("step1", "Step");
    model.func("step1").set("to", 5);
    model.func("step1").set("smooth", "3e-4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"2.4*Ra", "Ra/8"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Ra/2", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("twist", 30, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", "Ra/2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -30);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("ext1", "rot1");
    model.component("comp1").geom("geom1").feature("copy2").set("displz", "Ra");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("copy2");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", -60);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("copy3", "Copy");
    model.component("comp1").geom("geom1").feature("copy3").selection("input").set("ext1", "rot1");
    model.component("comp1").geom("geom1").feature("copy3").set("displz", "2*Ra");
    model.component("comp1").geom("geom1").run("copy3");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").set("rot", -120);
    model.component("comp1").geom("geom1").feature("rot3").selection("input").set("copy3");
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").create("copy4", "Copy");
    model.component("comp1").geom("geom1").feature("copy4").selection("input").set("ext1", "rot1", "rot2", "rot3");
    model.component("comp1").geom("geom1").feature("copy4").set("displz", "3*Ra");
    model.component("comp1").geom("geom1").run("copy4");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("copy4");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("rot4", "Rotate");
    model.component("comp1").geom("geom1").feature("rot4").selection("input").set("mir1");
    model.component("comp1").geom("geom1").feature("rot4").set("rot", 90);
    model.component("comp1").geom("geom1").run("rot4");
    model.component("comp1").geom("geom1").create("copy5", "Copy");
    model.component("comp1").geom("geom1").feature("copy5").selection("input").set("ext1", "rot1", "rot2", "rot3");
    model.component("comp1").geom("geom1").feature("copy5").set("displz", "6*Ra");
    model.component("comp1").geom("geom1").run("copy5");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Ra");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "14*Ra");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-2*Ra"});
    model.component("comp1").geom("geom1").feature("cyl1").set("rot", 15);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("copy5", "ext1", "rot1", "rot2", "rot3", "rot4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("uni1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(20);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_av");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(23);
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 2);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "step1(x[1/mm])", 0);
    model.component("comp1").physics("tds").feature("in1").selection().set(20);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().set(23);

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", 0.65);
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", 0.35);
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").label("\u5c42\u6d41");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").feature("stat2").label("\u7a00\u7269\u8d28\u4f20\u9012");
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58);
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
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, \u6d41\u7ebf (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "c");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, \u8868\u9762 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6d53\u5ea6\uff0c\u5207\u9762");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("expr", "c");
    model.result("pg5").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickznumber", 8);
    model.result("pg5").feature("slc1").set("resolution", "finer");
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 8);
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.025, 0.1});
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("radiusexpr", "0.05");
    model.result("pg1").feature("str1").set("color", "yellow");
    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str1").set("tuberadiusscale", 2);
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", -2);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u622a\u9762\u6d53\u5ea6\u56fe");
    model.result("pg6").set("data", "cpl1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "c");
    model.result("pg6").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg6").feature("surf1").set("resolution", "finer");
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 2);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 6);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 10);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 14);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 18);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 22);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 26);
    model.result("pg6").run();
    model.result().dataset("cpl1").set("quickz", 30);
    model.result("pg6").run();

    model.title("\u5c42\u6d41\u9759\u6001\u6df7\u5408\u5668");

    model
         .description("\u5728\u5c42\u6d41\u9759\u6001\u6df7\u5408\u5668\u4e2d\uff0c\u6d41\u4f53\u6cf5\u5165\u542b\u56fa\u5b9a\u6321\u677f\u7684\u7ba1\u9053\u4e2d\u8fdb\u884c\u6df7\u5408\u3002\u7531\u4e8e\u8fd9\u79cd\u6d41\u6001\u4e0b\u7684\u538b\u529b\u635f\u5931\u5c0f\uff0c\u56e0\u6b64\u8fd9\u79cd\u6df7\u5408\u6280\u672f\u7279\u522b\u9002\u7528\u4e8e\u5c42\u6d41\u6df7\u5408\u3002\u672c\u4f8b\u7814\u7a76\u626d\u66f2\u53f6\u7247\u9759\u6001\u6df7\u5408\u5668\u4e2d\u7684\u6d41\u52a8\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("laminar_static_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
