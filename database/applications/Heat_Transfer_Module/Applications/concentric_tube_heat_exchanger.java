/*
 * concentric_tube_heat_exchanger.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:17 by COMSOL 6.3.0.290. */
public class concentric_tube_heat_exchanger {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowAlgebraicYplus", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf2", "TurbulentFlowAlgebraicYplus", "geom1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

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
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/spf2", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("R1", "25[mm]");
    model.param().descr("R1", "Inner radius");
    model.param().set("R2", "40[mm]");
    model.param().descr("R2", "Outer radius");
    model.param().set("L", "10[m]");
    model.param().descr("L", "Length");
    model.param().set("ds", "1[mm]");
    model.param().descr("ds", "Thin interface thickness");
    model.param().set("dext", "1[mm]");
    model.param().descr("dext", "Outer tube thickness");
    model.param().set("T1", "360[K]");
    model.param().descr("T1", "Inner inlet temperature");
    model.param().set("T2", "300[K]");
    model.param().descr("T2", "Outer inlet temperature");
    model.param().set("mfr1", "0.1[kg/s]");
    model.param().descr("mfr1", "Inner mass flow rate");
    model.param().set("mfr2", "0.25[kg/s]");
    model.param().descr("mfr2", "Outer mass flow rate");
    model.param().set("pA1", "2[bar]");
    model.param().descr("pA1", "Inner outlet absolute pressure");
    model.param().set("pA2", "2[bar]");
    model.param().descr("pA2", "Outer outlet absolute pressure");
    model.param().set("vol1", "L*pi*R1^2");
    model.param().descr("vol1", "Inner tube volume");
    model.param().set("vol2", "L*pi*(R2^2-R1^2)");
    model.param().descr("vol2", "Outer tube volume");
    model.param().set("vol0", "vol1+vol2");
    model.param().descr("vol0", "Overall volume");
    model.param().set("S", "2*pi*R1*L");
    model.param().descr("S", "Heat exchange surface");
    model.param().set("beta", "S/vol0");
    model.param().descr("beta", "Compactness");
    model.param().set("rho0_tube", "7850[kg/m^3]");
    model.param().descr("rho0_tube", "Tube density");
    model.param().set("k0_tube", "44.5[W/(m*K)]");
    model.param().descr("k0_tube", "Tube thermal conductivity");
    model.param().set("rho0_inner", "1000[kg/m^3]");
    model.param().descr("rho0_inner", "Density, inner tube");
    model.param().set("Cp0_inner", "4200[J/(kg*K)]");
    model.param().descr("Cp0_inner", "Heat capacity at constant pressure, inner tube");
    model.param().set("k0_inner", "0.6[W/(m*K)]");
    model.param().descr("k0_inner", "Thermal conductivity, inner tube");
    model.param().set("mu0_inner", "0.001[Pa*s]");
    model.param().descr("mu0_inner", "Dynamic viscosity, inner tube");
    model.param().set("rho0_outer", "1000[kg/m^3]");
    model.param().descr("rho0_outer", "Density, outer tube");
    model.param().set("Cp0_outer", "4200[J/(kg*K)]");
    model.param().descr("Cp0_outer", "Heat capacity at constant pressure, outer tube");
    model.param().set("k0_outer", "0.6[W/(m*K)]");
    model.param().descr("k0_outer", "Thermal conductivity, outer tube");
    model.param().set("mu0_outer", "0.001[Pa*s]");
    model.param().descr("mu0_outer", "Dynamic viscosity, outer tube");
    model.param().set("rho_tube", "7850[kg/m^3]");
    model.param().descr("rho_tube", "Displayed tube density");
    model.param().set("k_tube", "44.5[W/(m*K)]");
    model.param().descr("k_tube", "Displayed tube thermal conductivity");
    model.param().set("rho_inner", "1000[kg/m^3]");
    model.param().descr("rho_inner", "Displayed density, inner tube");
    model.param().set("Cp_inner", "4200[J/(kg*K)]");
    model.param().descr("Cp_inner", "Displayed heat capacity at constant pressure, inner tube");
    model.param().set("k_inner", "0.6[W/(m*K)]");
    model.param().descr("k_inner", "Displayed thermal conductivity, inner tube");
    model.param().set("mu_inner", "0.001[Pa*s]");
    model.param().descr("mu_inner", "Displayed dynamic viscosity, inner tube");
    model.param().set("rho_outer", "1000[kg/m^3]");
    model.param().descr("rho_outer", "Displayed density, outer tube");
    model.param().set("Cp_outer", "4200[J/(kg*K)]");
    model.param().descr("Cp_outer", "Displayed heat capacity at constant pressure, outer tube");
    model.param().set("k_outer", "0.6[W/(m*K)]");
    model.param().descr("k_outer", "Displayed thermal conductivity, outer tube");
    model.param().set("mu_outer", "0.001[Pa*s]");
    model.param().descr("mu_outer", "Displayed dynamic viscosity, outer tube");
    model.param().set("Re_inner", "mfr1*2*R1/(mu_inner*pi*R1^2)");
    model.param().descr("Re_inner", "Reynolds number, inner tube");
    model.param().set("Re_outer", "mfr2*(R2-R1)/(mu_outer*pi*(R2^2-R1^2))");
    model.param().descr("Re_outer", "Reynolds number, outer tube");
    model.param().set("m_tube", "rho_tube*2*pi*L*(R1*ds+R2*dext)");
    model.param().descr("m_tube", "Tube mass");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat1").label("Water, liquid");
    model.material("mat1").set("family", "water");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat2").label("Transformer oil");
    model.material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"243.0", "273.0", "4492.20229-64.7408879*T^1+0.349900959*T^2-8.40477E-4*T^3+7.57041667E-7*T^4"}, {"273.0", "373.0", "91.4524999-1.33227058*T^1+0.00777680216*T^2-2.27271368E-5*T^3+3.32419673E-8*T^4-1.94631023E-11*T^5"}});
    model.material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"223.0", "293.0", "-117056.38+1816.76208*T^1-10.305786*T^2+0.0256691919*T^3-2.36742424E-5*T^4"}, {"293.0", "373.0", "-13408.1491+123.044152*T^1-0.335401786*T^2+3.125E-4*T^3"}});
    model.material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"223.0", "373.0", "1055.04607-0.581753034*T^1-6.40531689E-5*T^2"}});
    model.material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"223.0", "373.0", "0.134299084-8.04973822E-5*T^1"}});
    model.material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat2").propertyGroup("def").addInput("temperature");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat3").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat3").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat3").label("Air");
    model.material("mat3").set("family", "air");
    model.material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat3").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat3").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat3").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat3").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat3").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat3").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat3").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat3").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat3").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat3").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat3").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat3").propertyGroup("def").set("molarmass", "");
    model.material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat3").propertyGroup("def").addInput("temperature");
    model.material("mat3").propertyGroup("def").addInput("pressure");
    model.material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.material("mat3").materialType("nonSolid");
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("User defined, inner fluid");
    model.material("mat4").propertyGroup("def").set("dynamicviscosity", "");
    model.material("mat4").propertyGroup("def").set("heatcapacity", "");
    model.material("mat4").propertyGroup("def").set("density", "");
    model.material("mat4").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat4").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0_inner"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", new String[]{"Cp0_inner"});
    model.material("mat4").propertyGroup("def").set("density", new String[]{"rho0_inner"});
    model.material("mat4").propertyGroup("def").set("thermalconductivity", new String[]{"k0_inner"});
    model.material().create("mat5", "Common", "");
    model.material("mat5").label("User defined, outer fluid");
    model.material("mat5").propertyGroup("def").set("dynamicviscosity", "");
    model.material("mat5").propertyGroup("def").set("heatcapacity", "");
    model.material("mat5").propertyGroup("def").set("density", "");
    model.material("mat5").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat5").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0_outer"});
    model.material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"Cp0_outer"});
    model.material("mat5").propertyGroup("def").set("density", new String[]{"rho0_outer"});
    model.material("mat5").propertyGroup("def").set("thermalconductivity", new String[]{"k0_outer"});
    model.material().create("mat6", "Common", "");
    model.material("mat6").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat6").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat6").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat6").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat6").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat6").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat6").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat6").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat6").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat6").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat6").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat6").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat6").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat6").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat6").label("Structural steel");
    model.material("mat6").set("family", "custom");
    model.material("mat6")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat6").set("diffuse", "custom");
    model.material("mat6")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat6").set("ambient", "custom");
    model.material("mat6")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat6").set("noise", true);
    model.material("mat6").set("fresnel", 0.9);
    model.material("mat6").set("roughness", 0.3);
    model.material("mat6").set("diffusewrap", 0);
    model.material("mat6").set("reflectance", 0);
    model.material("mat6").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat6").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat6").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat6").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat6").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat6").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat6").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat6").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat6").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat6").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat6").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat6").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});

    return model;
  }

  public static Model run2(Model model) {
    model.material("mat6").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat6").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat6").propertyGroup("Enu").addInput("temperature");
    model.material("mat6").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat6").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat6").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat6").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat6").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat6").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat6").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat6").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat6").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat6").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat6").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat6").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat6").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat6").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat6").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat6").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat6").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat6").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.material("mat6").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat6").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat6").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat6").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat6").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat6").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat6").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat6").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat6").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat6").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat6").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat6").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat6").propertyGroup("Voce").addInput("temperature");
    model.material("mat6").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.material("mat6").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat6").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat6").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat6").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat6").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat6").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat6").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat6").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.material("mat6").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat6").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat6").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat6").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat6").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat6").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat6").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat6").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat6").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat6").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat6").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat6").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat6").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat6").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat6").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat6").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.material().create("mat7", "Common", "");
    model.material("mat7").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat7").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat7").label("Copper");
    model.material("mat7").set("family", "copper");
    model.material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat7").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat7").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat7").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat7").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat7").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat7").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat7").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat7").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat8", "Common", "");
    model.material("mat8").label("User defined, tubes");
    model.material("mat8").propertyGroup("def").set("density", "");
    model.material("mat8").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat8").propertyGroup("def").set("density", new String[]{"rho0_tube"});
    model.material("mat8").propertyGroup("def").set("thermalconductivity", new String[]{"k0_tube"});
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("link", "mat6", 0);
    model.material("lmat1").setIndex("thickness", "ds", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R1", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R2", "L"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pch1", "ParameterCheck");
    model.component("comp1").geom("geom1").feature("pch1").set("condition", "R2-R1<5[mm]-eps");
    model.component("comp1").geom("geom1").feature("pch1")
         .set("message", "The outer radius should be at least 5 mm greater than the inner radius.");
    model.component("comp1").geom("geom1").run("pch1");
    model.component("comp1").geom("geom1").create("pch2", "ParameterCheck");
    model.component("comp1").geom("geom1").feature("pch2")
         .set("condition", "Re_inner<=0||Re_inner>1e7||Re_outer<=0||Re_outer>1e7");
    model.component("comp1").geom("geom1").feature("pch2")
         .set("message", "The Reynolds numbers should be strictly greater than 0 and less than 1.0e7.");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").label("View: Scaled Geometry");
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view1").axis().set("xmin", "-R2/30");
    model.component("comp1").view("view1").axis().set("xmax", "R2+R2/30");
    model.component("comp1").view("view1").axis().set("ymin", "-L/30");
    model.component("comp1").view("view1").axis().set("ymax", "L+L/30");
    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Inner Domain");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Outer Domain");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("Lower Inner Boundary");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(2);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("Lower Outer Boundary");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(5);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("Upper Inner Boundary");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(3);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("Upper Outer Boundary");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(6);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("Interface");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(4);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("Interface and Exterior Boundary");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(4, 7);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("Average: Inner Inlet");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("sel3");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("Average: Inner Outlet");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("sel5");
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").label("Average: Outer Inlet");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop3").selection().named("sel6");
    model.component("comp1").cpl().create("aveop4", "Average");
    model.component("comp1").cpl("aveop4").set("axisym", true);
    model.component("comp1").cpl("aveop4").label("Average: Outer Outlet");
    model.component("comp1").cpl("aveop4").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop4").selection().named("sel4");
    model.component("comp1").cpl().create("aveop5", "Average");
    model.component("comp1").cpl("aveop5").set("axisym", true);
    model.component("comp1").cpl("aveop5").label("Average: Interface");
    model.component("comp1").cpl("aveop5").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop5").selection().named("sel7");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("Integration: Inner Inlet");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel3");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("Integration: Inner Outlet");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("sel5");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").label("Integration: Outer Inlet");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().named("sel6");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").label("Integration: Outer Outlet");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop4").selection().named("sel4");
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").label("Integration: Interface");
    model.component("comp1").cpl("intop5").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop5").selection().named("sel7");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("T1i", "T1");
    model.component("comp1").variable("var1").descr("T1i", "Inner inlet temperature");
    model.component("comp1").variable("var1").set("T1o", "aveop2(T)");
    model.component("comp1").variable("var1").descr("T1o", "Inner outlet temperature");
    model.component("comp1").variable("var1").set("T2i", "T2");
    model.component("comp1").variable("var1").descr("T2i", "Outer inlet temperature");
    model.component("comp1").variable("var1").set("T2o", "aveop4(T)");
    model.component("comp1").variable("var1").descr("T2o", "Outer outlet temperature");
    model.component("comp1").variable("var1").set("dT1", "T1i-T2o");
    model.component("comp1").variable("var1").descr("dT1", "Temperature difference, inner inlet vs. outer outlet");
    model.component("comp1").variable("var1").set("dT2", "T1o-T2i");
    model.component("comp1").variable("var1").descr("dT2", "Temperature difference, inner outlet vs. outer inlet");
    model.component("comp1").variable("var1").set("U", "abs(aveop5(ht.ndflux)*log(dT1/(dT2+eps))/(dT1-dT2+eps))");
    model.component("comp1").variable("var1").descr("U", "Overall heat transfer coefficient");
    model.component("comp1").variable("var1").set("A", "intop5(1)");
    model.component("comp1").variable("var1").descr("A", "Interface surface");
    model.component("comp1").variable("var1")
         .set("E", "abs(intop5(ht.ndflux)/(intop1(ht.rho*substval(ht.DeltaH,T,T1)*(-u*nr-w*nz))-intop2(ht.rho*substval(ht.DeltaH,T,T2)*(u*nr+w*nz))+eps))");
    model.component("comp1").variable("var1").descr("E", "Effectiveness");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().named("sel1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().named("sel2");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk3").selection().named("sel8");
    model.component("comp1").material("matlnk3").set("link", "mat6");
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").selection().named("sel7");

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T1");
    model.component("comp1").physics("ht").create("init2", "init", 2);
    model.component("comp1").physics("ht").feature("init2").selection().named("sel2");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T2");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 1);
    model.component("comp1").physics("ht").feature("sls1").selection().named("sel7");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T1");
    model.component("comp1").physics("ht").create("ifl2", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl2").selection().named("sel6");
    model.component("comp1").physics("ht").feature("ifl2").set("Tustr", "T2");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel5");
    model.component("comp1").physics("ht").create("ofl2", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl2").selection().named("sel4");
    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", 0);
    model.component("comp1").physics("spf").feature("init1").set("p_init", "pA1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel3");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("mfr", "mfr1");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel5");
    model.component("comp1").physics("spf").feature("out1").set("p0", "pA1");
    model.component("comp1").physics("spf2").selection().named("sel2");
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty").set("pref", 0);
    model.component("comp1").physics("spf2").feature("init1").set("p_init", "pA2");
    model.component("comp1").physics("spf2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf2").feature("inl1").selection().named("sel6");
    model.component("comp1").physics("spf2").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf2").feature("inl1").set("mfr", "mfr2");
    model.component("comp1").physics("spf2").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf2").feature("out1").selection().named("sel4");
    model.component("comp1").physics("spf2").feature("out1").set("p0", "pA2");

    model.component("comp1").multiphysics().create("nitf2", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf2").set("Fluid_physics", "spf2");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel8");
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothmaxiter", 20);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel8");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Velocity (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("Pressure (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("Revolution 2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Velocity, 3D (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("Exterior Walls");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(4);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("Wall Resolution (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("Wall Resolution");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("Height Expression");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 1);
    model.result().dataset("lshl1").selection().set(4);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("Temperature (ht)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().set(1, 2);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("Domain");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("surf2", "Surface");
    model.result("pg5").feature("surf2").label("Layered Shell");
    model.result("pg5").feature("surf2").set("data", "lshl1");
    model.result("pg5").feature("surf2").set("showsolutionparams", "on");
    model.result("pg5").feature("surf2").set("solutionparams", "parent");
    model.result("pg5").feature("surf2").set("expr", "T");
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").set("smooth", "internal");
    model.result("pg5").feature("surf2").set("showsolutionparams", "on");
    model.result("pg5").feature("surf2").set("data", "lshl1");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature().create("line1", "Line");
    model.result("pg5").feature("line1").label("Layered Shell Edges");
    model.result("pg5").feature("line1").set("data", "lshl1");
    model.result("pg5").feature("line1").set("showsolutionparams", "on");
    model.result("pg5").feature("line1").set("solutionparams", "parent");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "fromtheme");
    model.result("pg5").feature("line1").set("smooth", "internal");
    model.result("pg5").feature("line1").set("showsolutionparams", "on");
    model.result("pg5").feature("line1").set("data", "lshl1");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("Velocity (spf2)");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf2.U");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("Pressure (spf2)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("Contour");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p2");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("Velocity, 3D (spf2)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("Surface");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "spf2.U");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result().dataset().create("edg2", "Edge2D");
    model.result().dataset("edg2").label("Exterior Walls 1");
    model.result().dataset("edg2").set("data", "dset1");
    model.result().dataset("edg2").selection().geom("geom1", 1);
    model.result().dataset("edg2").selection().set(4, 7);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("Wall Resolution (spf2)");
    model.result("pg9").set("dataisaxisym", "off");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("line1", "Line");
    model.result("pg9").feature("line1").label("Wall Resolution");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("expr", "spf2.Delta_wPlus");
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("smooth", "internal");
    model.result("pg9").feature("line1").set("showsolutionparams", "on");
    model.result("pg9").feature("line1").set("data", "parent");
    model.result("pg9").feature("line1").feature().create("hght1", "Height");
    model.result("pg9").feature("line1").feature("hght1").label("Height Expression");
    model.result("pg9").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg9").feature("line1").feature("hght1").set("expr", "spf2.WRHeightExpr");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("Temperature and Fluid Flow (nitf1)");
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("Fluid Temperature");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "nitf1.T");
    model.result("pg10").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg10").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg10").feature().create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").label("Fluid Flow");
    model.result("pg10").feature("arws1").set("showsolutionparams", "on");
    model.result("pg10").feature("arws1").set("solutionparams", "parent");
    model.result("pg10").feature("arws1").set("expr", new String[]{"nitf1.ur", "nitf1.uz"});
    model.result("pg10").feature("arws1").set("xnumber", 30);
    model.result("pg10").feature("arws1").set("ynumber", 30);
    model.result("pg10").feature("arws1").set("arrowtype", "cone");
    model.result("pg10").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("arws1").set("showsolutionparams", "on");
    model.result("pg10").feature("arws1").set("data", "parent");
    model.result("pg10").feature("arws1").feature().create("col1", "Color");
    model.result("pg10").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg10").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg10").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("Temperature and Fluid Flow (nitf2)");
    model.result("pg11").set("dataisaxisym", "off");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("Fluid Temperature");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "nitf2.T");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg11").feature().create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").label("Fluid Flow");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("solutionparams", "parent");
    model.result("pg11").feature("arws1").set("expr", new String[]{"nitf2.ur", "nitf2.uz"});
    model.result("pg11").feature("arws1").set("xnumber", 30);
    model.result("pg11").feature("arws1").set("ynumber", 30);
    model.result("pg11").feature("arws1").set("arrowtype", "cone");
    model.result("pg11").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("arws1").set("showsolutionparams", "on");
    model.result("pg11").feature("arws1").set("data", "parent");
    model.result("pg11").feature("arws1").feature().create("col1", "Color");
    model.result("pg11").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg11").feature("arws1").feature("col1").set("expr", "spf2.U");
    model.result("pg11").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg11").feature("arws1").feature("filt1").set("expr", "spf2.U>nitf2.Uave");
    model.result("pg1").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("Temperature over Length");
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "Temperature over Length");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().set(1);
    model.result("pg12").feature("lngr1").set("expr", "T");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "z");
    model.result("pg12").feature("lngr1").set("linecolor", "custom");
    model.result("pg12").feature("lngr1").set("customlinecolor", new double[]{0, 0, 1});
    model.result("pg12").feature("lngr1").set("linewidth", 3);
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "Inner tube", 0);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr2").set("linewidth", "preference");
    model.result("pg12").feature("lngr2").selection().set(7);
    model.result("pg12").feature("lngr2").set("expr", "T");
    model.result("pg12").feature("lngr2").set("xdata", "expr");
    model.result("pg12").feature("lngr2").set("xdataexpr", "z");
    model.result("pg12").feature("lngr2").set("linecolor", "custom");
    model.result("pg12").feature("lngr2")
         .set("customlinecolor", new double[]{0.03529411926865578, 0.4627451002597809, 0.03529411926865578});
    model.result("pg12").feature("lngr2").set("linewidth", 3);
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("legendmethod", "manual");
    model.result("pg12").feature("lngr2").setIndex("legends", "Outer tube", 0);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("Pressure over Length");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("title", "Pressure over Length");
    model.result("pg13").set("legendpos", "middleright");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").selection().set(1);
    model.result("pg13").feature("lngr1").set("expr", "p");
    model.result("pg13").feature("lngr1").set("descr", "Pressure");
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "z");
    model.result("pg13").feature("lngr1").set("linecolor", "custom");
    model.result("pg13").feature("lngr1").set("customlinecolor", new double[]{0, 0, 1});
    model.result("pg13").feature("lngr1").set("linewidth", 3);
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("legendmethod", "manual");
    model.result("pg13").feature("lngr1").setIndex("legends", "Inner tube", 0);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").create("lngr2", "LineGraph");
    model.result("pg13").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr2").set("linewidth", "preference");
    model.result("pg13").feature("lngr2").selection().set(7);
    model.result("pg13").feature("lngr2").set("expr", "p2");
    model.result("pg13").feature("lngr2").set("descr", "Pressure");
    model.result("pg13").feature("lngr2").set("xdata", "expr");
    model.result("pg13").feature("lngr2").set("xdataexpr", "z");
    model.result("pg13").feature("lngr2").set("linecolor", "custom");
    model.result("pg13").feature("lngr2")
         .set("customlinecolor", new double[]{0.03529411926865578, 0.4627451002597809, 0.03529411926865578});
    model.result("pg13").feature("lngr2").set("linewidth", 3);
    model.result("pg13").feature("lngr2").set("legend", true);
    model.result("pg13").feature("lngr2").set("legendmethod", "manual");
    model.result("pg13").feature("lngr2").setIndex("legends", "Outer tube", 0);
    model.result("pg13").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Log Mean Temperature Difference");
    model.result().numerical("gev1").setIndex("expr", "abs(dT1-dT2)/abs(log(dT1/(dT2+eps))+eps)", 0);
    model.result().numerical("gev1").setIndex("descr", "Log mean temperature difference (LMTD)", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Log Mean Temperature Difference");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Overall Heat Transfer Coefficient");
    model.result().numerical("gev2").setIndex("expr", "U", 0);
    model.result().numerical("gev2").setIndex("descr", "Overall heat transfer coefficient", 0);
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Effectiveness");
    model.result().numerical("gev3").setIndex("expr", "E", 0);
    model.result().numerical("gev3").setIndex("descr", "Effectiveness", 0);
    model.result().numerical("gev3").set("table", "tbl1");
    model.result().numerical("gev3").appendResult();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Heat Transfer Units");
    model.result().numerical("gev4").setIndex("expr", "U*A*E*abs(T1-T2)/(abs(intop5(ht.ndflux))+eps)", 0);
    model.result().numerical("gev4").setIndex("descr", "Heat transfer units (NTU)", 0);
    model.result().numerical("gev4").set("table", "tbl1");
    model.result().numerical("gev4").appendResult();
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("Pressure Drop, Inner Tube");
    model.result().numerical("gev5").setIndex("expr", "aveop1(p)-aveop2(p)", 0);
    model.result().numerical("gev5").setIndex("descr", "Pressure drop, inner tube", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Pressure Drop, Inner Tube");
    model.result().numerical("gev5").set("table", "tbl2");
    model.result().numerical("gev5").setResult();
    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("Pressure Drop, Outer Tube");
    model.result().numerical("gev6").setIndex("expr", "aveop3(p2)-aveop4(p2)", 0);
    model.result().numerical("gev6").setIndex("descr", "Pressure drop, outer tube", 0);
    model.result().numerical("gev6").set("table", "tbl2");
    model.result().numerical("gev6").appendResult();
    model.result("pg12").run();

    model.title("\u540c\u5fc3\u7ba1\u5f0f\u6362\u70ed\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u7528\u4e8e\u5728\u5c42\u6d41\u6216\u6e4d\u6d41\u4e4b\u95f4\u8fdb\u884c\u5207\u6362\u7684\u7528\u6237\u9009\u9879\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u66f4\u6539\u8fb9\u754c\u6761\u4ef6\n\u2022 \u4f7f\u7528\u56fe\u8868\u7ed8\u56fe\u5c06\u6e29\u5ea6\u76f8\u5173\u7684\u6750\u6599\u5c5e\u6027\u53ef\u89c6\u5316\n\u2022 \u7528\u4e8e\u8bbe\u7f6e\u6c42\u89e3\u5668\u5bb9\u5dee\u7684\u7528\u6237\u9009\u9879\n\n\u786e\u5b9a\u5408\u9002\u7684\u6362\u70ed\u5668\u5c3a\u5bf8\u662f\u786e\u4fdd\u5176\u6709\u6548\u6027\u7684\u5173\u952e\u3002\u5728\u6362\u70ed\u5668\u8bbe\u8ba1\u8fc7\u7a0b\u4e2d\uff0c\u65e2\u9700\u8981\u5408\u9002\u7684\u5c3a\u5bf8\uff0c\u53c8\u8981\u80fd\u63d0\u4f9b\u9002\u5f53\u6e29\u5ea6\u7684\u70ed\u6d41\u4f53\u6216\u51b7\u6d41\u4f53\uff0c\u5c31\u5fc5\u987b\u8003\u8651\u5176\u4ed6\u5c5e\u6027\u3002\n\n\u8be5 App \u4e3a\u7531\u4e24\u4e2a\u540c\u5fc3\u7ba1\u7ec4\u6210\u7684\u6362\u70ed\u5668\u8ba1\u7b97\u8fd9\u4e9b\u7269\u7406\u91cf\u3002\u5176\u4e2d\u7684\u6d41\u4f53\u53ef\u4ee5\u5e73\u884c\u6216\u9006\u5411\u6d41\u52a8\u3002\n\n\u7528\u6237\u53ef\u4ee5\u66f4\u6539\u6362\u70ed\u5668\u7684\u6d41\u4f53\u5c5e\u6027\u3001\u4f20\u70ed\u7279\u6027\u548c\u5c3a\u5bf8\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u975e\u7b49\u6e29\u6d41\u52a8\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u4f20\u70ed\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("concentric_tube_heat_exchanger.mph");

    model.result("pg12").run();

    model.material("mat3").propertyGroup("def").func("rho").setIndex("plotaxis", false, 1);

    model.param().set("rtol", "1e-3");
    model.param().descr("rtol", "\u6c42\u89e3\u5668\u76f8\u5bf9\u5bb9\u5dee");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();

    model.study("std1").feature("wdi").set("usestol", true);
    model.study("std1").feature("wdi").set("stol", "rtol");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "rtol");

    model.material("mat1").propertyGroup("def").func("eta").createPlot("pg14");

    model.result("pg14").run();
    model.result("pg14").label("\u6750\u6599\u5c5e\u6027");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("xlabel", "\u6e29\u5ea6 (K)");
    model.result("pg14").run();
    model.result("pg14").feature("plot1").set("expr", "mat1.def.eta(root.T[K])");
    model.result("pg14").feature("plot1").set("linewidth", 3);
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().dataset("eta_ds1").set("source", "data");
    model.result().dataset("eta_ds1").set("data", "dset1");
    model.result().numerical().create("meas1", "MeasureSurface");
    model.result().numerical("meas1").set("intvolume", true);
    model.result().numerical("meas1").label("\u5185\u7ba1\u5bb9\u79ef");
    model.result().numerical("meas1").selection().named("sel1");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5185\u7ba1\u5bb9\u79ef");
    model.result().numerical("meas1").set("table", "tbl3");
    model.result().numerical("meas1").setResult();
    model.result().numerical().create("meas2", "MeasureSurface");
    model.result().numerical("meas2").set("intvolume", true);
    model.result().numerical("meas2").label("\u5916\u7ba1\u5bb9\u79ef");
    model.result().numerical("meas2").selection().named("sel2");
    model.result().numerical("meas2").set("table", "tbl3");
    model.result().numerical("meas2").appendResult();
    model.result().numerical().create("meas3", "MeasureSurface");
    model.result().numerical("meas3").set("intvolume", true);
    model.result().numerical("meas3").label("\u603b\u5bb9\u79ef");
    model.result().numerical("meas3").selection().all();
    model.result().numerical("meas3").set("table", "tbl3");
    model.result().numerical("meas3").appendResult();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u5185\u6d41\u4f53\u8d28\u91cf");
    model.result().numerical("int1").selection().named("sel1");
    model.result().numerical("int1").set("expr", new String[]{"ht.rho"});
    model.result().numerical("int1").set("descr", new String[]{"\u5bc6\u5ea6"});
    model.result().numerical("int1").set("unit", new String[]{"kg"});
    model.result().numerical("int1").setIndex("descr", "\u5185\u6d41\u4f53\u8d28\u91cf", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5185\u6d41\u4f53\u8d28\u91cf");
    model.result().numerical("int1").set("table", "tbl4");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").label("\u5916\u6d41\u4f53\u8d28\u91cf");
    model.result().numerical("int2").selection().named("sel2");
    model.result().numerical("int2").set("expr", new String[]{"ht.rho"});
    model.result().numerical("int2").set("descr", new String[]{"\u5bc6\u5ea6"});
    model.result().numerical("int2").set("unit", new String[]{"kg"});
    model.result().numerical("int2").setIndex("descr", "\u5916\u6d41\u4f53\u8d28\u91cf", 0);
    model.result().numerical("int2").set("table", "tbl4");
    model.result().numerical("int2").appendResult();
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").label("\u603b\u6d41\u4f53\u8d28\u91cf");
    model.result().numerical("int3").selection().all();
    model.result().numerical("int3").set("expr", new String[]{"ht.rho"});
    model.result().numerical("int3").set("descr", new String[]{"\u5bc6\u5ea6"});
    model.result().numerical("int3").set("unit", new String[]{"kg"});
    model.result().numerical("int3").setIndex("descr", "\u603b\u6d41\u4f53\u8d28\u91cf", 0);
    model.result().numerical("int3").set("table", "tbl4");
    model.result().numerical("int3").appendResult();
    model.result().numerical().create("int4", "IntLine");
    model.result().numerical("int4").set("intsurface", true);
    model.result().numerical("int4").label("\u4ea4\u6362\u529f\u7387");
    model.result().numerical("int4").selection().named("sel7");
    model.result().numerical("int4").setIndex("expr", "abs(ht.ndflux)", 0);
    model.result().numerical("int4").setIndex("unit", "W/m", 0);
    model.result().numerical("int4").setIndex("descr", "\u4ea4\u6362\u529f\u7387", 0);
    model.result().numerical("int4").set("table", "tbl1");
    model.result().numerical("int4").appendResult();
    model.result().numerical().create("meas4", "MeasureLine");
    model.result().numerical("meas4").set("intsurface", true);
    model.result().numerical("meas4").label("\u70ed\u4ea4\u6362\u9762\u79ef");
    model.result().numerical("meas4").selection().named("sel7");
    model.result().numerical("meas4").set("table", "tbl3");
    model.result().numerical("meas4").appendResult();
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").label("\u5bc6\u5ea6\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev7").setIndex("expr", "mat4.def.rho", 0);
    model.result().numerical("gev7").setIndex("descr", "\u5bc6\u5ea6\uff0c\u5185\u6d41\u4f53", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5bc6\u5ea6\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev7").set("table", "tbl5");
    model.result().numerical("gev7").setResult();
    model.result().numerical().create("gev8", "EvalGlobal");
    model.result().numerical("gev8").label("\u6052\u538b\u70ed\u5bb9\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev8").setIndex("expr", "mat4.def.Cp", 0);
    model.result().numerical("gev8").setIndex("descr", "\u6052\u538b\u70ed\u5bb9\uff0c\u5185\u6d41\u4f53", 0);
    model.result().numerical("gev8").set("table", "tbl5");
    model.result().numerical("gev8").appendResult();
    model.result().numerical().create("gev9", "EvalGlobal");
    model.result().numerical("gev9").label("\u5bfc\u70ed\u7cfb\u6570\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev9").setIndex("expr", "mat4.def.k11", 0);
    model.result().numerical("gev9").setIndex("descr", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u5185\u6d41\u4f53", 0);
    model.result().numerical("gev9").set("table", "tbl5");
    model.result().numerical("gev9").appendResult();
    model.result().numerical().create("gev10", "EvalGlobal");
    model.result().numerical("gev10").label("\u52a8\u529b\u9ecf\u5ea6\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev10").setIndex("expr", "mat4.def.mu", 0);
    model.result().numerical("gev10").setIndex("descr", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u5185\u6d41\u4f53", 0);
    model.result().numerical("gev10").set("table", "tbl5");
    model.result().numerical("gev10").appendResult();
    model.result().numerical().create("gev11", "EvalGlobal");
    model.result().numerical("gev11").label("\u5bc6\u5ea6\uff0c\u5916\u6d41\u4f53");
    model.result().numerical("gev11").setIndex("expr", "mat5.def.rho", 0);
    model.result().numerical("gev11").setIndex("descr", "\u5bc6\u5ea6\uff0c\u5916\u6d41\u4f53", 0);
    model.result().numerical("gev11").set("table", "tbl5");
    model.result().numerical("gev11").appendResult();
    model.result().numerical().create("gev12", "EvalGlobal");
    model.result().numerical("gev12").label("\u6052\u538b\u70ed\u5bb9\uff0c\u5916\u6d41\u4f53");
    model.result().numerical("gev12").setIndex("expr", "mat5.def.Cp", 0);
    model.result().numerical("gev12").setIndex("descr", "\u6052\u538b\u70ed\u5bb9\uff0c\u5916\u6d41\u4f53", 0);
    model.result().numerical("gev12").set("table", "tbl5");
    model.result().numerical("gev12").appendResult();
    model.result().numerical().create("gev13", "EvalGlobal");
    model.result().numerical("gev13").label("\u5bfc\u70ed\u7cfb\u6570\uff0c\u5916\u6d41\u4f53");
    model.result().numerical("gev13").setIndex("expr", "mat5.def.k11", 0);
    model.result().numerical("gev13").setIndex("descr", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u5916\u6d41\u4f53", 0);
    model.result().numerical("gev13").set("table", "tbl5");
    model.result().numerical("gev13").appendResult();
    model.result().numerical().create("gev14", "EvalGlobal");
    model.result().numerical("gev14").label("\u52a8\u529b\u9ecf\u5ea6\uff0c\u5916\u6d41\u4f53");
    model.result().numerical("gev14").setIndex("expr", "mat5.def.mu", 0);
    model.result().numerical("gev14").setIndex("descr", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u5916\u6d41\u4f53", 0);
    model.result().numerical("gev14").set("table", "tbl5");
    model.result().numerical("gev14").appendResult();
    model.result().numerical().create("gev15", "EvalGlobal");
    model.result().numerical("gev15").label("\u5bc6\u5ea6\uff0c\u7ba1");
    model.result().numerical("gev15").setIndex("expr", "mat6.def.rho", 0);
    model.result().numerical("gev15").setIndex("descr", "\u5bc6\u5ea6\uff0c\u7ba1", 0);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u5bc6\u5ea6\uff0c\u7ba1");
    model.result().numerical("gev15").set("table", "tbl6");
    model.result().numerical("gev15").setResult();
    model.result().numerical().create("gev16", "EvalGlobal");
    model.result().numerical("gev16").label("\u5bfc\u70ed\u7cfb\u6570\uff0c\u7ba1");
    model.result().numerical("gev16").setIndex("expr", "mat6.def.k11", 0);
    model.result().numerical("gev16").setIndex("descr", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u7ba1", 0);
    model.result().numerical("gev16").set("table", "tbl6");
    model.result().numerical("gev16").appendResult();
    model.result().numerical().create("gev17", "EvalGlobal");
    model.result().numerical("gev17").label("\u538b\u7f29\u7387");
    model.result().numerical("gev17").setIndex("expr", "S/vol0", 0);
    model.result().numerical("gev17").setIndex("descr", "\u538b\u7f29\u7387", 0);
    model.result().numerical("gev17").set("table", "tbl3");
    model.result().numerical("gev17").appendResult();
    model.result().numerical().create("gev18", "EvalGlobal");
    model.result().numerical("gev18").label("\u96f7\u8bfa\u6570\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev18").setIndex("expr", "mfr1*2*R1/intop1(spf.mu)", 0);
    model.result().numerical("gev18").setIndex("descr", "\u96f7\u8bfa\u6570\uff0c\u5185\u6d41\u4f53", 0);
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u96f7\u8bfa\u6570\uff0c\u5185\u6d41\u4f53");
    model.result().numerical("gev18").set("table", "tbl7");
    model.result().numerical("gev18").setResult();
    model.result().numerical().create("gev19", "EvalGlobal");
    model.result().numerical("gev19").label("\u96f7\u8bfa\u6570\uff0c\u5916\u6d41\u4f53");
    model.result().numerical("gev19").setIndex("expr", "mfr2*(R2-R1)/intop3(spf2.mu)", 0);
    model.result().numerical("gev19").setIndex("descr", "\u96f7\u8bfa\u6570\uff0c\u5916\u6d41\u4f53", 0);
    model.result().numerical("gev19").set("table", "tbl7");
    model.result().numerical("gev19").appendResult();
    model.result("pg12").run();

    model.setExpectedComputationTime("30 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///concentric_tube_heat_exchanger.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includename", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1").label("\u53c2\u6570 - \u7ba1");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 32, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 33, 1);

    return model;
  }

  public static Model run4(Model model) {
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 36, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 37, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 38, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param1")
         .setIndex("children", false, 39, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("param2", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .label("\u53c2\u6570 - \u6d41\u52a8");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 32, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 33, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 36, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 37, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 38, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param2")
         .setIndex("children", false, 39, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("param3", "Parameter");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .label("\u53c2\u6570 - \u6c42\u89e3\u5668");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 25, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 26, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 27, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 28, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 29, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 30, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 31, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 32, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 33, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 34, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 35, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 36, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 37, 1);
    model.result().report("rpt1").feature("sec1").feature("sec1").feature("param3")
         .setIndex("children", false, 38, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u5185\u5d4c\u6a21\u578b");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("img1", "Graphics");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("img1").set("source", "external");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("img1")
         .set("externalfile", "embedded:///concentric_tube_heat_exchanger_geom.png");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("img1")
         .set("caption", "\u540c\u5fc3\u7ba1\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").label("\u5185\u6d41\u4f53");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature()
         .create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature("mat1")
         .set("noderef", "matlnk1");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature("mat1")
         .set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature("mat1")
         .set("includeselection", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec2").label("\u5916\u6d41\u4f53");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec2").feature("mat1")
         .set("noderef", "matlnk2");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec3").label("\u7ba1");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec3").feature("mat1")
         .set("noderef", "matlnk3");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u7f51\u683c");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").set("includestats", true);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7814\u7a76");
    model.result().report("rpt1").feature("sec3").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec3").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec3").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").label("\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec1").label("\u7279\u5f81");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec1").feature("mtbl1")
         .set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec1").feature("mtbl1")
         .set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec2").label("\u538b\u964d");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec2").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec2").feature("mtbl1")
         .set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec2").feature("mtbl1")
         .set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec3").label("\u5c3a\u5bf8");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec3").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec3").feature("mtbl1")
         .set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec3").feature("mtbl1")
         .set("noderef", "tbl3");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec3").feature("mtbl1")
         .set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec4").label("\u8d28\u91cf");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec4").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec4").feature("mtbl1")
         .set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec4").feature("mtbl1")
         .set("noderef", "tbl4");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec4").feature("mtbl1")
         .set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec5").label("\u96f7\u8bfa\u6570");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec5").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec5").feature("mtbl1")
         .set("commentssource", "none");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec5").feature("mtbl1")
         .set("noderef", "tbl7");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("sec5").feature("mtbl1")
         .set("numberformat", "custom");
    model.result().report("rpt1").feature("sec4").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").feature("pg1")
         .set("noderef", "pg12");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").set("source", "firstchild");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature("pg1")
         .set("noderef", "pg13");
    model.result("pg12").run();

    model.title("\u540c\u5fc3\u7ba1\u5f0f\u6362\u70ed\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u7528\u4e8e\u5728\u5c42\u6d41\u6216\u6e4d\u6d41\u4e4b\u95f4\u8fdb\u884c\u5207\u6362\u7684\u7528\u6237\u9009\u9879\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u66f4\u6539\u8fb9\u754c\u6761\u4ef6\n\u2022 \u4f7f\u7528\u56fe\u8868\u7ed8\u56fe\u5c06\u6e29\u5ea6\u76f8\u5173\u7684\u6750\u6599\u5c5e\u6027\u53ef\u89c6\u5316\n\u2022 \u7528\u4e8e\u8bbe\u7f6e\u6c42\u89e3\u5668\u5bb9\u5dee\u7684\u7528\u6237\u9009\u9879\n\n\u786e\u5b9a\u5408\u9002\u7684\u6362\u70ed\u5668\u5c3a\u5bf8\u662f\u786e\u4fdd\u5176\u6709\u6548\u6027\u7684\u5173\u952e\u3002\u5728\u6362\u70ed\u5668\u8bbe\u8ba1\u8fc7\u7a0b\u4e2d\uff0c\u65e2\u9700\u8981\u5408\u9002\u7684\u5c3a\u5bf8\uff0c\u53c8\u8981\u80fd\u63d0\u4f9b\u9002\u5f53\u6e29\u5ea6\u7684\u70ed\u6d41\u4f53\u6216\u51b7\u6d41\u4f53\uff0c\u5c31\u5fc5\u987b\u8003\u8651\u5176\u4ed6\u5c5e\u6027\u3002\n\n\u8be5 App \u4e3a\u7531\u4e24\u4e2a\u540c\u5fc3\u7ba1\u7ec4\u6210\u7684\u6362\u70ed\u5668\u8ba1\u7b97\u8fd9\u4e9b\u7269\u7406\u91cf\u3002\u5176\u4e2d\u7684\u6d41\u4f53\u53ef\u4ee5\u5e73\u884c\u6216\u9006\u5411\u6d41\u52a8\u3002\n\n\u7528\u6237\u53ef\u4ee5\u66f4\u6539\u6362\u70ed\u5668\u7684\u6d41\u4f53\u5c5e\u6027\u3001\u4f20\u70ed\u7279\u6027\u548c\u5c3a\u5bf8\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u975e\u7b49\u6e29\u6d41\u52a8\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u4f20\u70ed\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("concentric_tube_heat_exchanger.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
