/*
 * baffled_piston_radiation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class baffled_piston_radiation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("u0", "1[m/s]", "\u6d3b\u585e\u901f\u5ea6\u5e45\u503c");
    model.param().set("a", "0.1[m]", "\u6d3b\u585e\u534a\u5f84");
    model.param().set("Rmodel", "0.2[m]", "\u57df\u534a\u5f84");
    model.param().set("f0", "20000[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("lam0", "c0/f0", "f0 \u4e0b\u7684\u6ce2\u957f");
    model.param().set("ka0", "2*pi*a/lam0", "k0*a");
    model.param().set("R0", "ka0*a/2", "f0 \u7684\u745e\u5229\u8ddd\u79bb");
    model.param().set("Rfar", "20*Rmodel", "\u8fdc\u573a\u8bc4\u4f30\u8ddd\u79bb");
    model.param().set("Scale", "a/lam0", "\u52a9\u53d8\u91cf");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Rmodel");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "a", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u89e3\u6790\u538b\u529b\u573a");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("k", "intop_pt(acpr.k)*1[1/rad]", "\u6ce2\u6570");
    model.component("comp1").variable("var1").set("ka", "k*a", "k*a");
    model.component("comp1").variable("var1")
         .set("p0", "intop_pt(acpr.rho*acpr.c*u0)", "\u7279\u5b9a\u53c2\u8003\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("p_axis", "p0*(exp(-i*k*z)-exp(-i*k*sqrt(z^2+a^2)))", "\u8f74\u4e0a\u8fd1\u573a\u538b\u529b\uff08\u89e3\u6790\u89e3\uff09");
    model.component("comp1").variable("var1").set("R0", "k*a^2/2", "\u745e\u5229\u957f\u5ea6\u5c3a\u5ea6");
    model.component("comp1").variable("var1")
         .set("p_axis_ff", "p0*(exp(-i*k*z)-exp(-i*k*sqrt(z^2+a^2)))", "\u8f74\u4e0a\u8fdc\u573a\u538b\u529b\uff08\u89e3\u6790\u89e3\uff09");
    model.component("comp1").variable("var1")
         .set("P_ar", "(pi/2)*a^2*u0*p0*(1-2*besselj(1,2*acpr.k*a)/(2*acpr.k*a))", "\u58f0\u5b66\u8f90\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("p_ana", "i*a*p0/dis*besselj(1,ka*sin(theta))/sin(theta)*exp(-i*ka/a*dis)", "\u5206\u6790\u8fdc\u573a\u538b\u529b\uff08\u7a7a\u95f4\uff09");
    model.component("comp1").variable("var1").set("dis", "sqrt(r^2+z^2)", "\u5230\u539f\u70b9\u7684\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("theta", "atan2(r,z)", "\u6781\u89d2\uff08\u4ece z \u8f74\uff09");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf\uff1a\u8868\u9762\u963b\u6297\u548c\u529f\u7387");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Zp", "acpr.rho*acpr.c*(R1+i*X1)", "\u6d3b\u585e\u8868\u9762\u963b\u6297");
    model.component("comp1").variable("var2").set("R1", "1-2*besselj(1,2*ka)/(2*ka)", "\u6d3b\u585e\u51fd\u6570 R1");
    model.component("comp1").variable("var2").set("X1", "2*K1/(2*ka)", "\u6d3b\u585e\u51fd\u6570 X1");
    model.component("comp1").variable("var2")
         .set("K1", "2/pi-besselj(0,2*ka)+0.5*(-5+16/pi)*sin(2*ka)/(ka)+(12-36/pi)*(1-cos(2*ka))/(2*ka)^2", "Struve \u51fd\u6570\uff08\u8fd1\u4f3c\uff09");
    model.component("comp1").variable("var2")
         .set("W_ana", "intop_pt(pi*a^2*(u0^2)/2*real(Zp))", "\u8f90\u5c04\u529f\u7387\uff08\u89e3\u6790\u89e3\uff09");
    model.component("comp1").variable("var2")
         .set("W", "intop_out(acpr.Iz*acpr.nz+acpr.Ir*acpr.nr)", "\u8f90\u5c04\u529f\u7387");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_pt");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_out");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4);

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

    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "AtmosphereAttenuation");
    model.component("comp1").physics("acpr").create("nvel1", "NormalVelocity", 1);
    model.component("comp1").physics("acpr").feature("nvel1").selection().set(2);
    model.component("comp1").physics("acpr").feature("nvel1").set("nvel", "u0");
    model.component("comp1").physics("acpr").create("swr1", "SphericalWaveRadiation", 1);
    model.component("comp1").physics("acpr").feature("swr1").selection().set(4);
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(4);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "10^{range(log10(10),1/9,log10(20000))}");
    model.study("std1").feature("freq").set("preusesol", "yes");
    model.study("std1").showAutoSequences("all");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("pc1", "ParCurve2D");
    model.result().dataset("pc1").set("parmax1", "pi/2");
    model.result().dataset("pc1").set("exprx", "1.3*a*cos(s)");
    model.result().dataset("pc1").set("expry", "1.3*a*sin(s)");
    model.result().dataset().create("pc2", "ParCurve2D");
    model.result().dataset("pc2").set("expry", "1.01*Rmodel*(1-s)+Rfar*s");
    model.result().dataset("pc2").set("global", true);
    model.result().dataset().create("pc3", "ParCurve2D");
    model.result().dataset("pc3").set("global", true);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("pc3").set("expry", "Rfar*s");
    model.result().dataset("rev1").set("startangle", -50);
    model.result().dataset("rev1").set("revangle", 230);
    model.result().dataset().create("grid1", "Grid2D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("par1", "r");
    model.result().dataset("grid1").set("parmin1", "-1.5*Rmodel");
    model.result().dataset("grid1").set("parmax1", "1.5*Rmodel");
    model.result().dataset("grid1").set("par2", "z");
    model.result().dataset("grid1").set("parmax2", "3*Rmodel");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b");
    model.result("pg1").setIndex("looplevel", 28, 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8f74\u4e0a\u58f0\u538b");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "z/R0");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "abs(p) (Pa)");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "abs(p/p0)");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr1").set("linecolor", "blue");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg2").run();
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", "abs(p_axis/p0)");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr2").set("linestyle", "none");
    model.result("pg2").feature("lngr2").set("linecolor", "red");
    model.result("pg2").feature("lngr2").set("linewidth", 2);
    model.result("pg2").feature("lngr2").set("linemarker", "point");
    model.result("pg2").feature("lngr2").set("markerpos", "interp");
    model.result("pg2").feature("lngr2").set("markers", 100);
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u5168\u89e3\u6790\u89e3", 0);
    model.result("pg2").run();
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr3").set("linewidth", "preference");
    model.result("pg2").feature("lngr3").set("data", "pc2");
    model.result("pg2").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr3").set("expr", "abs(pext(0,z)/p0)");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr3").set("linecolor", "blue");
    model.result("pg2").feature("lngr3").set("linewidth", 2);
    model.result("pg2").feature("lngr3").set("legendmethod", "manual");
    model.result("pg2").feature("lngr3").setIndex("legends", "COMSOL FE \u6a21\u578b", 0);
    model.result("pg2").run();
    model.result("pg2").create("lngr4", "LineGraph");
    model.result("pg2").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr4").set("linewidth", "preference");
    model.result("pg2").feature("lngr4").set("data", "pc2");
    model.result("pg2").feature("lngr4").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr4").set("expr", "abs(p_axis_ff/p0)");
    model.result("pg2").feature("lngr4").set("xdata", "expr");
    model.result("pg2").feature("lngr4").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr4").set("linestyle", "none");
    model.result("pg2").feature("lngr4").set("linecolor", "red");
    model.result("pg2").feature("lngr4").set("linewidth", 2);
    model.result("pg2").feature("lngr4").set("linemarker", "point");
    model.result("pg2").feature("lngr4").set("markerpos", "interp");
    model.result("pg2").feature("lngr4").set("markers", 20);
    model.result("pg2").run();
    model.result("pg2").create("lngr5", "LineGraph");
    model.result("pg2").feature("lngr5").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr5").set("linewidth", "preference");
    model.result("pg2").feature("lngr5").set("data", "pc3");
    model.result("pg2").feature("lngr5").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr5").set("expr", "2/sqrt(1+(2*z/R0)^2)");
    model.result("pg2").feature("lngr5").set("xdata", "expr");
    model.result("pg2").feature("lngr5").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr5").set("linestyle", "dashed");
    model.result("pg2").feature("lngr5").set("linecolor", "green");
    model.result("pg2").feature("lngr5").set("linewidth", 2);
    model.result("pg2").feature("lngr5").set("legend", true);
    model.result("pg2").feature("lngr5").set("legendmethod", "manual");
    model.result("pg2").feature("lngr5").setIndex("legends", "\u6e10\u8fd1\u7ebf M \u548c MM", 0);
    model.result("pg2").run();
    model.result("pg2").create("lngr6", "LineGraph");
    model.result("pg2").feature("lngr6").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr6").set("linewidth", "preference");
    model.result("pg2").feature("lngr6").set("data", "pc3");
    model.result("pg2").feature("lngr6").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr6").set("expr", "1/sqrt(1+(z/R0)^2)");
    model.result("pg2").feature("lngr6").set("xdata", "expr");
    model.result("pg2").feature("lngr6").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr6").set("linestyle", "dashed");
    model.result("pg2").feature("lngr6").set("linecolor", "green");
    model.result("pg2").feature("lngr6").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").create("lngr7", "LineGraph");
    model.result("pg2").feature("lngr7").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr7").set("linewidth", "preference");
    model.result("pg2").feature("lngr7").set("data", "pc2");
    model.result("pg2").feature("lngr7").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr7").set("expr", "abs(i*R0/z*exp(-i*k*z))");
    model.result("pg2").feature("lngr7").set("xdata", "expr");
    model.result("pg2").feature("lngr7").set("xdataexpr", "z/R0");
    model.result("pg2").feature("lngr7").set("linestyle", "dashed");
    model.result("pg2").feature("lngr7").set("linecolor", "black");
    model.result("pg2").feature("lngr7").set("linewidth", 2);
    model.result("pg2").feature("lngr7").set("legend", true);
    model.result("pg2").feature("lngr7").set("legendmethod", "manual");
    model.result("pg2").feature("lngr7").setIndex("legends", "\u8fdc\u573a\u6781\u9650", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").run();
    model.result("pg3").label("\u8fd1\u573a r=1.3a");
    model.result("pg3").set("data", "pc1");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{28}, 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "f=eval(freq) Hz \u65f6\u7684\u8fd1\u573a\u58f0\u538b\u7ea7");
    model.result("pg3").set("symmetricangle", true);
    model.result("pg3").set("zeroangle", "up");
    model.result("pg3").set("rotdir", "cw");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "acpr.Lp");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "theta");
    model.result("pg3").feature("lngr1").set("xdataunit", "1");
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("expr", "acpr.Lp");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "-theta");
    model.result("pg3").feature("lngr2").set("xdataunit", "1");
    model.result("pg3").feature("lngr2").set("linecolor", "blue");
    model.result("pg3").run();
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").run();
    model.result("pg4").label("\u8fdc\u573a r=Rfar");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{28}, 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "f=eval(freq) Hz \u65f6\u7684\u8fdc\u573a\u58f0\u538b\u7ea7");
    model.result("pg4").set("symmetricangle", true);
    model.result("pg4").set("zeroangle", "up");
    model.result("pg4").set("rotdir", "cw");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("markerpos", "datapoints");
    model.result("pg4").feature("rp1").set("linewidth", "preference");
    model.result("pg4").feature("rp1").set("phidisc", 180);
    model.result("pg4").feature("rp1").set("anglerestr", "manual");
    model.result("pg4").feature("rp1").set("phimin", -90);
    model.result("pg4").feature("rp1").set("phirange", 180);
    model.result("pg4").feature("rp1").set("radius", "Rfar");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("rp2", "RadiationPattern");
    model.result("pg4").feature("rp2").set("markerpos", "datapoints");
    model.result("pg4").feature("rp2").set("linewidth", "preference");
    model.result("pg4").feature("rp2").set("expr", "10*log10(0.5*p_ana*conj(p_ana)/(20e-6)^2)");
    model.result("pg4").feature("rp2").set("phidisc", 180);
    model.result("pg4").feature("rp2").set("anglerestr", "manual");
    model.result("pg4").feature("rp2").set("phimin", -90);
    model.result("pg4").feature("rp2").set("phirange", 180);
    model.result("pg4").feature("rp2").set("radius", "Rfar");
    model.result("pg4").feature("rp2").set("legend", true);
    model.result("pg4").feature("rp2").set("legendmethod", "manual");
    model.result("pg4").feature("rp2").setIndex("legends", "\u89e3\u6790\u89e3\uff08\u8fdc\u573a\uff09", 0);
    model.result("pg4").feature("rp2").set("linestyle", "none");
    model.result("pg4").feature("rp2").set("linemarker", "point");
    model.result("pg4").feature("rp2").set("markerpos", "interp");
    model.result("pg4").feature("rp2").set("markers", 180);
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("\u6ce2\u675f\u5bbd\u5ea6\u6781\u5750\u6807");
    model.result("pg5").set("symmetricangle", true);
    model.result("pg5").set("zeroangle", "up");
    model.result("pg5").set("rotdir", "cw");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rp1").set("linewidth", "preference");
    model.result("pg5").feature("rp1").set("phidisc", 90);
    model.result("pg5").feature("rp1").set("anglerestr", "manual");
    model.result("pg5").feature("rp1").set("phimin", -90);
    model.result("pg5").feature("rp1").set("phirange", 180);
    model.result("pg5").feature("rp1").set("beamwidth", true);
    model.result("pg5").feature("rp1").set("leveldown", 3);
    model.result("pg5").feature("rp1").set("radius", "Rfar");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6ce2\u675f\u5bbd\u5ea6");
    model.result("pg6").set("data", "none");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f (Hz)");
    model.result("pg6").set("ylabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").set("ylabel", "\u6ce2\u675f\u5bbd\u5ea6 (deg)");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("data", "dset1");
    model.result("pg6").feature("glob1").setIndex("expr", "2*asin(1.616/ka)/pi*180", 0);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u6ce2\u675f\u5bbd\u5ea6 (-3 dB)\uff0c\u89e3\u6790\u89e3", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "2*asin(0.610*2*pi/ka)/pi*180", 1);
    model.result("pg6").feature("glob1")
         .setIndex("descr", "\u96f6\u70b9\u5230\u96f6\u70b9\u6ce2\u675f\u5bbd\u5ea6\uff0c\u89e3\u6790\u89e3", 1);
    model.result("pg6").feature("glob1").set("linestyle", "none");
    model.result("pg6").feature("glob1").set("linemarker", "point");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").feature("glob1").set("markers", 50);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u8f90\u5c04\u529f\u7387");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f (Hz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u529f\u7387 (W)");
    model.result("pg7").set("xlog", true);
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "W", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "COMSOL \u6a21\u578b", 0);
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").create("glob2", "Global");
    model.result("pg7").feature("glob2").set("markerpos", "datapoints");
    model.result("pg7").feature("glob2").set("linewidth", "preference");
    model.result("pg7").feature("glob2").setIndex("expr", "W_ana", 0);
    model.result("pg7").feature("glob2").set("linestyle", "none");
    model.result("pg7").feature("glob2").set("linecolor", "red");
    model.result("pg7").feature("glob2").set("linewidth", 2);
    model.result("pg7").feature("glob2").set("linemarker", "point");
    model.result("pg7").feature("glob2").set("markerpos", "interp");
    model.result("pg7").feature("glob2").set("markers", 50);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u5916\u573a");
    model.result("pg8").set("data", "grid1");
    model.result("pg8").setIndex("looplevel", 26, 0);
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("data", "mir1");
    model.result("pg8").feature("surf1").setIndex("looplevel", 26, 0);
    model.result("pg8").feature("surf1").create("hght1", "Height");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg8").feature("surf1").feature("hght1").set("scale", "1.E-4");
    model.result("pg8").feature("surf1").feature("hght1").set("offset", 0.15);
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("expr", "pext(r,z)");
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").create("filt1", "Filter");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("filt1").set("expr", "sqrt(z^2+r^2)>1.01*Rmodel");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").create("hght1", "Height");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("hght1").set("offset", 0.15);
    model.result("pg8").run();
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("data", "mir1");
    model.result("pg8").feature("con1").setIndex("looplevel", 26, 0);
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").run();
    model.result("pg8").create("con2", "Contour");
    model.result("pg8").feature("con2").set("expr", "pext(r,z)");
    model.result("pg8").feature("con2").set("colorlegend", false);
    model.result("pg8").feature("con2").set("inheritplot", "con1");
    model.result("pg8").feature("con2").create("filt1", "Filter");
    model.result("pg8").run();
    model.result("pg8").feature("con2").feature("filt1").set("expr", "sqrt(z^2+r^2)>1.01*Rmodel");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u7f29\u7565\u56fe");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "acpr.Lp");
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").run();

    model.title("\u5e26\u6321\u677f\u7684\u6d3b\u585e\u8f90\u5c04");

    model
         .description("\u672c\u4f8b\u4ee5\u65e0\u9650\u6321\u677f\u4e2d\u521a\u6027\u6d3b\u585e\u7684\u8f74\u5bf9\u79f0\u6a21\u578b\u4e3a\u4f8b\uff0c\u6f14\u793a\u4e86\u201c\u58f0\u5b66\u6a21\u5757\u201d\u7684\u201c\u5916\u573a\u8ba1\u7b97\u201d\u7279\u5f81\uff1b\u5e76\u5c06 COMSOL Multiphysics\u00ae \u8f6f\u4ef6\u63d0\u4f9b\u7684\u8f90\u5c04\u7ed3\u679c\u4e0e\u8f74\u4e0a\u8f90\u5c04\u65b9\u5411\u56fe\uff08\u968f\u8ddd\u79bb\u53d8\u5316\uff09\u3001\u7a7a\u95f4\u8fdc\u573a\u54cd\u5e94\u4ee5\u53ca\u603b\u8f90\u5c04\u529f\u7387\u7684\u5206\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u5728\u8fd9\u4e2a\u8fc7\u7a0b\u4e2d\uff0c\u4ece\u8fd1\u573a\u5230\u8fdc\u573a\u7684\u8fc7\u6e21\u662f\u5e73\u6ed1\u4e14\u8fde\u7eed\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("baffled_piston_radiation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
