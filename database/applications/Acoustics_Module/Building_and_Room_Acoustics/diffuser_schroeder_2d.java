/*
 * diffuser_schroeder_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class diffuser_schroeder_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").label("\u5355\u4e2a\u6269\u6563\u4f53");

    model.component("comp1").geom("geom1").insertFile("diffuser_schroeder_2d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("5 \u5355\u5143\u6392\u5217");

    model.component("comp2").geom("geom2").insertFile("diffuser_schroeder_2d_geom_sequence.mph", "geom2");
    model.component("comp2").geom("geom2").run("fin");

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 2);

    model.component("comp3").mesh().create("mesh3");

    model.component("comp3").label("\u65e0\u9650\u6392\u5217");

    model.component("comp3").geom("geom3").insertFile("diffuser_schroeder_2d_geom_sequence.mph", "geom3");
    model.component("comp3").geom("geom3").run("fin");

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
    model.component("comp3").material().create("mat3", "Common");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp3").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp3").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp3").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat3").label("Air");
    model.component("comp3").material("mat3").set("family", "air");
    model.component("comp3").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp3").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp3").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp3").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp3").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp3").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp3").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp3").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp3").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp3").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp3").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp3").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp3").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp3").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp3").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp3").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp3").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp3").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp3").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp3").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp3").material("mat3").materialType("nonSolid");

    model.param().label("\u53c2\u6570 1 - \u51e0\u4f55");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u7269\u7406\u573a");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("c0", "343[m/s]", "\u58f0\u901f");
    model.param("par2").set("rho0", "1.225[kg/m^3]", "\u5bc6\u5ea6");
    model.param("par2").set("f0", "125[Hz]", "\u9891\u7387\u53c2\u6570");
    model.param("par2").set("n", "2", "\u9891\u5e26\u6570");
    model.param("par2").set("N", "6", "\u6bcf\u4e2a\u9891\u5e26\u7684\u9891\u7387");
    model.param("par2").set("f_start", "125[Hz]", "\u9891\u5e26 0 \u7684\u4e2d\u5fc3\u9891\u7387");
    model.param("par2").set("fC", "f_start*2^n", "\u9891\u5e26\u4e2d\u5fc3\u9891\u7387");
    model.param("par2").set("fL", "fC*2^(-1/2)", "\u9891\u5e26\u9891\u7387\u4e0b\u9650");
    model.param("par2").set("fU", "fC*2^(1/2)", "\u9891\u5e26\u9891\u7387\u4e0a\u9650");
    model.param("par2")
         .set("df_log", "(log10(fU[1/Hz])-log10(fL[1/Hz]))/N", "\u9891\u7387\u6b65\u957f\u7684\u5bf9\u6570\uff08\u9891\u5e26\u5185\uff09");
    model.param("par2").set("theta0", "45[deg]", "\u4e0e\u6cd5\u5411\u7684\u5165\u5c04\u6781\u89d2");
    model.param("par2").set("x_spec", "r0*sin(theta0)", "\u955c\u9762\u53cd\u5c04\u65b9\u5411\u7684 x \u5750\u6807");
    model.param("par2").set("y_spec", "r0*cos(theta0)", "\u955c\u9762\u53cd\u5c04\u65b9\u5411\u7684 y \u5750\u6807");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(29);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("p_inc", "1[Pa]*exp(i*2*pi*freq/c0*(sin(theta0)*x-cos(theta0)*y))", "\u5165\u5c04\u5e73\u9762\u6ce2");
    model.component("comp1").variable("var1")
         .set("p_inf", "1[Pa]*exp(i*2*pi*freq/c0*(sin(theta0)*x+cos(theta0)*y))", "\u65e0\u9650\u6321\u677f\u53cd\u5c04\u7684\u5e73\u9762\u6ce2");
    model.component("comp1").variable("var1").set("p_scat", "p_inf+acpr.p_s", "\u6563\u5c04\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("p_scat_ext", "p_inf+pext(x,y)", "\u5916\u573a\u7684\u6563\u5c04\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("Lp_scat", "20*log10(abs(p_scat)/sqrt(2)/(20e-6[Pa]))[dB]", "\u6563\u5c04\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("Lp_ext", "20*log10(abs(p_scat_ext)/sqrt(2)/(20e-6[Pa]))[dB]", "\u5916\u573a\u7684\u6563\u5c04\u58f0\u538b\u7ea7");
    model.component("comp1").variable("var1")
         .set("Pw_spec", "r0*abs(exp(i*2*pi*freq/c0*(sin(theta0)*x_spec+cos(theta0)*y_spec))+pext(x_spec,y_spec))^2", "\u955c\u9762\u53cd\u5c04\u65b9\u5411\u7684\u6563\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Pw_tot", "intop1(abs(p_scat_ext)^2)", "\u603b\u6563\u5c04\u529f\u7387");

    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(1);
    model.component("comp1").physics("acpr").feature("bpf1").set("PressureFieldType", "UserDefined");
    model.component("comp1").physics("acpr").feature("bpf1").set("p", "p_inc+p_inf");
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 1);
    model.component("comp1").physics("acpr").feature("pmb1").selection().set(27, 28);
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(27, 28);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition1", 1, 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "c0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "c0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m/s", 0);
    model.study("std1").feature("param").setIndex("pname", "n", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,1,5)", 0);
    model.study("std1").feature("freq")
         .set("plist", "10^{range(log10(fL[1/Hz])+df_log/2,df_log,log10(fU[1/Hz])-df_log/2)}");
    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").setIndex("pname_aux", "c0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m/s", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "c0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m/s", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "range(-80,10,80)", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "deg", 0);

    model.component("comp1").mesh("mesh1").run();

    model.component("comp2").physics().create("acpr2", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);

    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop2").selection().set(125);

    model.component("comp2").variable().create("var2");

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2")
         .set("p_inc", "1[Pa]*exp(i*2*pi*freq/c0*(sin(theta0)*x-cos(theta0)*y))", "\u5165\u5c04\u5e73\u9762\u6ce2");
    model.component("comp2").variable("var2")
         .set("p_inf", "1[Pa]*exp(i*2*pi*freq/c0*(sin(theta0)*x+cos(theta0)*y))", "\u65e0\u9650\u6321\u677f\u53cd\u5c04\u7684\u5e73\u9762\u6ce2");
    model.component("comp2").variable("var2").set("p_scat", "p_inf+acpr2.p_s", "\u6563\u5c04\u538b\u529b");
    model.component("comp2").variable("var2")
         .set("p_scat_ext", "p_inf+pext(x,y)", "\u5916\u573a\u7684\u6563\u5c04\u538b\u529b");
    model.component("comp2").variable("var2")
         .set("Lp_scat", "20*log10(abs(p_scat)/sqrt(2)/(20e-6[Pa]))[dB]", "\u6563\u5c04\u58f0\u538b\u7ea7");
    model.component("comp2").variable("var2")
         .set("Lp_ext", "20*log10(abs(p_scat_ext)/sqrt(2)/(20e-6[Pa]))[dB]", "\u5916\u573a\u7684\u6563\u5c04\u58f0\u538b\u7ea7");
    model.component("comp2").variable("var2")
         .set("Pw_spec", "r0*abs(exp(i*2*pi*freq/c0*(sin(theta0)*x_spec+cos(theta0)*y_spec))+pext(x_spec,y_spec))^2", "\u955c\u9762\u53cd\u5c04\u65b9\u5411\u7684\u6563\u5c04\u529f\u7387");
    model.component("comp2").variable("var2")
         .set("Pw_tot", "intop2(abs(p_scat_ext)^2)", "\u603b\u6563\u5c04\u529f\u7387");

    model.component("comp2").physics("acpr2").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp2").physics("acpr2").feature("bpf1").selection().set(1);
    model.component("comp2").physics("acpr2").feature("bpf1").set("PressureFieldType", "UserDefined");
    model.component("comp2").physics("acpr2").feature("bpf1").set("p", "p_inc+p_inf");
    model.component("comp2").physics("acpr2").create("pmb1", "PerfectlyMatchedBoundary", 1);
    model.component("comp2").physics("acpr2").feature("pmb1").selection().set(123, 124);
    model.component("comp2").physics("acpr2").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp2").physics("acpr2").feature("efc1").selection().set(123, 124);
    model.component("comp2").physics("acpr2").feature("efc1").setIndex("SymmetryCondition1", 1, 0);

    model.component("comp2").mesh("mesh2").run();

    model.component("comp3").physics().create("acpr3", "PressureAcoustics", "geom3");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", true);

    model.component("comp3").cpl().create("intop3", "Integration");
    model.component("comp3").cpl("intop3").set("axisym", true);
    model.component("comp3").cpl("intop3").selection().geom("geom3", 1);
    model.component("comp3").cpl("intop3").selection().set(3);

    model.component("comp3").variable().create("var3");

//    To import content from file, use:
//    model.component("comp3").variable("var3").loadFile("FILENAME");
    model.component("comp3").variable("var3").set("p_scat", "acpr3.p_s", "\u6563\u5c04\u538b\u529b");
    model.component("comp3").variable("var3").set("Lp_scat", "acpr3.Lp_s", "\u6563\u5c04\u58f0\u538b\u7ea7");
    model.component("comp3").variable("var3")
         .set("I_spec", "intop3(acpr3.I_sx*sin(theta0)+acpr3.I_sy*cos(theta0))", "\u955c\u9762\u53cd\u5c04\u65b9\u5411\u7684\u6563\u5c04\u5f3a\u5ea6");
    model.component("comp3").variable("var3")
         .set("I_tot", "intop3(sqrt(acpr3.I_sx^2+acpr3.I_sy^2))", "\u603b\u6563\u5c04\u5f3a\u5ea6");

    model.component("comp3").physics("acpr3").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp3").physics("acpr3").feature("bpf1").selection().set(1);
    model.component("comp3").physics("acpr3").feature("bpf1").set("pamp", 1);
    model.component("comp3").physics("acpr3").feature("bpf1").set("c", "c0");
    model.component("comp3").physics("acpr3").feature("bpf1")
         .set("dir", new String[]{"sin(theta0)", "-cos(theta0)", "0"});
    model.component("comp3").physics("acpr3").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp3").physics("acpr3").feature("bpf1").set("rho", "rho0");
    model.component("comp3").physics("acpr3").create("pmb1", "PerfectlyMatchedBoundary", 1);
    model.component("comp3").physics("acpr3").feature("pmb1").selection().set(3);
    model.component("comp3").physics("acpr3").feature("pmb1").set("directionType", "normal");
    model.component("comp3").physics("acpr3").create("pc1", "PeriodicCondition", 1);
    model.component("comp3").physics("acpr3").feature("pc1").selection().set(1, 28);
    model.component("comp3").physics("acpr3").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp3").physics("acpr3").feature("pc1")
         .set("kFloquet", new String[]{"sin(theta0)*acpr3.k", "-cos(theta0)*acpr3.k", "0"});

    model.component("comp3").mesh("mesh3").run();

    model.result().setOnlyPlotWhenRequested(true);

    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std1").feature("freq").setEntry("mesh", "geom2", "nomesh");
    model.study("std1").feature("freq").setEntry("mesh", "geom3", "nomesh");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg3").feature("rp1").set("legend", true);
    model.result("pg3").feature("rp1").set("phidisc", 180);
    model.result("pg3").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg4").feature("rp1").set("legend", true);
    model.result("pg4").feature("rp1").set("phidisc", 180);
    model.result("pg4").label("\u5916\u573a\u538b\u529b (acpr)");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", true);
    model.study("std1").feature("freq").setEntry("mesh", "geom2", "mesh2");
    model.study("std1").feature("freq").setEntry("mesh", "geom3", "mesh3");
    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result().dataset("dset4")
         .label("\u7814\u7a76 1/\u53c2\u6570\u5316\u89e3 1 (4) - \u5355\u4e2a\u6269\u6563\u4f53");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(1);
    model.result().dataset("dset5")
         .label("\u7814\u7a76 1/\u53c2\u6570\u5316\u89e3 1 (5) - 5 \u5355\u5143\u6392\u5217");
    model.result().dataset("dset5").selection().geom("geom2", 2);
    model.result().dataset("dset5").selection().geom("geom2", 2);
    model.result().dataset("dset5").selection().set(1);
    model.result().dataset("dset6")
         .label("\u7814\u7a76 1/\u53c2\u6570\u5316\u89e3 1 (6) - \u65e0\u9650\u6392\u5217");
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("data", "dset6");
    model.result().dataset("arr1")
         .label("\u4e8c\u7ef4\u6570\u7ec4 - \u65e0\u9650\u6392\u5217\u7684 5 \u4e2a\u5468\u671f");
    model.result().dataset("arr1").set("fullsize", new int[]{5, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"sin(theta0)*2*pi*freq/c0", "0"});
    model.result().dataset("arr1").setIndex("wavevector", "-cos(theta0)*2*pi*freq/c0", 1);
    model.result("pg1").set("outersolnum", 4);
    model.result("pg1").set("solnum", 94);
    model.result("pg1").feature("surf1").set("expr", "p_scat");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset5");
    model.result("pg1").run();
    model.result("pg1").set("data", "arr1");
    model.result("pg1").run();
    model.result("pg2").set("outersolnum", 4);
    model.result("pg2").set("solnum", 94);
    model.result("pg2").feature("surf1").set("expr", "Lp_scat");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset5");
    model.result("pg2").run();
    model.result("pg2").set("data", "arr1");
    model.result("pg2").run();
    model.result("pg3").set("outerinput", "manual");
    model.result("pg3").set("outersolnum", new int[]{2});
    model.result("pg3").set("innerinput", "manualindices");
    model.result("pg3").set("solnumindices", "range(12,17,102)");
    model.result("pg3").set("symmetricangle", true);
    model.result("pg3").set("zeroangle", "up");
    model.result("pg3").set("legendpos", "manual");
    model.result("pg3").set("legendposx", 0.5);
    model.result("pg3").set("legendposy", 0.25);
    model.result("pg3").feature("rp1").set("expr", "Lp_ext");
    model.result("pg3").feature("rp1").set("phidisc", 360);
    model.result("pg3").feature("rp1").set("anglerestr", "manual");
    model.result("pg3").feature("rp1").set("phimin", -90);
    model.result("pg3").feature("rp1").set("phirange", 180);
    model.result("pg3").feature("rp1").set("circle", "manual");
    model.result("pg3").feature("rp1").set("radius", "r0");
    model.result("pg3").feature("rp1").set("refdir2", new int[]{0, 1});
    model.result("pg3").run();
    model.result("pg3").set("data", "dset5");
    model.result("pg3").run();
    model.result("pg4").set("outerinput", "manual");
    model.result("pg4").set("outersolnum", new int[]{2});
    model.result("pg4").set("innerinput", "manualindices");
    model.result("pg4").set("solnumindices", "range(12,17,102)");
    model.result("pg4").set("symmetricangle", true);
    model.result("pg4").set("zeroangle", "up");
    model.result("pg4").set("legendpos", "manual");
    model.result("pg4").set("legendposx", 0.5);
    model.result("pg4").set("legendposy", 0.25);
    model.result("pg4").feature("rp1").set("expr", "abs(p_scat_ext)^2");
    model.result("pg4").feature("rp1").set("phidisc", 360);
    model.result("pg4").feature("rp1").set("anglerestr", "manual");
    model.result("pg4").feature("rp1").set("phimin", -90);
    model.result("pg4").feature("rp1").set("phirange", 180);
    model.result("pg4").feature("rp1").set("circle", "manual");
    model.result("pg4").feature("rp1").set("radius", "r0");
    model.result("pg4").feature("rp1").set("refdir2", new int[]{0, 1});
    model.result("pg4").run();
    model.result("pg4").set("data", "dset5");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset4");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("tablecols", "outer");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "fC", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "1-Pw_spec/Pw_tot", 1);
    model.result().evaluationGroup("eg1").feature("gev1").set("dataseries", "average");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").set("data", "dset5");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").set("data", "dset6");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "1-I_spec/I_tot", 1);
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u6563\u5c04\u7cfb\u6570");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9891\u5e26\u4e2d\u5fc3\u9891\u7387 (Hz)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6563\u5c04\u7cfb\u6570");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 125);
    model.result("pg5").set("xmax", 4000);
    model.result("pg5").set("ymin", 0);
    model.result("pg5").set("xlog", true);
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "\u5355\u4e2a\u6269\u6563\u4f53", 0);
    model.result("pg5").create("tblp2", "Table");
    model.result("pg5").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp2").set("linewidth", "preference");
    model.result("pg5").feature("tblp2").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg5").feature("tblp2").set("legend", true);
    model.result("pg5").feature("tblp2").set("legendmethod", "manual");
    model.result("pg5").feature("tblp2").setIndex("legends", "5 \u5355\u5143\u6392\u5217", 0);
    model.result("pg5").create("tblp3", "Table");
    model.result("pg5").feature("tblp3").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp3").set("linewidth", "preference");
    model.result("pg5").feature("tblp3").set("source", "evaluationgroup");
    model.result("pg5").feature("tblp3").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp3").set("legend", true);
    model.result("pg5").feature("tblp3").set("legendmethod", "manual");
    model.result("pg5").feature("tblp3").setIndex("legends", "\u65e0\u9650\u6392\u5217", 0);
    model.result("pg5").run();

    model.title("\u65bd\u7f57\u5fb7\u6269\u6563\u5668\u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u65bd\u7f57\u5fb7\u6269\u6563\u5668\u7684\u6563\u5c04\u7cfb\u6570\uff1b\u7136\u540e\uff0c\u5728\u5178\u578b\u7684\u5ba4\u5185\u58f0\u5b66\u4eff\u771f\u4e2d\uff0c\u4f7f\u7528\u8be5\u7cfb\u6570\u4f5c\u4e3a\u8f93\u5165\u6765\u8868\u793a\u8fb9\u754c\u6761\u4ef6\u3002\u6b64\u5916\uff0c\u8fd8\u901a\u8fc7\u7814\u7a76\u540c\u4e00\u6269\u6563\u5668\u4e0d\u540c\u5e03\u7f6e\u7684\u54cd\u5e94\uff0c\u63a2\u8ba8\u4e86\u5468\u671f\u6027\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("diffuser_schroeder_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
