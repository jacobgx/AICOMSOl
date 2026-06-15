/*
 * head_torso_hrtf.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class head_torso_hrtf {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/pabe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "4000[Hz]", "\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("theta0", "4.5[deg]", "\u6781\u89d2\u4fee\u6b63");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "head_torso_hrtf_measured.txt");
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("columnType", "col5", "value");
    model.func("int1").setEntry("columnType", "col6", "value");
    model.func("int1").setEntry("columnType", "col7", "value");
    model.func("int1").setIndex("argunit", "rad", 0);
    model.func("int1").setEntry("funcnames", "col2", "HRTF_1033_real");
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").setEntry("funcnames", "col3", "HRTF_1033_imag");
    model.func("int1").setIndex("fununit", "Pa", 1);
    model.func("int1").setEntry("funcnames", "col4", "HRTF_2067_real");
    model.func("int1").setIndex("fununit", "Pa", 2);
    model.func("int1").setEntry("funcnames", "col5", "HRTF_2067_imag");
    model.func("int1").setIndex("fununit", "Pa", 3);
    model.func("int1").setEntry("funcnames", "col6", "HRTF_3962_real");
    model.func("int1").setIndex("fununit", "Pa", 4);
    model.func("int1").setEntry("funcnames", "col7", "HRTF_3962_imag");
    model.func("int1").setIndex("fununit", "Pa", 5);
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").set("extrap", "linear");
    model.func("int1").importData();
    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "p1033");
    model.func("an1").set("expr", "HRTF_1033_real(theta+theta0)+i*HRTF_1033_imag(theta+theta0)");
    model.func("an1").set("args", "theta");
    model.func("an1").set("periodic", true);
    model.func("an1").set("periodicupper", "2*pi");
    model.func("an1").setIndex("argunit", "rad", 0);
    model.func("an1").set("fununit", "Pa");
    model.func("an1").set("complex", true);
    model.func("an1").setIndex("plotargs", "-pi", 0, 1);
    model.func("an1").setIndex("plotargs", "3*pi", 0, 2);
    model.func().duplicate("an2", "an1");
    model.func("an2").set("funcname", "p2067");
    model.func("an2").set("expr", "HRTF_2067_real(theta+theta0)+i*HRTF_2067_imag(theta+theta0)");
    model.func().duplicate("an3", "an2");
    model.func("an3").set("funcname", "p3962");
    model.func("an3").set("expr", "HRTF_3962_real(theta+theta0)+i*HRTF_3962_imag(theta+theta0)");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");
    model.mesh("mpart1").create("imp1", "Import");

    model.component("mcomp1").baseSystem(null);

    model.geom("mgeom1").lengthUnit("mm");

    model.mesh("mpart1").feature("imp1").set("filename", "head_torso_hrtf_scan.stl");
    model.mesh("mpart1").feature("imp1").set("createdom", true);
    model.mesh("mpart1").feature("imp1").set("facepartition", "detectfaces");
    model.mesh("mpart1").feature("imp1").set("facemaxangle", 180);
    model.mesh("mpart1").feature("imp1").create("tr1", "Transform");
    model.mesh("mpart1").feature("imp1").feature("tr1").set("scaletype", "anisotropic");
    model.mesh("mpart1").feature("imp1").feature("tr1").set("anisotropic", new int[]{1, 1, -1});
    model.mesh("mpart1").run("imp1");
    model.mesh("mpart1").create("cyl1", "Cylinder");
    model.mesh("mpart1").feature("cyl1").set("r", 2.8);
    model.mesh("mpart1").feature("cyl1").set("pos", new double[]{-1.3, 0, 0.6});
    model.mesh("mpart1").feature("cyl1").set("axistype", "y");
    model.mesh("mpart1").run("cyl1");
    model.mesh("mpart1").create("pln1", "IntersectPlane");
    model.mesh("mpart1").feature("pln1").set("planetype", "coordinates");
    model.mesh("mpart1").feature("pln1").setIndex("genpoints", -95, 0, 2);
    model.mesh("mpart1").feature("pln1").setIndex("genpoints", 10, 1, 0);
    model.mesh("mpart1").feature("pln1").setIndex("genpoints", -100, 1, 2);
    model.mesh("mpart1").feature("pln1").setIndex("genpoints", -95, 2, 2);
    model.mesh("mpart1").run("pln1");
    model.mesh("mpart1").run("fin");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "mesh");
    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart1");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");

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
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();

    model.component("comp1").physics("pabe").selection().set();
    model.component("comp1").physics("pabe").selection().allVoids();
    model.component("comp1").physics("pabe").prop("Stabilization").set("StabilizedFormulation", true);
    model.component("comp1").physics("pabe").create("nvel1", "NormalVelocity", 2);
    model.component("comp1").physics("pabe").feature("nvel1").selection().set(6);
    model.component("comp1").physics("pabe").feature("nvel1").set("nvel", 1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "min(20[mm],lam0/4)");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "3[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "lam0/4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("theta", "atan2(y,x)");
    model.component("comp1").variable("var1").descr("theta", "\u6c34\u5e73\u6781\u89d2");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "{1033.6, 2067.2, 3962.1, 6000, 8000}", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b\uff0c\u8fb9\u754c (pabe)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 5, 1);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset2");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin1", -0.4867121093904141);
    model.result().dataset("grid1").set("parmax1", 0.4172829194535914);
    model.result().dataset("grid1").set("parmin2", -0.8075448223718473);
    model.result().dataset("grid1").set("parmax2", 0.826052825386332);
    model.result().dataset("grid1").set("parmin3", -0.8106502198191579);
    model.result().dataset("grid1").set("parmax3", 0.6373535172957613);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 5, 1);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("colortable", "Wave");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"pabe.p_t"});
    model.result("pg2").label("\u58f0\u538b (pabe)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"1"});
    model.result("pg2").feature("line1").set("data", "dset2");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"pabe.p_t_bnd"});
    model.result("pg2").feature("surf1").set("data", "dset2");
    model.result("pg2").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "grid1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 5, 1);
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg3").feature("mslc1").set("expr", new String[]{"pabe.Lp_t"});
    model.result("pg3").label("\u58f0\u538b\u7ea7 (pabe)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"1"});
    model.result("pg3").feature("line1").set("data", "dset2");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"pabe.Lp_t_bnd"});
    model.result("pg3").feature("surf1").set("data", "dset2");
    model.result("pg3").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").run();
    model.result().dataset("grid1").set("parmin1", -0.3);
    model.result().dataset("grid1").set("parmax1", 0.3);
    model.result().dataset("grid1").set("parmin2", -0.5);
    model.result().dataset("grid1").set("parmax2", 0.5);
    model.result().dataset("grid1").set("parmin3", -0.5);
    model.result().dataset("grid1").set("parmax3", 0.5);
    model.result().dataset("grid1").set("res1", 40);
    model.result().dataset("grid1").set("res2", 120);
    model.result().dataset("grid1").set("res3", 120);
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").set("looplevel", new int[]{1, 1});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 3});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{1, 3});
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinearsymmetric");
    model.result("pg2").feature("mslc1").set("colorcalibration", -1);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{1, 3});
    model.result("pg3").run();
    model.result("pg3").feature("mslc1").set("ynumber", "0");
    model.result("pg3").feature("mslc1").set("znumber", "0");
    model.result("pg3").run();
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").run();
    model.result("pg4").label("HRTF");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("markerpos", "datapoints");
    model.result("pg4").feature("rp1").set("linewidth", "preference");
    model.result("pg4").feature("rp1").set("expr", "pabe.Lp_t");
    model.result("pg4").feature("rp1").set("phidisc", 360);
    model.result("pg4").feature("rp1").set("legend", true);
    model.result("pg4").feature("rp1").set("legendmethod", "evaluated");
    model.result("pg4").feature("rp1").set("legendpattern", "f = eval(f0,Hz,4) Hz");
    model.result("pg4").run();
    model.result("pg4").set("zeroangle", "up");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("HRTF\uff08\u5f52\u4e00\u5316\uff09");
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("expr", "pabe.Lp_t-at3_spatial(1[m],0,0,pabe.Lp_t,'minc')");
    model.result("pg5").run();
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").run();
    model.result("pg6").label("HRTF \u6bd4\u8f83 (1033 Hz), R = 1.4 m");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("markerpos", "datapoints");
    model.result("pg6").feature("rp1").set("linewidth", "preference");
    model.result("pg6").feature("rp1").set("expr", "pabe.Lp_t-at3_spatial(1.4[m],0,0,pabe.Lp_t,'minc')");
    model.result("pg6").feature("rp1").set("phidisc", 360);
    model.result("pg6").feature("rp1").set("radius", 1.4);
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("legendmethod", "manual");
    model.result("pg6").feature("rp1").setIndex("legends", "COMSOL", 0);
    model.result("pg6").run();
    model.result("pg6").create("rp2", "RadiationPattern");
    model.result("pg6").feature("rp2").set("markerpos", "datapoints");
    model.result("pg6").feature("rp2").set("linewidth", "preference");
    model.result("pg6").feature("rp2").set("expr", "20*log10(abs(p1033(theta)/p1033(0)))");
    model.result("pg6").feature("rp2").set("phidisc", 360);
    model.result("pg6").feature("rp2").set("radius", 1.4);
    model.result("pg6").feature("rp2").set("legend", true);
    model.result("pg6").feature("rp2").set("legendmethod", "manual");
    model.result("pg6").feature("rp2").setIndex("legends", "\u6d4b\u91cf\u503c", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("HRTF \u6bd4\u8f83 (2067 Hz), R = 1.4 m");
    model.result("pg7").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg7").run();
    model.result("pg7").feature("rp2").set("expr", "20*log10(abs(p2067(theta)/p2067(0)))");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("HRTF \u6bd4\u8f83 (3962 Hz), R = 1.4 m");
    model.result("pg8").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg8").run();
    model.result("pg8").feature("rp2").set("expr", "20*log10(abs(p3962(theta)/p3962(0)))");
    model.result("pg8").run();
    model.result("pg2").run();

    model.view("view6").set("ssao", true);

    model.title("\u5934\u90e8\u548c\u8eaf\u5e72 HRTF \u8ba1\u7b97");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u5bfc\u5165\u4eba\u4f53\u5934\u90e8\u548c\u8eaf\u5e72\u7684\u4e09\u7ef4\u626b\u63cf\u51e0\u4f55\u7ed3\u6784\u5e76\u8ba1\u7b97\u5934\u76f8\u5173\u4f20\u9012\u51fd\u6570 (HRTF)\u3002\u626b\u63cf\u7ed3\u679c\u4f5c\u4e3a .stl \u6587\u4ef6\u5bfc\u5165\uff0c\u5e76\u8f6c\u6362\u4e3a COMSOL \u51e0\u4f55\u7ed3\u6784\u3002HRTF \u662f\u7528\u4e92\u6613\u539f\u7406\u8ba1\u7b97\u7684\uff0c\u5c06\u6e90\u5b9a\u4f4d\u5728\u8033\u9053\u5165\u53e3\u5904\uff0c\u58f0\u5b66\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u8fb9\u754c\u5143\u201d\u63a5\u53e3\u6765\u6a21\u62df\u3002\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u5b9e\u6d4b\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\uff0c\u4e24\u8005\u8868\u73b0\u51fa\u5f88\u597d\u7684\u4e00\u81f4\u6027\u3002\n\n\u626b\u63cf\u7684\u51e0\u4f55\u7ed3\u6784\u548c\u6d4b\u91cf\u6570\u636e\u7531\u5fb7\u56fd\u4e9a\u741b\u5de5\u4e1a\u5927\u5b66\u6280\u672f\u58f0\u5b66\u7814\u7a76\u6240\u7684 MedAk \u56e2\u961f\u63d0\u4f9b\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("head_torso_hrtf.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
