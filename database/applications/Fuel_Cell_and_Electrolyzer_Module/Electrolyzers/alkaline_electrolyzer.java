/*
 * alkaline_electrolyzer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:05 by COMSOL 6.3.0.290. */
public class alkaline_electrolyzer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("we", "WaterElectrolyzer", "geom1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("H2O_l", "1");
    model.component("comp1").physics("we").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("we").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("we").prop("O2GasMixture").set("H2O_l", "1");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Hydroxide");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings").set("Phase", "Liquid");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("OperationMode", "Electrolyzer");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");
    model.component("comp1").physics().create("ee", "LaminarEulerEulerModel", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/we", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ee", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/we", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ee", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W_H2", "2[mm]", "\u6c22\u6c14\u5ba4\u5bbd\u5ea6");
    model.param().set("W_sep", "1[mm]", "\u9694\u819c\u5bbd\u5ea6");
    model.param().set("W_O2", "2[mm]", "\u6c27\u6c14\u5ba4\u5bbd\u5ea6");
    model.param().set("W_cell", "W_H2+W_sep+W_O2", "\u7535\u89e3\u69fd\u5bbd\u5ea6");
    model.param().set("H_elec", "0.1[m]", "\u7535\u6781\u9ad8\u5ea6");
    model.param().set("T", "70[degC]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("p_gas", "1[atm]", "\u6c14\u538b");
    model.param()
         .set("p_vapor", "0.61121*exp((18.678-(T-0[degC])/234.5[K])*((T-0[degC])/(257.14+T-0[degC])))[kPa]", "\u84b8\u6c7d\u538b");
    model.param().set("xH2O", "p_vapor/p_gas", "\u6c14\u76f8\u4e2d\u7684\u6c34\u6469\u5c14\u5206\u6570");
    model.param().set("xH2", "1-xH2O", "\u6c14\u76f8\u4e2d\u7684\u6c22\u6469\u5c14\u5206\u6570");
    model.param().set("xO2", "1-xH2O", "\u6c14\u76f8\u4e2d\u7684\u6c27\u6469\u5c14\u5206\u6570");
    model.param().set("d_bubble", "50[um]", "\u6c14\u6ce1\u76f4\u5f84");
    model.param().set("v_in_H2", "0.1[m/s]", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6\uff0c\u6c22\u4fa7");
    model.param().set("v_in_O2", "0.1[m/s]", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6\uff0c\u6c27\u4fa7");
    model.param().set("K_H2_div_d", "5[m/s]", "\u6c22\u6df7\u5408\u6c14\u6ce1\u7684\u5206\u6563\u56e0\u5b50");
    model.param().set("K_O2_div_d", "K_H2_div_d*2", "\u6c27\u6df7\u5408\u6c14\u6ce1\u7684\u5206\u6563\u56e0\u5b50");
    model.param().set("i0_ref_H2", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param().set("i0_ref_O2", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param().set("MH2O", "18[g/mol]", "\u6c34\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MO2", "32[g/mol]", "\u6c27\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MH2", "2[g/mol]", "\u6c22\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MOH", "17[g/mol]", "\u6c22\u6c27\u5316\u7269\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("eps_sep", "0.3", "\u9694\u819c\u5b54\u9699\u7387");
    model.param().set("t", "0[s]", "\u7a33\u6001\u7814\u7a76\u6b65\u9aa4\u7684\u865a\u62df\u53c2\u6570");
    model.param().set("c_KOH", "6[M]", "\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("E_cell", "1.2[V]", "\u7535\u89e3\u69fd\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u6c22\u6c14\u5ba4");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_H2", "H_elec"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W_sep", "H_elec"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"W_H2", "0"});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u6c27\u6c14\u5ba4");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W_O2", "H_elec"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"W_H2+W_sep", "0"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6c22\u6c14\u7535\u6781");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6c27\u6c14\u7535\u6781");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 10);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 2, 8);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 3, 9);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u9694\u819c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 4, 7);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u6c14\u4f53\u5ba4");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"r1", "r3"});

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("yscale", 0.2);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int5", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup()
         .create("WaterVaporLiquidPhaseTransition", "WaterVaporLiquidPhaseTransition", "Water vapor-liquid phase transition");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Potassium Hydroxide, KOH");
    model.component("comp1").material("mat1").comments("\n");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "A_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "-0.5031"}, 
         {"5", "-0.4821"}, 
         {"10", "-0.5026"}, 
         {"15", "-0.482"}, 
         {"20", "-0.4824"}, 
         {"25", "-0.4931"}, 
         {"30", "-0.4812"}, 
         {"35", "-0.4918"}, 
         {"40", "-0.4863"}, 
         {"45", "-0.4912"}, 
         {"50", "-0.4756"}, 
         {"55", "-0.4898"}, 
         {"60", "-0.4916"}, 
         {"65", "-0.4906"}, 
         {"70", "-0.4876"}, 
         {"80", "-0.4942"}, 
         {"90", "-0.5021"}, 
         {"100", "-0.501"}, 
         {"150", "-0.5206"}, 
         {"200", "-0.5538"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "B_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "45.876"}, 
         {"5", "45.648"}, 
         {"10", "45.889"}, 
         {"15", "45.659"}, 
         {"20", "45.649"}, 
         {"25", "45.761"}, 
         {"30", "45.568"}, 
         {"35", "45.698"}, 
         {"40", "45.601"}, 
         {"45", "45.62"}, 
         {"50", "45.336"}, 
         {"55", "45.543"}, 
         {"60", "45.53"}, 
         {"65", "45.45"}, 
         {"70", "45.396"}, 
         {"80", "45.409"}, 
         {"90", "45.432"}, 
         {"100", "45.361"}, 
         {"150", "45.217"}, 
         {"200", "45.173"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "C_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "1004.4"}, 
         {"5", "1003.8"}, 
         {"10", "1002.5"}, 
         {"15", "1002"}, 
         {"20", "1001"}, 
         {"25", "999.63"}, 
         {"30", "998.66"}, 
         {"35", "996.7"}, 
         {"40", "994.89"}, 
         {"45", "992.84"}, 
         {"50", "991.51"}, 
         {"55", "988.4"}, 
         {"60", "985.91"}, 
         {"65", "983.39"}, 
         {"70", "980.71"}, 
         {"80", "974.59"}, 
         {"90", "967.98"}, 
         {"100", "960.99"}, 
         {"150", "919.52"}, 
         {"200", "869.35"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("importedname", "mu_KOH.txt");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"mu", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("funcnames", new String[]{"col1", "int4", "col2", "int4", "col3", "mu"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("fununit", new String[]{"mPa*s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("argunit", new String[]{"%", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").label("Interpolation 5");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("importedname", "Cp_KOH.txt");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("funcnametable", new String[][]{{"Cp", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("funcnames", new String[]{"col1", "int5", "col2", "int5", "col3", "Cp"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("fununit", new String[]{"J/K/g"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("argunit", new String[]{"K", "M"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T,c)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Note: Measured for p=10 MPa\n\nReference: \nL. Hnedkovsky, S. Bochmann, P.M. May, and G. Hefter, Molar Volumes and Heat Capacities of Aqueous Solutions of Potassium Hydroxide and for Water Ionization up to 573 K at 10 MPa, J. Chem. Eng. Data 2017, 62, 2959\u22122972\n");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]", "0", "0", "0", "(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]", "0", "0", "0", "(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "D. Le Bideaua, P. Mandina, M. Benbouzid, M. Kim, M. Sellier, \nReview of necessary thermophysical properties and their sensivities with temperature and electrolyte mass fractions for alkaline water electrolysis multiphysics modelling, \nInternational Journal of Hydrogen Energy 44(2019) 4553-4569");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_reg", "min(max(T,0[degC]),200[degC])");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_reg", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("M_reg", "min(max(c,1e-6[M]),12[M])/1[M]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("M_reg", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("density", "(A_rho(T_degC)*M_reg^2+B_rho(T_degC)*M_reg+C_rho(T_degC))*1[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_degC", "(T_reg-0[degC])/1[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_degC", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("dynamicviscosity", "mu(c*56.1[g/mol]/rho,T_degC)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("dynamicviscosity", "K. I. Kuznetsov et al \"Measurements of the Dynamic Viscosity and Density of KOH\nSolutions at Atmospheric Pressure\", High Temperature, 2020, Vol. 58, No. 6, pp. 806\u2013811");
    model.component("comp1").material("mat1").propertyGroup("def").set("Y", "c/rho*56.1[g/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("Y", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("T_K", "def.T_reg/1[K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("T_K", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("M", "def.M_reg");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("M", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("A", "-2.041");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("A", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("B", "-0.0028");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("B", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("C", "0.005332");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("C", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("D", "207.2");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("D", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("E", "0.001043");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("E", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("F", "-0.0000003");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("F", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .label("Water vapor-liquid phase transition");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("funcname", "log10_p_vap");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("expr", "-0.01508*m - 0.0016788*m^2 + 2.25887e-5*m^3 +m_func(m)*(35.4462-3343.93/T-10.9*log10(T)+0.0041645*T)");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("args", new String[]{"m", "T"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotaxis", new String[]{"on", "on"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotfixedvalue", new String[]{"0", "0"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotargs", new String[][]{{"m", "0", "1"}, {"T", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("funcname", "dlog10_p_vap_dT");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("expr", "m_func(m)*(3343.93/T^2-10.9/T/log(10)+0.0041645)");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("args", new String[]{"m", "T"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotaxis", new String[]{"on", "on"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotfixedvalue", new String[]{"0", "0"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotargs", new String[][]{{"m", "0", "1"}, {"T", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("funcname", "m_func");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("expr", "1-0.0012062*m + 5.6024e-4*m^2-7.8228e-6*m^3");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("args", new String[]{"m"});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("plotargs", new String[][]{{"m", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("p_vap", "10^log10_p_vap(M_reg,T_reg)[bar]");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .setPropertyInfo("p_vap", "J. Balej,  Int. J. Hydrogen Energy, Vol. 10, No. 4, pp. 233-243, 1985");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("H_vap", "R_const*T^2*log(10)*dlog10_p_vap_dT(M_reg,T_reg)*1[1/K]");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .setPropertyInfo("H_vap", "J. Balej,  Int. J. Hydrogen Energy, Vol. 10, No. 4, pp. 233-243, 1985");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("M_reg", "min(max(c/def.rho,eps),18[mol/kg])/1[mol/kg]");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .descr("M_reg", "Regularized molality");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("T_reg", "min(max(T,0[degC]),300[degC])[1/K]");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .descr("T_reg", "Regularized temperature");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("WaterVaporLiquidPhaseTransition")
         .addInput("temperature");

    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("we").prop("O2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("we").feature("h2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("we").feature("h2gasph1").set("T_hum", "T");
    model.component("comp1").physics("we").feature("h2gasph1").set("pA_hum", "p_gas");
    model.component("comp1").physics("we").feature("h2gasph1").set("p_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("h2gasph1").set("H_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("o2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("we").feature("o2gasph1").set("T_hum", "T");
    model.component("comp1").physics("we").feature("o2gasph1").set("pA_hum", "p_gas");
    model.component("comp1").physics("we").feature("o2gasph1").set("p_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("o2gasph1").set("H_vap_mat", "from_mat");
    model.component("comp1").physics("we").create("sep1", "Separator", 2);
    model.component("comp1").physics("we").feature("sep1").selection().set(2);
    model.component("comp1").physics("we").feature("sep1").set("epsl", "eps_sep");
    model.component("comp1").physics("we").create("h2gec1", "H2GasElectrolyteCompartment", 2);
    model.component("comp1").physics("we").feature("h2gec1").selection().set(1);
    model.component("comp1").physics("we").feature("h2gec1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("we").create("o2gec1", "O2GasElectrolyteCompartment", 2);
    model.component("comp1").physics("we").feature("o2gec1").selection().named("geom1_r3_dom");
    model.component("comp1").physics("we").create("h2es1", "H2ElectrodeSurface", 1);
    model.component("comp1").physics("we").feature("h2es1").selection().named("geom1_sel1");
    model.component("comp1").physics("we").feature("h2es1").feature("h2er1").set("nuH2O", 0);
    model.component("comp1").physics("we").feature("h2es1").feature("h2er1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("h2es1").feature("h2er1").set("i0_ref", "i0_ref_H2");
    model.component("comp1").physics("we").create("o2es1", "O2ElectrodeSurface", 1);
    model.component("comp1").physics("we").feature("o2es1").selection().named("geom1_sel2");
    model.component("comp1").physics("we").feature("o2es1").set("phisext0", "E_cell");
    model.component("comp1").physics("we").feature("o2es1").feature("o2er1").set("nuH2O", 0);
    model.component("comp1").physics("we").feature("o2es1").feature("o2er1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("o2es1").feature("o2er1").set("i0_ref", "i0_ref_O2");

    model.common("cminpt")
         .set("modified", new String[][]{{"temperature", "T"}, {"concentration", "c_KOH"}, {"pressure", "p_gas"}});

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").selection().named("geom1_sel2");
    model.component("comp1").probe("bnd1").set("expr", "we.iloc_o2er1");
    model.component("comp1").probe("bnd1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").probe("bnd1").set("unit", "A/cm^2");
    model.component("comp1").probe("bnd1").set("descractive", true);
    model.component("comp1").probe("bnd1").set("descr", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");

    model.study("std1").label("\u7814\u7a76 1 - \u65e0\u6c14\u4f53\u6790\u51fa");
    model.study("std1").feature("stat").setSolveFor("/physics/ee", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "W_H2", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "W_H2", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1.2,0.05,1.9)", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 15, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (we)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"we.phil"});
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"we.Ilx", "we.Ily"});
    model.result("pg2").feature("arws1").set("arrowbase", "center");
    model.result("pg2").feature("arws1").set("color", "gray");
    model.result("pg2").feature("arws1").create("filt1", "Filter");
    model.result("pg2").feature("arws1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg2").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("switchxy", true);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u6c22\u6c14\u5ba4");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_r1_dom");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("v_in", "v_in_H2*6/W_H2^2*x*(W_H2-x)", "\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("m_OH", "-we.Ilx/F_const*MOH", "\u79bb\u5b50\u8d28\u91cf\u6d41\u7387");
    model.component("comp1").variable("var1")
         .set("eps_liquid", "max(ee.phic,1e-4)", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("slipevelmag", "sqrt(max((udx-ucx)^2+(udy-ucy)^2,eps^2))", "\u6ed1\u79fb\u901f\u5ea6\u5927\u5c0f");
    model.component("comp1").variable("var1").set("prefac", "-ee.rhoc*K_H2_div_d*slipevelmag", "\u524d\u56e0\u5b50");
    model.component("comp1").variable("var1")
         .set("FD_BDx", "prefac*d(phid,x)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u5206\u6563\u76f8\u4e2d\u7684 x \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("FD_BDy", "prefac*d(phid,y)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u5206\u6563\u76f8\u4e2d\u7684 y \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("FC_BDx", "-prefac*d(phid,x)*phid/max(ee.phic,1e-4)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u8fde\u7eed\u76f8\u4e2d\u7684 x \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("FC_BDy", "-prefac*d(phid,y)*phid/max(ee.phic,1e-4)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u8fde\u7eed\u76f8\u4e2d\u7684 y \u5206\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf 2 - \u6c27\u6c14\u5ba4");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("geom1_r3_dom");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("v_in", "v_in_O2*6/W_O2^2*(x-W_sep-W_H2)*(W_H2-(x-W_sep-W_H2))", "\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var2")
         .set("m_OH", "we.Ilx/F_const*MOH", "\u79bb\u5b50\u8d28\u91cf\u6d41\u7387");
    model.component("comp1").variable("var2")
         .set("eps_liquid", "max(ee.phic,1e-4)", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var2")
         .set("slipevelmag", "sqrt(max((udx-ucx)^2+(udy-ucy)^2,eps^2))", "\u6ed1\u79fb\u901f\u5ea6\u5927\u5c0f");
    model.component("comp1").variable("var2").set("prefac", "-ee.rhoc*K_O2_div_d*slipevelmag", "\u524d\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("FD_BDx", "prefac*d(phid,x)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u5206\u6563\u76f8\u4e2d\u7684 x \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("FD_BDy", "prefac*d(phid,y)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u5206\u6563\u76f8\u4e2d\u7684 y \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("FC_BDx", "-prefac*d(phid,x)*phid/max(ee.phic,1e-4)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u8fde\u7eed\u76f8\u4e2d\u7684 x \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("FC_BDy", "-prefac*d(phid,y)*phid/max(ee.phic,1e-4)", "\u6c14\u6ce1\u5206\u6563\u4f53\u79ef\u529b\uff0c\u8fde\u7eed\u76f8\u4e2d\u7684 y \u5206\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf 3 - \u6c22\u6c14\u7535\u6781");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().named("geom1_sel1");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("N_H2_far", "-we.iloc_h2er1/(2*F_const)", "\u6cd5\u62c9\u7b2c\u6c14\u4f53\u9038\u51fa\u7387");
    model.component("comp1").variable("var3")
         .set("N_H2O_evap", "N_H2_far*xH2O/(1-xH2O)", "\u6c34\u84b8\u53d1\u7387");
    model.component("comp1").variable("var3")
         .set("m_gas", "N_H2O_evap*MH2O+N_H2_far*MH2", "\u6c14\u76f8\u8d28\u91cf\u6d41\u7387");
    model.component("comp1").variable("var3").set("m_liquid", "-m_gas", "\u6db2\u76f8\u8d28\u91cf\u6d41\u7387");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u53d8\u91cf 4 - \u6c27\u6c14\u7535\u6781");
    model.component("comp1").variable("var4").selection().geom("geom1", 1);
    model.component("comp1").variable("var4").selection().named("geom1_sel2");

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4")
         .set("N_O2_far", "we.iloc_o2er1/(4*F_const)", "\u6cd5\u62c9\u7b2c\u6c14\u4f53\u9038\u51fa\u7387");
    model.component("comp1").variable("var4")
         .set("N_H2O_evap", "N_O2_far*xH2O/(1-xH2O)", "\u6c34\u84b8\u53d1\u7387");
    model.component("comp1").variable("var4")
         .set("m_gas", "N_H2O_evap*MH2O+N_O2_far*MO2", "\u6c14\u76f8\u8d28\u91cf\u6d41\u7387");
    model.component("comp1").variable("var4").set("m_liquid", "-m_gas", "\u6db2\u76f8\u8d28\u91cf\u6d41\u7387");

    model.component("comp1").physics("we").feature("h2gec1").set("epsl", "eps_liquid");
    model.component("comp1").physics("we").feature("o2gec1").set("epsl", "eps_liquid");
    model.component("comp1").physics("we").feature("h2es1").feature("h2er1").set("i0_ref", "i0_ref_H2*eps_liquid");
    model.component("comp1").physics("we").feature("o2es1").feature("o2er1").set("i0_ref", "i0_ref_O2*eps_liquid");
    model.component("comp1").physics("ee").selection().named("geom1_unisel2");
    model.component("comp1").physics("ee").prop("PhysicalModelProperty")
         .set("DispersedPhase", "LiquidDropletsBubbles");
    model.component("comp1").physics("ee").feature("pp1").set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("ee").feature("pp1").set("rhod_mat", "root.comp1.we.rho");
    model.component("comp1").physics("ee").feature("pp1").set("mud_mat", "root.comp1.we.mu");
    model.component("comp1").physics("ee").feature("pp1").set("diam", "d_bubble");
    model.component("comp1").physics("ee").feature("init1").set("uc", new String[]{"0", "v_in", "0"});
    model.component("comp1").physics("ee").feature("init1").set("ud", new String[]{"0", "v_in", "0"});
    model.component("comp1").physics("ee").feature("init1").set("p", "g_const*1000[kg/m^3]*(H_elec-y)");
    model.component("comp1").physics("ee").create("gr1", "Gravity", 2);
    model.component("comp1").physics("ee").feature("gr1").selection().all();
    model.component("comp1").physics("ee").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("ee").feature("vf1").label("\u4f53\u79ef\u529b - \u6c14\u6ce1\u5206\u6563");
    model.component("comp1").physics("ee").feature("vf1").set("Fc", new String[]{"FC_BDx", "FC_BDy", "0"});
    model.component("comp1").physics("ee").feature("vf1").set("Fd", new String[]{"FD_BDx", "FD_BDy", "0"});
    model.component("comp1").physics("ee").feature("vf1").selection().all();
    model.component("comp1").physics("ee").create("inl1", "Inlet", 1);
    model.component("comp1").physics("ee").feature("inl1").set("uc0", new String[]{"0", "v_in", "0"});
    model.component("comp1").physics("ee").feature("inl1").set("ud0", new String[]{"0", "v_in", "0"});
    model.component("comp1").physics("ee").feature("inl1")
         .set("phidConditionMixtureDispersed", "NoDispersedPhaseFlux");
    model.component("comp1").physics("ee").feature("inl1").selection().named("geom1_sel3");
    model.component("comp1").physics("ee").create("out1", "Outlet", 1);
    model.component("comp1").physics("ee").feature("out1").selection().named("geom1_sel4");
    model.component("comp1").physics("ee").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("ee").feature("wallbc2").label("\u58c1 2 - \u7535\u6781");
    model.component("comp1").physics("ee").feature("wallbc2").selection().named("geom1_unisel1");
    model.component("comp1").physics("ee").feature("wallbc2").set("leakageC", true);
    model.component("comp1").physics("ee").feature("wallbc2").set("mPricC", "m_liquid");
    model.component("comp1").physics("ee").feature("wallbc2")
         .set("DispersedPhaseBoundaryCondition", "DispersedPhaseLeakage");
    model.component("comp1").physics("ee").feature("wallbc2").set("mPricD", "m_gas");
    model.component("comp1").physics("ee").create("wallbc3", "WallBC", 1);
    model.component("comp1").physics("ee").feature("wallbc3").label("\u58c1 3 - \u9694\u819c");
    model.component("comp1").physics("ee").feature("wallbc3").selection().named("geom1_sel5");
    model.component("comp1").physics("ee").feature("wallbc3").set("leakageC", true);
    model.component("comp1").physics("ee").feature("wallbc3").set("mPricC", "m_OH");

    model.component("comp1").func().create("rm1", "Ramp");

    model.component("comp1").physics("we").feature("o2es1").set("phisext0", "E_cell+rm1(t[1/min])*0.1[V]");

    model.component("comp1").probe().duplicate("bnd2", "bnd1");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("bnd2").set("table", "tbl2");

    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(1, 4, 7, 10);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 500);
    model.component("comp1").mesh("mesh1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis2").selection().set(2, 3, 8, 9);
    model.component("comp1").mesh("mesh1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis3").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "3e-5");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/we", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/ee", true);
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("ftplistmethod", "manual");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/we", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ee", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5305\u542b\u6c14\u4f53\u6790\u51fa");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0,1,7)");
    model.study("std2").feature("stat").set("probesel", "none");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"bnd2"});
    model.study("std1").feature("stat").set("probesel", "manual");
    model.study("std1").feature("stat").set("probes", new String[]{"bnd1"});
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("bnd2").genResult("none");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 8, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (we) 1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"we.phil"});
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"we.Ilx", "we.Ily"});
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").set("color", "gray");
    model.result("pg3").feature("arws1").create("filt1", "Filter");
    model.result("pg3").feature("arws1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg3").feature("arws1").feature("filt1").set("nodespec", "all");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u8fde\u7eed\u76f8 (ee)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u901f\u5ea6\u5927\u5c0f\uff0c\u8fde\u7eed\u76f8");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "ee.Uc");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u5206\u6563\u76f8 (ee)");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 8, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u5206\u6563\u76f8\u4f53\u79ef\u5206\u6570");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "phid");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u6781\u5316\u56fe");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5e73\u5747\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u7814\u7a76 1 - \u65e0\u6c14\u4f53\u6790\u51fa", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("preprocx", "linear");
    model.result("pg1").feature("tblp2").set("scalingx", 0.1);
    model.result("pg1").feature("tblp2").set("shiftx", "E_cell");
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u7814\u7a76 2 - \u6709\u6c14\u4f53\u6790\u51fa", 0);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset4");
    model.result().dataset("cln1").setIndex("genpoints", "W_cell/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "W_cell/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H_elec", 1, 1);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u9694\u819c\u4e2d\u95f4\u7684\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u9694\u819c\u4e2d\u95f4\u7684\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "-we.Ilx");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "y");
    model.result("pg6").run();
    model.result("pg2").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").label("\u7ec4 1 - \u7814\u7a76 1");

    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg1");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u7ec4 2 - \u7814\u7a76 2");

    model.title("\u78b1\u6027\u7535\u89e3\u69fd");

    model
         .description("\u78b1\u6027\u6c34\u7535\u89e3\u662f\u4e00\u79cd\u6210\u719f\u7684\u751f\u4ea7\u6c22\u6c14\u7684\u5de5\u4e1a\u7ea7\u5de5\u827a\u3002\u5728\u7535\u89e3\u6c60\u7684\u9634\u6781\u4ea7\u751f\u6c22\u6c14\uff0c\u5728\u9633\u6781\u4ea7\u751f\u6c27\u6c14\u3002\n\n\u7535\u89e3\u8d28\u662f\u4e00\u79cd\u6c34\u6eb6\u6db2\uff0c\u5f53\u653e\u51fa\u7684\u6c14\u4f53\u5f62\u6210\u6c14\u6ce1\u65f6\uff0c\u6709\u6548\u79bb\u5b50\u7535\u5bfc\u7387\u5c31\u4f1a\u964d\u4f4e\uff1b\u4ea7\u751f\u7684\u6c14\u4f53\u4e5f\u53ef\u80fd\u4f1a\u964d\u4f4e\u7535\u6781\u53cd\u5e94\u7684\u6d3b\u6027\u8868\u9762\u79ef\u800c\u5bf9\u7535\u89e3\u6c60\u6027\u80fd\u4ea7\u751f\u4e0d\u5229\u5f71\u54cd\u3002\n\n\u672c\u4f8b\u7814\u7a76\u6c14\u4f53\u5f62\u6210\u5bf9\u78b1\u6027\u7535\u89e3\u6c60\u6027\u80fd\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("alkaline_electrolyzer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
