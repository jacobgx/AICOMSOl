/*
 * bessel_panel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class bessel_panel {

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
    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/pabe", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.4, 2.4, 2.4});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -1, 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -1, 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("arr1", "Array");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 5, 5});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0, 0.5, 0.5});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "pnt");
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("S", "0.01[m^3/s]");
    model.param().descr("S", "\u6d41\u52a8\u6e90");
    model.param().set("f0", "200[Hz]");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("c0", "343[m/s]");
    model.param().descr("c0", "\u58f0\u901f");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Qs", "-S*i");
    model.component("comp1").variable("var1").descr("Qs", "\u6e90\u5f3a\u5ea6");
    model.component("comp1").variable("var1").selection().geom("geom1", 0);
    model.component("comp1").variable("var1").selection().set(5, 9, 25, 29);
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("Qs", "2*S*i", "\u6e90\u5f3a\u5ea6");
    model.component("comp1").variable("var2").selection().geom("geom1", 0);
    model.component("comp1").variable("var2").selection().set(6, 10, 14, 26);
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("Qs", "-2*S*i", "\u6e90\u5f3a\u5ea6");
    model.component("comp1").variable("var3").selection().geom("geom1", 0);
    model.component("comp1").variable("var3").selection().set(7, 8, 15, 19, 20, 24, 27, 28);
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").set("Qs", "-4*S*i", "\u6e90\u5f3a\u5ea6");
    model.component("comp1").variable("var4").selection().geom("geom1", 0);
    model.component("comp1").variable("var4").selection().set(11, 17, 18, 22, 23);
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").set("Qs", "4*S*i", "\u6e90\u5f3a\u5ea6");
    model.component("comp1").variable("var5").selection().geom("geom1", 0);
    model.component("comp1").variable("var5").selection().set(12, 13, 16, 21);

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
    model.component("comp1").material("mat1").selection().all();
    model.component("comp1").material("mat1").selection().allVoids();

    model.component("comp1").physics("acpr").create("mps1", "FrequencyMonopolePointSource", 0);
    model.component("comp1").physics("acpr").feature("mps1").selection().named("geom1_arr1_pnt");
    model.component("comp1").physics("acpr").feature("mps1").set("Qs", "Qs");
    model.component("comp1").physics("pabe").selection().set();
    model.component("comp1").physics("pabe").selection().allVoids();

    model.component("comp1").multiphysics().create("aab1", "AcousticAcousticBoundary", 2);
    model.component("comp1").multiphysics("aab1").selection().all();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().named("geom1_arr1_pnt");

    model.component("comp1").variable().create("var6");
    model.component("comp1").variable("var6").set("r", "sqrt((dest(x)-x)^2+(dest(y)-y)^2 +(dest(z)-z)^2)");
    model.component("comp1").variable("var6").descr("r", "\u6e90\u70b9\u4e0e\u89c2\u5bdf\u70b9\u7684\u95f4\u8ddd");
    model.component("comp1").variable("var6")
         .set("p_an", "intop1(Qs*acpr.omega*acpr.rho*exp(-i*acpr.k*r)/(4*pi*r))");
    model.component("comp1").variable("var6").descr("p_an", "\u89c2\u5bdf\u70b9\u7684\u89e3\u6790\u538b\u529b");

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 6);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58f0\u538b\uff0c\u8fb9\u754c (pabe)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin1", -0.6000000000000001);
    model.result().dataset("grid1").set("parmax1", 0.6000000000000001);
    model.result().dataset("grid1").set("parmin2", -3.5999999999999996);
    model.result().dataset("grid1").set("parmax2", 3.5999999999999996);
    model.result().dataset("grid1").set("parmin3", -3.5999999999999996);
    model.result().dataset("grid1").set("parmax3", 3.5999999999999996);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "grid1");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("colortable", "Wave");
    model.result("pg5").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("mslc1").set("expr", new String[]{"pabe.p_t"});
    model.result("pg5").label("\u58f0\u538b (pabe)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"1"});
    model.result("pg5").feature("line1").set("data", "dset1");
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").feature("line1").set("solutionparams", "parent");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"pabe.p_t_bnd"});
    model.result("pg5").feature("surf1").set("data", "dset1");
    model.result("pg5").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "grid1");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg6").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg6").feature("mslc1").set("expr", new String[]{"pabe.Lp_t"});
    model.result("pg6").label("\u58f0\u538b\u7ea7 (pabe)");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"1"});
    model.result("pg6").feature("line1").set("data", "dset1");
    model.result("pg6").feature("line1").set("titletype", "none");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "black");
    model.result("pg6").feature("line1").set("solutionparams", "parent");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"pabe.Lp_t_bnd"});
    model.result("pg6").feature("surf1").set("data", "dset1");
    model.result("pg6").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").run();
    model.result().dataset("grid1").set("parmin1", -2);
    model.result().dataset("grid1").set("parmax1", 2);
    model.result().dataset("grid1").set("parmin2", -6);
    model.result().dataset("grid1").set("parmax2", 6);
    model.result().dataset("grid1").set("parmin3", -6);
    model.result().dataset("grid1").set("parmax3", 6);
    model.result().dataset("grid1").set("res2", 60);
    model.result().dataset("grid1").set("res3", 60);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", 0.2);
    model.result("pg5").feature("mslc1").set("ynumber", "0");
    model.result("pg5").feature("mslc1").set("znumber", "0");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").active(false);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("mslc2", "mslc1");
    model.result("pg5").run();
    model.result("pg5").feature("mslc2").set("data", "dset1");
    model.result("pg5").feature("mslc2").set("expr", "acpr.p_t");
    model.result("pg5").feature("mslc2").set("titletype", "none");
    model.result("pg5").feature("mslc2").set("inheritplot", "mslc1");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e09\u7ef4\u7a7a\u95f4\u54cd\u5e94");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("rp1", "RadiationPattern");
    model.result("pg7").feature("rp1").set("expr", "pabe.Lp_t");
    model.result("pg7").feature("rp1").set("thresholdactive", true);
    model.result("pg7").feature("rp1").set("threshold", 74);
    model.result("pg7").feature("rp1").set("thetadisc", 50);
    model.result("pg7").feature("rp1").set("phidisc", 100);
    model.result("pg7").feature("rp1").set("sphere", "manual");
    model.result("pg7").feature("rp1").set("radius", 100);
    model.result("pg7").feature("rp1").set("grid", "finer");
    model.result("pg7").run();
    model.result().create("pg8", "PolarGroup");
    model.result("pg8").run();
    model.result("pg8").label("xz \u5e73\u9762\u7684\u7a7a\u95f4\u54cd\u5e94");
    model.result("pg8").create("rp1", "RadiationPattern");
    model.result("pg8").feature("rp1").set("markerpos", "datapoints");
    model.result("pg8").feature("rp1").set("linewidth", "preference");
    model.result("pg8").feature("rp1").set("expr", "pabe.Lp_t");
    model.result("pg8").feature("rp1").set("phidisc", 360);
    model.result("pg8").feature("rp1").set("radius", 100);
    model.result("pg8").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg8").feature("rp1").set("legend", true);
    model.result("pg8").feature("rp1").set("legendmethod", "manual");
    model.result("pg8").feature("rp1").setIndex("legends", "\u8ba1\u7b97\u503c", 0);
    model.result("pg8").feature().duplicate("rp2", "rp1");
    model.result("pg8").run();
    model.result("pg8").feature("rp2").set("expr", "10*log10(0.5*abs(p_an)^2/abs(acpr.pref_SPL)^2)");
    model.result("pg8").feature("rp2").set("linestyle", "none");
    model.result("pg8").feature("rp2").set("linemarker", "point");
    model.result("pg8").feature("rp2").set("markerpos", "interp");
    model.result("pg8").feature("rp2").set("markers", 90);
    model.result("pg8").feature("rp2").setIndex("legends", "\u89e3\u6790\u503c", 0);
    model.result("pg8").run();
    model.result("pg7").run();

    model.title("\u8d1d\u585e\u5c14\u9762\u677f");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5177\u6709\u4e0d\u540c\u5e45\u5ea6\u548c\u76f8\u4f4d\u7684\u626c\u58f0\u5668\u9762\u677f\u7684\u58f0\u573a\u3002\u901a\u8fc7\u5de7\u5999\u7684\u626c\u58f0\u5668\u6392\u5217\uff0c\u5b9e\u73b0\u4e86\u5728\u8fdc\u573a\u60c5\u51b5\u4e0b\u4e0e\u5355\u4e2a\u626c\u58f0\u5668\u76f8\u4f3c\u7684\u58f0\u538b\u5206\u5e03\u3002\u8be5\u6a21\u578b\u4f7f\u7528 BEM-FEM \u65b9\u6cd5\u6c42\u89e3\u7406\u60f3\u5316\u626c\u58f0\u5668\u9762\u677f\u7684\u8f90\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("bessel_panel.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
