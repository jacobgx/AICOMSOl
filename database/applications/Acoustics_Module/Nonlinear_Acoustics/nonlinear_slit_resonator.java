/*
 * nonlinear_slit_resonator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class nonlinear_slit_resonator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tatd", "ThermoacousticsSinglePhysicsTransient", "geom1");
    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std1").feature("time").setSolveFor("/physics/actd", true);

    model.param().label("\u53c2\u6570 1 - \u6a21\u578b");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "2000[Hz]", "\u9a71\u52a8\u9891\u7387");
    model.param().set("dvisc", "220[um]*sqrt(100[Hz]/f0)", "f0 \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("T0", "1/f0", "\u5468\u671f");
    model.param().set("L0", "155[dB]", "\u5165\u5c04\u6ce2\u632f\u5e45 dB");
    model.param().set("p0", "10^(L0/20)*20e-6[Pa]", "\u5165\u5c04\u6ce2\u632f\u5e45 Pa");
    model.param().set("Tstart", "2.5*y_in/c0", "\u540e\u5904\u7406\u5f00\u59cb\u65f6\u95f4");
    model.param().set("Tend", "Tstart+5*T0", "\u540e\u5904\u7406\u7ed3\u675f\u65f6\u95f4");
    model.param().set("N0", "6", "\u8981\u89e3\u6790\u7684\u8c10\u6ce2");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u51e0\u4f55");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("w_tube", "2[in]", "\u7ba1\u5bbd\u5ea6");
    model.param("par2").set("h_r", "(2.8+1.4+1.08+0.72)[in]", "\u8c10\u632f\u5668\u9ad8\u5ea6");
    model.param("par2").set("h_slit", "0.04[in]", "\u72ed\u7f1d\u9ad8\u5ea6");
    model.param("par2").set("w_slit", "0.05[in]", "\u72ed\u7f1d\u5bbd\u5ea6");
    model.param("par2").set("h_in", "24[in]", "\u5165\u53e3\u9ad8\u5ea6");
    model.param("par2").set("y_in", "h_in+h_slit/2", "\u5165\u53e3\u4f4d\u7f6e y \u5750\u6807");
    model.param("par2").set("h_tube", "h_r+h_in+h_slit", "\u603b\u7ba1\u957f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_tube", "h_tube"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "h_tube/2-(h_r+h_slit/2)"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_tube", "h_slit"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "(w_tube-w_slit)/2", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"10*w_slit", "40*h_slit"});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"3*w_slit", "20*h_slit"});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"w_tube", "0.14[m]"});
    model.component("comp1").geom("geom1").feature("r5").set("base", "center");
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2", "r3", "r4", "r5");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 3, 7, 10, 13, 14, 15);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1", 24, 25);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(10);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u70ed\u9ecf\u6027\u58f0\u5b66");
    model.component("comp1").selection("sel2").set(2, 3, 5, 6, 7);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u538b\u529b\u58f0\u5b66");
    model.component("comp1").selection("sel3").set(1, 4);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().named("sel2");

    model.component("comp1").physics("tatd").selection().named("sel2");
    model.component("comp1").physics("tatd").prop("TransientSettings").set("fmax", "N0*f0");
    model.component("comp1").physics("tatd").prop("ShapeProperty").set("shapeorder_u", 1);
    model.component("comp1").physics("tatd").prop("ShapeProperty").set("shapeorder_T", 1);
    model.component("comp1").physics("tatd").prop("Stabilization").set("selStab", "GLSStab");
    model.component("comp1").physics("tatd").create("ntac1", "NonlinearThermoviscousAcousticsContributions", 2);
    model.component("comp1").physics("tatd").feature("ntac1").selection().named("sel2");
    model.component("comp1").physics("tatd").create("wall2", "Wall", 1);
    model.component("comp1").physics("tatd").feature("wall2").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("tatd").feature("wall2").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("tatd").feature("wall2").selection().set(3, 6, 36, 37);
    model.component("comp1").physics("actd").selection().named("sel3");
    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "N0*f0");
    model.component("comp1").physics("actd").create("pwr1", "PlaneWaveRadiation", 1);
    model.component("comp1").physics("actd").feature("pwr1").selection().named("sel1");
    model.component("comp1").physics("actd").feature("pwr1").create("ipf1", "IncidentPressureField", 1);
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("pamp", "p0");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("c_mat", "from_mat");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("f0", "f0");

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 1);
    model.component("comp1").multiphysics("atb1").selection().all();

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "dvisc/3");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "2.5*dvisc");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "12*dvisc");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "w_tube/8");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().set(19, 21, 23, 24, 25, 26);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmax", "dvisc");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(5, 7, 13, 15, 19, 21, 23, 24, 25, 26, 28, 30, 32, 34);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.1*dvisc");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "c0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "c0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "500 1000 1500 2000", 0);
    model.study("std1").feature("time").set("tlist", "range(0,T0/30,Tend)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (tatd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 417, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u901f (tatd)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 417, 0);
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "tatd.v_inst");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316 (tatd)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 417, 0);
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "tatd.T_t");
    model.result("pg3").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 417, 0);
    model.result("pg4").setIndex("looplevel", 4, 1);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b (actd)");
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "actd.p_t");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 2);
    model.result("pg2").selection().named("sel2");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("xnumber", 100);
    model.result("pg2").feature("arws1").set("ynumber", 200);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new String[]{"interp", "4"});
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+0*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+1*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+2*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+3*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+4*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("interp", new String[]{"Tstart+4*T0+5*T0/6"});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{417, 4});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u58f0\u80fd\u5bc6\u5ea6\u53d8\u5316 (tatd)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "tatd.rho");
    model.result("pg5").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u6da1\u5ea6\u7684 log10");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "log10(abs(uy-vx))");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u72ed\u7f1d\u538b\u529b");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevelinput", "first", 1);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(16);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u72ed\u7f1d\u538b\u529b\uff0cFFT");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(16);
    model.result("pg8").feature("ptgr1").set("xdata", "fourier");
    model.result("pg8").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg8").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg8").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg8").feature("ptgr1").set("freqmin", 100);
    model.result("pg8").feature("ptgr1").set("freqmax", 10000);
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").set("xlog", true);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u5165\u5c04\u548c\u53cd\u5c04");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevelinput", "first", 1);
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "t (s)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").selection().set(6);
    model.result("pg9").feature("ptgr1").set("expr", "actd.p_i");
    model.result("pg9").feature("ptgr1").set("legend", true);
    model.result("pg9").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr1").setIndex("legends", "\u5165\u5c04", 0);
    model.result("pg9").run();
    model.result("pg9").create("ptgr2", "PointGraph");
    model.result("pg9").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr2").set("linewidth", "preference");
    model.result("pg9").feature("ptgr2").selection().set(6);
    model.result("pg9").feature("ptgr2").set("expr", "actd.p_t-actd.p_i");
    model.result("pg9").feature("ptgr2").set("legend", true);
    model.result("pg9").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr2").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u53cd\u5c04\u7cfb\u6570");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevelinput", "last", 0);
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "f (Hz)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "|R| (1)");
    model.result("pg10").set("axislimits", true);
    model.result("pg10").set("xmin", 100);
    model.result("pg10").set("xmax", 2500);
    model.result("pg10").set("ymin", 0);
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").selection().set(6);
    model.result("pg10").feature("ptgr1")
         .set("expr", "sqrt(timeint(Tstart,Tend-T0,(actd.p_t-actd.p_i)^2))/sqrt(timeint(Tstart,Tend-T0,actd.p_i^2))");
    model.result("pg10").feature("ptgr1").set("xdatasolnumtype", "outer");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").set("linestyle", "none");
    model.result("pg10").feature("ptgr1").set("linewidth", 2);
    model.result("pg10").feature("ptgr1").set("linemarker", "circle");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u5bc6\u5ea6\u7684\u7ebf\u6027\u5047\u8bbe");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "maxop1(abs(tatd.rho_t/tatd.rho0))", 0);
    model.result("pg11").run();
    model.result("pg2").run();

    model.title("\u975e\u7ebf\u6027\u72ed\u7f1d\u8c10\u632f\u5668");

    model
         .description("\u5728\u8bb8\u591a\u5e94\u7528\u4e2d\uff0c\u58f0\u6ce2\u4f1a\u4e0e\u5e26\u6709\u5c0f\u7a7f\u5b54\u6216\u72ed\u7f1d\u7684\u8868\u9762\u76f8\u4e92\u4f5c\u7528\uff0c\u8fd9\u4e9b\u8868\u9762\u591a\u89c1\u4e8e\u6d88\u58f0\u5668\u7cfb\u7edf\u3001\u9694\u97f3\u7ed3\u6784\u3001\u55b7\u6c14\u53d1\u52a8\u673a\u7684\u566a\u58f0\u6291\u5236\u886c\u57ab\uff0c\u6216\u79fb\u52a8\u8bbe\u5907\u5fae\u578b\u626c\u58f0\u5668\u524d\u7684\u683c\u6805\u548c\u7f51\u7f69\u3002\n\n\u5728\u4e2d\u9ad8\u58f0\u538b\u7ea7\u4e0b\uff0c\u7a7f\u5b54\u6216\u72ed\u7f1d\u7684\u7a84\u533a\u57df\u4e2d\u7684\u5c40\u90e8\u7c92\u5b50\u901f\u5ea6\u53ef\u80fd\u975e\u5e38\u5927\uff0c\u5bfc\u81f4\u7ebf\u6027\u58f0\u5b66\u5047\u8bbe\u5931\u6548\u3002\u901a\u5e38\uff0c\u8be5\u533a\u57df\u9644\u8fd1\u4f1a\u53d1\u751f\u6da1\u65cb\u8131\u843d\uff0c\u5f15\u8d77\u975e\u7ebf\u6027\u635f\u8017\uff0c\u5e76\u5728\u97f3\u9891\u5e94\u7528\u4e2d\u9020\u6210\u58f0\u97f3\u4fe1\u53f7\u7684\u975e\u7ebf\u6027\u5931\u771f\u3002\u8fd9\u4e9b\u975e\u7ebf\u6027\u6548\u5e94\u6709\u65f6\u901a\u8fc7\u534a\u7ecf\u9a8c\u53c2\u6570\uff0c\u5728\u7a7f\u5b54\u7684\u89e3\u6790\u8f6c\u79fb\u963b\u6297\u6a21\u578b\u4e2d\u4f53\u73b0\u51fa\u6765\u3002\n\n\u672c\u6559\u7a0b\u4e2d\u7684\u6a21\u578b\u5728\u8c10\u632f\u5668\u4f53\u7684\u524d\u65b9\u8bbe\u6709\u4e00\u4e2a\u7a84\u7f1d\uff0c\u901a\u8fc7\u8026\u5408\u201c\u538b\u529b\u58f0\u5b66\uff0c\u77ac\u6001\u201d\u4e0e\u201c\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u77ac\u6001\u201d\u63a5\u53e3\u6765\u6a21\u62df\u975e\u7ebf\u6027\u77ac\u6001\u95ee\u9898\uff1b\u5e76\u4f7f\u7528\u201c\u975e\u7ebf\u6027\u70ed\u9ecf\u6027\u58f0\u5b66\u8d21\u732e\u201d\u7279\u5f81\u6355\u6349\u4e0e\u6da1\u65cb\u8131\u843d\u548c\u9ecf\u6027\u8017\u6563\u76f8\u5173\u7684\u590d\u6742\u975e\u7ebf\u6027\u635f\u8017\u3002\u5165\u5c04\u58f0\u573a\u7684\u5e45\u5ea6\u5bf9\u5e94\u4e8e 155\u00a0dB \u7684\u58f0\u538b\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("nonlinear_slit_resonator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
