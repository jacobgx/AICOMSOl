/*
 * active_flame_validation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class active_flame_validation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("ftplistmethod", "manual");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Q_s", "1[W/m^3]", "\u7a33\u5b9a\u653e\u70ed");
    model.param().set("u_s", "1[m/s]", "\u7a33\u6001\u6d41\u4f53\u6d41\u52a8");
    model.param().set("tau_u", "1e-4[s]", "\u65f6\u95f4\u6ede\u540e");
    model.param().set("n", "5", "\u6bd4\u4f8b\u53c2\u6570");
    model.param().set("delta_f", "0.001[m]", "\u706b\u7130\u533a\u5bbd\u5ea6");
    model.param().set("P_s", "1[atm]", "\u7a33\u6001\u538b\u529b");
    model.param().set("L", "0.5[m]", "\u957f\u5ea6");
    model.param().set("c1", "347.19[m/s]", "\u58f0\u901f\uff0c\u51b7\u533a");
    model.param().set("Gamma", "0.5", "\u4f3d\u739b\u56e0\u5b50");
    model.param().set("freq_lin", "200[Hz]", "\u6c42\u89e3\u5668\u7684\u7ebf\u6027\u5316\u9891\u7387");
    model.param()
         .set("omega10", "4*c1/L*acos(sqrt(1/4*(Gamma-1)/(Gamma+1)+3/4))", "\u7b2c\u4e00\u79cd\u6a21\u5f0f\uff0c\u975e\u6d3b\u52a8\u706b\u7130");
    model.param()
         .set("omega1Res", "4*c1/L*acos(sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega10))-1)/(Gamma*(1+n*exp(-i*tau_u*omega10))+1)+3/4))", "\u7b2c\u4e00\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 1 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega1Res2", "4*c1/L*acos(sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega1Res))-1)/(Gamma*(1+n*exp(-i*tau_u*omega1Res))+1)+3/4))", "\u7b2c\u4e00\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 2 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega1Res3", "4*c1/L*acos(sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega1Res2))-1)/(Gamma*(1+n*exp(-i*tau_u*omega1Res2))+1)+3/4))", "\u7b2c\u4e00\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 3 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega30", "4*c1/L*acos(-sqrt(1/4*(Gamma-1)/(Gamma+1)+3/4))", "\u7b2c\u4e09\u79cd\u6a21\u5f0f\uff0c\u975e\u6d3b\u52a8\u706b\u7130");
    model.param()
         .set("omega3Res", "4*c1/L*acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega30))-1)/(Gamma*(1+n*exp(-i*tau_u*omega30))+1)+3/4))", "\u7b2c\u4e09\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 1 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega3Res2", "4*c1/L*acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega3Res))-1)/(Gamma*(1+n*exp(-i*tau_u*omega3Res))+1)+3/4))", "\u7b2c\u4e09\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 2 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega3Res3", "4*c1/L*acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega3Res2))-1)/(Gamma*(1+n*exp(-i*tau_u*omega3Res2))+1)+3/4))", "\u7b2c\u4e09\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 3 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega40", "4*c1/L*(-acos(-sqrt(1/4*(Gamma-1)/(Gamma+1)+3/4))+2*pi)", "\u7b2c\u56db\u79cd\u6a21\u5f0f\uff0c\u975e\u6d3b\u52a8\u706b\u7130");
    model.param()
         .set("omega4Res", "4*c1/L*(-acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega40))-1)/(Gamma*(1+n*exp(-i*tau_u*omega40))+1)+3/4))+2*pi)", "\u7b2c\u56db\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 1 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega4Res2", "4*c1/L*(-acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega4Res))-1)/(Gamma*(1+n*exp(-i*tau_u*omega4Res))+1)+3/4))+2*pi)", "\u7b2c\u56db\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 2 \u6b21\u8fed\u4ee3");
    model.param()
         .set("omega4Res3", "4*c1/L*(-acos(-sqrt(1/4*(Gamma*(1+n*exp(-i*tau_u*omega4Res2))-1)/(Gamma*(1+n*exp(-i*tau_u*omega4Res2))+1)+3/4))+2*pi)", "\u7b2c\u56db\u79cd\u6a21\u5f0f\uff0c\u6d3b\u52a8\u706b\u7130\u7b2c 3 \u6b21\u8fed\u4ee3");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "L/10"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L/2", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "delta_f", 1);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
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

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("T_s", "300[K]");
    model.component("comp1").variable("var1")
         .set("n_u", "n/delta_f*u_s/Q_s*(acpr.rho*acpr.flm1.Cp)/acpr.flm1.alpha_p");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(2, 3);
    model.component("comp1").variable("var2").set("T_s", "1200[K]");
    model.component("comp1").variable("var2")
         .set("n_u", "n/delta_f*u_s/Q_s*(acpr.rho*acpr.flm1.Cp)/acpr.flm1.alpha_p");

    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "T_s");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_pressure", "P_s");
    model.component("comp1").physics("acpr").create("pr1", "Pressure", 1);
    model.component("comp1").physics("acpr").feature("pr1").selection().set(10);
    model.component("comp1").physics("acpr").create("flm1", "FlameModel", 2);
    model.component("comp1").physics("acpr").feature("flm1").selection().set(2);
    model.component("comp1").physics("acpr").feature("flm1").set("n_u", "n_u");
    model.component("comp1").physics("acpr").feature("flm1").set("tau_u", "tau_u");
    model.component("comp1").physics("acpr").feature("flm1").set("Q_s", "Q_s");
    model.component("comp1").physics("acpr").feature("flm1").set("U_s", "u_s");
    model.component("comp1").physics("acpr").feature("flm1").set("acoRef", "refBoundary");
    model.component("comp1").physics("acpr").feature("flm1").selection("selReferenceBoundary").set(4);
    model.component("comp1").physics("acpr").feature("flm1").set("revertNormalVector", true);

    model.component("comp1").mesh("mesh1").contribute("physics/acpr", false);
    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("eigsolver", "arpacknonlinear");
    model.study("std1").feature("eig").set("eigmethod", "region");
    model.study("std1").feature("eig").set("appnreigs", 5);
    model.study("std1").feature("eig").set("maxnreigs", 10);
    model.study("std1").feature("eig").set("eigsr", 100);
    model.study("std1").feature("eig").set("eiglr", 2000);
    model.study("std1").feature("eig").set("eigsi", -200);
    model.study("std1").feature("eig").set("eigli", 200);
    model.study("std1").feature("eig").set("scalingnep", 100);
    model.study("std1").feature("eig").set("chkeigregion", false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Q_s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "W/m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "Q_s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "W/m^3", 0);
    model.study("std1").feature("param").setIndex("pname", "n", 0);
    model.study("std1").feature("param").setIndex("plistarr", "{0, 5}", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("con1").set("colortable", "Wave");
    model.result("pg1").feature("con1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)", 3);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1/s", 3);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7279\u5f81\u9891\u7387\u7684\u6570\u503c\u89e3\u548c\u89e3\u6790\u89e3");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").setIndex("looplevelinput", "first", 0);
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("xlabel", "\u9891\u7387 [Hz]");
    model.result("pg3").set("ylabel", "Imag(freq) [Hz]");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg3").feature("tblp1").set("xaxisdata", 2);
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg3").feature("tblp1").set("imagplot", true);
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linemarker", "circle");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "COMSOL, n=0", 0);
    model.result("pg3").feature("tblp1").create("filt1", "TableFilter");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").feature("filt1").set("expr", "col1==0");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").setIndex("legends", "COMSOL, n=5", 0);
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").feature("filt1").set("expr", "col1==5");
    model.result("pg3").create("lnsg1", "LineSegments");
    model.result("pg3").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg3").feature("lnsg1").set("linewidth", "preference");
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "real(omega1Res3)/(2*pi)", 0);
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "c1/L", 1);
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "real(omega3Res3)/(2*pi)", 2);
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "real(omega4Res3)/(2*pi)", 3);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "imag(omega1Res3)/(2*pi)", 0);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", 0, 1);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "imag(omega3Res3)/(2*pi)", 2);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "imag(omega4Res3)/(2*pi)", 3);
    model.result("pg3").feature("lnsg1").set("linestyle", "none");
    model.result("pg3").feature("lnsg1").set("linecolor", "blue");
    model.result("pg3").feature("lnsg1").set("linemarker", "plus");
    model.result("pg3").feature("lnsg1").set("legend", true);
    model.result("pg3").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg3").feature("lnsg1").setIndex("legends", "\u89e3\u6790\uff0cn=0", 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lnsg2", "lnsg1");
    model.result("pg3").run();
    model.result("pg3").feature("lnsg2").set("data", "dset2");
    model.result("pg3").feature("lnsg2").setIndex("looplevelinput", "last", 1);
    model.result("pg3").feature("lnsg2").setIndex("looplevelinput", "first", 0);
    model.result("pg3").feature("lnsg2").set("linecolor", "green");
    model.result("pg3").feature("lnsg2").setIndex("legends", "\u89e3\u6790\uff0cn=5", 0);
    model.result("pg3").run();
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").set("data", "dset2");
    model.result().numerical("av1").selection().set(2);
    model.result().numerical("av1").setIndex("expr", "freq", 0);
    model.result().numerical("av1").setIndex("expr", "realdot(p,acpr.flm1.Q_heat)", 1);
    model.result().numerical("av1").label("\u745e\u5229\u51c6\u5219");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u745e\u5229\u51c6\u5219");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayaxis", "y");
    model.result("pg4").label("\u58f0\u5b66\u6a21\u5f0f");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").feature("surf1").set("data", "dset2");
    model.result("pg4").feature("surf1").set("looplevel", new int[]{1, 1});
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("looplevel", new int[]{2, 1});
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("looplevel", new int[]{3, 1});
    model.result("pg4").feature().duplicate("surf4", "surf3");
    model.result("pg4").feature("surf4").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").set("looplevel", new int[]{4, 1});
    model.result("pg4").run();

    model.title("\u71c3\u70e7\u706b\u7130\u9a8c\u8bc1");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u706b\u7130\u6a21\u578b \u57df\u7279\u5f81\u6765\u6a21\u62df\u58f0\u573a\u4e0e\u706b\u7130\u91ca\u653e\u7684\u70ed\u91cf\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u4e3a\u4e86\u7406\u89e3\u548c\u9884\u6d4b\u71c3\u6c14\u8f6e\u673a\u548c\u55b7\u6c14\u53d1\u52a8\u673a\u7684\u4e0d\u7a33\u5b9a\u58f0\u5b66\u6a21\u5f0f\uff08\u79f0\u4e3a\u71c3\u70e7\u4e0d\u7a33\u5b9a\u6027\uff09\uff0c\u5bf9\u8fd9\u79cd\u76f8\u4e92\u4f5c\u7528\u8fdb\u884c\u5efa\u6a21\u975e\u5e38\u91cd\u8981\u3002\u672c\u6a21\u578b\u7531\u4e00\u4e2a\u7b80\u5355\u7684\u4e8c\u7ef4\u7ba1\u9053\u51e0\u4f55\u548c\u4e00\u4e2a\u7d27\u51d1\u7684\u706b\u7130\u533a\u57df\u7ec4\u6210\uff0c\u8fd9\u79cd\u7b80\u5355\u7684\u51e0\u4f55\u7ed3\u6784\u4f7f\u6211\u4eec\u80fd\u591f\u6839\u636e\u89e3\u6790\u89e3\u9a8c\u8bc1\u4eff\u771f\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("active_flame_validation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
