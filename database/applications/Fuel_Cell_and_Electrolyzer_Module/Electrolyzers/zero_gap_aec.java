/*
 * zero_gap_aec.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:07 by COMSOL 6.3.0.290. */
public class zero_gap_aec {

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
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");
    model.component("comp1").physics().create("phtr", "PhaseTransportFreePorous", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 3);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();
    model.component("comp1").multiphysics().create("nsd1", "NavierStokesDarcyCoupling", 2);
    model.component("comp1").multiphysics("nsd1").set("NavierStokes_physics", "spf");
    model.component("comp1").multiphysics("nsd1").set("Darcy_physics", "dl");
    model.component("comp1").multiphysics("nsd1").selection().all();
    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 3);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

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
    model.study("std1").feature("cdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/dl", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/mfpm1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nsd1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/mfmm1", true);
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
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mfpm1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nsd1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mfmm1", true);

    model.component("comp1").geom("geom1").insertFile("zero_gap_aec_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("E_cell", "E_cell_lower", "\u5355\u5143\u7535\u538b");
    model.param("par2").set("T0", "80[degC]", "\u521d\u59cb\u6e29\u5ea6");
    model.param("par2").set("eps_pores", "0.75", "GDE \u5b54\u9699\u7387");
    model.param("par2").set("perm_GDE", "1e-10[m^2]", "GDE \u6e17\u900f\u7387");
    model.param("par2").set("v_in", "0.01[m/s]", "\u5165\u53e3\u6d41\u901f");
    model.param("par2").set("p_out", "25[atm]", "\u51fa\u53e3\u538b\u529b");
    model.param("par2").set("epsl_sep", "0.5", "\u9694\u819c\u5b54\u9699\u7387");
    model.param("par2").set("c_KOH", "5[M]", "\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param("par2").set("Av", "1e4[m^2/m^3]", "GDE \u7684\u6bd4\u8868\u9762\u79ef");
    model.param("par2").set("s_g_in", "0.01", "\u5165\u53e3\u6c14\u4f53\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("sigma_Ni_eff", "1e5[S/m]", "\u954d\u6be1\u6709\u6548\u7535\u5bfc\u7387");
    model.param("par2")
         .set("i0_ref_her", "1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6790\u6c22\u53cd\u5e94");
    model.param("par2")
         .set("i0_ref_oer", "0.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6790\u6c27\u53cd\u5e94");
    model.param("par2").set("Cp_KOH", "3.37[J/(g*K)]", "KOH \u70ed\u5bb9");
    model.param("par2").set("kappa_KOH", "0.66[W/m/K]", "KOH \u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kappa_sep", "1[W/m/K]", "\u9694\u819c\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kappa_Ni", "100[W/m/K]", "\u954d\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("rho_sep", "1000[kg/m^3]", "\u9694\u819c\u5bc6\u5ea6");
    model.param("par2").set("rho_Ni", "8900[kg/m^3]", "\u954d\u5bc6\u5ea6");
    model.param("par2").set("Cp_sep", "1[J/(g*K)]", "\u9694\u819c\u70ed\u5bb9");
    model.param("par2").set("Cp_Ni", "0.44[J/(g*K)]", "\u954d\u70ed\u5bb9");
    model.param("par2")
         .set("E_cell_lower", "1.5[V]", "\u6781\u5316\u56fe\u4e2d\u7684\u8f83\u4f4e\u5355\u5143\u7535\u538b");
    model.param("par2")
         .set("E_cell_upper", "2.1[V]", "\u6781\u5316\u56fe\u4e2d\u7684\u8f83\u9ad8\u5355\u5143\u7535\u538b");
    model.param("par2").set("dpc_dsg", "100[Pa]", "\u6bdb\u7ec6\u538b\u529b\u7cfb\u6570");

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
    model.component("comp1").material("mat1").selection().named("geom1_sel6");
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
    model.component("comp1").material("mat2").selection().named("geom1_unisel6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - GDE");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("geom1_unisel2");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("epsl", "eps_pores*s_l", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1").set("pA_liquid", "dl.pA", "\u7edd\u5bf9\u6db2\u538b");
    model.component("comp1").variable("var1").set("pA_gas", "phtr.p_s_g", "\u7edd\u5bf9\u6c14\u538b");
    model.component("comp1").variable("var1")
         .set("rho_mix", "dl.rho", "\u4e24\u76f8\u6df7\u5408\u7269\u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("kappa_KOH", "mat2.def.k_iso", "KOH \u7684\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("kappa_two_phase_mix", "s_g*we.kgas_mix+s_l*kappa_KOH", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u6db2\u4f53\u548c\u6c14\u4f53\u6df7\u5408\u7269");
    model.component("comp1").variable("var1").set("rho_KOH", "mat2.def.rho", "KOH \u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("Cp_KOH", "mat2.def.Cp", "KOH \u7684\u70ed\u5bb9");
    model.component("comp1").variable("var1")
         .set("Cp_two_phase_mix", "(s_g*we.rho*we.Cp_mix+s_l*rho_KOH*Cp_KOH)/rho_mix", "\u6052\u538b\u70ed\u5bb9\uff0c\u6c14\u4f53");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf - \u901a\u9053");
    model.component("comp1").variable("var2").selection().geom("geom1", 3);
    model.component("comp1").variable("var2").selection().named("geom1_unisel1");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("epsl", "s_l", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var2").set("pA_liquid", "spf.pA", "\u7edd\u5bf9\u6db2\u538b");
    model.component("comp1").variable("var2").set("pA_gas", "spf.pA", "\u7edd\u5bf9\u6c14\u538b");
    model.component("comp1").variable("var2")
         .set("rho_mix", "spf.rho", "\u4e24\u76f8\u6df7\u5408\u7269\u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var2")
         .set("kappa_KOH", "mat2.def.k_iso", "KOH \u7684\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var2")
         .set("kappa_two_phase_mix", "s_g*we.kgas_mix+s_l*kappa_KOH", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u6db2\u4f53\u548c\u6c14\u4f53\u6df7\u5408\u7269");
    model.component("comp1").variable("var2").set("rho_KOH", "mat2.def.rho", "KOH \u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("Cp_KOH", "mat2.def.Cp", "KOH \u7684\u70ed\u5bb9");
    model.component("comp1").variable("var2")
         .set("Cp_two_phase_mix", "(s_g*we.rho*we.Cp_mix+s_l*rho_KOH*Cp_KOH)/rho_mix", "\u6052\u538b\u70ed\u5bb9\uff0c\u6c14\u76f8");

    return model;
  }

  public static Model run2(Model model) {

    model.component("comp1").physics("phtr").field("volumefraction").component(1, "s_l");
    model.component("comp1").physics("phtr").field("volumefraction").component(2, "s_g");
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("we").prop("O2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("we").create("cc1", "CurrentCollector", 3);
    model.component("comp1").physics("we").feature("cc1").selection().named("geom1_sel6");
    model.component("comp1").physics("we").feature("cc1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("we").create("h2gec1", "H2GasElectrolyteCompartment", 3);
    model.component("comp1").physics("we").feature("h2gec1").selection().named("geom1_sel4");
    model.component("comp1").physics("we").feature("h2gec1").set("epsl", "epsl");
    model.component("comp1").physics("we").create("h2gde1", "H2GasDiffusionElectrode", 3);
    model.component("comp1").physics("we").feature("h2gde1").selection().named("geom1_sel5");
    model.component("comp1").physics("we").feature("h2gde1")
         .set("sigmas", new String[]{"sigma_Ni_eff", "0", "0", "0", "sigma_Ni_eff", "0", "0", "0", "sigma_Ni_eff"});
    model.component("comp1").physics("we").feature("h2gde1").set("epsl", "epsl");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("nuH2O", 0);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_her");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("Av", "Av");
    model.component("comp1").physics("we").create("sep1", "Separator", 3);
    model.component("comp1").physics("we").feature("sep1").selection().named("geom1_sel1");
    model.component("comp1").physics("we").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("we").create("o2gde1", "O2GasDiffusionElectrode", 3);
    model.component("comp1").physics("we").feature("o2gde1").selection().named("geom1_sel3");
    model.component("comp1").physics("we").feature("o2gde1")
         .set("sigmas", new String[]{"sigma_Ni_eff", "0", "0", "0", "sigma_Ni_eff", "0", "0", "0", "sigma_Ni_eff"});
    model.component("comp1").physics("we").feature("o2gde1").set("epsl", "epsl");
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("nuH2O", 0);
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("nuH2O_l", -1);
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("Av", "Av");
    model.component("comp1").physics("we").create("o2gec1", "O2GasElectrolyteCompartment", 3);
    model.component("comp1").physics("we").feature("o2gec1").selection().named("geom1_sel2");
    model.component("comp1").physics("we").feature("o2gec1").set("epsl", "epsl");
    model.component("comp1").physics("we").feature("h2gasph1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("we").feature("h2gasph1").set("minput_pressure", "pA_gas");
    model.component("comp1").physics("we").feature("h2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("we").feature("h2gasph1").set("T_hum", "T");
    model.component("comp1").physics("we").feature("h2gasph1").set("pA_hum", "pA_gas");
    model.component("comp1").physics("we").feature("h2gasph1").set("p_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("h2gasph1").set("H_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("o2gasph1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("we").feature("o2gasph1").set("minput_pressure", "pA_gas");
    model.component("comp1").physics("we").feature("o2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("we").feature("o2gasph1").set("T_hum", "T");
    model.component("comp1").physics("we").feature("o2gasph1").set("pA_hum", "pA_gas");
    model.component("comp1").physics("we").feature("o2gasph1").set("p_vap_mat", "from_mat");
    model.component("comp1").physics("we").feature("o2gasph1").set("H_vap_mat", "from_mat");
    model.component("comp1").physics("we").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("we").feature("pc1").selection().set(23, 54);
    model.component("comp1").physics("we").feature("pc1").set("deltaphil", "-2*E_cell");
    model.component("comp1").physics("we").feature("pc1").set("ApplyForElectrodePhase", false);
    model.component("comp1").physics("we").feature("ecph1").create("egnd1", "ElectricGroundPoint", 0);
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").selection().set(19);
    model.component("comp1").physics("spf").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_out");
    model.component("comp1").physics("spf").feature("fp1").set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_unisel4");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_unisel5");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection()
         .set(2, 6, 19, 20, 31, 43, 69, 75, 82, 89, 105, 106);
    model.component("comp1").physics("dl").selection().named("geom1_unisel2");
    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("pref", "p_out");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "eps_pores");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"perm_GDE", "0", "0", "0", "perm_GDE", "0", "0", "0", "perm_GDE"});
    model.component("comp1").physics("phtr").selection().named("geom1_unisel3");
    model.component("comp1").physics("phtr").feature("fluid1").set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("phtr").feature("fluid1").create("tm1", "TurbulentMixing", 3);
    model.component("comp1").physics("phtr").feature("fluid1").feature("tm1").set("nuT", "1e-6");
    model.component("comp1").physics("phtr").feature("fluid1").feature("tm1").set("ScT", 1);
    model.component("comp1").physics("phtr").feature("porous1").selection().named("geom1_unisel2");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").setIndex("pc", "dpc_dsg*s_g", 1);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("phaselist_s_l", "mat2");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("kappar_s_l", "s_l^2");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("rhoint_s_g_mat", "root.comp1.we.rho");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("mu_s_g_mat", "root.comp1.we.mu");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("kappar_s_g", "s_g^2");
    model.component("comp1").physics("phtr").feature("init1").setIndex("s0", "s_g_in", 1);
    model.component("comp1").physics("phtr").feature("fpi1").set("PorousBoundaryCondition_s_g", "Signorini");
    model.component("comp1").physics("phtr").feature("fpi1").set("s0thresh_s_g", "s_g_in");
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 2);
    model.component("comp1").physics("phtr").feature("sa1").selection().named("geom1_unisel4");
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("s0", "s_g_in", 1);
    model.component("comp1").physics("phtr").create("of1", "Outflow", 2);
    model.component("comp1").physics("phtr").feature("of1").selection().named("geom1_unisel5");
    model.component("comp1").physics("phtr").create("ms1", "MassSource", 3);
    model.component("comp1").physics("phtr").feature("ms1").selection().named("geom1_unisel2");
    model.component("comp1").physics("phtr").feature("ms1").setIndex("qs_s_g_src", "root.comp1.we.Q_g", 0);
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_unisel1");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_pressure", "pA_liquid");
    model.component("comp1").physics("ht").feature("fluid1").set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("ht").feature("fluid1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1")
         .set("k", new String[]{"kappa_two_phase_mix", "0", "0", "0", "kappa_two_phase_mix", "0", "0", "0", "kappa_two_phase_mix"});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rho_mix");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "Cp_two_phase_mix");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("porous1", "PorousMediumHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("porous1").label("\u591a\u5b54\u4ecb\u8d28 - \u9694\u819c");
    model.component("comp1").physics("ht").feature("porous1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "epsl_sep");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_b", new String[]{"kappa_sep", "0", "0", "0", "kappa_sep", "0", "0", "0", "kappa_sep"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b", "rho_sep");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b", "Cp_sep");
    model.component("comp1").physics("ht").create("porous2", "PorousMediumHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("porous2").label("\u591a\u5b54\u4ecb\u8d28 - GDE");
    model.component("comp1").physics("ht").feature("porous2").selection().named("geom1_unisel2");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("minput_pressure_src", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("minput_pressure", "pA_liquid");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("minput_concentration_src", "fromCommonDef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1")
         .set("k", new String[]{"kappa_two_phase_mix", "0", "0", "0", "kappa_two_phase_mix", "0", "0", "0", "kappa_two_phase_mix"});
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("rho", "rho_mix");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("Cp", "Cp_two_phase_mix");
    model.component("comp1").physics("ht").feature("porous2").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("poro", "eps_pores");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1")
         .set("k_b", new String[]{"kappa_Ni", "0", "0", "0", "kappa_Ni", "0", "0", "0", "kappa_Ni"});
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("rho_b", "rho_Ni");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous2").feature("pm1").set("Cp_b", "Cp_Ni");
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc1").selection().set(23, 54);
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_unisel4");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_unisel5");

    model.component("comp1").multiphysics("mfmm1").set("minput_temperature_src", "fromCommonDef");
    model.component("comp1").multiphysics("mfmm1").set("DispersedPhase", "LiquidDropletsBubbles");
    model.component("comp1").multiphysics("mfmm1").set("ShearInducedMigration", true);
    model.component("comp1").multiphysics("mfmm1").set("rho_s_g_mat", "root.comp1.we.rho");
    model.component("comp1").multiphysics("mfmm1").set("mu_s_g_mat", "root.comp1.we.mu");
    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.common("cminpt").set("modified", new String[][]{{"concentration", "c_KOH"}});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_sel12");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(36, 45, 51, 60, 88, 94, 98, 104, 201);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(33, 48, 63, 86, 96, 106, 202);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "W_rib/10");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel11");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "H_ch/4");
    model.component("comp1").mesh("mesh1").run("ftri1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_sel11");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(43, 46, 55, 58, 68, 70, 72, 73, 75, 76, 82, 84);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "H_ch/50");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_ribch/10");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat").label("\u7a33\u6001 - \u6d41\u52a8\u521d\u59cb\u5316");
    model.study("std1").feature("stat").setSolveFor("/physics/we", false);
    model.study("std1").feature("stat").setSolveFor("/physics/phtr", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat2").label("\u7a33\u6001 2 - \u6240\u6709\u7269\u7406\u573a");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "Av", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "Av", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat2").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "range(E_cell_lower,0.1,E_cell_upper)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").create("fc1", "FullyCoupled");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(54);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6781\u5316\u56fe");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "E_cell", 0);
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "aveop1(-we.nIl)");
    model.result("pg1").feature("glob1").set("xdataunit", "A/cm^2");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u7535\u89e3\u6c60\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (A/cm<sup>2</sup>)");
    model.result("pg1").set("showlegends", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6781\u76f8\u7535\u4f4d");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "we.phis");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u89e3\u8d28\u76f8\u7535\u4f4d");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("unit", "\u00b0C");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u901a\u9053\u4e2d\u6c14\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg5").selection().geom("geom1", 3);
    model.result("pg5").selection().named("geom1_unisel1");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "s_g");
    model.result("pg5").feature("vol1").set("descr", "\u4f53\u79ef\u5206\u6570");
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("GDE \u4e2d\u6c14\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg6").selection().named("geom1_unisel2");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6c14\u4f53\u7684\u4f53\u79ef\u5206\u6570\u548c\u6d41\u7ebf");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("edges", false);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"phtr.Nx_s_g", "phtr.Ny_s_g", "phtr.Nz_s_g"});
    model.result("pg7").feature("str1").set("descr", "\u8d28\u91cf\u901a\u91cf\uff0c\u76f8\u201cs_g\u201d");
    model.result("pg7").feature("str1").set("posmethod", "magnitude");
    model.result("pg7").feature("str1").set("mdist", new double[]{0.02, 0.15});
    model.result("pg7").feature("str1").set("linetype", "ribbon");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").create("col1", "Color");
    model.result("pg7").run();
    model.result("pg7").feature("str1").feature("col1").set("expr", "s_g");
    model.result("pg7").feature("str1").feature("col1").set("descr", "\u4f53\u79ef\u5206\u6570");
    model.result("pg7").run();
    model.result("pg7").feature("str1").create("sel1", "Selection");
    model.result("pg7").feature("str1").feature("sel1").selection().named("geom1_sel2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("str2", "str1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("str2").feature("col1").set("colortable", "Disco");
    model.result("pg7").run();
    model.result("pg7").feature("str2").feature("sel1").selection().named("geom1_sel4");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg7").feature("surf1").feature("sel1").selection().named("geom1_sel1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("sel1").selection().named("geom1_unisel2");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("mtrl1").set("family", "carbonforged");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("sel1").selection().named("geom1_sel6");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").feature("mtrl1").set("family", "steel");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u9694\u819c\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u4f53\uff1a\u9694\u819c\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg8").selection().geom("geom1", 3);
    model.result("pg8").selection().named("geom1_sel1");
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", "abs(we.Ilz)");
    model.result("pg8").feature("vol1").set("colortable", "Prism");
    model.result("pg8").run();

    model.title("\u4e24\u76f8\u975e\u7b49\u6e29\u96f6\u95f4\u9699\u78b1\u6027\u6c34\u7535\u89e3\u69fd");

    model
         .description("\u672c\u6a21\u578b\u5b9a\u4e49\u4e86\u4e00\u4e2a\u96f6\u95f4\u9699\u78b1\u6027\u6c34\u7535\u89e3\u69fd\uff0c\u5176\u4e2d\u6c27\u6c14\u548c\u6c22\u6c14\u5728\u7d27\u90bb\u591a\u5b54\u5206\u79bb\u5668\uff08\u9694\u819c\uff09\u7684\u591a\u5b54\u6c14\u4f53\u6269\u6563\u954d\u6be1\u7535\u6781\u4e2d\u4ea7\u751f\u5e76\u91ca\u653e\u3002\n\n\u6a21\u578b\u51e0\u4f55\u5b9a\u4e49\u4e86\u4e00\u4e2a\u7535\u89e3\u69fd\u5806\u7684\u7535\u6c60\u5355\u5143\uff0c\u5176\u4e2d\u5305\u542b\u4e24\u4e2a\u5b8c\u6574\u7684\u7535\u89e3\u6c60\uff0c\u6cbf\u901a\u9053\u65b9\u5411\u5ef6\u4f38 10\u00a0cm\uff0c\u901a\u8fc7\u6ce2\u7eb9\u53cc\u6781\u94a2\u677f\u9694\u5f00\u3002\n\n\u6a21\u578b\u6c42\u89e3\u4e86\u7535\u89e3\u6c60\u4e2d\u5b8c\u5168\u76f8\u4e92\u8026\u5408\u7684\u7535\u6d41\u5206\u5e03\u3001\u9038\u51fa\u6c14\u4f53\u548c\u7535\u89e3\u8d28\u7684\u4e24\u76f8\u6d41\uff0c\u4ee5\u53ca\u4f20\u70ed\u95ee\u9898\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("zero_gap_aec.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
