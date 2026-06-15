/*
 * pmm_steady_state.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:20 by COMSOL 6.3.0.290. */
public class pmm_steady_state {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mmtp", "MagneticMachineryTimePeriodic", "geom1");

    model.component("comp1").geom("geom1").insertFile("pmm_steady_state_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().create("par2");
    model.param()
         .move(new String[]{"Np", "Ns", "Nsec", "stator_core_rout", "stator_core_th", "stator_core_rin", "N_coil_slot", "airgap", "slot_pitch_frc", "slot_shoe_tip_height", 
         "slot_shoe_angle", "slot_opening", "slot_width", "slot_height", "slot_shoe_base_height", "coil_height", "rotor_core_rout", "shaft_rout", "rotor_bridge", "pole_bridge", 
         "v_bridge", "mag_ang", "magnet_height", "flux_barrier"}, "par2");
    model.param().set("speed", "5000[rpm]");
    model.param().descr("speed", "Shaft speed");
    model.param().set("f_el", "speed*Np/2");
    model.param().descr("f_el", "Electrical excitation frequency");
    model.param().set("Ipk", "50[A]");
    model.param().descr("Ipk", "Phase current peak");
    model.param().set("init_ang", "0[rad]");
    model.param().descr("init_ang", "Initial current angle");
    model.param().set("n_cog", "Ns/gcd(Np,Ns)*2");
    model.param().descr("n_cog", "Cogging torque harmonic order");
    model.param().set("Nframes", "n_cog*6");
    model.param().descr("Nframes", "Number of frames");
    model.param().set("L", "250[mm]");
    model.param().descr("L", "Motor axial length");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat1").label("N42 (Sintered NdFeB)");
    model.component("comp1").material("mat1").set("family", "chrome");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").set("normBr", "1.31[T]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").label("Silicon Steel NGO 35PN270");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.851852[MS/m]", "0", "0", "0", "1.851852[MS/m]", "0", "0", "0", "1.851852[MS/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1[1]", "0", "0", "0", "1[1]", "0", "0", "0", "1[1]"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("funcnametable", new String[][]{{"BHCurve1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"20", "0.064356436"}, 
         {"22.5", "0.0817476280729167"}, 
         {"25", "0.0995255775833333"}, 
         {"27.5", "0.11807704196875"}, 
         {"30", "0.137788778666667"}, 
         {"32.5", "0.159008869369792"}, 
         {"35", "0.181930692791667"}, 
         {"37.5", "0.206708951901042"}, 
         {"40", "0.233498349666667"}, 
         {"42.5260416666667", "0.262646967765625"}, 
         {"45.2083333333333", "0.295276402708333"}, 
         {"48.203125", "0.332701629713542"}, 
         {"51.6666666666667", "0.376237624"}, 
         {"55.7291666666667", "0.426657900398437"}, 
         {"60.4166666666667", "0.4825701321875"}, 
         {"65.7291666666667", "0.542040532257812"}, 
         {"71.6666666666667", "0.6031353135"}, 
         {"78.3854166666667", "0.664475041114583"}, 
         {"86.6666666666667", "0.726897689541667"}, 
         {"97.4479166666667", "0.79179558553125"}, 
         {"111.666666666667", "0.860561055833333"}, 
         {"129.921875", "0.933593749796875"}, 
         {"151.458333333333", "1.00732260716667"}, 
         {"175.182291666667", "1.07718389028646"}, 
         {"200", "1.1386138615"}, 
         {"225.260416666667", "1.18827351500781"}, 
         {"252.083333333333", "1.2277227724375"}, 
         {"282.03125", "1.25974628727344"}, 
         {"316.666666666667", "1.287128713"}, 
         {"357.03125", "1.31216481035677"}, 
         {"402.083333333333", "1.33518976910417"}, 
         {"450.260416666667", "1.35604888625781"}, 
         {"500", "1.37458745883333"}, 
         {"550.260416666667", "1.39075391916667"}, 
         {"602.083333333333", "1.404909240875"}, 
         {"657.03125", "1.41751753289583"}, 
         {"716.666666666667", "1.42904290416667"}, 
         {"783.854166666667", "1.4400139231875"}, 
         {"866.666666666667", "1.45121699670833"}, 
         {"974.479166666667", "1.46350299104167"}, 
         {"1116.66666666667", "1.4777227725"}, 
         {"1299.21875", "1.49437912571615"}, 
         {"1514.58333333333", "1.51258250860417"}, 
         {"1751.82291666667", "1.53109529739844"}, 
         {"2000", "1.54867986833333"}, 
         {"2252.60416666667", "1.56447246315885"}, 
         {"2520.83333333333", "1.5791047856875"}, 
         {"2820.3125", "1.5935824052474"}, 
         {"3166.66666666667", "1.60891089116667"}, 
         {"3570.3125", "1.6257606229974"}, 
         {"4020.83333333333", "1.6434612211875"}, 
         {"4502.60416666667", "1.66100711640885"}, 
         {"5000", "1.67739273933333"}, 
         {"5502.60416666667", "1.69190903466667"}, 
         {"6020.83333333333", "1.70503300325"}, 
         {"6570.3125", "1.71753815995833"}, 
         {"7166.66666666667", "1.73019801966667"}, 
         {"7838.54166666667", "1.7437474215"}, 
         {"8666.66666666667", "1.75876650158333"}, 
         {"9744.79166666667", "1.77579672029167"}, 
         {"11166.6666666667", "1.795379538"}, 
         {"12992.1875", "1.81770833340625"}, 
         {"15145.8333333333", "1.8415841585"}, 
         {"17518.2291666667", "1.86545998359375"}, 
         {"20000", "1.887788779"}, 
         {"22552.0833333333", "1.9073844886224"}, 
         {"25416.6666666667", "1.92450495072917"}, 
         {"28906.25", "1.93976897717969"}, 
         {"33333.3333333333", "1.95379537983333"}, 
         {"38843.7447916667", "1.96701632833879"}, 
         {"44916.625", "1.97911742350197"}, 
         {"50864.4427083333", "1.9895976239181"}, 
         {"55999.6666666667", "1.99795588818242"}, 
         {"60093.015625", "2.00423728388627"}, 
         {"64748.2083333333", "2.01067131460551"}, 
         {"72027.2135416667", "2.02003359291211"}, 
         {"83992", "2.03509973137807"}, 
         {"102079.71875", "2.05782942911622"}, 
         {"125228.25", "2.08691873140287"}, 
         {"151750.65625", "2.12024777005519"}, 
         {"179960", "2.15569667689034"}});
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat4").label("Copper");
    model.component("comp1").material("mat4").set("family", "copper");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat4").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().named("geom1_unisel3");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_rotor_magnets_dom");
    model.component("comp1").material("mat3").selection().all();
    model.component("comp1").material().move("mat3", 0);
    model.component("comp1").material("mat4").selection().named("geom1_rot2_dom");
    model.component("comp1").material("mat1").set("family", "plastic");
    model.component("comp1").material("mat1").set("color", "custom");
    model.component("comp1").material("mat1")
         .set("customcolor", new double[]{0.12156862765550613, 0.12156862765550613, 0.12156862765550613});
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});

    model.component("comp1").view("view1").set("showmaterial", true);

    model.param("par2").set("Nsec", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.param("par2").set("Nsec", "gcd(Np,Ns)");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mmtp").prop("d").set("d", "L");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("freqTP", "f_el");
    model.component("comp1").physics("mmtp").prop("TimePeriodicSettings").set("nTP", "Nframes");
    model.component("comp1").physics("mmtp").prop("MotionSettings").set("NoPoles", "Np");
    model.component("comp1").physics("mmtp").feature().create("drcon1", "RotationalContinuityPair");
    model.component("comp1").physics("mmtp").feature("drcon1").set("pairs", "ap1");
    model.component("comp1").physics("mmtp").feature().create("drper1", "RotationalPeriodicity");
    model.component("comp1").physics("mmtp").feature("drper1").selection().set(1, 2, 3, 4, 19, 32, 36, 37, 69, 77);
    model.component("comp1").physics("mmtp").create("wnd1", "MultiphaseWinding", 2);
    model.component("comp1").physics("mmtp").feature("wnd1").selection().named("geom1_rot2_dom");
    model.component("comp1").physics("mmtp").feature("wnd1").set("Ipk", "Ipk");
    model.component("comp1").physics("mmtp").feature("wnd1").set("alpha_i", "init_ang");
    model.component("comp1").physics("mmtp").feature("wnd1").set("WindingLayout", "automatic");
    model.component("comp1").physics("mmtp").feature("wnd1").set("NoSlots", "Ns");
    model.component("comp1").physics("mmtp").feature("wnd1").runCommand("addPhases");
    model.component("comp1").physics("mmtp").create("lc1", "LaminatedCore", 2);
    model.component("comp1").physics("mmtp").feature("lc1").selection().named("geom1_unisel3");
    model.component("comp1").physics("mmtp").feature("lc1").set("alpha", 1.45);
    model.component("comp1").physics("mmtp").feature("lc1").set("beta_steinmetz", 2.06);
    model.component("comp1").physics("mmtp").create("mag1", "Magnet", 2);
    model.component("comp1").physics("mmtp").feature("mag1").selection().named("geom1_pi1_rotor_magnets_dom");
    model.component("comp1").physics("mmtp").feature("mag1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mmtp").feature("mag1").feature("north1").selection().set(22, 26);
    model.component("comp1").physics("mmtp").feature("mag1").feature("south1").selection().set(7, 17);
    model.component("comp1").physics("mmtp").create("tprot1", "TimePeriodicRotatingDomain", 2);
    model.component("comp1").physics("mmtp").feature("tprot1").selection().named("geom1_pi1_all_dom");
    model.component("comp1").physics("mmtp").feature("tprot1").set("TimePeriodicRotationType", "Nonrotating");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_unisel3");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 3);
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(8, 10);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "airgap/3");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mmtp", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (mmtp)");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", "off");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "mmtp.normB(phase)");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("expr", new String[]{"mmtp.BX(phase)", "mmtp.BY(phase)"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114);
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("expr", "mmtp.normB(phase)");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature("str1").feature().create("def1", "Deform");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("str1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("str1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mmtp.AZ(phase)");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature("con1").feature().create("def1", "Deform");
    model.result("pg1").feature("con1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("con1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "fromtheme");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result("pg1").feature("line1").feature().create("def1", "Deform");
    model.result("pg1").feature("line1").feature("def1")
         .set("expr", new String[]{"mmtp.displX(phase)", "mmtp.displY(phase)"});
    model.result("pg1").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"mmtp.drcon1.Tax_tpph"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u8f74\u5411\u626d\u77e9\uff0c\u76f8\u4f4d\u51fd\u6570"});
    model.result("pg2").feature("glob1").set("xdata", "phase");
    model.result("pg2").feature("glob1").set("xdataphaserange", "range(0,1/Nframes,1)*2*pi");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg2").feature("glob1").feature("gmrk1").set("anchorpoint", "middleleft");
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"mmtp.drcon1.Tax_tpavg"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u8f74\u5411\u626d\u77e9\uff0c\u65f6\u95f4\u5468\u671f\u5e73\u5747\u503c"});
    model.result().evaluationGroup("eg1").run();

    model.param().set("init_ang", "4.625[rad]");

    model.component("comp1").physics("mmtp").feature("tprot1")
         .set("TimePeriodicRotationType", "SyncrhonousPolePair");
    model.component("comp1").physics("mmtp").feature("mag1").set("sigma_mat", "from_mat");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("gmrk1").active(false);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().dataset("dset1").set("phase", 90);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(true);
    model.result().dataset("dset1").set("phase", 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mmtp.wnd1.aPh1.V_tpph"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u7ed5\u7ec4\u76f8\u7535\u538b\uff0c\u76f8\u4f4d\u51fd\u6570"});
    model.result("pg3").feature("glob1").setIndex("expr", "mmtp.wnd1.aPh2.V_tpph", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "mmtp.wnd1.aPh3.V_tpph", 2);
    model.result("pg3").feature("glob1").set("xdata", "phase");
    model.result("pg3").feature("glob1").set("xdataphaserange", "range(0,1/Nframes,1)*2*pi");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "mmtp.Qh");
    model.result("pg4").feature("surf1").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg4").feature("surf1").set("colortable", "GrayBody");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").run();
    model.result("pg1").run();
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "mmtp.drcon1.Tax_tpavg*2*pi*speed", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("unit", "kW", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "Shaft power", 0);
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_rot2_dom");
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"mmtp.Qh"});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("descr", new String[]{"\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1"});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "mmtp.Qh*L*Nsec", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("unit", "kW", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Winding loss", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").selection().named("geom1_dif2_dom");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "Stator core loss", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int3").selection().named("geom1_pi1_rotor_iron_dom");
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "Rotor core loss", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int4", "int3");
    model.result().evaluationGroup("eg1").feature("int4").selection().named("geom1_pi1_rotor_magnets_dom");
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "Magnet loss", 0);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("type", "general");
    model.result().evaluationGroup("eg1").set("keepchildnodes", true);
    model.result().evaluationGroup("eg1").set("generalexpr", "gev2/(gev2+int1+int2+int3+int4)*100");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(25);
    model.result("pg5").feature("ptgr1").set("expr", "mmtp.JZ_tpph");
    model.result("pg5").feature("ptgr1")
         .set("descr", "\u9762\u5916\u7535\u6d41\u5bc6\u5ea6\uff0c\u76f8\u4f4d\u51fd\u6570");
    model.result("pg5").feature("ptgr1").set("expr", "mmtp.JZ_tpph*50");
    model.result("pg5").feature("ptgr1").set("descractive", true);
    model.result("pg5").feature("ptgr1").set("xdata", "phase");
    model.result("pg5").feature("ptgr1").set("xdataphaserange", "range(0,1/Nframes,1)*2*pi");
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("autopoint", false);
    model.result("pg5").feature("ptgr1").set("autosolution", false);
    model.result("pg5").feature("ptgr1").set("autodescr", true);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").selection().set(83);
    model.result("pg5").feature("ptgr2").set("expr", "mmtp.JZ_tpph");
    model.result("pg5").create("lnsg1", "LineSegments");
    model.result("pg5").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg5").feature("lnsg1").set("linewidth", "preference");
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.0873, 0);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", 0.0873, 1);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "-5e6", 0);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "5e6", 1);
    model.result("pg5").feature("lnsg1").set("linecolor", "red");
    model.result("pg5").feature("lnsg1").set("linestyle", "dashed");
    model.result("pg5").feature().duplicate("lnsg2", "lnsg1");
    model.result("pg5").run();
    model.result("pg5").feature("lnsg2").setIndex("xexpr", "0.0873+pi", 0);
    model.result("pg5").feature("lnsg2").setIndex("xexpr", "0.0873+pi", 1);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showmaterial", false);

    model.component("comp1").material().remove("mat3");
    model.component("comp1").material().remove("mat4");

    model.result("pg1").run();

    model.title("\u7a33\u6001\u6c38\u78c1\u7535\u673a");

    model
         .description("\u5bf9\u4e8e\u7535\u673a\u8bbe\u8ba1\u8005\u6765\u8bf4\uff0c\u83b7\u53d6\u5176\u7a33\u6001\u6027\u80fd\u662f\u4e00\u9879\u81f3\u5173\u91cd\u8981\u7684\u4efb\u52a1\u3002\u672c\u6559\u7a0b\u4f7f\u7528\u201c\u78c1\u529b\u673a\u68b0\uff0c\u65f6\u95f4\u5468\u671f\u201d\u63a5\u53e3\u76f4\u63a5\u6c42\u89e3\u7535\u673a\u7684\u7a33\u6001\u8fd0\u884c\u60c5\u51b5\uff0c\u5e76\u5145\u5206\u8003\u8651\u4e86\u975e\u7ebf\u6027\u6750\u6599\u548c\u611f\u5e94\u7535\u6d41\u7684\u5f71\u54cd\u3002\u901a\u8fc7\u5bf9\u5206\u5e03\u7ed5\u7ec4\u5185\u7f6e\u5f0f\u6c38\u78c1\u7535\u673a\u8fdb\u884c\u5efa\u6a21\uff0c\u6f14\u793a\u4e86\u65f6\u95f4\u5468\u671f\u63a5\u53e3\u7684\u57fa\u672c\u539f\u7406\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pmm_steady_state.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
