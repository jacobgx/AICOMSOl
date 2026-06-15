/*
 * pm_motor_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pm_motor_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rmm", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n_sectors", "18", "\u6247\u533a\u6570");
    model.param().set("n_pairs", "n_sectors/2", "\u7ebf\u5708\u5bf9\u6570\u91cf");
    model.param().set("sector_angle", "360[deg]/n_sectors", "\u6247\u5f62\u89d2");
    model.param().set("rpm", "3000[rpm]", "\u7535\u673a\u8f6c\u901f");
    model.param()
         .set("time_one_cycle", "1/(rpm*n_sectors)", "\u4e00\u4e2a\u6247\u533a\u7684\u65cb\u8f6c\u65f6\u95f4");
    model.param().set("omega_rotor", "2*pi*rpm", "\u8f6c\u5b50\u89d2\u9891\u7387");
    model.param().set("freq", "n_pairs*rpm", "\u7535\u6e90\u9891\u7387");
    model.param().set("omega", "2*pi*freq", "\u7535\u6e90\u89d2\u9891\u7387");
    model.param().set("I_RMS", "100[A]", "\u7ebf\u5708\u5b89\u531d\u6570\uff0c\u5747\u65b9\u6839");
    model.param().set("I0", "I_RMS*sqrt(2)", "\u7ebf\u5708\u5b89\u531d\u6570\uff0c\u5cf0\u503c");
    model.param().set("t", "0[s]", "\u5c06\u7a33\u6001\u7814\u7a76\u7684\u65f6\u95f4 t \u8bbe\u4e3a\u96f6");
    model.param().set("r_outer", "1[dm]", "\u5b9a\u5b50\u5916\u534a\u5f84");
    model.param().set("th_outer", "0.05[dm]", "\u5916\u90e8\u5b9a\u5b50\u94c1\u82af\u7684\u5f84\u5411\u539a\u5ea6");
    model.param().set("w_pole", "0.1[dm]", "\u5b9a\u5b50\u78c1\u6781\u5bbd\u5ea6");
    model.param().set("sector_angle_shift", "2[deg]", "\u6247\u5f62\u89d2");
    model.param().set("r_inner", "0.8[dm]", "\u5b9a\u5b50\u78c1\u6781\u4e2d\u5fc3\u7247\u7684\u5185\u534a\u5f84");
    model.param().set("th_inner", "0.02[dm]", "\u5185\u90e8\u5b9a\u5b50\u94c1\u82af\u7684\u5f84\u5411\u539a\u5ea6");
    model.param().set("coil_offset", "0.01[dm]", "\u7ebf\u5708\u504f\u79fb\u91cf");
    model.param()
         .set("h_coil", "(r_outer-th_outer)-r_inner-2*coil_offset-t_pole_chamfer", "\u7ebf\u5708\u5f84\u5411\u4f38\u957f\u91cf");
    model.param().set("w_coil", "0.05[dm]", "\u7ebf\u5708\u5bbd\u5ea6");
    model.param().set("a_coil", "w_coil*h_coil", "\u7ebf\u5708\u6a2a\u622a\u9762\u79ef");
    model.param().set("m_th", "0.1[dm]", "\u6c38\u78c1\u4f53\u5f84\u5411\u4f38\u957f\u91cf");
    model.param().set("magnet_angle_shift", "5[deg]", "\u78c1\u4f53\u7684\u89d2\u5ea6\u53c2\u6570");
    model.param().set("s_th", "0.1[dm]", "\u8f6c\u5b50\u94c1\u82af\u539a\u5ea6");
    model.param().set("air_gap", "0.02[dm]", "\u6c14\u9699\u539a\u5ea6");
    model.param().set("t_case", "2[mm]", "\u94dd\u58f3\u539a\u5ea6");
    model.param().set("r_shaft", "20[mm]", "\u8f74\u534a\u5f84");
    model.param()
         .set("t_stator_core", "10[mm]", "\u5b9a\u5b50\u94c1\u82af\uff08\u4e00\u534a\uff09\u8f74\u5411\u5c3a\u5bf8");
    model.param()
         .set("t_inner", "18[mm]", "\u5b9a\u5b50\u548c\u8f6c\u5b50\u95f4\u7a7a\u6c14\u7684\u6324\u538b\u957f\u5ea6");
    model.param().set("t_shaft", "60[mm]", "\u8f74\u7684\u62c9\u4f38\u957f\u5ea6");
    model.param().set("t_magnet", "10[mm]", "\u78c1\u4f53\uff08\u4e00\u534a\uff09\u8f74\u5411\u5c3a\u5bf8");
    model.param().set("t_pole_chamfer", "3[mm]", "\u5b9a\u5b50\u94c1\u82af\u7684\u5012\u89d2\u5927\u5c0f");
    model.param().set("magnet_fillet", "3[mm]", "\u78c1\u4f53\u5706\u89d2\u534a\u5f84");
    model.param().set("end_winding_fillet", "2[mm]", "\u7aef\u90e8\u7ed5\u7ec4\u5706\u89d2\u534a\u5f84");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "pm_motor_3d.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

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
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat2").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N50 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.41[T]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat4").label("Aluminum");
    model.component("comp1").material("mat4").set("family", "aluminum");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat5").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat5").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat5").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat5").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat5").label("Structural steel");
    model.component("comp1").material("mat5").set("family", "custom");
    model.component("comp1").material("mat5")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat5").set("diffuse", "custom");
    model.component("comp1").material("mat5")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat5").set("ambient", "custom");
    model.component("comp1").material("mat5")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat5").set("noise", true);
    model.component("comp1").material("mat5").set("fresnel", 0.9);
    model.component("comp1").material("mat5").set("roughness", 0.3);
    model.component("comp1").material("mat5").set("diffusewrap", 0);
    model.component("comp1").material("mat5").set("reflectance", 0);
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat5").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat5").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat5").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat5").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat5").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat5").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat5").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat5").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat5").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat5").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat5").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat5").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat5").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat5").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat5").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat5").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat5").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat5").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat5").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat5").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat5").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat5").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat5").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat5").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat5").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat5").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat5").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat6").label("Copper");
    model.component("comp1").material("mat6").set("family", "copper");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat6").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat6").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().set(4, 12);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1[S/m]"});
    model.component("comp1").material("mat3").label("\u6c38\u78c1\u4f53");
    model.component("comp1").material("mat3").selection().set(14);
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"7e5"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.02"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("normBr", new String[]{"1[T]"});
    model.component("comp1").material("mat4").selection().set(1, 2, 3);
    model.component("comp1").material("mat5").selection().set(13, 15, 16);
    model.component("comp1").material("mat6").selection().set(7, 8, 9);

    model.component("comp1").physics("rmm").selection().set(4, 5, 6, 7, 8, 9, 10, 11, 12, 14);
    model.component("comp1").physics("rmm").create("mfc1", "MagneticFluxConservation", 3);
    model.component("comp1").physics("rmm").feature("mfc1").label("\u78c1\u901a\u91cf\u5b88\u6052 - \u7a7a\u6c14");
    model.component("comp1").physics("rmm").feature("mfc1").selection().set(5, 6, 10, 11);
    model.component("comp1").physics("rmm").create("mfc2", "MagneticFluxConservation", 3);
    model.component("comp1").physics("rmm").feature("mfc2").label("\u78c1\u901a\u91cf\u5b88\u6052 - \u94c1");
    model.component("comp1").physics("rmm").feature("mfc2").selection().set(12);
    model.component("comp1").physics("rmm").feature("mfc2").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("rmm").create("lcvf1", "LaminatedCoreVectorFormulation", 3);
    model.component("comp1").physics("rmm").feature("lcvf1").selection().set(4);
    model.component("comp1").physics("rmm").feature("lcvf1").set("LaminationModel", "DirectionBased");
    model.component("comp1").physics("rmm").feature("lcvf1").set("Direction", new int[]{0, 0, 1});
    model.component("comp1").physics("rmm").create("cmag1", "ConductingMagnet", 3);
    model.component("comp1").physics("rmm").feature("cmag1").selection().set(14);
    model.component("comp1").physics("rmm").feature("cmag1").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("rmm").feature("cmag1").feature("north1").selection().set(100);
    model.component("comp1").physics("rmm").feature("cmag1").feature("south1").selection().set(99);
    model.component("comp1").physics("rmm").create("coil1", "Coil", 3);
    model.component("comp1").physics("rmm").feature("coil1").selection().set(7, 8, 9);
    model.component("comp1").physics("rmm").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("rmm").feature("coil1").set("ICoil", "I0*sin(omega*t)");
    model.component("comp1").physics("rmm").feature("coil1").set("N", 1);
    model.component("comp1").physics("rmm").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("rmm").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("rmm").feature("coil1").set("coilWindArea", "a_coil");
    model.component("comp1").physics("rmm").feature("coil1").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").set("fl", 2);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").feature("ct1").selection().set(43);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("rmm").feature("coil1").feature("ccc1").feature("cg1").selection().set(61);

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().set(10, 11, 12, 14);
    model.component("comp1").common("rot1").set("rotationAngle", "omega_rotor*t");
    model.component("comp1").common("rot1").set("rotationAxis", new String[]{"0", "0", "-1"});

    model.component("comp1").physics("rmm").create("fcal1", "ForceCalculation", 3);
    model.component("comp1").physics("rmm").feature("fcal1").selection().set(12, 14);
    model.component("comp1").physics("rmm").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("rmm").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("rmm").feature("pc1").selection().set(26, 32, 71, 72);
    model.component("comp1").physics("rmm").feature("pc1").set("PeriodicType", "AntiPeriodicity");
    model.component("comp1").physics("rmm").feature().duplicate("pc2", "pc1");
    model.component("comp1").physics("rmm").feature("pc2").selection().set(23, 73);
    model.component("comp1").physics("rmm").feature().duplicate("pc3", "pc2");
    model.component("comp1").physics("rmm").feature("pc3").selection().set(74, 78, 82, 113, 114, 115);
    model.component("comp1").physics("rmm").create("ssc1", "SectorSymmetry", 2);
    model.component("comp1").physics("rmm").feature("ssc1").set("pairs", new String[]{"ap3"});
    model.component("comp1").physics("rmm").feature("ssc1").set("nsector", "n_sectors");
    model.component("comp1").physics("rmm").feature("ssc1").set("PeriodicType", "AntiPeriodicity");
    model.component("comp1").physics("rmm").create("ark1", "ArkkioTorqueCalculation", 3);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 - \u6c38\u78c1\u4f53");
    model.component("comp1").cpl("intop1").set("opname", "intop1_magnet");
    model.component("comp1").cpl("intop1").selection().set(14);
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 - \u7ebf\u5708");
    model.component("comp1").cpl("intop2").set("opname", "intop2_coil");
    model.component("comp1").cpl("intop2").selection().set(7, 8, 9);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 1 - \u626d\u77e9");
    model.component("comp1").probe("var1").set("expr", "rmm.Tax_0*n_sectors*2");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u8f74\u5411\u626d\u77e9 (N*m)");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2")
         .label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 2 - Arkkio \u7684\u626d\u77e9\u6cd5");
    model.component("comp1").probe("var2").set("expr", "rmm.Tark_1*2");
    model.component("comp1").probe("var2").set("descr", "Arkkio \u626d\u77e9\u6cd5 (N*m)");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3")
         .label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 3 - \u78c1\u4f53\u635f\u8017");
    model.component("comp1").probe("var3").set("expr", "intop1_magnet(rmm.Qh)*n_sectors*2");
    model.component("comp1").probe("var3").set("descractive", true);
    model.component("comp1").probe("var3").set("descr", "\u78c1\u4f53\u4e2d\u7684\u635f\u8017 (W)");
    model.component("comp1").probe("var3").set("window", "new");
    model.component("comp1").probe().duplicate("var4", "var3");
    model.component("comp1").probe("var4")
         .label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 4 - \u7ebf\u5708\u635f\u8017");
    model.component("comp1").probe("var4").set("expr", "intop2_coil(rmm.Qh)*n_sectors*2");
    model.component("comp1").probe("var4").set("descr", "\u7ebf\u5708\u4e2d\u7684\u635f\u8017 (W)");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(92, 100, 109);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "0.0005");
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(36);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.001);
    model.component("comp1").mesh("mesh1").feature().duplicate("ftri2", "ftri1");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(75);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "0.001/1.25");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").feature().duplicate("ftri3", "ftri2");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().set(23, 26, 32, 74, 78, 82);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftri3");
    model.component("comp1").mesh("mesh1").run("id3");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(105, 124);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.001/2");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(14);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(91, 92, 94, 99, 100, 109, 110);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.8);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").setGenPlots(false);
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature().move("ccc", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\uff08\u7a33\u6001\uff09");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("arrowcount", 2000);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/rmm", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tlist", "range(0,time_one_cycle/25,1.5*time_one_cycle)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("atolglobal", 0.005);

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c38\u78c1\u4f53\uff08\u77ac\u6001\uff09");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").selection().geom("geom1", 3);
    model.result("pg5").selection().geom("geom1", 3);
    model.result("pg5").selection().set(14);
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "rmm.normJ");
    model.result("pg5").feature("vol1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"rmm.Jx", "rmm.Jy", "rmm.Jz"});
    model.result("pg5").feature("arws1")
         .set("descr", "\u7535\u6d41\u5bc6\u5ea6 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg5").feature("arws1").set("arrowcount", 400);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").set("frametype", "spatial");

    model.study("std2").feature("time").set("plot", true);
    model.study("std2").feature("time").set("plotgroup", "pg5");
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u626d\u77e9");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u6c38\u78c1\u4f53\u635f\u8017");
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result("pg3").label("\u7ebf\u5708\u635f\u8017");
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset5", "dset4");
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().set(4, 12, 14);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u78c1\u901a\u5bc6\u5ea6\uff08\u77ac\u6001\uff09");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("arrowcount", 2000);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").run();

    model.study().create("std3");
    model.study("std3").create("emloss", "TimeToFrequencyLosses");
    model.study("std3").feature("emloss").set("fftinputstudy", "current");
    model.study("std3").feature("emloss").set("lossstarttime", "0");
    model.study("std3").feature("emloss").set("notsolnum", "auto");
    model.study("std3").feature("emloss").set("outputmap", new String[]{});
    model.study("std3").feature("emloss").setSolveFor("/physics/rmm", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u635f\u8017\u8ba1\u7b97");
    model.study("std3").feature("emloss").set("fftinputstudy", "std2");
    model.study("std3").feature("emloss").set("emT0", "time_one_cycle");
    model.study("std3").feature("emloss").set("nharmonics", 12);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u78c1\u4f53\u4e2d\u7684\u635f\u8017\u5bc6\u5ea6");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "rmm.Qh");
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().set(14);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").set("data", "dset6");
    model.result().numerical("int1").selection().set(14);
    model.result().numerical("int1").setIndex("expr", "rmm.Qh*n_sectors*2", 0);
    model.result().numerical("int1").setIndex("descr", "\u6c38\u78c1\u4f53\u7684\u603b\u635f\u8017\u529f\u7387", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();

    model.title("\u6c38\u78c1\u7535\u673a\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u6c38\u78c1 (PM) \u7535\u673a\u7528\u4e8e\u8bb8\u591a\u9ad8\u7aef\u5e94\u7528\uff0c\u4f8b\u5982\u7535\u52a8\u6c7d\u8f66\u548c\u6df7\u5408\u52a8\u529b\u6c7d\u8f66\u7b49\u3002\u8fd9\u91cc\u5b58\u5728\u4e00\u4e2a\u91cd\u8981\u7684\u8bbe\u8ba1\u9650\u5236\uff0c\u5373\u78c1\u4f53\u5bf9\u9ad8\u6e29\u5f88\u654f\u611f\uff0c\u800c\u9ad8\u6e29\u53ef\u80fd\u901a\u8fc7\u7535\u6d41\uff08\u5c24\u5176\u662f\u6da1\u6d41\uff09\u5f15\u8d77\u7684\u70ed\u635f\u8017\u800c\u4ea7\u751f\u3002\n\n\u672c\u6559\u7a0b\u5bf9 18 \u6781\u6c38\u78c1\u7535\u673a\u8fdb\u884c\u4e09\u7ef4\u5efa\u6a21\uff0c\u4ee5\u51c6\u786e\u6355\u83b7\u78c1\u4f53\u4e2d\u7684\u6da1\u6d41\u635f\u8017\u3002\u5176\u4e2d\u5c06\u51e0\u4f55\u7ed3\u6784\u7684\u4e2d\u5fc3\u90e8\u5206\uff08\u5305\u542b\u8f6c\u5b50\u548c\u90e8\u5206\u6c14\u9699\uff09\u5efa\u6a21\u4e3a\u76f8\u5bf9\u4e8e\u5b9a\u5b50\u5750\u6807\u7cfb\u65cb\u8f6c\uff0c\u5e76\u5229\u7528\u6247\u533a\u5bf9\u79f0\u548c\u8f74\u5411\u955c\u50cf\u5bf9\u79f0\u6765\u51cf\u5c11\u8ba1\u7b97\u91cf\uff0c\u540c\u65f6\u4ecd\u6355\u83b7\u8be5\u8bbe\u5907\u7684\u5168\u4e09\u7ef4\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("pm_motor_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
