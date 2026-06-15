/*
 * tweeter_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class tweeter_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "shell");
    model.component("comp1").multiphysics("asb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").insertFile("tweeter_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel11");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("c0", "343[m/s]", "\u58f0\u901f");
    model.param("par2").set("fmax", "20[kHz]", "\u5206\u6790\u7684\u6700\u5927\u9891\u7387");
    model.param("par2").set("fmax_optim", "20[kHz]", "\u4f18\u5316\u7684\u6700\u5927\u9891\u7387");
    model.param("par2").set("fmin", "0.5[kHz]", "\u5206\u6790\u7684\u6700\u5c0f\u9891\u7387");
    model.param("par2").set("fmin_optim", "5[kHz]", "\u4f18\u5316\u7684\u6700\u5c0f\u9891\u7387");
    model.param("par2").set("lam0", "c0/fmax", "\u6700\u5c0f\u6ce2\u957f");
    model.param("par2").set("nf", "150", "\u9891\u7387\u6570");
    model.param("par2").set("nf_optim", "60", "\u4f18\u5316\u4e2d\u7684\u9891\u7387\u6570");
    model.param("par2").set("V0", "sqrt(2)[V]", "\u9a71\u52a8\u7535\u538b");
    model.param("par2")
         .set("rf_pa", "5000[Pa*s/m^2]", "\u591a\u5b54\u4ecb\u8d28\u58f0\u5b66\u6750\u6599\u7684\u6d41\u963b\u7387");
    model.param("par2").set("th_dome", "0.5[mm]", "\u9ad8\u97f3\u7f69\u539a\u5ea6");
    model.param("par2").set("th_former", "0.25[mm]", "\u97f3\u5708\u9aa8\u67b6\u539a\u5ea6");
    model.param("par2").set("th_susp", "0.25[mm]", "\u60ac\u67b6\u539a\u5ea6");
    model.param("par2").set("damp_susp", "0.2", "\u60ac\u67b6\u7684\u963b\u5c3c\u6bd4");
    model.param("par2")
         .set("mesh_optim", "1[mm]", "\u5f62\u72b6\u4f18\u5316\u548c\u7ed3\u6784\u8fb9\u754c\u7684\u6700\u5927\u7f51\u683c\u5927\u5c0f");
    model.param("par2").set("target_spl", "75[dB]", "\u76ee\u6807\u58f0\u538b\u7ea7");
    model.param("par2")
         .set("disp_dome", "3[mm]", "\u9ad8\u97f3\u7f69\u81ea\u7531\u5f62\u72b6\u8fb9\u754c\u7684\u6700\u5927\u4f4d\u79fb");
    model.param("par2")
         .set("disp_wave", "6[mm]", "\u6ce2\u5bfc\u81ea\u7531\u5f62\u72b6\u8fb9\u754c\u7684\u6700\u5927\u4f4d\u79fb");
    model.param().create("par3");
    model.param("par3").label("Thiele Small \u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("BL", "3.2[T*m]", "\u529b\u56e0\u5b50\uff0c\u78c1\u901a\u5bc6\u5ea6 (B) \u4e58\u4ee5\u97f3\u5708\u957f\u5ea6 (L)");
    model.param("par3").set("R_E", "4[ohm]", "\u97f3\u5708\u7535\u963b");
    model.param("par3").set("L_e", "0.1[mH]", "\u97f3\u5708\u7535\u611f\uff08\u6052\u5b9a\uff09");
    model.param("par3").set("n_e", "0.7", "\u97f3\u5708\u635f\u8017\u56e0\u5b50");
    model.param("par3").set("V0rms", "V0/sqrt(2)", "\u9a71\u52a8\u7535\u538b\uff08\u5747\u65b9\u6839\uff09");
    model.param("par3").set("R_g", "0[ohm]", "\u9a71\u52a8\u8f93\u51fa\u7535\u963b");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("geom1_sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("L_E", "(L_e/(sin(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e-1)");
    model.component("comp1").variable("var1")
         .descr("L_E", "\u97f3\u5708\u7535\u611f\uff08\u9891\u7387\u76f8\u5173\uff09");
    model.component("comp1").variable("var1").set("Rp_E", "(L_e/(cos(n_e*pi/2)))*(acpr.omega[s/rad])^(n_e)[ohm/H]");
    model.component("comp1").variable("var1")
         .descr("Rp_E", "\u7535\u963b\uff08\u78c1\u7cfb\u7edf\u4e2d\u7684\u635f\u8017\uff09");
    model.component("comp1").variable("var1").set("v0", "aveop1(solid.u_tZ)");
    model.component("comp1").variable("var1")
         .set("obj_1", "sum((comp1.Lp_pext_opt(sin(angle_eval/8*n), cos(angle_eval/8*n))-target_spl)^2, n, 0, 4)");
    model.component("comp1").variable("var1").descr("v0", "\u4f18\u5316\u76ee\u6807");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common("fsd1").selection().set(2, 3);
    model.component("comp1").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp1").common("fsb1").selection().named("geom1_sel6");
    model.component("comp1").common("fsb1").set("filterRadiusType", "Medium");
    model.component("comp1").common("fsb1").set("maximumDisplacement", "disp_dome");
    model.component("comp1").common().create("fsb2", "FreeShapeBoundary");
    model.component("comp1").common("fsb2").selection().named("geom1_sel10");
    model.component("comp1").common("fsb2").set("filterRadiusType", "Medium");
    model.component("comp1").common("fsb2").set("maximumDisplacement", "disp_wave");
    model.component("comp1").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp1").common("fsr1").selection().named("geom1_sel7");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("geom1_sel1");
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");

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
    model.component("comp1").material("mat2").label("Composite");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Cloth");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Coil");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Glass Fiber");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material("mat5").selection().geom("geom1", 1);
    model.component("comp1").material("mat5").selection().named("geom1_ls1_bnd");
    model.component("comp1").material("mat1").selection().named("geom1_comsel1");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().named("geom1_sel6");
    model.component("comp1").material("mat3").selection().geom("geom1", 1);
    model.component("comp1").material("mat3").selection().named("geom1_sel8");
    model.component("comp1").material("mat4").selection().named("geom1_sel2");

    model.component("comp1").physics("acpr").selection().named("geom1_comsel1");
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 2);
    model.component("comp1").physics("acpr").feature("nra1").selection().named("geom1_sel3");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra1").set("h", "air_gap");
    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 2);
    model.component("comp1").physics("acpr").feature("pom1").selection().named("geom1_r3_dom");
    model.component("comp1").physics("acpr").feature("pom1").set("Rf_mat", "userdef");
    model.component("comp1").physics("acpr").feature("pom1").set("Rf", "rf_pa");
    model.component("comp1").physics("acpr").feature("pom1").set("Constants", "Miki");
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().named("geom1_sel5");
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("shell").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("emm1").create("dmp1", "Damping", 1);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("shell").feature("emm1").create("dmp2", "Damping", 1);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").selection().named("geom1_sel8");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").set("f1", "fmin");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").set("zeta1", "damp_susp");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").set("f2", "fmax");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").set("zeta2", "damp_susp");
    model.component("comp1").physics("shell").feature("to1").set("d", "th_dome");
    model.component("comp1").physics("shell").create("to2", "ThicknessOffset", 1);
    model.component("comp1").physics("shell").feature("to2").selection().named("geom1_ls1_bnd");
    model.component("comp1").physics("shell").feature("to2").set("d", "th_former");
    model.component("comp1").physics("shell").create("to3", "ThicknessOffset", 1);
    model.component("comp1").physics("shell").feature("to3").selection().named("geom1_sel8");
    model.component("comp1").physics("shell").feature("to3").set("d", "th_susp");
    model.component("comp1").physics("shell").create("fix1", "Fixed", 0);
    model.component("comp1").physics("shell").feature("fix1").selection().named("geom1_sel11");
    model.component("comp1").physics("solid").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("solid").feature("bl1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bl1").set("force", new String[]{"0", "0", "BL*cir.R1.i"});
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_g");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R_E");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L_E");
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "Rp_E");
    model.component("comp1").physics("cir").create("V2", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("V2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V2").set("value", "BL*v0");

    model.component("comp1").multiphysics().create("asb2", "AcousticStructureBoundary", 1);
    model.component("comp1").multiphysics("asb2").selection().all();
    model.component("comp1").multiphysics("asb2").set("Structure_physics", "solid");
    model.component("comp1").multiphysics().create("sshc1", "SolidShellConnection2DAxis", -1);
    model.component("comp1").multiphysics("sshc1").set("connectionSettings", "sharedBnd");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(1, 7, 8, 11);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "air_gap");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "r1_susp");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 3, 4, 6, 9, 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_sel6");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "mesh_optim");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("geom1_sel10");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_sel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(37);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_sel5");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "range(fmin,(fmax-fmin)/(nf-1),fmax)");
    model.study("std1").feature("freq").set("probesel", "none");
    model.study("std1").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().named("geom1_sel4");
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").label("\u7814\u7a76 1 - \u521d\u59cb\u8bbe\u8ba1/\u65cb\u8f6c");

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("dset2").selection().geom("geom1", 1);
    model.result().dataset("dset2").selection().named("geom1_sel9");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c - \u521d\u59cb\u8bbe\u8ba1");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("startangle", 90);
    model.result().dataset("rev1").set("revangle", 90);
    model.result().dataset("rev1").set("endcaps", false);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("hasvar", true);
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8f74\u4e0a 1 m \u5904\u7684\u58f0\u538b\u7ea7");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "freq (Hz)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "SPL (dB)");
    model.result("pg1").set("legendpos", "lowermiddle");
    model.result("pg1").create("oct1", "OctaveBand");
    model.result("pg1").feature("oct1").set("quantity", "bandpower");
    model.result("pg1").feature("oct1").set("markerpos", "datapoints");
    model.result("pg1").feature("oct1").set("linewidth", "preference");
    model.result("pg1").feature("oct1").selection().geom("geom1");
    model.result("pg1").feature("oct1").set("expr", "pext(0,1[m])");
    model.result("pg1").feature("oct1").set("descractive", true);
    model.result("pg1").feature("oct1")
         .set("descr", "1 m \u5904\u7684\u58f0\u538b\u7ea7\uff08\u5916\u573a\uff09- \u521d\u59cb\u8bbe\u8ba1");
    model.result("pg1").feature("oct1").set("quantity", "continuous");
    model.result("pg1").feature("oct1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "target_spl", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "", 0);
    model.result("pg1").feature("glob1").set("linestyle", "dotted");
    model.result("pg1").feature("glob1").set("linecolor", "black");
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u65b9\u5411\u6027");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("dir1", "Directivity");
    model.result("pg2").feature("dir1").set("linewidth", "preference");
    model.result("pg2").feature("dir1").set("normalization", "none");
    model.result("pg2").feature("dir1").set("expr", "acpr.efc1.Lp_pext-target_spl");
    model.result("pg2").feature("dir1").set("anglerestr", "manual");
    model.result("pg2").feature("dir1").set("phimin", -90);
    model.result("pg2").feature("dir1").set("phirange", 90);
    model.result("pg2").feature("dir1").set("radius", 1000);
    model.result("pg2").feature("dir1").set("levelmethod", "levels");
    model.result("pg2").feature("dir1").set("levels", "-42 -30 -24 -18 -12 -6 -3 3 6 9");
    model.result("pg2").feature("dir1").set("layout", "frequencyy");
    model.result("pg2").feature("dir1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("dir2", "dir1");
    model.result("pg2").run();
    model.result("pg2").feature("dir2").set("levels", "-6 -3 3 6");
    model.result("pg2").feature("dir2").set("coloring", "uniform");
    model.result("pg2").feature("dir2").set("color", "black");
    model.result("pg2").feature("dir2").set("contourtype", "lines");
    model.result("pg2").feature("dir2").set("linewidth", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b\u7ea7");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "mir1x<0");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "0");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "gray");
    model.result("pg3").feature("line1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("line1").feature("filt1").set("expr", "mir1x<0");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("line2", "line1");
    model.result("pg3").run();
    model.result("pg3").feature("line2").set("linetype", "tube");
    model.result("pg3").feature("line2").set("color", "black");
    model.result("pg3").feature("line2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("line2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("line2").feature("def1").set("scale", 10000);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u51e0\u4f55\u5f62\u72b6");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "0");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("filt1").set("expr", "(rev1y>0)*(rev1x<0)");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/asb2", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/sshc1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u4f18\u5316");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 20);
    model.study("std2").feature("sho").set("objectivesolution", "max");
    model.study("std2").feature("sho").setIndex("optobj", "comp1.obj_1", 0);
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").feature("freq")
         .set("plist", "range(fmin_optim,(fmax_optim-fmin_optim)/(nf_optim-1),fmax_optim)");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("geom1_sel4");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("edgecolor", "gray");
    model.result("pg5").set("frametype", "geometry");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg5").feature("arwl1").set("placement", "elements");
    model.result("pg5").feature("arwl1").set("scaleactive", true);
    model.result("pg5").feature("arwl1").set("arrowbase", "head");
    model.result("pg5").feature("arwl1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg5").feature("arwl1").feature("col1").set("rangecoloractive", true);
    model.result("pg5").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg5").feature("arwl1").feature("col1").set("colortable", "Rainbow");
    model.result("pg5").feature("arwl1").feature("col1").set("colorscalemode", "linear");
    model.result("pg5").run();

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg5").run();

    model.study("std2").feature("sho").set("probewindow", "");
    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/shell", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb2", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/sshc1", true);
    model.study("std3").feature("freq").set("plist", "range(fmin,(fmax-fmin)/(nf-1),fmax)");
    model.study("std3").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3 - \u4f18\u5316\u540e\u7684\u8bbe\u8ba1");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().named("geom1_sel4");
    model.result().dataset().duplicate("dset5", "dset4");
    model.result().dataset("dset5").label("\u7814\u7a76 3 - \u4f18\u5316\u540e\u7684\u8bbe\u8ba1/\u65cb\u8f6c");
    model.result().dataset("dset5").set("solution", "sol2");
    model.result().dataset("dset5").selection().geom("geom1", 1);
    model.result().dataset("dset5").selection().named("geom1_sel9");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c - \u4f18\u5316\u540e\u7684\u8bbe\u8ba1");
    model.result().dataset("rev2").set("data", "dset5");
    model.result().dataset("rev2").set("revangle", 90);
    model.result().dataset("rev2").set("endcaps", false);
    model.result().dataset("rev2").set("hasspacevars", true);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("oct2", "oct1");
    model.result("pg1").run();
    model.result("pg1").feature("oct2").set("data", "dset4");
    model.result("pg1").feature("oct2").set("descr", "1 m \u5904\u7684\u58f0\u538b\u7ea7 - \u4f18\u5316");
    model.result("pg1").feature("oct2").set("linestyle", "dashed");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("dir3", "dir1");
    model.result("pg2").run();
    model.result("pg2").feature("dir3").set("data", "dset4");
    model.result("pg2").feature("dir3").set("phimin", 0);
    model.result("pg2").feature("dir3").set("colorlegend", true);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("dir4", "dir2");
    model.result("pg2").run();
    model.result("pg2").feature("dir4").set("data", "dset4");
    model.result("pg2").feature("dir4").set("phimin", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg2").feature("lnsg1").set("linewidth", "preference");
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "-angle_eval/2", 0);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "deg", 0);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "angle_eval/2", 1);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "deg", 1);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "angle_eval/2", 2);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "deg", 2);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "-angle_eval/2", 3);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "deg", 3);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "-angle_eval/2", 4);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "deg", 4);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "fmin_optim", 0);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "fmin_optim", 1);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "fmax_optim", 2);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "fmax_optim", 3);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "fmin_optim", 4);
    model.result("pg2").feature("lnsg1").set("linestyle", "dashed");
    model.result("pg2").feature("lnsg1").set("linecolor", "gray");
    model.result("pg2").feature("lnsg1").set("linewidth", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("lnsg2", "LineSegments");
    model.result("pg2").feature("lnsg2").set("markerpos", "datapoints");
    model.result("pg2").feature("lnsg2").set("linewidth", "preference");
    model.result("pg2").feature("lnsg2").setIndex("xexpr", 0, 0);
    model.result("pg2").feature("lnsg2").setIndex("xexpr", 0, 1);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "fmin", 0);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "fmax", 1);
    model.result("pg2").feature("lnsg2").set("linestyle", "dashdot");
    model.result("pg2").feature("lnsg2").set("linecolor", "black");
    model.result("pg2").feature("lnsg2").set("linewidth", 1);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "dset4");
    model.result("pg3").feature("surf2").set("expr", "acpr.Lp_t");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("line3", "Line");
    model.result("pg3").feature("line3").set("data", "dset4");
    model.result("pg3").feature("line3").set("expr", "0");
    model.result("pg3").feature("line3").set("coloring", "uniform");
    model.result("pg3").feature("line3").set("color", "gray");
    model.result("pg3").run();
    model.result("pg3").create("line4", "Line");
    model.result("pg3").feature("line4").set("data", "dset4");
    model.result("pg3").feature("line4").set("expr", "0");
    model.result("pg3").feature("line4").set("linetype", "tube");
    model.result("pg3").feature("line4").set("inheritplot", "line2");
    model.result("pg3").feature("line4").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("data", "rev2");
    model.result("pg4").feature("surf2").set("expr", "0");
    model.result("pg4").feature("surf2").set("coloring", "uniform");
    model.result("pg4").feature("surf2").set("color", "custom");
    model.result("pg4").feature("surf2")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg4").feature("surf2").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("filt1").set("expr", "(rev2y>0)*(rev2x>0)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("data", "rev2");
    model.result("pg4").feature("line1").set("expr", "0");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("data", "rev2");
    model.result("pg4").feature("con1").set("expr", "fsd1.rel_disp");
    model.result("pg4").feature("con1").set("colortable", "HeatCamera");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").set("xlog", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg4").run();

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.title("\u9ad8\u97f3\u7f69\u548c\u6ce2\u5bfc\u7684\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u9ad8\u97f3\u626c\u58f0\u5668\u662f\u626c\u58f0\u5668\u7cfb\u7edf\u4e2d\u4f7f\u7528\u7684\u4e00\u79cd\u9ad8\u9891\u9a71\u52a8\u5668\u3002\u7406\u60f3\u7684\u9ad8\u97f3\u626c\u58f0\u5668\u5c06\u5728\u9a71\u52a8\u5668\u524d\u65b9\u4e00\u5b9a\u8ddd\u79bb\u5904\u4ea7\u751f\u6052\u5b9a\u7684\u58f0\u538b\u7ea7\uff08\u4e0e\u9891\u7387\u65e0\u5173\uff09\uff0c\u4e5f\u5c31\u662f\u5e73\u5766\u7684\u54cd\u5e94\u3002\u7406\u60f3\u60c5\u51b5\u4e0b\uff0c\u5f53\u542c\u97f3\u70b9\u504f\u79bb\u8f74\u65f6\uff0c\u9ad8\u97f3\u626c\u58f0\u5668\u4e5f\u5c06\u5728\u4e00\u5b9a\u7a0b\u5ea6\u4e0a\u4fdd\u6301\u8fd9\u79cd\u5e73\u5766\u54cd\u5e94\u3002\u9ad8\u97f3\u7f69\u5206\u5272\u632f\u52a8\u4e0e\u6ce2\u675f\u4e4b\u95f4\u590d\u6742\u7684\u76f8\u4e92\u4f5c\u7528\u662f\u626c\u58f0\u5668\u9a71\u52a8\u5668\u8bbe\u8ba1\u7684\u56fa\u6709\u7279\u5f81\uff1b\u8fd9\u53cd\u8fc7\u6765\u53c8\u4f1a\u5bfc\u81f4\u504f\u79bb\u7406\u60f3\u7684\u8f90\u5c04\u7279\u6027\u3002\n\n\u672c\u6559\u7a0b\u6f14\u793a\u9ad8\u97f3\u7f69\u548c\u6ce2\u5bfc\u7684\u5f62\u72b6\u4f18\u5316\u793a\u4f8b\u3002\u901a\u8fc7\u6539\u53d8\u8fd9\u4e24\u4e2a\u7ec4\u4ef6\u7684\u5f62\u72b6\uff0c\u53ef\u4ee5\u8c03\u6574\u7eb8\u76c6\u5206\u5272\u632f\u52a8\u548c\u6ce2\u675f\u6548\u5e94\uff0c\u4f7f\u4e24\u8005\u5728\u6574\u4e2a\u9891\u7387\u8303\u56f4\u5185\u76f8\u4e92\u62b5\u6d88\uff0c\u4ece\u800c\u521b\u5efa\u4e00\u4e2a\u66f4\u63a5\u8fd1\u7406\u60f3\u9ad8\u97f3\u7684\u626c\u58f0\u5668\u3002\u4f18\u5316\u7684\u8bbe\u8ba1\u5728\u6574\u4e2a\u9891\u7387\u8303\u56f4\u5185\u5448\u73b0\u51fa\u66f4\u5e73\u5766\u7684\u54cd\u5e94\u66f2\u7ebf\uff0c\u540c\u65f6\u6539\u8fdb\u4e86\u8f90\u5c04\u65b9\u5411\u56fe\u3002\u8fd9\u8868\u660e\u4e86\u4f18\u5316\u8fd9\u4e9b\u7c7b\u578b\u7684\u632f\u52a8\u58f0\u5b66\u95ee\u9898\u7684\u53ef\u80fd\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("tweeter_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
