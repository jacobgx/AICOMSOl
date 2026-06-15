/*
 * lightning_surge_tower.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:50 by COMSOL 6.3.0.290. */
public class lightning_surge_tower {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Discharge-Induced_Effects");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.param().set("ds", "1[m]");
    model.param().descr("ds", "\u7ec6\u5bfc\u7ebf\u57df\u7f51\u683c\u5927\u5c0f");
    model.param().set("r_sw", "1[cm]");
    model.param().descr("r_sw", "\u5c4f\u853d\u7ebf\u7684\u534a\u5f84");
    model.param().set("epsilonr_sw", "log(1/0.23)/log(ds/r_sw)");
    model.param().descr("epsilonr_sw", "\u4fee\u6b63\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("mur_sw", "1/epsilonr_sw");
    model.param().descr("mur_sw", "\u4fee\u6b63\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.param().set("r_pc", "10[cm]");
    model.param().descr("r_pc", "\u76f8\u5bfc\u7ebf\u7684\u534a\u5f84");
    model.param().set("epsilonr_pc", "log(1/0.23)/log(ds/r_pc)");
    model.param().descr("epsilonr_pc", "\u4fee\u6b63\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("mur_pc", "1/epsilonr_pc");
    model.param().descr("mur_pc", "\u4fee\u6b63\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.param().set("sigma_soil", "0.01[S/m]");
    model.param().descr("sigma_soil", "\u571f\u58e4\u7535\u5bfc\u7387");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Random_Surfaces\\random_flat_surface.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "lightning_surge_tower_geom.mphbin");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5854");
    model.component("comp1").geom("geom1").feature("imp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", 1.5);
    model.component("comp1").geom("geom1").run("sca1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new double[]{-0.6, -0.6, 0});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("sca2").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("sca2").set("isotropic", 1200);
    model.component("comp1").geom("geom1").run("sca2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("sca2");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", 35);
    model.component("comp1").geom("geom1").feature("mov1").set("disply", 10);
    model.component("comp1").geom("geom1").feature("mov1").set("displz", -40);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmin", -200);
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", 200);
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"-18", "s", "s^2/3e3+23"});
    model.component("comp1").geom("geom1").feature("pc1").set("pos", new int[]{0, 200, 0});
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .set("pos", new String[]{"-18", "23+40/3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").setIndex("layer", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").set("wp1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").set("pc1", 1);
    model.component("comp1").geom("geom1").feature("swe1").set("keep", false);
    model.component("comp1").geom("geom1").run("swe1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("swe1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 9);
    model.component("comp1").geom("geom1").feature("copy1").set("displz", 14);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("swe1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{18, 0, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u76f8\u5bfc\u7ebf");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{2, 1, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{18, 0, 0});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u5c4f\u853d\u7ebf");
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"2", "40/3-12"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new int[]{-10, 48});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("arr1", "arr2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, -1, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{810, 810, 800});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, 0, 100});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("mov1");
    model.component("comp1").geom("geom1").run("par1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u96f7\u51fb\u901a\u9053");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"200.00", "200.00", "400.00"}, 
         {"204.36", "198.77", "403.58"}, 
         {"195.64", "198.27", "378.77"}, 
         {"186.60", "186.83", "368.45"}, 
         {"188.49", "179.11", "361.68"}, 
         {"179.44", "169.63", "349.01"}, 
         {"168.75", "166.84", "334.08"}, 
         {"159.19", "163.28", "332.75"}, 
         {"159.25", "155.73", "315.99"}, 
         {"151.92", "150.47", "302.50"}, 
         {"148.57", "137.71", "289.98"}, 
         {"144.95", "137.93", "277.75"}, 
         {"126.06", "123.02", "267.92"}, 
         {"120.67", "126.44", "258.18"}, 
         {"116.34", "112.63", "246.78"}, 
         {"116.09", "102.80", "235.63"}, 
         {"101.96", "100.66", "223.59"}, 
         {"93.61", "95.80", "204.77"}, 
         {"87.76", "87.25", "199.95"}, 
         {"90.02", "83.88", "191.93"}, 
         {"80.14", "71.94", "183.55"}, 
         {"82.20", "69.60", "156.43"}, 
         {"75.00", "54.12", "144.75"}, 
         {"59.41", "56.19", "147.25"}, 
         {"52.35", "42.97", "130.47"}, 
         {"46.95", "36.55", "111.05"}, 
         {"49.22", "29.94", "101.77"}, 
         {"36.66", "23.73", "98.05"}, 
         {"24.25", "23.25", "74.62"}, 
         {"9.00", "0.00", "40/3+37+1"}});
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pol1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6240\u6709\u5bfc\u7ebf");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(67, 75, 1365, 1375, 2624, 2654, 4127, 4137, 5205, 5213);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u571f\u58e4");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_soil"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5c4f\u853d\u7ebf\u57df");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"epsilonr_sw"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"mur_sw"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u76f8\u5bfc\u7ebf\u57df");
    model.component("comp1").material("mat4").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"epsilonr_pc"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"mur_pc"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("temw").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection().set(1, 2, 3, 4, 5, 7, 8, 9, 2308);
    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("temw").create("constr1", "PointwiseConstraint", 1);
    model.component("comp1").physics("temw").feature("constr1").selection().named("sel1");
    model.component("comp1").physics("temw").feature("constr1").set("constraintExpression", "0-tAx");
    model.component("comp1").physics("temw").create("constr2", "PointwiseConstraint", 1);
    model.component("comp1").physics("temw").feature("constr2").selection().named("sel1");
    model.component("comp1").physics("temw").feature("constr2").set("constraintExpression", "0-tAy");
    model.component("comp1").physics("temw").create("constr3", "PointwiseConstraint", 1);
    model.component("comp1").physics("temw").feature("constr3").selection().named("sel1");
    model.component("comp1").physics("temw").feature("constr3").set("constraintExpression", "0-tAz");
    model.component("comp1").physics("temw").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("temw").feature("edc1").selection().named("geom1_pol1_edg");
    model.component("comp1").physics("temw").feature("edc1").set("CurrentPulseType", "Lightning");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseAmplitude", "10[kA]");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseRiseTimeConstant", "1[us]");
    model.component("comp1").physics("temw").feature("edc1").set("LightningPulseDecayTimeConstant", "60[us]");
    model.component("comp1").physics("temw").feature("edc1").set("v_p", "c_const/3");
    model.component("comp1").physics("temw").feature("edc1").set("reverseDirection", true);

    model.func().create("Pulse1", "Analytic");
    model.func("Pulse1").set("args", "t");
    model.func("Pulse1").setIndex("argunit", "s", 0);
    model.func("Pulse1").set("fununit", "A");
    model.func("Pulse1").set("expr", "1.0070493454179255*10[kA]*(-t/1[us])^10*exp(-t/60[us])/(1+(-t/1[us])^10) ");
    model.func("Pulse1").setIndex("plotargs", "5.9999999999999995E-5", 0, 2);

    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "t");
    model.result().dataset("grid1").set("parmax1", "5.9999999999999995E-5");
    model.result().dataset("grid1").set("res1", 10000);
    model.result().dataset("grid1").set("distribution", "mixed");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "grid1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8fb9\u7535\u6d41\u8109\u51b2\u56fe");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Pulse current (A)");
    model.result("pg1").set("windowtitle", "\u56fe\u5f62");
    model.result("pg1").set("window", "window1");
    model.result("pg1").create("fun1", "Function");
    model.result("pg1").feature("fun1").set("linewidth", "preference");
    model.result("pg1").feature("fun1").set("expr", "Pulse1(t[1/m][s])");
    model.result("pg1").feature("fun1").set("xdataexpr", "t");
    model.result("pg1").feature("fun1").set("xdataunit", "");
    model.result("pg1").feature("fun1").set("xdatadescractive", true);
    model.result("pg1").feature("fun1").set("xdatadescr", "Time (s)");
    model.result("pg1").feature("fun1").set("upperbound", "5.9999999999999995E-5");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().dataset().remove("grid1");

    model.func().remove("Pulse1");

    model.component("comp1").physics("temw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec3").selection()
         .set(543, 545, 548, 671, 674, 1706, 1708, 1711, 1856, 1859);

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);

    model.component("comp1").physics("temw").prop("MeshControl").set("PhysicsControlledMeshMaximumElementSize", 200);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 100);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 15);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_csel2_dom");
    model.component("comp1").mesh("mesh1").feature().move("swe1", 1);
    model.component("comp1").mesh("mesh1").feature().duplicate("swe2", "swe1");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_csel3_dom");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(7);
    model.component("comp1").view("view1").hideEntities("hide1").add(4);
    model.component("comp1").view("view1").hideEntities("hide1").add(5);

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,0.05,6)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.02[us]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("solvertype", "none");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solvertype", "none");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -18, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "40/3+23", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", -18, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 43, 1, 2);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").setIndex("genpoints", 0, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 0, 1, 0);
    model.result().dataset().duplicate("cln3", "cln2");
    model.result().dataset("cln3").setIndex("genpoints", 18, 0, 0);
    model.result().dataset("cln3").setIndex("genpoints", 18, 1, 0);
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").set("data", "cln1");
    model.result().numerical("int1").setIndex("expr", "temw.Ez", 0);
    model.result().numerical("int1").setIndex("unit", "kV", 0);
    model.result().numerical("int1").setIndex("descr", "A \u76f8\u611f\u5e94\u7535\u538b", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").set("data", "cln2");
    model.result().numerical("int2").setIndex("descr", "B \u76f8\u611f\u5e94\u7535\u538b", 0);
    model.result().numerical("int2").set("table", "tbl1");
    model.result().numerical("int2").appendResult();
    model.result().numerical().duplicate("int3", "int2");
    model.result().numerical("int3").set("data", "cln3");
    model.result().numerical("int3").setIndex("descr", "C \u76f8\u611f\u5e94\u7535\u538b", 0);
    model.result().numerical("int3").set("table", "tbl1");
    model.result().numerical("int3").appendResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u611f\u5e94\u7535\u538b (kV)");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "temw.Ez");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature("mslc1").set("rangecoloractive", true);
    model.result("pg1").feature("mslc1").set("rangecolormin", -10000);
    model.result("pg1").feature("mslc1").set("rangecolormax", 10000);
    model.result("pg1").feature("mslc1").set("colortable", "ThermalWaveDark");
    model.result("pg1").feature("mslc1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").feature("tran1").set("transparency", 0.85);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "z/40");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("colortable", "ThermalLightClassic");
    model.result("pg1").feature("line1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("line1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("tran1").set("transparency", 0.85);
    model.result("pg1").run();
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().named("geom1_pol1_edg");
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colortable", "GaiaLight");
    model.result("pg1").feature("vol1").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("vol1").feature("mtrl1").set("family", "soil");
    model.result("pg1").feature("vol1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg1").run();
    model.result("pg1").create("vol2", "Volume");
    model.result("pg1").feature("vol2").set("colortable", "Dipole");
    model.result("pg1").feature("vol2").set("colorscalemode", "logarithmic");
    model.result("pg1").feature("vol2").create("sel1", "Selection");
    model.result("pg1").feature("vol2").feature("sel1").selection().named("geom1_csel2_dom");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("tran1").set("transparency", 0.75);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol3", "vol2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol3").feature("sel1").selection().named("geom1_csel3_dom");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg1").run();
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").set("expr", "1");
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line2").set("tuberadiusscale", 0.3);
    model.result("pg1").feature("line2").create("sel1", "Selection");
    model.result("pg1").feature("line2").feature("sel1").selection().named("geom1_pol1_edg");
    model.result("pg1").run();
    model.result("pg1").feature("line2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("line2").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("line2").feature("mtrl1").set("family", "gold");
    model.result("pg1").run();
    model.result("pg1").create("line3", "Line");
    model.result("pg1").feature("line3").set("expr", "1");
    model.result("pg1").feature("line3").set("linetype", "tube");
    model.result("pg1").feature("line3").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line3").set("tuberadiusscale", 0.3);
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "black");
    model.result("pg1").feature("line3").create("sel1", "Selection");
    model.result("pg1").feature("line3").feature("sel1").selection().named("sel1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").run();

    model.component("comp1").view("view1").camera().set("zoomanglefull", 50);
    model.component("comp1").view("view1").camera().setIndex("position", -130, 0);
    model.component("comp1").view("view1").camera().setIndex("position", -100, 1);
    model.component("comp1").view("view1").camera().set("position", new int[]{-130, -100, 35});
    model.component("comp1").view("view1").camera().setIndex("target", 5000, 0);
    model.component("comp1").view("view1").camera().setIndex("target", 4500, 1);
    model.component("comp1").view("view1").camera().set("target", new int[]{5000, 4500, 200});
    model.component("comp1").view("view1").camera().setIndex("up", 0, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0, 1);
    model.component("comp1").view("view1").camera().set("up", new int[]{0, 0, 1});
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", -20, 0);
    model.component("comp1").view("view1").camera().setIndex("rotationpoint", -18, 1);
    model.component("comp1").view("view1").camera().set("rotationpoint", new int[]{-20, -18, 34});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", -0.09, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new String[]{"-0.09", "0.00"});

    model.result("pg1").run();

    model.title("\u8f93\u7535\u5854\u4e0a\u7684\u96f7\u51fb\u6d6a\u6d8c");

    model
         .description("\u672c\u6a21\u578b\u65e8\u5728\u901a\u8fc7\u6a21\u62df\u96f7\u51fb\u6d6a\u6d8c\u5bf9\u9ad8\u538b\u8f93\u7535\u5854\u7684\u5f71\u54cd\u6765\u89e3\u51b3\u76f8\u5173\u95ee\u9898\u3002\u901a\u8fc7\u68c0\u67e5\u96f7\u51fb\u5728\u4e09\u76f8\u5bfc\u4f53\u4e0a\u4ea7\u751f\u7684\u611f\u5e94\u7535\u538b\uff0c\u53ef\u4ee5\u8bc4\u4f30\u6f5c\u5728\u7684\u8fc7\u7535\u538b\uff0c\u5e76\u5e2e\u52a9\u8bbe\u8ba1\u6709\u6548\u7684\u4fdd\u62a4\u63aa\u65bd\u3002\u6a21\u578b\u4e2d\u7279\u522b\u5173\u6ce8\u4e86\u643a\u5e26 10\u00a0kA \u7535\u6d41\u7684\u96f7\u7535\u51fb\u4e2d\u5854\u4e0a\u4e00\u6839\u5c4f\u853d\u7ebf\u7684\u60c5\u51b5\uff0c\u5e76\u901a\u8fc7\u7cbe\u786e\u7684\u4eff\u771f\u6280\u672f\u6765\u8ba1\u7b97\u4e09\u76f8\u5bfc\u4f53\u4e0a\u7684\u611f\u5e94\u7535\u538b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("lightning_surge_tower.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
