/*
 * cluster_setup_validation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class cluster_setup_validation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.param().set("p0", "1[Pa]");
    model.param().descr("p0", "Inlet pressure amplitude");

    model.component("comp1").geom("geom1").insertFile("automotive_muffler_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl("intop1").set("opname", "intop_inlet");
    model.component("comp1").cpl("intop1").label("Inlet");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl("intop2").selection().set(50);
    model.component("comp1").cpl("intop2").set("opname", "intop_outlet");
    model.component("comp1").cpl("intop2").label("Outlet");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("P_in", "intop_inlet(p0^2/(2*acpr.rho*acpr.c))");
    model.component("comp1").variable("var1").descr("P_in", "Incoming power");
    model.component("comp1").variable("var1").set("P_out", "intop_outlet(p*conj(p)/(2*acpr.rho*acpr.c))");
    model.component("comp1").variable("var1").descr("P_out", "Outgoing power");
    model.component("comp1").variable("var1").set("TL", "10*log10(P_in/P_out)");
    model.component("comp1").variable("var1").descr("TL", "Transmission loss");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(10, 11, 13, 14, 16, 20, 21, 22, 23, 25, 26, 28, 29, 32, 36, 37, 38, 39);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").selection("sel1").label("Interior Boundaries");

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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
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

    model.component("comp1").physics("acpr").create("pwr1", "PlaneWaveRadiation", 2);
    model.component("comp1").physics("acpr").feature("pwr1").selection().set(1);
    model.component("comp1").physics("acpr").feature("pwr1").create("ipf1", "IncidentPressureField", 2);
    model.component("comp1").physics("acpr").feature("pwr1").feature("ipf1").set("pamp", "p0");
    model.component("comp1").physics("acpr").feature("pwr1").feature("ipf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("pwr1").feature("ipf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("acpr").create("pwr2", "PlaneWaveRadiation", 2);
    model.component("comp1").physics("acpr").feature("pwr2").selection().set(50);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").physics("acpr").create("ishb1", "InteriorSoundHard", 2);
    model.component("comp1").physics("acpr").feature("ishb1").selection().named("sel1");

    model.study("std1").feature("freq").set("plist", "range(100,10,1000)");

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 10);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 91, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("Acoustic Pressure (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 91, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("Sound Pressure Level (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 91, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("Acoustic Pressure, Isosurfaces (acpr)");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 43, 44, 45, 46, 47, 48, 49, 50);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 40, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "acpr.absp_t");
    model.result("pg1").feature("surf1").set("descr", "Absolute total acoustic pressure");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("iso1", "Isosurface");
    model.result("pg1").feature("iso1").set("expr", "p");
    model.result("pg1").feature("iso1").set("descr", "Acoustic pressure");
    model.result("pg1").feature("iso1").set("number", 10);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"TL"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"Transmission loss"});
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").run();
    model.result("pg4").label("Transmission Loss");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Frequency (Hz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Transmission loss (dB)");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u6c7d\u8f66\u6d88\u58f0\u5668");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5185\u71c3\u673a\u6d88\u58f0\u5668\u4e2d\u7684\u538b\u529b\u6ce2\u4f20\u64ad\u60c5\u51b5\uff0c\u4f7f\u7528\u4e00\u822c\u7684\u65b9\u6cd5\u6765\u5206\u6790\u8c10\u538b\u529b\u6ce2\u7684\u4f20\u64ad\u8870\u51cf\u3002\u6a21\u578b\u5728\u9891\u57df\u4e2d\u6c42\u89e3\uff0c\u5206\u6790\u4e86\u9891\u7387\u8303\u56f4\u4ece 100 \u5230 1000\u00a0Hz \u7684\u6709\u6548\u8870\u51cf\u3002");

    model.label("automotive_muffler.mph");

    model.result("pg1").run();

    model.title("\u96c6\u7fa4\u8bbe\u7f6e\u9a8c\u8bc1");

    model
         .description("\u60a8\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u6765\u9a8c\u8bc1\u9ed8\u8ba4\u8bbe\u7f6e\u662f\u5426\u5c06\u4f5c\u4e1a\u6b63\u786e\u63d0\u4ea4\u5230\u96c6\u7fa4\uff0c\u8fd9\u4e9b\u9ed8\u8ba4\u8bbe\u7f6e\u6765\u81ea\u9996\u9009\u9879\u3002\n\n\u8be5 App \u5141\u8bb8\u60a8\u66ff\u4ee3\u9ed8\u8ba4\u96c6\u7fa4\u8bbe\u7f6e\uff0c\u6d4b\u8bd5\u5bf9\u5f53\u524d\u8bbe\u7f6e\u7684\u4fee\u6539\uff0c\u4ee5\u53ca\u6d4b\u8bd5\u7528\u4e8e\u8fde\u63a5\u96c6\u7fa4\u7684\u4e0d\u540c\u8bbe\u7f6e\u3002");

    model.sol().remove("sol1");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("freq").set("plist", 490);
    model.study("std1").create("cluco", "ClusterComputing");
    model.study("std2").create("cluco", "ClusterComputing");
    model.study("std1").feature("cluco").set("batchfileactive", true);
    model.study("std1").feature("cluco").set("batchfile", "Sweep.mph");
    model.study("std2").feature("cluco").set("batchfileactive", true);
    model.study("std2").feature("cluco").set("batchfile", "Stationary.mph");

    model.title("\u96c6\u7fa4\u8bbe\u7f6e\u9a8c\u8bc1");

    model
         .description("\u60a8\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u6765\u9a8c\u8bc1\u9ed8\u8ba4\u8bbe\u7f6e\u662f\u5426\u5c06\u4f5c\u4e1a\u6b63\u786e\u63d0\u4ea4\u5230\u96c6\u7fa4\uff0c\u8fd9\u4e9b\u9ed8\u8ba4\u8bbe\u7f6e\u6765\u81ea\u9996\u9009\u9879\u3002\n\n\u8be5 App \u5141\u8bb8\u60a8\u66ff\u4ee3\u9ed8\u8ba4\u96c6\u7fa4\u8bbe\u7f6e\uff0c\u6d4b\u8bd5\u5bf9\u5f53\u524d\u8bbe\u7f6e\u7684\u4fee\u6539\uff0c\u4ee5\u53ca\u6d4b\u8bd5\u7528\u4e8e\u8fde\u63a5\u96c6\u7fa4\u7684\u4e0d\u540c\u8bbe\u7f6e\u3002");

    model.label("cluster_setup_validation.mph");

    model.setExpectedComputationTime("1 \u5206\u949f\uff1b5 \u5206\u949f");

    model.title("\u96c6\u7fa4\u8bbe\u7f6e\u9a8c\u8bc1");

    model
         .description("\u60a8\u53ef\u4ee5\u4f7f\u7528\u6b64 App \u6765\u9a8c\u8bc1\u9ed8\u8ba4\u8bbe\u7f6e\u662f\u5426\u5c06\u4f5c\u4e1a\u6b63\u786e\u63d0\u4ea4\u5230\u96c6\u7fa4\uff0c\u8fd9\u4e9b\u9ed8\u8ba4\u8bbe\u7f6e\u6765\u81ea\u9996\u9009\u9879\u3002\n\n\u8be5 App \u5141\u8bb8\u60a8\u66ff\u4ee3\u9ed8\u8ba4\u96c6\u7fa4\u8bbe\u7f6e\uff0c\u6d4b\u8bd5\u5bf9\u5f53\u524d\u8bbe\u7f6e\u7684\u4fee\u6539\uff0c\u4ee5\u53ca\u6d4b\u8bd5\u7528\u4e8e\u8fde\u63a5\u96c6\u7fa4\u7684\u4e0d\u540c\u8bbe\u7f6e\u3002");

    model.label("cluster_setup_validation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
