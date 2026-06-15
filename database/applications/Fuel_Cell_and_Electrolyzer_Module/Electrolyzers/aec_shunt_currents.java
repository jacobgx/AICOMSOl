/*
 * aec_shunt_currents.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:04 by COMSOL 6.3.0.290. */
public class aec_shunt_currents {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

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

    model.component("comp1").geom("geom1").insertFile("aec_shunt_currents_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel7");

    model.component("comp1").view().create("view20", "geom1");
    model.component("comp1").view("view20").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view20").camera().set("xscale", 10);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("c_KOH", "6[M]", "KOH \u6d53\u5ea6");
    model.param("par2")
         .set("E_cell", "1.3[V]", "\u7535\u89e3\u69fd\u7535\u538b\uff08\u968f\u626b\u63cf\u53d8\u5316\uff09");
    model.param("par2").set("E_stack", "N_cells*E_cell", "\u7535\u89e3\u69fd\u5806\u7535\u538b");
    model.param("par2").set("T", "85[degC]", "\u7535\u89e3\u69fd\u6e29\u5ea6");
    model.param("par2")
         .set("epsl_upper", "50[%]", "\u4e0a\u90e8\u901a\u9053\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2")
         .set("epsl_lower", "100[%]", "\u4e0b\u90e8\u901a\u9053\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("p_abs", "80[bar]", "\u53e0\u5c42\u5de5\u4f5c\u538b\u529b");
    model.param("par2")
         .set("epsl_sep", "50[%]", "\u9694\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2")
         .set("i0_ref_h2", "100[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6790\u6c22");
    model.param("par2")
         .set("i0_ref_o2", "1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6790\u6c27");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int5", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup()
         .create("WaterVaporLiquidPhaseTransition", "WaterVaporLiquidPhaseTransition", "Water vapor-liquid phase transition");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func()
         .create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Potassium Hydroxide, KOH");
    model.component("comp1").material("mat2").comments("\n");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "A_rho");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "B_rho");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("funcname", "C_rho");
    model.component("comp1").material("mat2").propertyGroup("def").func("int3")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("source", "file");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("importedname", "mu_KOH.txt");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("importeddim", "2D");
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"mu", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("filecolumns", 3);
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("funcnames", new String[]{"col1", "int4", "col2", "int4", "col3", "mu"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("fununit", new String[]{"mPa*s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4")
         .set("argunit", new String[]{"%", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int4").set("sourcetype", "model");
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").set("source", "file");
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").label("Interpolation 5");
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").set("importedname", "Cp_KOH.txt");
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").set("importeddim", "2D");
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("funcnametable", new String[][]{{"Cp", "1"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").set("filecolumns", 3);
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("funcnames", new String[]{"col1", "int5", "col2", "int5", "col3", "Cp"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("fununit", new String[]{"J/K/g"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5")
         .set("argunit", new String[]{"K", "M"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int5").set("sourcetype", "model");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T,c)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Note: Measured for p=10 MPa\n\nReference: \nL. Hnedkovsky, S. Bochmann, P.M. May, and G. Hefter, Molar Volumes and Heat Capacities of Aqueous Solutions of Potassium Hydroxide and for Water Ionization up to 573 K at 10 MPa, J. Chem. Eng. Data 2017, 62, 2959\u22122972\n");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]", "0", "0", "0", "(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]", "0", "0", "0", "(5.545e-1+2.46e-3*T_degC-1.184e-5*T_degC^2)*(1-Y*1.28e-1)[W/m/K]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "D. Le Bideaua, P. Mandina, M. Benbouzid, M. Kim, M. Sellier, \nReview of necessary thermophysical properties and their sensivities with temperature and electrolyte mass fractions for alkaline water electrolysis multiphysics modelling, \nInternational Journal of Hydrogen Energy 44(2019) 4553-4569");
    model.component("comp1").material("mat2").propertyGroup("def").set("T_reg", "min(max(T,0[degC]),200[degC])");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T_reg", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("M_reg", "min(max(c,1e-6[M]),12[M])/1[M]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("M_reg", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("density", "(A_rho(T_degC)*M_reg^2+B_rho(T_degC)*M_reg+C_rho(T_degC))*1[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat2").propertyGroup("def").set("T_degC", "(T_reg-0[degC])/1[K]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T_degC", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("dynamicviscosity", "mu(c*56.1[g/mol]/rho,T_degC)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("dynamicviscosity", "K. I. Kuznetsov et al \"Measurements of the Dynamic Viscosity and Density of KOH\nSolutions at Atmospheric Pressure\", High Temperature, 2020, Vol. 58, No. 6, pp. 806\u2013811");
    model.component("comp1").material("mat2").propertyGroup("def").set("Y", "c/rho*56.1[g/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("Y", "");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("T_K", "def.T_reg/1[K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("T_K", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("M", "def.M_reg");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("M", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("A", "-2.041");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("A", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("B", "-0.0028");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("B", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("C", "0.005332");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("C", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("D", "207.2");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("D", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("E", "0.001043");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("E", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("F", "-0.0000003");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("F", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .label("Water vapor-liquid phase transition");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("funcname", "log10_p_vap");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("expr", "-0.01508*m - 0.0016788*m^2 + 2.25887e-5*m^3 +m_func(m)*(35.4462-3343.93/T-10.9*log10(T)+0.0041645*T)");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("args", new String[]{"m", "T"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotaxis", new String[]{"on", "on"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotfixedvalue", new String[]{"0", "0"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an1")
         .set("plotargs", new String[][]{{"m", "0", "1"}, {"T", "0", "1"}});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("funcname", "dlog10_p_vap_dT");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("expr", "m_func(m)*(3343.93/T^2-10.9/T/log(10)+0.0041645)");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("args", new String[]{"m", "T"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotaxis", new String[]{"on", "on"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotfixedvalue", new String[]{"0", "0"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an2")
         .set("plotargs", new String[][]{{"m", "0", "1"}, {"T", "0", "1"}});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("funcname", "m_func");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("expr", "1-0.0012062*m + 5.6024e-4*m^2-7.8228e-6*m^3");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("args", new String[]{"m"});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition").func("an3")
         .set("plotargs", new String[][]{{"m", "0", "1"}});
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("p_vap", "10^log10_p_vap(M_reg,T_reg)[bar]");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .setPropertyInfo("p_vap", "J. Balej,  Int. J. Hydrogen Energy, Vol. 10, No. 4, pp. 233-243, 1985");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("H_vap", "R_const*T^2*log(10)*dlog10_p_vap_dT(M_reg,T_reg)*1[1/K]");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .setPropertyInfo("H_vap", "J. Balej,  Int. J. Hydrogen Energy, Vol. 10, No. 4, pp. 233-243, 1985");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("M_reg", "min(max(c/def.rho,eps),18[mol/kg])/1[mol/kg]");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .descr("M_reg", "Regularized molality");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .set("T_reg", "min(max(T,0[degC]),300[degC])[1/K]");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .descr("T_reg", "Regularized temperature");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("WaterVaporLiquidPhaseTransition")
         .addInput("temperature");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").selection().named("geom1_unisel3");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("epsl", "if(z<-H_active/2,epsl_lower,if(z>H_active/2,epsl_upper, (z+H_active/2)/H_active*(epsl_upper-epsl_lower)+epsl_lower))", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("Eeq_cell", "intop_point_o2(we.Eeq_o2er1)-intop_point_h2(we.Eeq_h2er1)", "\u7535\u89e3\u69fd\u5e73\u8861\u7535\u538b");
    model.component("comp1").variable("var1")
         .set("Etherm_cell", "intop_point_o2(we.Etherm_o2er1)-intop_point_h2(we.Etherm_h2er1)", "\u7535\u89e3\u69fd\u70ed\u4e2d\u6027\u7535\u538b");
    model.component("comp1").variable("var1").set("I_stack", "intop_cc(we.nIs)", "\u53e0\u5c42\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("I_h2", "-intop_h2_electrodes(we.iloc_h2er1)", "\u603b\u4ea7\u6c22\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("Eff_coulombic", "I_h2/(I_stack*N_cells)", "\u7535\u6d41\u5230\u6c22\u7684\u5e93\u4ed1\u6548\u7387");
    model.component("comp1").variable("var1")
         .set("Eff_energy", "I_h2*Eeq_cell/(E_stack*I_stack)", "\u7535\u80fd\u8f6c\u5316\u4e3a\u6c22\u80fd\u7684\u6548\u7387");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_point_h2");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(15);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_point_o2");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(57);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_h2_electrodes");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("geom1_intsel1");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intop_cc");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop4").selection().set(1);
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").set("opname", "intop_upper");
    model.component("comp1").cpl("intop5").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop5").selection().named("geom1_intsel3");
    model.component("comp1").cpl().create("intop6", "Integration");
    model.component("comp1").cpl("intop6").set("axisym", true);
    model.component("comp1").cpl("intop6").set("opname", "intop_lower");
    model.component("comp1").cpl("intop6").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop6").selection().named("geom1_intsel4");

    model.component("comp1").physics("we").prop("H2GasMixture").set("H2O", false);
    model.component("comp1").physics("we").prop("O2GasMixture").set("H2O", false);
    model.component("comp1").physics("we").create("sep1", "Separator", 3);
    model.component("comp1").physics("we").feature("sep1").selection().named("geom1_csel3_dom");
    model.component("comp1").physics("we").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("we").create("cc1", "CurrentCollector", 3);
    model.component("comp1").physics("we").feature("cc1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("we").feature("cc1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("we").create("h2gec1", "H2GasElectrolyteCompartment", 3);
    model.component("comp1").physics("we").feature("h2gec1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("we").feature("h2gec1").set("epsl", "epsl");
    model.component("comp1").physics("we").create("o2gec1", "O2GasElectrolyteCompartment", 3);
    model.component("comp1").physics("we").feature("o2gec1").selection().named("geom1_csel4_dom");
    model.component("comp1").physics("we").feature("o2gec1").set("epsl", "epsl");
    model.component("comp1").physics("we").create("ih2es1", "InternalH2ElectrodeSurface", 2);
    model.component("comp1").physics("we").feature("ih2es1").selection().named("geom1_intsel1");
    model.component("comp1").physics("we").feature("ih2es1").feature("h2er1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("ih2es1").feature("h2er1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("we").feature("ih2es1").feature("h2er1").set("i0_ref", "i0_ref_h2");
    model.component("comp1").physics("we").feature("ih2es1").feature("h2er1")
         .set("PressureDependenceType", "CathodicReactionOrders");
    model.component("comp1").physics("we").feature("ih2es1").feature("h2er1").set("rcathodicH2", 1);
    model.component("comp1").physics("we").create("io2es1", "InternalO2ElectrodeSurface", 2);
    model.component("comp1").physics("we").feature("io2es1").selection().named("geom1_intsel2");
    model.component("comp1").physics("we").feature("io2es1").feature("o2er1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("io2es1").feature("o2er1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("we").feature("io2es1").feature("o2er1").set("i0_ref", "i0_ref_o2");
    model.component("comp1").physics("we").feature("io2es1").feature("o2er1")
         .set("PressureDependenceType", "AnodicReactionOrders");
    model.component("comp1").physics("we").feature("io2es1").feature("o2er1").set("ranodicO2", 1);
    model.component("comp1").physics("we").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").selection().set(1);
    model.component("comp1").physics("we").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("we").feature("ecph1").feature("pot1").selection().set(1610);
    model.component("comp1").physics("we").feature("ecph1").feature("pot1").set("phisbnd", "E_stack");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("we").feature("icph1").set("minput_concentration_src", "userdef");
    model.component("comp1").physics("we").feature("icph1").set("minput_concentration", "c_KOH");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}, {"pressure", "p_abs"}});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_unisel5");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_unisel2");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_csel2_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().named("geom1_csel4_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").selection().named("geom1_csel3_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_comsel1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_unisel6");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "D_o2/3");
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().named("geom1_unisel4");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "D_o2/3");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "c_KOH", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mol/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "c_KOH", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mol/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1.3,0.1,1.8)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (we)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"we.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"we.Isx", "we.Isy", "we.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.we.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (we)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"we.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"we.Ilx", "we.Ily", "we.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result("pg1").run();
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").active(false);
    model.result("pg1").feature("arwv1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "we.phis");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").active(false);
    model.result("pg2").feature("arwv1").active(false);
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5206\u6d41\u7535\u6d41\u7ebf");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("selnumber", 100);
    model.result("pg3").feature("str1").selection().named("geom1_unisel4");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").create("sel1", "Selection");
    model.result("pg3").feature("str1").feature("sel1").selection().named("geom1_unisel7");
    model.result("pg3").run();
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("titletype", "auto");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "AuroraBorealis");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("titletype", "none");
    model.result("pg3").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("mtrl1").set("material", "mat2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("tran1").set("transparency", 0.9);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6781\u5316\u56fe");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"E_cell"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u7535\u89e3\u69fd\u7535\u538b\uff08\u968f\u626b\u63cf\u53d8\u5316\uff09"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"E_cell", "Eeq_cell"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u7535\u89e3\u69fd\u7535\u538b\uff08\u968f\u626b\u63cf\u53d8\u5316\uff09", "\u7535\u89e3\u69fd\u5e73\u8861\u7535\u538b"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"E_cell", "Eeq_cell", "Etherm_cell"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u7535\u89e3\u69fd\u7535\u538b\uff08\u968f\u626b\u63cf\u53d8\u5316\uff09", "\u7535\u89e3\u69fd\u5e73\u8861\u7535\u538b", "\u7535\u89e3\u69fd\u70ed\u4e2d\u6027\u7535\u538b"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "I_stack");
    model.result("pg4").feature("glob1").set("xdatadescr", "\u53e0\u5c42\u7535\u6d41");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "E<sub>cell</sub>", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "E<sub>ocv</sub>", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "E<sub>therm</sub>", 2);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u538b (V)");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e93\u4ed1\u6548\u7387");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"Eff_coulombic"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u7535\u6d41\u5230\u6c22\u7684\u5e93\u4ed1\u6548\u7387"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob1").setIndex("unit", "%", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "I_stack");
    model.result("pg5").feature("glob1").set("xdatadescr", "\u53e0\u5c42\u7535\u6d41");
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u80fd\u91cf\u6548\u7387");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"Eff_energy"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u7535\u80fd\u8f6c\u5316\u4e3a\u6c22\u80fd\u7684\u6548\u7387"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg6").feature("glob1").setIndex("unit", "%", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "I_stack");
    model.result("pg6").feature("glob1").set("xdatadescr", "\u53e0\u5c42\u7535\u6d41");
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").run();
    model.result().numerical().create("gevs1", "EvalGlobalSweep");
    model.result().numerical("gevs1").setIndex("looplevel", 1, 0);
    model.result().numerical("gevs1").setIndex("pname", "Cell", 0);
    model.result().numerical("gevs1").setIndex("plistarr", "range(1,1,N_cells)", 0);
    model.result().numerical("gevs1")
         .set("expr", new String[]{"comp1.intop_upper(if(x>(Cell-1)*D_cell+D_endplate,if(x<(Cell-1)*D_cell+D_endplate+D_h2,we.Ilz,0[A/m^2]),0[A/m^2]))", "comp1.intop_lower(if(x>(Cell-1)*D_cell+D_endplate,if(x<(Cell-1)*D_cell+D_endplate+D_h2,-we.Ilz,0[A/m^2]),0[A/m^2]))", "comp1.intop_upper(if(x>(Cell-1)*D_cell+D_endplate+D_h2+D_sep,if(x<(Cell-1)*D_cell+D_endplate+D_cell,we.Ilz,0[A/m^2]),0[A/m^2]))", "comp1.intop_lower(if(x>(Cell-1)*D_cell+D_endplate+D_h2+D_sep,if(x<(Cell-1)*D_cell+D_endplate+D_cell,-we.Ilz,0[A/m^2]),0[A/m^2]))"});
    model.result().numerical("gevs1").set("unit", new String[]{"A", "A", "A", "A"});
    model.result().numerical("gevs1")
         .set("descr", new String[]{"\u6c22\uff0c\u4e0a\u90e8", "\u6c22\uff0c\u4e0b\u90e8", "\u6c27\uff0c\u4e0a\u90e8", "\u6c27\uff0c\u4e0b\u90e8"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97\u626b\u63cf 1");
    model.result().numerical("gevs1").set("table", "tbl1");
    model.result().numerical("gevs1").setResult();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5206\u6d41");
    model.result("pg7").label("\u6bcf\u4e2a\u7535\u6c60\u7684\u5206\u6d41");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("linestyle", "none");
    model.result("pg7").feature("tblp1").set("linemarker", "cycle");
    model.result("pg7").run();
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7535\u6d41 (A)");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();
    model.result().numerical("gevs1").setIndex("looplevel", 6, 0);
    model.result().numerical("gevs1").set("table", "tbl1");
    model.result().numerical("gevs1").setResult();
    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u78b1\u6027\u7535\u89e3\u69fd\u5806\u4e2d\u7684\u5206\u6d41");

    model
         .description("\u5728\u78b1\u6027\u7535\u89e3\u69fd\u5806\u4e2d\uff0c\u6240\u6709\u7535\u6c60\u90fd\u5171\u4eab\u76f8\u540c\u7684\u7535\u89e3\u8d28\u3002\u7531\u4e8e\u6240\u6709\u7535\u6c60\u90fd\u5904\u4e8e\u79bb\u5b50\u63a5\u89e6\u72b6\u6001\uff0c\u5bc4\u751f\u5206\u6d41\u5728\u7535\u6c60\u4e4b\u95f4\u901a\u8fc7\u6b67\u7ba1\u548c\u7535\u89e3\u8d28\u901a\u9053\u5728\u5165\u53e3\u548c\u51fa\u53e3\u4fa7\u6d41\u52a8\u3002\n\n\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u5305\u542b 20\u00a0\u4e2a\u7535\u6c60\u7684\u7535\u6c60\u7ec4\u4e2d\u7684\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u8ba1\u7b97\u4e86\u8be5\u7535\u6c60\u7ec4\u7684\u7535\u6c22\u5e93\u4ed1\u6548\u7387\u548c\u80fd\u91cf\u6548\u7387\uff0c\u4ee5\u53ca\u8fdb\u5165\u6216\u79bb\u5f00\u6bcf\u4e2a\u7535\u6c60\u7684\u5404\u4e2a\u5206\u6d41\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("aec_shunt_currents.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
