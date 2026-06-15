/*
 * iron_sphere_bfield_01_static.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class iron_sphere_bfield_01_static {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("B0", "1[mT]");
    model.param().descr("B0", "\u80cc\u666f\u78c1\u573a");
    model.param().set("r0", "0.125[mm]");
    model.param().descr("r0", "\u94c1\u7403\u534a\u5f84");
    model.param().set("sigma_air", "0[S/m]");
    model.param().descr("sigma_air", "\u7a7a\u6c14\u7684\u7a33\u5b9a\u7535\u5bfc\u7387");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "3*r0");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "r0", 0);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "r0", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u94c1\u7403");
    model.component("comp1").selection("sel1").set(9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u94c1\u7403\u8868\u9762");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").add(17, 18, 19, 20, 31, 32, 39, 42);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u65e0\u9650\u5143\u57df");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 10, 11, 14, 17);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u5206\u6790\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"sel3"});

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("sel3");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(2, 6, 11, 13);

    model.component("comp1").physics("mf").prop("BackgroundField").set("SolveFor", "ReducedField");
    model.component("comp1").physics("mf").prop("BackgroundField").set("Ab", new String[]{"0", "0", "B0*y"});
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().named("sel1");

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Iron");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_air"});
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel2");

    model.title("\u78c1\u573a\u4e2d\u7684\u94c1\u7403 - \u5165\u95e8");

    model
         .description("\u4e00\u4e2a\u94c1\u7403\u66b4\u9732\u5728\u4e00\u7cfb\u5217\u7a7a\u95f4\u5747\u5300\u7684\u80cc\u666f\u78c1\u573a\u4e2d\u3002\u8fd9\u4e2a\u5165\u95e8\u6a21\u578b\u6982\u8ff0\u4e86\u672c\u7cfb\u5217\u4e2d\u6db5\u76d6\u7684\u56db\u79cd\u4e0d\u540c\u7684\u5efa\u6a21\u573a\u666f\u53ca\u5176\u76f8\u5e94\u7684\u7ed3\u679c\uff0c\u5176\u4e2d\u8fd8\u63d0\u4f9b\u4e86\u4e3a\u540e\u7eed\u6559\u5b66\u6a21\u578b\u8bbe\u7f6e\u51e0\u4f55\u548c\u6750\u6599\u7684\u5efa\u6a21\u64cd\u4f5c\u8bf4\u660e\u3002\u540e\u7eed\u6559\u7a0b\u5747\u4ee5\u8fd9\u4e2a\u5165\u95e8\u6a21\u578b\u4f5c\u4e3a\u8d77\u70b9\uff0c\u53ef\u4ee5\u6309\u4efb\u610f\u987a\u5e8f\u5b8c\u6210\u3002");

    model.label("iron_sphere_bfield_00_introduction.mph");

    model.param().set("mu_r", "4000");
    model.param().descr("mu_r", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94c1\u7403");
    model.param().set("B_analytic", "((3*mu_r)/(mu_r+2))*B0");

    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"mu_r"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").active(false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "B0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "T", 0);
    model.study("std1").feature("stat").setIndex("pname", "B0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "T", 0);
    model.study("std1").feature("stat").setIndex("pname", "mu_r", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "2 4 10 20 40 100 200 400 1000 4000", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().named("com1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").label("\u89e3\u6790\u89e3");
    model.result("pg2").feature("ptgr1").set("expr", "B_analytic");
    model.result("pg2").feature("ptgr1").set("unit", "mT");
    model.result("pg2").feature("ptgr1").set("linestyle", "dotted");
    model.result("pg2").feature("ptgr1").set("linecolor", "gray");
    model.result("pg2").feature("ptgr1").set("data", "cpt1");
    model.result("pg2").feature("ptgr1").set("titletype", "label");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr1").set("autosolution", false);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").feature("ptgr1").set("autounit", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").label("\u77e2\u52bf (mf)");
    model.result("pg2").feature("ptgr2").set("data", "cpt1");
    model.result("pg2").feature("ptgr2").set("unit", "mT");
    model.result("pg2").feature("ptgr2").set("linestyle", "none");
    model.result("pg2").feature("ptgr2").set("linemarker", "point");
    model.result("pg2").feature("ptgr2").set("linecolor", "blue");
    model.result("pg2").feature("ptgr2").set("titletype", "label");
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr2").set("autosolution", false);
    model.result("pg2").feature("ptgr2").set("autopoint", false);
    model.result("pg2").feature("ptgr2").set("autounit", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6\u6bd4\u8f83");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("data", "cpt1");
    model.result("pg3").feature("ptgr1").set("expr", "(mf.normB-B_analytic)/B_analytic");
    model.result("pg3").feature("ptgr1").set("titletype", "label");
    model.result("pg3").feature("ptgr1").set("linestyle", "none");
    model.result("pg3").feature("ptgr1").set("linemarker", "point");
    model.result("pg3").feature("ptgr1").set("linecolor", "blue");
    model.result("pg3").feature("ptgr1")
         .label("\u77e2\u91cf (mf) \u4e0e\u89e3\u6790\u89e3\u7684\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg3").feature("ptgr1").set("autopoint", false);
    model.result("pg3").feature("ptgr1").set("autosolution", false);
    model.result("pg3").run();

    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/mfnc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);

    model.component("comp1").physics("mfnc").prop("BackgroundField").set("SolveFor", "ReducedField");
    model.component("comp1").physics("mfnc").prop("BackgroundField")
         .set("Hb", new String[]{"B0/mu0_const", "0", "0"});
    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 3);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection().named("sel1");
    model.component("comp1").physics("mfnc").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("mfnc").feature("zsp1").selection().set(8);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").autoMeshSize(4);
    model.component("comp1").mesh("mesh2").contribute("physics/mf", false);
    model.component("comp1").mesh("mesh2").run();

    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mfnc", true);
    model.study("std2").feature("stat").label("\u6807\u91cf\u52bf\u516c\u5f0f");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "B0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "T", 0);
    model.study("std2").feature("stat").setIndex("pname", "B0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "T", 0);
    model.study("std2").feature("stat").setIndex("pname", "mu_r", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "2 4 10 20 40 100 200 400 1000 4000", 0);
    model.study("std2").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6 (mfnc)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 10, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "mfnc.normB");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "mfnc.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "mfnc.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "mfnc.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"mfnc.Bx", "mfnc.By", "mfnc.Bz"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "mfnc.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "mfnc.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "mfnc.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "mfnc.normB");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u78c1\u6807\u52bf (mfnc)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 10, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "Vm");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "mfnc.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "mfnc.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "mfnc.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Dipole");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"mfnc.Hx", "mfnc.Hy", "mfnc.Hz"});
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "mfnc.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "mfnc.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "mfnc.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "Vm");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("com1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().dataset("cpt2").set("pointx", 0);
    model.result().dataset("cpt2").set("pointy", 0);
    model.result().dataset("cpt2").set("pointz", 0);
    model.result("pg2").run();
    model.result("pg2").create("ptgr3", "PointGraph");
    model.result("pg2").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr3").set("linewidth", "preference");
    model.result("pg2").feature("ptgr3").label("\u6807\u91cf\u52bf (mfnc)");
    model.result("pg2").feature("ptgr3").set("data", "cpt2");
    model.result("pg2").feature("ptgr3").set("expr", "mfnc.normB");
    model.result("pg2").feature("ptgr3").set("unit", "mT");
    model.result("pg2").feature("ptgr3").set("titletype", "label");
    model.result("pg2").feature("ptgr3").set("linestyle", "none");
    model.result("pg2").feature("ptgr3").set("linemarker", "diamond");
    model.result("pg2").feature("ptgr3").set("linecolor", "red");
    model.result("pg2").feature("ptgr3").set("legend", true);
    model.result("pg2").feature("ptgr3").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr3").set("autopoint", false);
    model.result("pg2").feature("ptgr3").set("autosolution", false);
    model.result("pg2").feature("ptgr3").set("autounit", true);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").create("ptgr2", "PointGraph");
    model.result("pg3").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr2").set("linewidth", "preference");
    model.result("pg3").feature("ptgr2")
         .label("\u6807\u91cf (mfnc) \u4e0e\u89e3\u6790\u89e3\u7684\u76f8\u5bf9\u8bef\u5dee");
    model.result("pg3").feature("ptgr2").set("data", "cpt2");
    model.result("pg3").feature("ptgr2").set("expr", "(mfnc.normB-B_analytic)/B_analytic");
    model.result("pg3").feature("ptgr2").set("linestyle", "none");
    model.result("pg3").feature("ptgr2").set("linemarker", "diamond");
    model.result("pg3").feature("ptgr2").set("linecolor", "red");
    model.result("pg3").feature("ptgr2").set("legend", true);
    model.result("pg3").feature("ptgr2").set("autoplotlabel", true);
    model.result("pg3").feature("ptgr2").set("autopoint", false);
    model.result("pg3").feature("ptgr2").set("autosolution", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").createTable("tbl1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").createTable("tbl2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr1").createTable("tbl3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").createTable("tbl4");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").createTable("tbl5");
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsmaxmin", false);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("unit", "mT");
    model.result("pg6").feature("mslc1").set("xcoord", "");
    model.result("pg6").feature("mslc1").set("ycoord", "");
    model.result("pg6").feature("mslc1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").feature("tran1").set("transparency", 0.15);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("strmsl1").set("xcoord", "");
    model.result("pg6").feature("strmsl1").set("ycoord", "");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "mf.normJ");
    model.result("pg6").feature("slc1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg6").feature("slc1").set("quickxnumber", 1);
    model.result("pg6").feature("slc1").set("colortable", "Thermal");
    model.result("pg6").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").create("sel1", "Selection");
    model.result("pg6").feature("slc1").feature("sel1").selection().named("sel1");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "material.boundary");
    model.result("pg6").feature("surf1").set("descr", "\u6750\u6599\u8bbe\u7f6e");
    model.result("pg6").feature("surf1").set("titletype", "none");
    model.result("pg6").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("surf1").feature("mtrl1").set("family", "ironscratched");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("filt1", "Filter");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("filt1").set("expr", "x>0");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg6").run();

    model.title("\u78c1\u573a\u4e2d\u7684\u94c1\u7403 - \u9759\u78c1\u573a");

    model
         .description("\u4e00\u4e2a\u94c1\u7403\u66b4\u9732\u5728\u7a7a\u95f4\u5747\u5300\u7684\u9759\u6001\u80cc\u666f\u78c1\u573a\u4e2d\u3002\u672c\u4f8b\u5728\u4e00\u5b9a\u7684\u94c1\u7403\u76f8\u5bf9\u78c1\u5bfc\u7387\u8303\u56f4\u5185\uff0c\u7814\u7a76\u7403\u5185\u7684\u78c1\u573a\u6270\u52a8\u548c\u573a\u5f3a\u53d8\u5316\u60c5\u51b5\uff1b\u8ba1\u7b97\u7403\u5185\u7684\u573a\u5f3a\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u5176\u4e2d\u4f7f\u7528\u4e24\u79cd\u516c\u5f0f\u6765\u6c42\u89e3\u8be5\u95ee\u9898\uff0c\u5e76\u8ba8\u8bba\u4e86\u5b83\u4eec\u4e4b\u95f4\u7684\u5dee\u5f02\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("iron_sphere_bfield_01_static.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
