/*
 * turbulent_free_porous.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class turbulent_free_porous {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowlowRekeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("H", "0.03[m]", "\u900f\u660e\u901a\u9053\u9ad8\u5ea6");
    model.param().set("ReH", "5500", "\u900f\u660e\u901a\u9053\u4e2d\u7684\u672c\u4f53\u96f7\u8bfa\u6570");
    model.param().set("epsilon_p_i", "0.95", "\u5b54\u9699\u7387");
    model.param().set("Da_i", "1.9e-4", "\u8fbe\u897f\u6570");
    model.param().set("kappa_i", "Da_i*H^2", "\u6e17\u900f\u7387");
    model.param().set("cF_i", "0.14", "Forchheimer \u53c2\u6570");
    model.param().set("rho_i", "1[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("mu_i", "rho_i*nu_i", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("nu_i", "1e-5 [m^2/s]", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("Ub", "ReH*mu_i/(rho_i*H)", "\u900f\u660e\u901a\u9053\u4e2d\u7684\u672c\u4f53\u901f\u5ea6");
    model.param().set("H_E95", "0.03[m]", "E95 \u9ad8\u5ea6");
    model.param().set("H_06", "round(sqrt(8.7e-8/(1.04e-4)),3)[m]", "#06 \u9ad8\u5ea6");
    model.param().set("L", "0.2*H", "\u901a\u9053\u957f\u5ea6");
    model.param().set("CL", "0.5*L", "\u622a\u7ebf\u4f4d\u7f6e");
    model.param().set("Nmesh", "160", "\u7f51\u683c\u5355\u5143\u6570");
    model.param()
         .set("cUu", "0.25", "\u4e0a\u9650\u4f30\u8ba1\u4e2d\u7684\u63d2\u503c\u7cfb\u6570 w^2=cUu*u^2+cVv*v^2");
    model.param()
         .set("cUv", "1", "\u4e0a\u9650\u4f30\u8ba1\u4e2d\u7684\u63d2\u503c\u7cfb\u6570 w^2=cUu*u^2+cVv*v^2");
    model.param()
         .set("cLu", "0.1", "\u4e0b\u9650\u4f30\u8ba1\u4e2d\u7684\u63d2\u503c\u7cfb\u6570 w^2=cLu*u^2+cLv*v^2");
    model.param()
         .set("cLv", "1", "\u4e0b\u9650\u4f30\u8ba1\u4e2d\u7684\u63d2\u503c\u7cfb\u6570 w^2=cLu*u^2+cLv*v^2");
    model.param().set("manual_x_max", "3.1", "\u624b\u52a8\u8bbe\u7f6e\u7684 x \u7684\u6700\u5927\u9650\u5236");

    model.component("comp1").view("view1").set("showlabels", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-H"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "CL", 0);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "H", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(9);

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", "CL", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "H", 1);
    model.component("comp1").probe("pdom1").set("bndsnap2", true);
    model.component("comp1").probe("pdom1").feature("ppb1").label("\u56fa\u4f53\u58c1\u4e0a\u7684\u6469\u64e6");
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "friction_s");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "-spf.nu*ppr(uy)");
    model.component("comp1").probe().duplicate("pdom2", "pdom1");
    model.component("comp1").probe("pdom2").setIndex("coords2", 0, 1);
    model.component("comp1").probe("pdom2").feature("ppb2")
         .label("\u591a\u5b54\u754c\u9762\u4e0a\u7684\u6469\u64e6");
    model.component("comp1").probe("pdom2").feature("ppb2").set("probename", "friction_p");
    model.component("comp1").probe("pdom2").feature("ppb2").set("expr", "side(2,(spf.nuT+spf.nu)*pprint(uy))");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("shear_stress", "spf.nuT*ppr(uy)", "\u526a\u5207\u5e94\u529b");
    model.component("comp1").variable("var1")
         .set("Cs1000", "2000*friction_s/Ub^2", "\u56fa\u4f53\u58c1\u4e0a\u7684\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.component("comp1").variable("var1")
         .set("Cp1000", "2000*friction_p/Ub^2", "\u591a\u5b54\u754c\u9762\u4e0a\u7684\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.component("comp1").variable("var1")
         .set("utau_s", "sqrt(friction_s)", "\u56fa\u4f53\u58c1\u4e0a\u7684\u6469\u64e6\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("utau_p", "sqrt(friction_p)", "\u591a\u5b54\u754c\u9762\u4e0a\u7684\u6469\u64e6\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Retp", "utau_p*H/nu_i", "\u57fa\u4e8e utau_p \u7684\u6469\u64e6\u96f7\u8bfa\u6570");
    model.component("comp1").variable("var1")
         .set("Rek", "utau_p*sqrt(kappa_i)/nu_i", "\u6e17\u900f\u7387\u96f7\u8bfa\u6570");
    model.component("comp1").variable("var1")
         .set("C_help_var", "2000*(nu_i/sqrt(kappa_i)/Ub)^2", "\u7528\u4e8e\u8ba1\u7b97\u5b9e\u9a8c Cf \u548c Cp \u7684\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("y_H", "y/H", "\u7ed8\u56fe\u5750\u6807");

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho_i");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu_i");
    model.component("comp1").physics("spf").feature("fp1").set("LengthScaleSpecification", "Manual");
    model.component("comp1").physics("spf").feature("fp1").set("lref", "H");
    model.component("comp1").physics("spf").feature("init1")
         .set("u_init", new String[]{"Ub*cos(2*pi*y/H)", "0", "0"});
    model.component("comp1").physics("spf").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("spf").feature("porous1").selection().set(1);
    model.component("comp1").physics("spf").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("porous1").feature("fluid1").set("rho", "rho_i");
    model.component("comp1").physics("spf").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("porous1").feature("fluid1").set("mu", "mu_i");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("epsilon_p", "epsilon_p_i");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_i", "0", "0", "0", "kappa_i", "0", "0", "0", "kappa_i"});
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("cf", "cF_i");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 1);
    model.component("comp1").physics("spf").feature("pfc1").selection().set(1, 3, 8, 9);
    model.component("comp1").physics("spf").feature("pfc1").set("PeriodicFlowConditionUserInput", "MassFlow");
    model.component("comp1").physics("spf").feature("pfc1").set("mf", "rho_i*Ub*H*1[m]");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(4);
    model.component("comp1").physics("spf").selection().set(2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "Nmesh/2");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(1, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", "Nmesh/2");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(2, 5, 7);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhtot", "3*H/Nmesh");
    model.component("comp1").mesh("mesh1").feature().duplicate("bl2", "bl1");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(4, 6);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 12);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhtot", "4*H/Nmesh");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "H", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "H", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "ReH", 0);
    model.study("std1").feature("param").setIndex("plistarr", "5500 5400 9500", 0);
    model.study("std1").feature("param").setIndex("punit", 1, 0);
    model.study("std1").feature("param").setIndex("pname", "H", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "H", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("plistarr", "H_E95 H_06 H_06", 1);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");

    model.batch("p1").run("compute");

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset3");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(4, 5, 6, 7);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg2").run();

    model.sol("sol3").copySolution("sol7");
    model.sol("sol7").label("\u7eaf\u6d41\u4f53\u901a\u9053\u6d41\u52a8");

    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u7eaf\u6d41\u4f53\u901a\u9053\u4e2d\u5fc3\u7ebf");
    model.result().dataset("cln1").set("data", "dset5");
    model.result().dataset("cln1").setIndex("genpoints", "CL", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "CL", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-H", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "H", 1, 1);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("spf").selection().set(1, 2);
    model.component("comp1").physics("spf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "P_driving", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "aveop1(u)-Ub", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("spf").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("spf").feature("ge1").setIndex("CustomDependentVariableUnit", "Pa", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("spf").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("spf").feature("ge1").setIndex("CustomSourceTermUnit", "m/s", 0, 0);
    model.component("comp1").physics("spf").feature("pfc1")
         .set("PeriodicFlowConditionUserInput", "PressureDifference");
    model.component("comp1").physics("spf").feature("pfc1").set("pdiff", "P_driving");

    model.study("std1").feature("param")
         .set("plistarr", new String[]{"5500 5400 9500", "H_E95 H_06 H_06", "0.95 0.8 0.8", "0.14 0.095 0.095", "1.9e-4 1.04e-4 1.04e-4"});
    model.study("std1").feature("param").set("pname", new String[]{"ReH", "H", "epsilon_p_i", "cF_i", "Da_i"});
    model.study("std1").showAutoSequences("sol");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");

    model.batch("p1").run("compute");

    model.result("pg2").run();

    model.sol("sol3").label("\u76f8\u90bb\u81ea\u7531-\u591a\u5b54\u6d41");

    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").label("\u76f8\u90bb\u81ea\u7531-\u591a\u5b54\u6d41\u4e2d\u5fc3\u7ebf");
    model.result().dataset("cln2").set("data", "dset3");
    model.result().table().create("tbl2", "Table");
    model.result().table().create("tbl3", "Table");
    model.result().table().create("tbl4", "Table");
    model.result().table().create("tbl5", "Table");
    model.result().table().create("tbl6", "Table");
    model.result().table().create("tbl7", "Table");
    model.result().table().create("tbl8", "Table");
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl2").label("U_E95");
    model.result().table("tbl2").importData("turbulent_free_porous_U_E95.txt");
    model.result().table("tbl3").label("k_E95");
    model.result().table("tbl3").importData("turbulent_free_porous_k_E95.txt");
    model.result().table("tbl4").label("uv_E95");
    model.result().table("tbl4").importData("turbulent_free_porous_uv_E95.txt");
    model.result().table("tbl5").label("U_06_5400");
    model.result().table("tbl5").importData("turbulent_free_porous_U_#06_5400.txt");
    model.result().table("tbl6").label("uv_06_5400");
    model.result().table("tbl6").importData("turbulent_free_porous_uv_#06_5400.txt");
    model.result().table("tbl7").label("U_06_9500");
    model.result().table("tbl7").importData("turbulent_free_porous_U_#06_9500.txt");
    model.result().table("tbl8").label("uv_06_9500");
    model.result().table("tbl8").importData("turbulent_free_porous_uv_#06_9500.txt");
    model.result().table("tbl9").label("u-rms \u548c v-rms #06");
    model.result().table("tbl9").importData("turbulent_free_porous_rms_#06.txt");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("u_rms #06 5400");
    model.component("comp1").func("int1").set("source", "resultTable");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").func("int1").set("resultTable", "tbl9");
    model.component("comp1").func("int1").setEntry("funcnames", "col2", "u1");
    model.component("comp1").func().duplicate("int2", "int1");
    model.component("comp1").func("int2").label("v_rms #06 5400");
    model.component("comp1").func("int2").setEntry("columnType", "col2", "none");
    model.component("comp1").func("int2").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int2").setEntry("funcnames", "col3", "v1");
    model.component("comp1").func().duplicate("int3", "int2");
    model.component("comp1").func("int3").label("u_rms #06 9500");
    model.component("comp1").func("int3").setEntry("columnType", "col3", "none");
    model.component("comp1").func("int3").setEntry("columnType", "col4", "value");
    model.component("comp1").func("int3").setEntry("funcnames", "col4", "u2");
    model.component("comp1").func().duplicate("int4", "int3");
    model.component("comp1").func("int4").label("v_rms #06 9500");
    model.component("comp1").func("int4").setEntry("columnType", "col4", "none");
    model.component("comp1").func("int4").setEntry("columnType", "col5", "value");
    model.component("comp1").func("int4").setEntry("funcnames", "col5", "v2");

    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").label("\u6805\u683c\uff0c\u5b9e\u9a8c\u503c");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("U_E95");
    model.result("pg5").set("data", "cln2");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u901f\u5ea6\uff0cE95\uff0cBreugem \u7b49\u4eba\uff082006 \u5e74\uff09");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "y/H");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "U/Ub");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", -0.2);
    model.result("pg5").set("ymin", 0);
    model.result("pg5").set("ymax", 1.3);
    model.result("pg5").set("legendpos", "lowermiddle");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").label("\u76f8\u90bb");
    model.result("pg5").feature("lngr1").set("expr", "u/Ub");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "y_H");
    model.result("pg5").feature("lngr1").set("linecolor", "red");
    model.result("pg5").feature("lngr1").set("linewidth", 3);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1").set("autoplotlabel", true);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").label("\u7eaf\u6d41\u4f53");
    model.result("pg5").feature("lngr2").set("data", "cln1");
    model.result("pg5").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr2").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg5").feature("lngr2").set("linecolor", "green");
    model.result("pg5").feature("lngr2").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").label("DNS");
    model.result("pg5").feature("tblp1").set("table", "tbl2");
    model.result("pg5").feature("tblp1").set("linestyle", "none");
    model.result("pg5").feature("tblp1").set("linecolor", "black");
    model.result("pg5").feature("tblp1").set("linemarker", "circle");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("autoheaders", false);
    model.result("pg5").feature("tblp1").set("autoplotlabel", true);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("k_E95");
    model.result("pg6").set("title", "\u6e4d\u52a8\u80fd\uff0cE95\uff0cBreugem \u7b49\u4eba\uff082006 \u5e74\uff09");
    model.result("pg6").set("ylabel", "k/u<sup>s</sup><sub>\\tau</sub> <sup>2</sup>");
    model.result("pg6").set("ymax", 8.5);
    model.result("pg6").set("legendpos", "upperright");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "k/friction_s");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "k/friction_s");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("table", "tbl3");
    model.result("pg6").feature("tblp1").set("markerpos", "interp");
    model.result("pg6").feature("tblp1").set("markers", 100);
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result("pg7").run();
    model.result("pg7")
         .set("title", "\u526a\u5207\u5e94\u529b\uff0cE95\uff0cBreugem \u7b49\u4eba\uff082006 \u5e74\uff09");
    model.result("pg7").label("uv_E95");
    model.result("pg7")
         .set("ylabel", "-u<sup>\\prime</sup>v<sup>\\prime</sup>/u<sup>s</sup><sub>\\tau</sub> <sup>2</sup>");
    model.result("pg7").set("ymin", -0.8);
    model.result("pg7").set("ymax", 2.7);
    model.result("pg7").set("legendpos", "upperright");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "shear_stress/friction_s");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "shear_stress/friction_s");
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("table", "tbl4");
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").label("U_#06_Re=5400");
    model.result("pg8").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg8").set("title", "\u901f\u5ea6\uff0c#06 Re=5400\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").label("\u5b9e\u9a8c\u503c");
    model.result("pg8").feature("tblp1").set("table", "tbl5");
    model.result("pg6").run();
    model.result().duplicate("pg9", "pg6");
    model.result("pg9").run();
    model.result("pg9").label("k_#06_Re=5400");
    model.result("pg9").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg9")
         .set("title", "\u6e4d\u52a8\u80fd\uff0c#06 Re=5400\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg9").feature().duplicate("lngr3", "lngr2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature().remove("tblp1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").label("\u5b9e\u9a8c\u4f30\u503c\u4e0a\u9650");
    model.result("pg9").feature("lngr3").set("data", "grid1");
    model.result("pg9").feature("lngr3").set("expr", "((1+cUu)*u1(x)^2+(1+cUv)*v1(x)^2)/2");
    model.result("pg9").feature("lngr3").set("xdataexpr", "x");
    model.result("pg9").feature("lngr3").set("linestyle", "dashed");
    model.result("pg9").feature("lngr3").set("linecolor", "black");
    model.result("pg9").feature("lngr3").set("linewidth", 1);
    model.result("pg9").feature("lngr3").set("legendmethod", "manual");
    model.result("pg9").feature().duplicate("lngr4", "lngr3");
    model.result("pg9").run();
    model.result("pg9").feature("lngr4").label("\u5b9e\u9a8c\u4f30\u503c\u4e0b\u9650");
    model.result("pg9").feature("lngr4").set("expr", "((1+cLu)*u1(x)^2+(1+cLv)*v1(x)^2)/2");
    model.result("pg9").feature("lngr4").set("linestyle", "solid");
    model.result("pg9").feature("lngr4").setIndex("legends", "\u5b9e\u9a8c\u4f30\u503c\u4e0b\u9650", 0);
    model.result("pg9").feature().duplicate("lngr5", "lngr4");
    model.result("pg9").run();
    model.result("pg9").feature("lngr5").label("\u5b9e\u9a8c\u4e0b\u9650");
    model.result("pg9").feature("lngr5").set("expr", "(u1(x)^2+2*v1(x)^2)/2");
    model.result("pg9").feature("lngr5").set("linestyle", "dotted");
    model.result("pg9").feature("lngr5").setIndex("legends", "\u5b9e\u9a8c\u4e0b\u9650", 0);
    model.result("pg7").run();
    model.result().duplicate("pg10", "pg7");
    model.result("pg10").run();
    model.result("pg10").label("uv_#06_Re=5400");
    model.result("pg10").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg10")
         .set("title", "\u526a\u5207\u5e94\u529b\uff0c#06 Re=5400\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg10").run();
    model.result("pg10").feature("lngr2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg10").run();
    model.result("pg10").feature("tblp1").label("\u5b9e\u9a8c\u503c");
    model.result("pg10").feature("tblp1").set("table", "tbl6");
    model.result("pg8").run();
    model.result().duplicate("pg11", "pg8");
    model.result("pg11").run();
    model.result("pg11").label("U_#06_Re=9500");
    model.result("pg11").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg11").set("title", "\u901f\u5ea6\uff0c#06 Re=9500\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg11").run();
    model.result("pg11").feature("lngr2").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg11").run();
    model.result("pg11").feature("tblp1").set("table", "tbl7");
    model.result("pg9").run();
    model.result().duplicate("pg12", "pg9");
    model.result("pg12").run();
    model.result("pg12").label("k_#06_Re=9500");
    model.result("pg12").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg12")
         .set("title", "\u6e4d\u52a8\u80fd\uff0c#06 Re=9500\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg12").run();
    model.result("pg12").feature("lngr2").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg12").run();
    model.result("pg12").feature("lngr3").set("expr", "((1+cUu)*u2(x)^2+(1+cUv)*v2(x)^2)/2");
    model.result("pg12").run();
    model.result("pg12").feature("lngr4").set("expr", "((1+cLu)*u2(x)^2+(1+cLv)*v2(x)^2)/2");
    model.result("pg12").run();
    model.result("pg12").feature("lngr5").set("expr", "(u2(x)^2+2*v2(x)^2)/2");
    model.result("pg10").run();
    model.result().duplicate("pg13", "pg10");
    model.result("pg13").run();
    model.result("pg13").label("uv_#06_Re=9500");
    model.result("pg13").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg13")
         .set("title", "\u526a\u5207\u5e94\u529b\uff0c#06 Re=9500\uff0cSuga \u7b49\u4eba\uff082010 \u5e74\uff09");
    model.result("pg13").run();
    model.result("pg13").feature("lngr2").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg13").run();
    model.result("pg13").feature("tblp1").set("table", "tbl8");
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u56fa\u4f53\u58c1\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u56fa\u4f53\u58c1\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.result("pg14").set("axislimits", true);
    model.result("pg14").set("xmin", 0.9);
    model.result("pg14").set("xmax", "manual_x_max");
    model.result("pg14").set("ymin", 0);
    model.result("pg14").set("ymax", 12);
    model.result("pg14").set("manualgrid", true);
    model.result("pg14").set("yspacing", 0.25);
    model.result("pg14").create("glob1", "Global");
    model.result("pg14").feature("glob1").set("markerpos", "datapoints");
    model.result("pg14").feature("glob1").set("linewidth", "preference");
    model.result("pg14").feature("glob1").label("\u76f8\u90bb");
    model.result("pg14").feature("glob1").set("data", "dset3");
    model.result("pg14").feature("glob1").setIndex("expr", "Cs1000", 0);
    model.result("pg14").feature("glob1").set("linestyle", "none");
    model.result("pg14").feature("glob1").set("linecolor", "black");
    model.result("pg14").feature("glob1").set("linemarker", "plus");
    model.result("pg14").feature("glob1").set("autodescr", false);
    model.result("pg14").feature("glob1").set("autosolution", false);
    model.result("pg14").feature("glob1").set("autoplotlabel", true);
    model.result("pg14").feature().duplicate("glob2", "glob1");
    model.result("pg14").run();
    model.result("pg14").feature("glob2").label("\u7eaf\u6d41\u4f53");
    model.result("pg14").feature("glob2").set("data", "dset5");
    model.result("pg14").feature("glob2").set("linemarker", "square");
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("glob3", "glob1");
    model.result("pg14").run();
    model.result("pg14").feature("glob3").label("DNS E95");
    model.result("pg14").feature("glob3").setIndex("looplevelinput", "manual", 0);
    model.result("pg14").feature("glob3").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg14").feature("glob3").setIndex("expr", 10.9, 0);
    model.result("pg14").feature("glob3").set("linecolor", "red");
    model.result("pg14").feature("glob3").set("linemarker", "circle");
    model.result("pg14").feature().duplicate("glob4", "glob3");
    model.result("pg14").run();
    model.result("pg14").feature("glob4").label("\u5b9e\u9a8c #06 Re=5400");
    model.result("pg14").feature("glob4").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg14").feature("glob4").setIndex("expr", "C_help_var*(6.23/1.55)^2", 0);
    model.result("pg14").feature("glob4").set("linecolor", "blue");
    model.result("pg14").feature().duplicate("glob5", "glob4");
    model.result("pg14").run();
    model.result("pg14").feature("glob5").label("\u5b9e\u9a8c #06 Re=9500");
    model.result("pg14").feature("glob5").setIndex("looplevel", new int[]{3}, 0);
    model.result("pg14").feature("glob5").set("linecolor", "green");
    model.result("pg14").feature("glob5").setIndex("expr", "C_help_var*(11.05/1.63)^2", 0);
    model.result("pg14").run();
    model.result("pg14").create("ann1", "Annotation");
    model.result("pg14").feature("ann1")
         .set("text", "\\unicode{\u89e3} 1: E95 \\\\ Re=5500 \\\\ $\\epsilon_p=0.95$ \\\\ Da=1.9e-4 \\\\ $c_F=0.14$ \\\\ ~ \\\\ \\unicode{\u89e3} 2: #06 Re=5400 \\\\ \\unicode{\u89e3} 3: #06 Re=9500 \\\\ $\\epsilon_p$=0.8 \\\\ Da=1.04e-4 \\\\ $c_F=0.095$");
    model.result("pg14").feature("ann1").set("latexmarkup", true);
    model.result("pg14").feature("ann1").set("anchorpoint", "upperright");
    model.result("pg14").feature("ann1").set("posxexpr", 3.9);
    model.result("pg14").feature("ann1").set("posyexpr", 5);
    model.result("pg14").feature("ann1").set("showpoint", false);
    model.result("pg14").feature("ann1").set("showframe", true);
    model.result("pg14").feature("ann1").set("backgroundcolor", "white");
    model.result("pg14").run();
    model.result("pg14").set("legendpos", "upperright");
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u591a\u5b54\u754c\u9762\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.result("pg15").set("title", "\u591a\u5b54\u754c\u9762\u6469\u64e6\u7cfb\u6570 (x1000)");
    model.result("pg15").set("ymax", 32);
    model.result("pg15").run();
    model.result("pg15").feature("glob1").setIndex("expr", "Cp1000", 0);
    model.result("pg15").run();
    model.result("pg15").feature("glob2").setIndex("expr", "Cp1000", 0);
    model.result("pg15").run();
    model.result("pg15").feature("glob3").setIndex("expr", 30.4, 0);
    model.result("pg15").run();
    model.result("pg15").feature("glob4").setIndex("expr", "C_help_var*(6.23)^2", 0);
    model.result("pg15").run();
    model.result("pg15").feature("glob5").setIndex("expr", "C_help_var*(11.05)^2", 0);
    model.result("pg15").run();
    model.result("pg15").feature("ann1").set("posyexpr", 14);
    model.result("pg15").run();
    model.result("pg15").set("showlegends", true);
    model.result().duplicate("pg16", "pg15");
    model.result("pg16").run();
    model.result("pg16").label("\u591a\u5b54\u6469\u64e6\u96f7\u8bfa\u6570");
    model.result("pg16").set("title", "\u591a\u5b54\u6469\u64e6\u96f7\u8bfa\u6570 Re<sub>\\tau</sub> <sup>p</sup>");
    model.result("pg16").set("ymax", 1100);
    model.result("pg16").set("yspacing", 50);
    model.result("pg16").run();
    model.result("pg16").feature("glob1").setIndex("expr", "Retp", 0);
    model.result("pg16").feature("glob1").setIndex("descr", "", 0);
    model.result("pg16").run();
    model.result("pg16").feature("glob2").setIndex("expr", "Retp", 0);
    model.result("pg16").feature("glob2").setIndex("descr", "", 0);
    model.result("pg16").run();
    model.result("pg16").feature("glob3").setIndex("expr", 678, 0);
    model.result("pg16").run();
    model.result("pg16").feature("glob4").setIndex("expr", "6.23/sqrt(Da_i)", 0);
    model.result("pg16").run();
    model.result("pg16").feature("glob5").setIndex("expr", "11.05/sqrt(Da_i)", 0);
    model.result("pg16").run();
    model.result("pg16").feature("ann1").set("posyexpr", 600);
    model.result("pg16").run();
    model.result("pg16").set("showlegends", true);
    model.result().duplicate("pg17", "pg16");
    model.result("pg17").run();
    model.result("pg17").label("\u6469\u64e6\u901f\u5ea6\u6bd4");
    model.result("pg17")
         .set("title", "\u6469\u64e6\u901f\u5ea6\u6bd4 u<sub>\\tau</sub> <sup>p</sup>/u<sub>\\tau</sub> <sup>s</sup>");
    model.result("pg17").set("ymax", 1.8);
    model.result("pg17").set("yspacing", 0.1);
    model.result("pg17").run();
    model.result("pg17").feature("glob1").setIndex("expr", "utau_p/utau_s", 0);
    model.result("pg17").run();
    model.result("pg17").feature("glob2").setIndex("expr", "utau_p/utau_s", 0);
    model.result("pg17").run();
    model.result("pg17").feature("glob3").setIndex("expr", "sqrt(30.4/10.9)", 0);
    model.result("pg17").run();
    model.result("pg17").feature("glob4").setIndex("expr", 1.55, 0);
    model.result("pg17").run();
    model.result("pg17").feature("glob5").setIndex("expr", 1.63, 0);
    model.result("pg17").run();
    model.result("pg17").feature("ann1").set("posyexpr", 1);
    model.result("pg17").run();
    model.result("pg17").set("showlegends", true);
    model.result().create("pg18", "PlotGroup2D");
    model.result("pg18").run();
    model.result("pg18").label("\u9762\u7bad\u5934\uff0cE95");
    model.result("pg18").set("data", "dset3");
    model.result("pg18").setIndex("looplevel", 1, 0);
    model.result("pg18").set("titletype", "manual");
    model.result("pg18").set("title", "\u901f\u5ea6\u3001\u6e4d\u52a8\u80fd\u548c\u526a\u5207\u5e94\u529b");
    model.result("pg18").set("showlegends", false);
    model.result("pg18").set("plotarrayenable", true);
    model.result("pg18").set("relpadding", 4);
    model.result("pg18").create("arws1", "ArrowSurface");
    model.result("pg18").feature("arws1").set("arraydim", "1");
    model.result("pg18").feature("arws1").label("\u901f\u5ea6");
    model.result("pg18").feature("arws1").set("expr", new String[]{"u/Ub", "0"});
    model.result("pg18").feature("arws1").set("xnumber", 1);
    model.result("pg18").feature("arws1").set("ynumber", 500);
    model.result("pg18").feature("arws1").set("arrowtype", "cone");
    model.result("pg18").feature("arws1").set("scaleactive", true);
    model.result("pg18").feature("arws1").set("scale", "1e-2");
    model.result("pg18").feature("arws1").create("col1", "Color");
    model.result("pg18").run();
    model.result("pg18").feature("arws1").feature("col1").set("colordata", "arrowlength");
    model.result("pg18").feature("arws1").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("arws2", "arws1");
    model.result("pg18").feature("arws2").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature("arws2").label("\u6e4d\u52a8\u80fd");
    model.result("pg18").feature("arws2").set("expr", new String[]{"k/utau_s^2", "0"});
    model.result("pg18").feature("arws2").set("scale", "2e-3");
    model.result("pg18").run();
    model.result("pg18").feature("arws2").feature("col1").set("colortable", "Disco");
    model.result("pg18").feature("arws2").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("arws3", "arws2");
    model.result("pg18").feature("arws3").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature("arws3").label("\u526a\u5207\u5e94\u529b");
    model.result("pg18").feature("arws3").set("expr", new String[]{"shear_stress/utau_s^2", "0"});
    model.result("pg18").feature("arws3").set("scale", "5e-3");
    model.result("pg18").run();
    model.result("pg18").feature("arws3").feature("col1").set("colortable", "Plasma");
    model.result("pg18").run();
    model.result("pg18").create("ann1", "Annotation");
    model.result("pg18").feature("ann1").set("arraydim", "1");
    model.result("pg18").feature("ann1").set("latexmarkup", true);
    model.result("pg18").feature("ann1").set("text", "\\Huge$\\frac{u}{U_b}$");
    model.result("pg18").feature("ann1").set("posxexpr", "L");
    model.result("pg18").feature("ann1").set("posyexpr", "-2*L");
    model.result("pg18").feature("ann1").set("showpoint", false);
    model.result("pg18").feature("ann1").set("anchorpoint", "middleleft");
    model.result("pg18").feature("ann1").set("belongstoplotarray", false);
    model.result("pg18").feature().duplicate("ann2", "ann1");
    model.result("pg18").feature("ann2").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature("ann2").set("text", "\\Huge$\\frac{k}{{(u_\\tau^s})^2}$");
    model.result("pg18").feature("ann2").set("posxexpr", "6*L");
    model.result("pg18").feature().duplicate("ann3", "ann2");
    model.result("pg18").feature("ann3").set("arraydim", "1");
    model.result("pg18").run();
    model.result("pg18").feature("ann3")
         .set("text", "\\Huge$\\frac{-\\overline{u^\\prime\\,v^\\prime}}{{(u_\\tau^s})^2}$");
    model.result("pg18").feature("ann3").set("posxexpr", "11*L");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7eaf\u6d41\u4f53\u901a\u9053");
    model.result().numerical("gev1").set("data", "dset5");
    model.result().numerical("gev1").setIndex("expr", "Cs1000", 0);
    model.result().numerical("gev1").setIndex("expr", "Cp1000", 1);
    model.result().numerical("gev1").setIndex("expr", "utau_p*H/nu_i", 2);
    model.result().numerical("gev1")
         .setIndex("descr", "\u57fa\u4e8e\u591a\u5b54\u754c\u9762\u6469\u64e6\u901f\u5ea6\u7684\u96f7\u8bfa\u6570", 2);
    model.result().numerical("gev1").setIndex("expr", "utau_p/utau_s", 3);
    model.result().numerical("gev1")
         .setIndex("descr", "\u6469\u64e6\u901f\u5ea6\u6bd4\uff08\u56fa\u4f53\u58c1\u4e0a\u7684\u591a\u5b54\u754c\u9762\uff09", 3);
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl10").comments("\u7eaf\u6d41\u4f53\u901a\u9053");
    model.result().numerical("gev1").set("table", "tbl10");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("\u76f8\u90bb");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().numerical("gev2").set("table", "tbl10");
    model.result().numerical("gev2").appendResult();

    model.param().set("manual_x_max", "3.95");

    model
         .title("\u5177\u6709\u76f8\u90bb\u6d41\u4f53\u548c\u591a\u5b54\u533a\u57df\u7684\u901a\u9053\u4e2d\u7684\u6e4d\u6d41\u5e73\u884c\u6d41\u52a8");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u901a\u9053\u4e2d\u7684\u6e4d\u6d41\u7279\u6027\u5982\u4f55\u56e0\u76f8\u90bb\u591a\u5b54\u533a\u57df\u7684\u5b58\u5728\u800c\u53d1\u751f\u6539\u53d8\u3002\u901a\u8fc7\u4eff\u771f\u53ef\u4ee5\u89c2\u5bdf\u5230\uff0c\u56fa\u4f53\u58c1\u548c\u6d41\u4f53-\u591a\u5b54\u754c\u9762\u5904\u5177\u6709\u4e0d\u5bf9\u79f0\u7684\u901f\u5ea6\u5206\u5e03\u3001\u66f4\u9ad8\u7684\u6e4d\u6d41\u6c34\u5e73\u4ee5\u53ca\u66f4\u9ad8\u7684\u6469\u64e6\u7cfb\u6570\u3002\u9ad8\u6e17\u900f\u7387\u6848\u4f8b\u5206\u6790\u7684\u7ed3\u679c\u8868\u73b0\u51fa\u4e0e DNS \u548c\u5b9e\u9a8c\u7ed3\u679c\u9ad8\u5ea6\u7684\u4e00\u81f4\u6027\uff0c\u800c\u65e0\u9700\u4e13\u95e8\u6821\u51c6\u7684\u7ecf\u9a8c\u51fd\u6570\u3002");

    model.label("turbulent_free_porous.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
