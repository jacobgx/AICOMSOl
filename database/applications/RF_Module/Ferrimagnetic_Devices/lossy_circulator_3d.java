/*
 * lossy_circulator_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:34 by COMSOL 6.3.0.290. */
public class lossy_circulator_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Ferrimagnetic_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("sc_chamfer", "3");
    model.param().descr("sc_chamfer", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50");
    model.param().set("sc_ferrite", "0.5");
    model.param().descr("sc_ferrite", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50");

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1")
         .set("eps0", "8.854187817e-12[F/m]", "\u81ea\u7531\u7a7a\u95f4\u7684\u4ecb\u7535\u5e38\u6570");
    model.variable("var1").set("mu0", "4e-7*pi[H/m]", "\u81ea\u7531\u7a7a\u95f4\u78c1\u5bfc\u7387");
    model.variable("var1").set("w", "2*pi*freq", "\u89d2\u9891\u7387");
    model.variable("var1").set("gamma", "1.759e11[C/kg]", "\u65cb\u78c1\u6bd4");
    model.variable("var1").set("H0", "(100*1e3/(4*pi))[A/m]", "\u5916\u52a0\u504f\u7f6e\u78c1\u573a");
    model.variable("var1").set("w0", "mu0*gamma*H0", "\u62c9\u83ab\u5c14\u9891\u7387");
    model.variable("var1").set("Ms", "680e-4[Wb/m^2]/mu0", "\u9971\u548c\u78c1\u5316\u5f3a\u5ea6");
    model.variable("var1")
         .set("wm", "mu0*gamma*Ms", "\u9971\u548c\u6781\u9650\u4e0b\u7684\u62c9\u83ab\u5c14\u9891\u7387");
    model.variable("var1").set("dH", "(40*1e3/(4*pi))[A/m]", "\u7ebf\u5bbd");
    model.variable("var1").set("alpha", "dH*mu0*gamma/(2*w)", "\u963b\u5c3c\u7cfb\u6570");
    model.variable("var1")
         .set("chim_xx_p", "(w0*wm*(w0^2-w^2)+w0*wm*w^2*alpha^2)/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u5b9e\u90e8");
    model.variable("var1")
         .set("chim_xx_b", "(alpha*w*wm*(w0^2+w^2*(1+alpha^2)))/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u865a\u90e8");
    model.variable("var1")
         .set("chim_xy_p", "(w*wm*(w0^2-w^2*(1+alpha^2)))/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u5b9e\u90e8");
    model.variable("var1")
         .set("chim_xy_b", "2*w0*wm*w^2*alpha/((w0^2-w^2*(1+alpha^2))^2+4*(w0*w*alpha)^2)", "\u78c1\u5316\u7387\uff0c\u865a\u90e8");
    model.variable("var1").set("chim_xx", "chim_xx_p-j*chim_xx_b", "\u590d\u78c1\u5316\u7387");
    model.variable("var1").set("chim_xy", "chim_xy_b+j*chim_xy_p", "\u590d\u78c1\u5316\u7387");
    model.variable("var1").set("murxx", "(1+chim_xx)", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murxy", "chim_xy", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murxz", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryx", "-chim_xy", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryy", "murxx", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("muryz", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzx", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzy", "0", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("murzz", "1", "\u590d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.variable("var1").set("tdeltae", "0.0002", "\u6709\u6548\u635f\u8017\u89d2\u6b63\u5207");
    model.variable("var1").set("eps_r_p", "14.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5b9e\u90e8");
    model.variable("var1").set("eps_r_b", "14.5*tdeltae", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u865a\u90e8");
    model.variable("var1").set("eps_r", "eps_r_p-j*eps_r_b", "\u590d\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.2-0.1/(3*sqrt(3))", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "0.2/3", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-0.2", "-0.1/3"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", 120);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy2").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("copy2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", -120);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "rot1", "rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "0.2/(3*sqrt(3))");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy3", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy3").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("copy3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot3").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot3").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy4", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy4").selection("input").set("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").set("isotropic", "sc_chamfer");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").selection("input").set("copy4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("sca1", "uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca2").selection("input").set("rot3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca2").set("isotropic", "sc_ferrite");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.1/3", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");

    model.title("\u53c2\u6570\u5316\u73af\u884c\u5668\u51e0\u4f55\u7ed3\u6784");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5176\u4e2d\u5305\u542b\u201c\u6709\u635f\u94c1\u6c27\u4f53\u4e09\u7aef\u53e3\u73af\u884c\u5668\u7684\u963b\u6297\u5339\u914d\u201d\u6a21\u578b\u7684\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("lossy_circulator_3d_geom.mph");

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

    model.component("comp1").physics("emw").create("wee2", "WaveEquationElectric", 3);
    model.component("comp1").physics("emw").feature("wee2").selection().set(2);
    model.component("comp1").physics("emw").feature("wee2").set("DisplacementFieldModel", "DielectricLoss");
    model.component("comp1").physics("emw").feature("wee2").set("epsilonPrim_mat", "userdef");
    model.component("comp1").physics("emw").feature("wee2")
         .set("epsilonPrim", new String[]{"eps_r_p", "0", "0", "0", "eps_r_p", "0", "0", "0", "eps_r_p"});
    model.component("comp1").physics("emw").feature("wee2").set("epsilonBis_mat", "userdef");
    model.component("comp1").physics("emw").feature("wee2")
         .set("epsilonBis", new String[]{"eps_r_b", "0", "0", "0", "eps_r_b", "0", "0", "0", "eps_r_b"});
    model.component("comp1").physics("emw").feature("wee2").set("mur_mat", "userdef");
    model.component("comp1").physics("emw").feature("wee2")
         .set("mur", new String[]{"murxx", "muryx", "murzx", "murxy", "muryy", "murzy", "murxz", "muryz", "murzz"});
    model.component("comp1").physics("emw").feature("wee2").set("sigma_mat", "userdef");
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(1);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(18);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port3", "Port", 2);
    model.component("comp1").physics("emw").feature("port3").selection().set(19);
    model.component("comp1").physics("emw").feature("port3").set("PortType", "Rectangular");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "1.5e-2");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "4.5e-3");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "3[GHz]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17, 18, 19);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().set(8, 9, 14);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("expr", "emw.normE");
    model.result("pg2").feature("surf3").create("sel1", "Selection");
    model.result("pg2").feature("surf3").feature("sel1").selection().set(1, 18, 19);
    model.result("pg2").feature("surf3").set("colortable", "Dipole");
    model.result("pg2").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf3").create("tran1", "Transparency");
    model.result("pg2").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-0.6067209140114163, -0.7883431600487751, 0.5985037140224292});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 31.330180168151855);

    model.result("pg2").set("view", "view3");
    model.result("pg1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "sc_chamfer", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "sc_chamfer", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "sc_ferrite", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.5,3e-3,0.53)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (emw) 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 11, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg3").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"S11", "S21", "S31"});
    model.result("pg4").label("S \u53c2\u6570 (emw)");
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg4").feature("glob1").set("xdataexpr", "sc_ferrite");
    model.result("pg4").feature("glob1").set("xdataunit", "");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg5", "SmithGroup");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("rgr1", "ReflectionGraph");
    model.result("pg5").feature("rgr1").set("unit", new String[]{""});
    model.result("pg5").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg5").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg5").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg5").feature("rgr1").set("titletype", "manual");
    model.result("pg5").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg5").feature("rgr1").set("linemarker", "point");
    model.result("pg5").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("rgr1").create("col1", "Color");
    model.result("pg5").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg5").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17, 18, 19);

    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "emw.normE");
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg6").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "emw.normE");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(8, 9, 14);
    model.result("pg6").feature("surf2").set("colortable", "Dipole");
    model.result("pg6").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf2").create("tran1", "Transparency");
    model.result("pg6").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg6").create("surf3", "Surface");
    model.result("pg6").feature("surf3").set("expr", "emw.normE");
    model.result("pg6").feature("surf3").create("sel1", "Selection");
    model.result("pg6").feature("surf3").feature("sel1").selection().set(1, 18, 19);
    model.result("pg6").feature("surf3").set("colortable", "Dipole");
    model.result("pg6").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg6").feature("surf3").create("tran1", "Transparency");
    model.result("pg6").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-0.01546358284742936, 0, 1.1564441349195398});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 39.54187870025635);

    model.result("pg6").set("view", "view4");
    model.result("pg3").run();

    model.param().set("sc_ferrite", "0.518");

    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("xdataexpr", "sc_chamfer");

    model.study("std1").feature("param").setIndex("pname", "sc_chamfer", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(2.8,0.04,3.2)", 0);
    model.study("std1").createAutoSequences("all");

    model.batch("p1").run("compute");

    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").set("plist", "range(2.8[GHz],20[MHz],3.2[GHz])");

    model.component("comp1").geom("geom1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol14").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (emw) 2");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 21, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg7").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg8").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"S11", "S21", "S31"});
    model.result("pg8").label("S \u53c2\u6570 (emw) 1");
    model.result("pg8").feature("glob1").set("titletype", "none");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg8").feature("glob1").set("xdataexpr", "freq");
    model.result("pg8").feature("glob1").set("xdataunit", "GHz");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg9", "SmithGroup");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("rgr1", "ReflectionGraph");
    model.result("pg9").feature("rgr1").set("unit", new String[]{""});
    model.result("pg9").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg9").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg9").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg9").feature("rgr1").set("titletype", "manual");
    model.result("pg9").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg9").feature("rgr1").set("linemarker", "point");
    model.result("pg9").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("rgr1").create("col1", "Color");
    model.result("pg9").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg9").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").label("\u7535\u573a, \u5bf9\u6570 (emw) 2");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17, 18, 19);

    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "emw.normE");
    model.result("pg10").feature("surf1").create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf1").create("tran1", "Transparency");
    model.result("pg10").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg10").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg10").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg10").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg10").feature("surf1").set("expr", "1");
    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("expr", "emw.normE");
    model.result("pg10").feature("surf2").create("sel1", "Selection");
    model.result("pg10").feature("surf2").feature("sel1").selection().set(8, 9, 14);
    model.result("pg10").feature("surf2").set("colortable", "Dipole");
    model.result("pg10").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf2").create("tran1", "Transparency");
    model.result("pg10").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg10").create("surf3", "Surface");
    model.result("pg10").feature("surf3").set("expr", "emw.normE");
    model.result("pg10").feature("surf3").create("sel1", "Selection");
    model.result("pg10").feature("surf3").feature("sel1").selection().set(1, 18, 19);
    model.result("pg10").feature("surf3").set("colortable", "Dipole");
    model.result("pg10").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf3").create("tran1", "Transparency");
    model.result("pg10").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view5", "geom1");
    model.component("comp1").view("view5").camera()
         .set("position", new double[]{-0.01546358284742936, 0, 1.1564441349195398});
    model.component("comp1").view("view5").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view5").camera().set("zoomanglefull", 39.54187870025635);

    model.result("pg10").set("view", "view5");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();

    model.param().set("PortName", "1");
    model.param().descr("PortName", "\u7aef\u53e3\u540d\u79f0");

    model.component("comp1").physics("emw").prop("PortSweepSettings").set("useSweep", true);

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").set("plotgroup", "Default");
    model.study("std3").feature("freq").set("solnum", "auto");
    model.study("std3").feature("freq").set("notsolnum", "auto");
    model.study("std3").feature("freq").set("outputmap", new String[]{});
    model.study("std3").feature("freq").set("ngenAUX", "1");
    model.study("std3").feature("freq").set("goalngenAUX", "1");
    model.study("std3").feature("freq").set("ngenAUX", "1");
    model.study("std3").feature("freq").set("goalngenAUX", "1");
    model.study("std3").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std3").feature("freq").set("plist", "3[GHz]");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "sc_chamfer", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "sc_chamfer", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").feature("param").setIndex("pname", "PortName", 0);
    model.study("std3").feature("param").setIndex("plistarr", "1 2 3", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol16");
    model.sol("sol16").study("std3");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol16");
    model.batch("p2").run("compute");

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7535\u573a (emw) 3");
    model.result("pg11").set("data", "dset5");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").setIndex("looplevel", 3, 1);
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature().create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg11").feature("mslc1").set("smooth", "internal");
    model.result("pg11").feature("mslc1").set("data", "parent");
    model.result("pg11").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg11").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").set("data", "dset5");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", ""});
    model.result("pg12").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S12dB", "emw.S13dB", "emw.S21dB", "emw.S22dB", "emw.S23dB", "emw.S31dB", "emw.S32dB", "emw.S33dB"});
    model.result("pg12").feature("glob1")
         .set("descr", new String[]{"S11", "S12", "S13", "S21", "S22", "S23", "S31", "S32", "S33"});
    model.result("pg12").label("S \u53c2\u6570 (emw) 2");
    model.result("pg12").feature("glob1").set("titletype", "none");
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg12").feature("glob1").set("xdataexpr", "PortName");
    model.result("pg12").feature("glob1").set("xdataunit", "");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg12").feature("glob1").active(true);
    model.result().create("pg13", "SmithGroup");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").create("rgr1", "ReflectionGraph");
    model.result("pg13").feature("rgr1").set("unit", new String[]{"", "", ""});
    model.result("pg13").feature("rgr1").set("expr", new String[]{"emw.S11", "emw.S22", "emw.S33"});
    model.result("pg13").feature("rgr1").set("descr", new String[]{"S11", "S22", "S33"});
    model.result("pg13").label("\u53f2\u5bc6\u65af\u56fe (emw) 2");
    model.result("pg13").feature("rgr1").set("titletype", "manual");
    model.result("pg13").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg13").feature("rgr1").set("linemarker", "point");
    model.result("pg13").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg13").feature("rgr1").create("col1", "Color");
    model.result("pg13").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg13").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset5");
    model.result("pg14").label("\u7535\u573a, \u5bf9\u6570 (emw) 3");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17, 18, 19);

    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "emw.normE");
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 15, 16, 17);
    model.result("pg14").feature("surf1").set("colortable", "Dipole");
    model.result("pg14").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg14").feature("surf1").create("tran1", "Transparency");
    model.result("pg14").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg14").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg14").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg14").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg14").feature("surf1").set("expr", "1");
    model.result("pg14").create("surf2", "Surface");
    model.result("pg14").feature("surf2").set("expr", "emw.normE");
    model.result("pg14").feature("surf2").create("sel1", "Selection");
    model.result("pg14").feature("surf2").feature("sel1").selection().set(8, 9, 14);
    model.result("pg14").feature("surf2").set("colortable", "Dipole");
    model.result("pg14").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg14").feature("surf2").create("tran1", "Transparency");
    model.result("pg14").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg14").create("surf3", "Surface");
    model.result("pg14").feature("surf3").set("expr", "emw.normE");
    model.result("pg14").feature("surf3").create("sel1", "Selection");
    model.result("pg14").feature("surf3").feature("sel1").selection().set(1, 18, 19);
    model.result("pg14").feature("surf3").set("colortable", "Dipole");
    model.result("pg14").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg14").feature("surf3").create("tran1", "Transparency");
    model.result("pg14").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view6", "geom1");
    model.component("comp1").view("view6").camera()
         .set("position", new double[]{-0.01546358284742936, 0, 1.1564441349195398});
    model.component("comp1").view("view6").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view6").camera().set("zoomanglefull", 39.54187870025635);

    model.result("pg14").set("view", "view6");
    model.result("pg11").run();

    model.component("comp1").physics("emw").prop("PortSweepSettings").set("ExportTouchstone", true);
    model.component("comp1").physics("emw").prop("PortSweepSettings")
         .set("TouchstoneFile", "lossy_circulator_3d.s3p");

    model.result().numerical().create("gmev1", "EvalGlobalMatrix");
    model.result().numerical("gmev1").set("data", "dset5");
    model.result().numerical("gmev1").set("expr", "emw.SdB");
    model.result().numerical("gmev1").set("descr", "S \u53c2\u6570\uff0cdB");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u77e9\u9635\u8ba1\u7b97 1");
    model.result().numerical("gmev1").set("table", "tbl2");
    model.result().numerical("gmev1").setResult();
    model.result("pg10").run();

    model.component("comp1").view().create("view8", "geom1");
    model.component("comp1").view("view3").set("showgrid", false);

    model.result("pg11").run();
    model.result("pg11").set("view", "view3");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature().remove("mslc1");
    model.result("pg11").run();
    model.result("pg11").create("slc1", "Slice");
    model.result("pg11").feature("slc1").set("quickplane", "xy");
    model.result("pg11").feature("slc1").set("quickznumber", 1);
    model.result("pg11").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("slc1").create("def1", "Deform");
    model.result("pg11").run();
    model.result("pg11").feature("slc1").feature("def1").set("expr", new String[]{"emw.Ex", "emw.Ey", "emw.Ez"});
    model.result("pg11").feature("slc1").feature("def1").set("descr", "\u7535\u573a");
    model.result("pg11").feature("slc1").feature("def1").set("descractive", true);
    model.result("pg11").run();
    model.result("pg11").create("arwv1", "ArrowVolume");
    model.result("pg11").feature("arwv1").set("expr", new String[]{"emw.Hx", "emw.Hy", "emw.Hz"});
    model.result("pg11").feature("arwv1").set("descr", "\u78c1\u573a");
    model.result("pg11").feature("arwv1").set("descractive", true);
    model.result("pg11").feature("arwv1").set("xnumber", 45);
    model.result("pg11").feature("arwv1").set("ynumber", 45);
    model.result("pg11").feature("arwv1").set("arrowzmethod", "coord");
    model.result("pg11").feature("arwv1").set("zcoord", "0.1/3");
    model.result("pg11").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("arwv1").set("color", "green");
    model.result("pg11").run();
    model.result("pg11").setIndex("looplevel", 2, 1);
    model.result("pg11").run();
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevelinput", "manual", 1);
    model.result("pg12").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg12").run();
    model.result("pg12").feature("glob1").remove("unit", new int[]{1, 2, 4, 5, 7, 8});
    model.result("pg12").feature("glob1").remove("descr", new int[]{1, 2, 4, 5, 7, 8});
    model.result("pg12").feature("glob1").remove("expr", new int[]{1, 2, 4, 5, 7, 8});
    model.result("pg12").feature("glob1").set("linemarker", "cycle");
    model.result("pg12").run();

    model.title("\u6709\u635f\u94c1\u6c27\u4f53\u4e09\u7aef\u53e3\u73af\u884c\u5668\u7684\u963b\u6297\u5339\u914d");

    model
         .description("\u672c\u4f8b\u5bf9\u4e00\u4e2a\u5728 3\u00a0GHz \u4e0b\u5de5\u4f5c\u7684\u6709\u635f\u94c1\u6c27\u4f53\u73af\u884c\u5668\u8fdb\u884c\u5efa\u6a21\u4eff\u771f\uff0c\u901a\u8fc7\u6539\u53d8\u51e0\u4f55\u8bbe\u8ba1\u53c2\u6570\u4ee5\u540c\u963b\u6297\u5339\u914d\uff0c\u6700\u5927\u7a0b\u5ea6\u5730\u51cf\u5c11\u57fa\u672c TE10\u00a0\u77e9\u5f62\u6ce2\u5bfc\u6a21\u5f0f\u7684\u53cd\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();

    model.label("lossy_circulator_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
