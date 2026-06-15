/*
 * type_43_ear_simulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class type_43_ear_simulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.param().set("vn", "1[m/s]");
    model.param().descr("vn", "\u6e90\u901f\u5ea6");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "type_43_ear_simulator_impedance.txt");
    model.func("int1").setIndex("argunit", "1", 0);
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("funcnames", "col2", "absZ");
    model.func("int1").setIndex("fununit", "kg/(m^2*s)", 0);
    model.func("int1").setEntry("funcnames", "col3", "argZ");
    model.func("int1").setIndex("fununit", "rad", 1);
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").set("extrap", "linear");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "type_43_ear_simulator_ear_canal.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

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

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Ztrans", "aveop1(acpr.p_t)");
    model.component("comp1").variable("var1").set("Zin", "aveop2(acpr.p_t)/intop1(vn)");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6e90");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u63a2\u9488\u9ea6\u514b\u98ce");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(9);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8033\u819c");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(7);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel3");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().named("sel2");

    model.component("comp1").physics("acpr").selection().set(2);
    model.component("comp1").physics("acpr").prop("cref").set("cref", "343[m/s]");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").selection().named("sel3");
    model.component("comp1").physics("acpr").feature("imp1")
         .set("Zn", "absZ(log10(freq[1/Hz]))*exp(i*argZ(log10(freq[1/Hz])))");
    model.component("comp1").physics("acpr").create("nvel1", "NormalVelocity", 2);
    model.component("comp1").physics("acpr").feature("nvel1").selection().named("sel1");
    model.component("comp1").physics("acpr").feature("nvel1").set("nvel", "vn");
    model.component("comp1").physics("acpr").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 2);
    model.component("comp1").physics("acpr").feature("tvb1").selection().set(4, 5);
    model.component("comp1").physics("acpr").feature("tvb1").set("FluidMaterial", "mat1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "type_43_ear_simulator_full_ear.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").label("Air");
    model.component("comp2").material("mat2").set("family", "air");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat2").materialType("nonSolid");

    model.component("comp2").selection().create("sel4", "Explicit");
    model.component("comp2").selection("sel4").label("\u8033\u819c");
    model.component("comp2").selection("sel4").geom(2);
    model.component("comp2").selection("sel4").set(8);

    model.component("comp2").physics().create("acpr2", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);

    model.component("comp2").physics("acpr2").create("tvb1", "ThermoviscousBoundaryLayerImpedance", 2);
    model.component("comp2").physics("acpr2").feature("tvb1").selection().set(6);
    model.component("comp2").physics("acpr2").feature("tvb1").set("FluidMaterial", "mat2");
    model.component("comp2").physics("acpr2").create("bpf1", "BackgroundPressureField", 3);
    model.component("comp2").physics("acpr2").feature("bpf1").selection().set(1);
    model.component("comp2").physics("acpr2").feature("bpf1").set("pamp", 1);
    model.component("comp2").physics("acpr2").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp2").physics("acpr2").feature("bpf1").set("dir", new int[]{0, -1, 0});
    model.component("comp2").physics("acpr2").create("pmb1", "PerfectlyMatchedBoundary", 2);
    model.component("comp2").physics("acpr2").feature("pmb1").selection().set(1, 2, 4, 7, 9);
    model.component("comp2").physics("acpr2").feature("pmb1")
         .set("r0", new String[]{"110.0[mm]", "67.6[mm]", "40.7[mm]"});
    model.component("comp2").physics("acpr2").create("imp1", "Impedance", 2);
    model.component("comp2").physics("acpr2").feature("imp1").selection().named("sel4");
    model.component("comp2").physics("acpr2").feature("imp1")
         .set("Zn", "absZ(log10(freq[1/Hz]))*exp(i*argZ(log10(freq[1/Hz])))");

    model.study("std1").label("\u7814\u7a76 1 - \u8033\u9053");
    model.study("std1").feature("freq")
         .set("plist", "{20, 21.2, 22.4, 23.6, 25, 26.5, 28, 30, 31.5, 33.5, 35.5, 37.5, 40, 42.5, 45, 47.5, 50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3, 1.06e3, 1.12e3, 1.18e3, 1.25e3, 1.32e3, 1.4e3, 1.5e3, 1.6e3, 1.7e3, 1.8e3, 1.9e3, 2e3, 2.12e3, 2.24e3, 2.36e3, 2.5e3, 2.65e3, 2.8e3, 3e3, 3.15e3, 3.35e3, 3.55e3, 3.75e3, 4e3, 4.25e3, 4.5e3, 4.75e3, 5e3, 5.3e3, 5.6e3, 6e3, 6.3e3, 6.7e3, 7.1e3, 7.5e3, 8e3, 8.5e3, 9e3, 9.5e3, 1e4, 1.06e4, 1.12e4, 1.18e4, 1.25e4, 1.32e4, 1.4e4, 1.5e4, 1.6e4, 1.7e4, 1.8e4, 1.9e4, 2e4}");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std1").showAutoSequences("all");
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5168\u8033");
    model.study("std2").feature("freq")
         .set("plist", "{20, 21.2, 22.4, 23.6, 25, 26.5, 28, 30, 31.5, 33.5, 35.5, 37.5, 40, 42.5, 45, 47.5, 50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3, 1.06e3, 1.12e3, 1.18e3, 1.25e3, 1.32e3, 1.4e3, 1.5e3, 1.6e3, 1.7e3, 1.8e3, 1.9e3, 2e3, 2.12e3, 2.24e3, 2.36e3, 2.5e3, 2.65e3, 2.8e3, 3e3, 3.15e3, 3.35e3, 3.55e3, 3.75e3, 4e3, 4.25e3, 4.5e3, 4.75e3, 5e3, 5.3e3, 5.6e3, 6e3, 6.3e3, 6.7e3, 7.1e3, 7.5e3, 8e3, 8.5e3, 9e3, 9.5e3, 1e4, 1.06e4, 1.12e4, 1.18e4, 1.25e4, 1.32e4, 1.4e4, 1.5e4, 1.6e4, 1.7e4, 1.8e4, 1.9e4, 2e4}");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std2").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 121, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 121, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").label("\u6a21\u578b\u7684\u8f6c\u79fb\u963b\u6297");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "|f \\cdot Z| (dB rel. L @ 500 Hz)");
    model.result("pg4").set("xlog", true);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1")
         .setIndex("expr", "20*log10(freq*abs(Ztrans))-withsol('sol1',20*log10(freq*abs(Ztrans)),setval(freq,500))", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u6a21\u578b", 0);
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 121, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b (acpr2)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 121, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"acpr2.Lp_t"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b\u7ea7 (acpr2)");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 121, 0);
    model.result("pg7").create("iso1", "Isosurface");
    model.result("pg7").feature("iso1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg7").feature("iso1").set("number", "10");
    model.result("pg7").feature("iso1").set("colortable", "Wave");
    model.result("pg7").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr2)");
    model.result("pg5").run();
    model.result().dataset().remove("dset2");
    model.result().dataset().remove("dset3");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(3, 5, 6, 8);
    model.result("pg5").run();
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").feature("line1").create("sel1", "Selection");
    model.result("pg5").feature("line1").feature("sel1").selection().set(6, 7, 8, 9, 13, 14);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().set(3, 5, 6, 8);
    model.result("pg6").run();
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", "1");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "black");
    model.result("pg6").feature("line1").create("sel1", "Selection");
    model.result("pg6").feature("line1").feature("sel1").selection().set(6, 7, 8, 9, 13, 14);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5f00\u653e\u8033\u7684\u8033\u819c\u54cd\u5e94");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlog", true);
    model.result("pg8").create("oct1", "OctaveBand");
    model.result("pg8").feature("oct1").set("quantity", "bandpower");
    model.result("pg8").feature("oct1").set("markerpos", "datapoints");
    model.result("pg8").feature("oct1").set("linewidth", "preference");
    model.result("pg8").feature("oct1").selection().geom("geom2", 2);
    model.result("pg8").feature("oct1").selection().named("sel4");
    model.result("pg8").feature("oct1").set("quantity", "continuous");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u7f29\u7565\u56fe");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("edges", false);
    model.result("pg9").create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("expr", "acpr2.p_s");
    model.result("pg9").feature("slc1").set("quickxnumber", 1);
    model.result("pg9").feature("slc1").set("interactive", true);
    model.result("pg9").feature("slc1").set("shift", -0.006);
    model.result("pg9").run();
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").feature("surf1").set("coloring", "uniform");
    model.result("pg9").feature("surf1").set("color", "custom");
    model.result("pg9").feature("surf1")
         .set("customcolor", new double[]{0.9882352948188782, 0.7803921699523926, 0.6980392336845398});
    model.result("pg9").feature("surf1").create("tran1", "Transparency");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("tran1").set("transparency", 0.25);
    model.result("pg9").feature("surf1").feature("tran1").set("uniformblending", 0.6);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(5, 6, 8);
    model.result("pg9").run();

    model.title("4.3 \u578b\u8033\u6735\u6a21\u62df\u5668");

    model
         .description("\u672c\u6a21\u578b\u4e3a P.57 4.3 \u578b\u5168\u9891\u5e26\u8033\u6735\u6a21\u62df\u5668\uff0c\u5176\u4e2d\u5305\u542b\u8033\u9053\u7684\u51e0\u4f55\u5f62\u72b6\u4ee5\u53ca\u6839\u636e ITU-T P.57 \u6807\u51c6\u5b9a\u4e49\u7684\u8033\u5ed3\u3002\u6b64\u5916\uff0c\u6a21\u578b\u4e2d\u8fd8\u5305\u542b\u8033\u819c\u963b\u6297\u7684\u63d2\u503c\u6570\u636e\uff0c\u4ee5\u786e\u4fdd\u8033\u6735\u5177\u6709\u51c6\u786e\u7684\u58f0\u5b66\u5c5e\u6027\u3002\u8be5\u6a21\u578b\u7684\u8bbe\u8ba1\u65e8\u5728\u6ee1\u8db3\u6807\u51c6\u4e2d\u5b9a\u4e49\u7684\u51e0\u4f55\u548c\u58f0\u5b66\u8981\u6c42\uff0c\u800c\u975e\u7279\u5b9a\u7684\u5e02\u552e\u8033\u6735\u6a21\u62df\u5668\u3002\n\n\u8033\u819c\u963b\u6297\u6570\u636e\u6765\u81ea\u4ee5\u4e0b\u53c2\u8003\u8d44\u6599\uff1aL.B. Nielsen and M. Herring Jensen, \"The Digital Twin of a New and Standardized Fullband Ear Simulator,\" DAGA 2022\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("type_43_ear_simulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
