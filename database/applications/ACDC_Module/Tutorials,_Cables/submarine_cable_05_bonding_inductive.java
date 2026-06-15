/*
 * submarine_cable_05_bonding_inductive.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_05_bonding_inductive {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dcon", "26.2[mm]", "\u4e3b\u5bfc\u4f53\u76f4\u5f84\uff08\u76f8\uff09");
    model.param().set("Tins", "24.0[mm]", "\u7edd\u7f18\u539a\u5ea6 (XLPE)");
    model.param().set("Dins", "77.6[mm]", "\u7edd\u7f18\u76f4\u5f84\uff08XLPE \u548c SCC\uff09");
    model.param().set("Tscc", "(Dins/2-Dcon/2-Tins)/2", "\u534a\u5bfc\u4f53\u5316\u5408\u7269\u539a\u5ea6");
    model.param().set("Tpbs", "2.9[mm]", "\u94c5\u5305\u539a\u5ea6");
    model.param().set("Tpe", "2.9[mm]", "\u805a\u4e59\u70ef\u62a4\u5957\u539a\u5ea6");
    model.param()
         .set("Dpha", "Dins+2*Tpbs+2*Tpe", "\u76f8\u4f4d\u76f4\u5f84\uff08\u5305\u542b\u62a4\u5957\u548c PE\uff09");
    model.param().set("Dpha3", "Dpha*(2/sqrt(3)+1)", "\u4e09\u76f8\u7ed3\u5408\u7684\u76f4\u5f84");
    model.param().set("Dfic", "2.5[mm]", "\u5149\u7ea4\u7ea4\u82af\u76f4\u5f84");
    model.param().set("Tfih", "0.5[mm]", "\u94a2\u87ba\u65cb\u7ebf\u539a\u5ea6\uff08\u5149\u7ea4\uff09");
    model.param().set("Dfib", "9.2[mm]", "\u5149\u7f06\u76f4\u5f84");
    model.param().set("Dcab", "219.0[mm]", "\u6d77\u5e95\u7535\u7f06\u5916\u5f84");
    model.param().set("Darm", "(Dcab+Dpha3)/2", "\u94e0\u88c5\u73af\u4e2d\u5fc3\u76f4\u5f84");
    model.param().set("Tarm", "5.6[mm]", "\u94e0\u88c5\u539a\u5ea6\uff08\u7ebf\u5f84\uff09");
    model.param().set("Narm", "110", "\u73af\u4e2d\u7684\u94e0\u88c5\u7ebf\u6570");
    model.param().set("mfil", "0.5[mm]", "\u586b\u6599\u4f59\u91cf");
    model.param().set("marm", "pi*Darm/Narm-Tarm", "\u94e0\u88c5\u4f59\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570 2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Acon", "500[mm^2]", "\u4e3b\u5bfc\u4f53\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2")
         .set("Ncon", "Acon/(pi*(Dcon/2)^2)", "\u5bfc\u4f53\u5806\u79ef\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par2")
         .set("Apbs", "pi*(Dins+Tpbs)*Tpbs", "\u94c5\u5305\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2").set("Lsec1", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 1");
    model.param("par2")
         .set("Lsec2", "1-Lsec1-Lsec3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 2");
    model.param("par2").set("Lsec3", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 3");
    model.param("par2").set("Lcab", "10[km]", "\u6d77\u5e95\u7535\u7f06\u603b\u957f");
    model.param("par2")
         .set("Scab", "1e5", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff09");
    model.param("par2").set("Lsec1", "1");
    model.param("par2").set("Lsec3", "0");
    model.param().create("par3");
    model.param("par3").label("\u7535\u78c1\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("f0", "50[Hz]", "\u5de5\u4f5c\u9891\u7387");
    model.param("par3").set("w0", "(2*pi*f0[1/Hz])[rad/s]", "\u89d2\u9891\u7387");
    model.param("par3").set("V0", "220[kV]/sqrt(3)", "\u76f8\u5bf9\u5730\u7535\u538b\uff08\u5e45\u503c\uff09");
    model.param("par3").set("I0", "655[A]*sqrt(2)", "\u989d\u5b9a\u7535\u6d41\uff08\u5e45\u503c\uff09");
    model.param("par3").set("Scup", "5.96e7[S/m]", "\u94dc\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Spbs", "4.55e6[S/m]", "\u94c5\u5305\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Sarm", "4.03e6[S/m]", "\u94e0\u88c5\u7ebf\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Mcup", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94dc");
    model.param("par3").set("Mpbs", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94c5\u5305");
    model.param("par3").set("Marm", "100-50*j", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94e0\u88c5\u7ebf");
    model.param("par3")
         .set("Dscup", "min(1/real(sqrt(j*w0*mu0_const*Mcup*Scup)),Dcon/3)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94dc\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dspbs", "min(1/real(sqrt(j*w0*mu0_const*Mpbs*Spbs)),12*Tpbs)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94c5\u5305\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dsarm", "min(1/real(sqrt(j*w0*mu0_const*Marm*Sarm)),Tarm/2)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94e0\u88c5\u7ebf\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rcon", "1/Acon/Scup", "\u6bcf\u76f8\u4e3b\u5bfc\u4f53\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rpbs", "1/Apbs/Spbs", "\u6bcf\u76f8\u94c5\u5305\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Exlpe", "2.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570 XLPE\uff08\u53d6\u81ea IEC 60287\uff09");
    model.param("par3")
         .set("Cpha", "2*pi*epsilon0_const*Exlpe/log((Dins/2-Tscc)/(Dcon/2+Tscc))", "\u6bcf\u76f8\u7535\u5bb9\uff08\u89e3\u6790\uff09");
    model.param("par3").set("Icpha", "w0*Cpha*V0", "\u6bcf\u76f8\u5145\u7535\u7535\u6d41\uff08\u89e3\u6790\uff09");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u76f8");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Dcon/2");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").label("\u5c4f\u853d\u5c42");
    model.component("comp1").geom("geom1").feature("c2").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Dins/2+Tpbs");
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "Tpbs", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("c1", "c2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "0[deg], 120[deg], 240[deg]");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").label("\u7535\u7f06\u94e0\u88c5");
    model.component("comp1").geom("geom1").feature("c3").set("r", "Tarm/2");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("c3").set("selresult", true);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").named("c3");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "360[deg]*range(1/Narm,1/Narm,1)");
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").label("\u7535\u7f06\u57df");
    model.component("comp1").geom("geom1").feature("c4").set("r", "Dcab/2");
    model.component("comp1").geom("geom1").feature("c4").set("selresult", true);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").feature().duplicate("c5", "c4");
    model.component("comp1").geom("geom1").feature("c5").label("\u7535\u78c1\u57df");
    model.component("comp1").geom("geom1").feature("c5").set("r", "5*Dcab/2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u91d1\u5c5e");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_c1_dom", "geom1_c2_dom", "geom1_c3_dom"});

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().named("uni1");

    model.component("comp1").view("view1").set("showmaterial", true);

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
    model.component("comp1").material("mat2").label("\u94dc");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").selection().named("geom1_c1_dom");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u94c5");
    model.component("comp1").material("mat3").selection().named("geom1_c2_dom");
    model.component("comp1").material("mat3").set("family", "lead");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u9540\u950c\u94a2");
    model.component("comp1").material("mat4").selection().named("geom1_c3_dom");
    model.component("comp1").material("mat4").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas")
         .set("ratioofspecificheat", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", new String[]{"x"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", new String[]{"0.02897[kg/mol]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", new String[]{"muB(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"eta(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", new String[]{"1.4"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"cs(T)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel")
         .set("BA", new String[]{"(def.gamma+1)/2"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", new String[]{"R_const/Mn"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", new String[]{"Cp(T)"});
    model.component("comp1").material("mat1").propertyGroup("idealGas")
         .set("ratioofspecificheat", new String[]{"1.4"});
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", new String[]{"0.02897"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"Mcup"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"Ncon*Scup"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"Mpbs"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"Spbs"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"Marm"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"Sarm"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("mf").label("\u78c1\u573a\uff0c\u7b2c 1 \u6bb5");
    model.component("comp1").physics("mf").prop("d").set("d", "Lsec1*Lcab");
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").label("\u76f8 1");
    model.component("comp1").physics("mf").feature("coil1").selection().set(51);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I0");
    model.component("comp1").physics("mf").create("coil2", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil2").label("\u76f8 2");
    model.component("comp1").physics("mf").feature("coil2").selection().set(80);
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I0*exp(-120[deg]*j)");
    model.component("comp1").physics("mf").create("coil3", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil3").label("\u76f8 3");
    model.component("comp1").physics("mf").feature("coil3").selection().set(50);
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "I0*exp(+120[deg]*j)");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorcalibration", -1.5);
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("con1").set("number", 20);
    model.result("pg1").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u76f8\u635f\u8017");
    model.result().numerical("int1").selection().set(2);
    model.result().numerical("int1").selection().named("geom1_c1_dom");
    model.result().numerical("int1").setIndex("expr", "mf.Qh", 0);
    model.result().numerical("int1").setIndex("unit", "W/km", 0);
    model.result().numerical("int1").setIndex("descr", "\u76f8\u635f\u8017\uff08\u7d27\u56fa\u4e92\u8054\uff09", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u76f8\u635f\u8017");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").label("\u5c4f\u853d\u5c42\u635f\u8017");
    model.result().numerical("int2").selection().named("geom1_c2_dom");
    model.result().numerical("int2").setIndex("expr", "mf.Qh", 0);
    model.result().numerical("int2").setIndex("unit", "W/km", 0);
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u7d27\u56fa\u4e92\u8054\uff09", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5c4f\u853d\u5c42\u635f\u8017");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u76f8\u4ea4\u6d41\u7535\u963b");
    model.result().numerical("gev1").setIndex("expr", "(mf.RCoil_1+mf.RCoil_2+mf.RCoil_3)/(3*Lsec1*Lcab)", 0);
    model.result().numerical("gev1").setIndex("unit", "mohm/km", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u7d27\u56fa\u4e92\u8054\uff09", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u76f8\u4ea4\u6d41\u7535\u963b");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u76f8\u7535\u611f");
    model.result().numerical("gev2").setIndex("expr", "(mf.LCoil_1+mf.LCoil_2+mf.LCoil_3)/(3*Lsec1*Lcab)", 0);
    model.result().numerical("gev2").setIndex("unit", "mH/km", 0);
    model.result().numerical("gev2").setIndex("descr", "\u76f8\u7535\u611f\uff08\u7d27\u56fa\u4e92\u8054\uff09", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u76f8\u7535\u611f");
    model.result().numerical("gev2").set("table", "tbl4");
    model.result().numerical("gev2").setResult();

    model.component("comp1").physics("mf").create("coil4", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil4").label("\u5c4f\u853d\u5c42 1");
    model.component("comp1").physics("mf").feature("coil4").selection().set(37);
    model.component("comp1").physics("mf").feature("coil4").set("ICoil", "0[A]");
    model.component("comp1").physics("mf").create("coil5", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil5").label("\u5c4f\u853d\u5c42 2");
    model.component("comp1").physics("mf").feature("coil5").selection().set(68);
    model.component("comp1").physics("mf").feature("coil5").set("ICoil", "0[A]");
    model.component("comp1").physics("mf").create("coil6", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil6").label("\u5c4f\u853d\u5c42 3");
    model.component("comp1").physics("mf").feature("coil6").selection().set(36);
    model.component("comp1").physics("mf").feature("coil6").set("ICoil", "0[A]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("int1").setIndex("descr", "\u76f8\u635f\u8017\uff08\u5355\u70b9\u4e92\u8054\uff09", 0);
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u5355\u70b9\u4e92\u8054\uff09", 0);
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").appendResult();
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u5355\u70b9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2").setIndex("descr", "\u76f8\u7535\u611f\uff08\u5355\u70b9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev2").set("table", "tbl4");
    model.result().numerical("gev2").appendResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u5c4f\u853d\u5c42\u7535\u538b");
    model.result().numerical("gev3").setIndex("expr", "abs(mf.VCoil_4)", 0);
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 1 \u4e2d\u7684\u7535\u538b\uff08\u5355\u70b9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev3").setIndex("expr", "abs(mf.VCoil_5)", 1);
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 2 \u4e2d\u7684\u7535\u538b\uff08\u5355\u70b9\u4e92\u8054\uff09", 1);
    model.result().numerical("gev3").setIndex("expr", "abs(mf.VCoil_6)", 2);
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 3 \u4e2d\u7684\u7535\u538b\uff08\u5355\u70b9\u4e92\u8054\uff09", 2);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5c4f\u853d\u5c42\u7535\u538b");
    model.result().numerical("gev3").set("table", "tbl5");
    model.result().numerical("gev3").setResult();

    model.param("par2").set("Lsec1", "1/3");
    model.param("par2").set("Lsec3", "1/3");

    model.component("comp1").physics().create("mf2", "InductionCurrents", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/mf2", true);

    model.component("comp1").physics().create("mf3", "InductionCurrents", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/mf3", true);

    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);

    model.component("comp1").physics("mf2").label("\u78c1\u573a\uff0c\u7b2c 2 \u6bb5");
    model.component("comp1").physics("mf2").prop("d").set("d", "Lsec2*Lcab");
    model.component("comp1").physics("mf3").label("\u78c1\u573a\uff0c\u7b2c 3 \u6bb5");
    model.component("comp1").physics("mf3").prop("d").set("d", "Lsec3*Lcab");
    model.component("comp1").physics("mf").feature("coil4").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf").feature("coil5").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf").feature("coil6").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf2").feature().copy("als1", "mf/als1");
    model.component("comp1").physics("mf2").feature().copy("coil1", "mf/coil1");
    model.component("comp1").physics("mf2").feature().copy("coil2", "mf/coil2");
    model.component("comp1").physics("mf2").feature().copy("coil3", "mf/coil3");
    model.component("comp1").physics("mf2").feature().copy("coil4", "mf/coil4");
    model.component("comp1").physics("mf2").feature().copy("coil5", "mf/coil5");
    model.component("comp1").physics("mf2").feature().copy("coil6", "mf/coil6");
    model.component("comp1").physics("mf3").feature().copy("als1", "mf/als1");
    model.component("comp1").physics("mf3").feature().copy("coil1", "mf/coil1");
    model.component("comp1").physics("mf3").feature().copy("coil2", "mf/coil2");
    model.component("comp1").physics("mf3").feature().copy("coil3", "mf/coil3");
    model.component("comp1").physics("mf3").feature().copy("coil4", "mf/coil4");
    model.component("comp1").physics("mf3").feature().copy("coil5", "mf/coil5");
    model.component("comp1").physics("mf3").feature().copy("coil6", "mf/coil6");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 11, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.mf.VCoil_4");
    model.component("comp1").physics("cir").create("IvsU2", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU2").setIndex("Connections", 12, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU2").setIndex("Connections", 11, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU2").set("V_src", "root.comp1.mf2.VCoil_5");
    model.component("comp1").physics("cir").create("IvsU3", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU3").setIndex("Connections", 13, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU3").setIndex("Connections", 12, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU3").set("V_src", "root.comp1.mf3.VCoil_6");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 13, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "1[uohm]");
    model.component("comp1").physics("cir").create("IvsU4", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU4").setIndex("Connections", 21, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU4").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU4").set("V_src", "root.comp1.mf.VCoil_5");
    model.component("comp1").physics("cir").create("IvsU5", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU5").setIndex("Connections", 22, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU5").setIndex("Connections", 21, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU5").set("V_src", "root.comp1.mf2.VCoil_6");
    model.component("comp1").physics("cir").create("IvsU6", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU6").setIndex("Connections", 23, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU6").setIndex("Connections", 22, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU6").set("V_src", "root.comp1.mf3.VCoil_4");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 23, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "1[uohm]");
    model.component("comp1").physics("cir").create("IvsU7", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU7").setIndex("Connections", 31, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU7").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU7").set("V_src", "root.comp1.mf.VCoil_6");
    model.component("comp1").physics("cir").create("IvsU8", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU8").setIndex("Connections", 32, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU8").setIndex("Connections", 31, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU8").set("V_src", "root.comp1.mf2.VCoil_4");
    model.component("comp1").physics("cir").create("IvsU9", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU9").setIndex("Connections", 33, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU9").setIndex("Connections", 32, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU9").set("V_src", "root.comp1.mf3.VCoil_5");
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 33, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "1[uohm]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("int1").setIndex("descr", "\u76f8\u635f\u8017\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 0);
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 0);
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").appendResult();
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2").setIndex("descr", "\u76f8\u7535\u611f\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev2").set("table", "tbl4");
    model.result().numerical("gev2").appendResult();
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 1 \u4e2d\u7684\u7535\u538b\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 0);
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 2 \u4e2d\u7684\u7535\u538b\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 1);
    model.result().numerical("gev3")
         .setIndex("descr", "\u5c4f\u853d\u5c42 3 \u4e2d\u7684\u7535\u538b\uff08\u4ea4\u53c9\u4e92\u8054\uff09", 2);
    model.result().numerical("gev3").set("table", "tbl5");
    model.result().numerical("gev3").appendResult();
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u6d77\u5e95\u7535\u7f06 5 - \u4e92\u8054\u7535\u611f");

    model
         .description("\u5728\u201c\u7535\u611f\u6548\u5e94\u201d\u6559\u7a0b\uff08\u672c\u7cfb\u5217\u7684\u524d\u4e00\u7bc7\u6559\u7a0b\uff09\u4e2d\uff0c\u6211\u4eec\u5c06\u201c\u7ebf\u5708\u7ec4\u201d\u9009\u9879\u7528\u4f5c\u6a21\u62df\u4ea4\u53c9\u4e92\u8054\u6548\u5e94\u7684\u65b9\u6cd5\uff0c\u4f46\u672a\u663e\u793a\u6b64\u65b9\u6cd5\u6709\u6548\u6027\u7684\u786e\u5207\u8303\u56f4\u3002\u4e3a\u4e86\u66f4\u6df1\u5165\u5730\u7814\u7a76\u4e0d\u540c\u7684\u4e92\u8054\u7c7b\u578b\uff0c\u672c\u6559\u7a0b\u5355\u72ec\u5206\u6790\u4e09\u6bb5\u7535\u7f06\uff0c\u5b83\u4eec\u5206\u522b\u7528\u4e09\u4e2a\u5355\u72ec\u7684\u201c\u78c1\u573a\u201d\u63a5\u53e3\u8868\u793a\u3002\n\n\u8be5\u6a21\u578b\u4f7f\u7528\u9ad8\u5ea6\u7b80\u5316\u7684\u51e0\u4f55\u7ed3\u6784\uff0c\u5c3d\u7ba1\u5982\u6b64\uff0c\u5206\u6790\u7ed3\u679c\u4ecd\u4e0e\u201c\u7535\u611f\u6548\u5e94\u201d\u6559\u7a0b\u7684\u7ed3\u679c\u975e\u5e38\u4e00\u81f4\u3002\u8fd9\u8bf4\u660e\u672c\u6559\u7a0b\u4e2d\u7684\u7b80\u5316\u51e0\u4f55\u4ee5\u53ca\u201c\u7535\u611f\u6548\u5e94\u201d\u6559\u7a0b\u4e2d\u5efa\u8bae\u7684\u4ea4\u8054\u65b9\u6cd5\u90fd\u662f\u5408\u7406\u7684\u3002\u6700\u540e\uff0c\u4e0e\u672c\u7cfb\u5217\u4e2d\u7684\u5176\u4ed6\u7535\u611f\u6a21\u578b\u76f8\u53cd\uff0c\u6b64\u6a21\u578b\u5141\u8bb8\u7814\u7a76\u4e0d\u540c\u7684\u5206\u6bb5\u957f\u5ea6\u3002");

    model.label("submarine_cable_05_bonding_inductive.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
