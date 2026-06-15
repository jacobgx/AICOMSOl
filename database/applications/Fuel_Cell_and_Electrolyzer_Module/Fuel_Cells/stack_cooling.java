/*
 * stack_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:11 by COMSOL 6.3.0.290. */
public class stack_cooling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/fc", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/dl", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ht", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").geom("geom1").insertFile("stack_cooling_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").run("adjsel1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("kappa_GDL_TP", "0.3[W/m/K]", "GDL \u7a7f\u5c42\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kappa_GDL_IP", "3[W/m/K]", "GDL \u9762\u5185\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param("par2")
         .set("kappa_BPP_eff", "4.5[W/m/K]", "\u6709\u6548\u5bfc\u70ed\u7cfb\u6570\uff0c\u53cc\u6781\u677f");
    model.param("par2").set("E_cell_avg", "E_cell_avg_start", "\u5e73\u5747\u7535\u6c60\u7535\u538b");
    model.param("par2").set("E_stack", "N_cells*E_cell_avg", "\u7535\u6c60\u5806\u7535\u538b");
    model.param("par2").set("perm_gas_BPP", "5e-9[m^2]", "\u6709\u6548\u78c1\u5bfc\u7387\uff0c\u53cc\u6781\u677f");
    model.param("par2")
         .set("i0_H2_ref", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("i0_O2_ref", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("a_CL", "5e7[m^2/m^3]", "\u6bd4\u9762\u79ef\uff0c\u50ac\u5316\u5c42");
    model.param("par2").set("sigmas_GDL", "200[S/m]", "\u7535\u5bfc\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("kappag_GDL", "5e-12[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("alphaa_O2", "3", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2")
         .set("epsg_GDL", "1-epss_GDL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("epss_GDL", "0.4", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("L_CL", "10[um]", "\u50ac\u5316\u5c42\u539a\u5ea6");
    model.param("par2")
         .set("E_cell_avg_start", "0.85[V]", "\u8f85\u52a9\u626b\u63cf\u4e2d\u7684\u521d\u59cb\u5e73\u5747\u7535\u6c60\u7535\u538b");
    model.param("par2").set("T_in", "60[degC]", "\u5165\u53e3\u6e29\u5ea6");
    model.param("par2").set("p_in_cath", "80[mbar]", "\u5165\u53e3\u6c14\u538b\uff0c\u9634\u6781\u4fa7");
    model.param("par2").set("p_in_an", "20[mbar]", "\u5165\u53e3\u6c14\u538b\uff0c\u9633\u6781\u4fa7");
    model.param("par2")
         .set("perm_cool_BPP", "1e-8[m^2]", "\u51b7\u5374\u6db2\u6e17\u900f\u7387\uff0c\u53cc\u6781\u677f");
    model.param("par2")
         .set("eps_gas_BPP", "0.45", "\u53cc\u6781\u677f\u4e2d\u7684\u6c14\u4f53\u4f53\u79ef\u5206\u6570");
    model.param("par2")
         .set("eps_l_BPP", "0.45", "\u53cc\u6781\u677f\u4e2d\u7684\u51b7\u5374\u6db2\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("sigma_BPP_eff", "1e3[S/m]", "\u6709\u6548\u7535\u5bfc\u7387\uff0c\u53cc\u6781\u677f");
    model.param("par2").set("v_cool_in", "10[cm/s]", "\u51b7\u5374\u6db2\u5165\u53e3\u901f\u5ea6");
    model.param("par2").set("D_stack", "D_cell*N_cells+D_cc*2+D_bpp*2", "\u7535\u6c60\u5806\u539a\u5ea6");

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
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("MembraneCrossover", "MembraneCrossover", "Membrane crossover");
    model.component("comp1").material("mat3").propertyGroup()
         .create("PolymerElectrolyteWaterTransport", "PolymerElectrolyteWaterTransport", "Polymer electrolyte water transport");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("PolymerElectrolyteWaterAbsorptionDesorption", "PolymerElectrolyteWaterAbsorptionDesorption", "Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat3").label("Nafion\u00ae, EW 1100, Vapor Equilibrated, Protonated");
    model.component("comp1").material("mat3").set("family", "glass");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Ex situ measurements of through-plane thermal conductivities\nin a polymer electrolyte fuel cell,\nO Burheim, P Vie, J Pharoah, S. Kjelstrup, Journal of Power Sources 195 (2010) 249\u2013256");
    model.component("comp1").material("mat3").propertyGroup("def").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat3").propertyGroup("def").descr("lambda", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("source", "file");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importedname", "nafion_1100_conductivity_vapor_eq.csv");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnametable", new String[][]{{"sigma_vs_T_and_RH", "1"}});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 3);
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "sigma_vs_T_and_RH"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/cm"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1", "1"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Yoshitsugu Sone et al 1996 J. Electrochem. Soc. 143 1254\n(Conductivity data refers to the \"E-form\" values, Figure 5a and 5b)");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("relativehumidity");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").label("Membrane crossover");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .set("Psi_H2", "(2.2e-11*f+2.9e-12)[mol/cm/s/bar]*exp(21[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_H2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041\n");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .set("Psi_O2", "(1.9e-11*f+1.1e-12)[mol/cm/s/bar]*exp(22[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_O2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").set("Psi_N2", "Psi_O2");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_N2", "Approximated as equal to oxygen value (no reference).");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").descr("lambda", "Water uptake");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").set("EW", "1100[g/mol]");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").addInput("relativehumidity");
    model.component("comp1").material("mat3").propertyGroup("MembraneCrossover").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .label("Polymer electrolyte water transport");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("funcname", "lambda");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("table", new String[][]{{"0.00", "0.0"}, 
         {"0.14", "1.9"}, 
         {"0.37", "2.9"}, 
         {"0.45", "3.1"}, 
         {"0.51", "3.7"}, 
         {"0.57", "4.3"}, 
         {"0.79", "6.3"}, 
         {"0.80", "6.8"}, 
         {"0.81", "7.9"}, 
         {"0.88", "10.0"}, 
         {"0.91", "12.0"}, 
         {"0.96", "13.2"}, 
         {"1.00", "14.0"}});
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("alpha", "max(c0*Dmu/(R_const*T*(1-x0)),1e-9)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("alpha", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("xi", "if(lambda>1,1,lambda)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("xi", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("lambda", "lambda(phi)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("lambda", "Water uptake");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("EW", "1100[g/mol]");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Dmu", "1.8e-5[cm^2/s]*f*exp(20[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Dmu", "Water diffusion coefficient");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("c0", "lambda/(V0*lambda+Vm)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("c0", "Water concentration");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("x0", "lambda/(lambda+1)");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("x0", "Water mole fraction");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("relativehumidity");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .label("Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .set("k_abs_dsp", "1.04e-7*exp(4.48*max(min(phi,0.85),0.25))[mol/cm^2/s]");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .setPropertyInfo("k_abs_dsp", "Kientiz, Yamada, Nonoyama, Weber,\nJournal of Fuel Cell Science and Technology, Feb 2011, Vol. 8,  Article Number 011013");
    model.component("comp1").material("mat3").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .addInput("relativehumidity");
    model.component("comp1").material("mat1").selection().named("geom1_unisel11");
    model.component("comp1").material("mat2").selection().named("geom1_unisel10");
    model.component("comp1").material("mat3").selection().named("geom1_unisel7");

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_unisel7");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T_in");
    model.component("comp1").physics("fc").feature("mem1").feature("wadh2side1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("fc").feature("mem1").feature("wado2side1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_ext7_dom");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL", "0", "0", "0", "sigmas_GDL", "0", "0", "0", "sigmas_GDL"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_ext13_dom");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL", "0", "0", "0", "sigmas_GDL", "0", "0", "0", "sigmas_GDL"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_GDL");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("kappag", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fc").create("h2gdl2", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl2")
         .label("\u6c22\u6c14\u6269\u6563\u5c42 2\uff08\u6b67\u7ba1\uff09");
    model.component("comp1").physics("fc").feature("h2gdl2").selection().named("geom1_ext3_dom");
    model.component("comp1").physics("fc").feature("h2gdl2")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("h2gdl2").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("h2gdl2")
         .set("kappag", new String[]{"perm_gas_BPP", "0", "0", "0", "perm_gas_BPP", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").create("o2gdl2", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl2")
         .label("\u6c27\u6c14\u6269\u6563\u5c42 2\uff08\u6b67\u7ba1\uff09");
    model.component("comp1").physics("fc").feature("o2gdl2").selection().named("geom1_ext9_dom");
    model.component("comp1").physics("fc").feature("o2gdl2")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("o2gdl2").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("o2gdl2")
         .set("kappag", new String[]{"perm_gas_BPP", "0", "0", "0", "perm_gas_BPP", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").create("cc1", "CurrentCollector", 3);
    model.component("comp1").physics("fc").feature("cc1").label("\u96c6\u6d41\u4f53 1\uff08\u7aef\u5757\uff09");
    model.component("comp1").physics("fc").feature("cc1").selection().named("geom1_unisel11");
    model.component("comp1").physics("fc").feature("cc1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("fc").create("cc2", "CurrentCollector", 3);
    model.component("comp1").physics("fc").feature("cc2")
         .label("\u96c6\u6d41\u4f53\uff08\u5e26\u51b7\u5374\u6d41\uff09");
    model.component("comp1").physics("fc").feature("cc2").selection().named("geom1_unisel14");
    model.component("comp1").physics("fc").feature("cc2")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("geom1_intsel1");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_H2_ref");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("geom1_intsel2");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_O2_ref");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("alphaa", "alphaa_O2");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").create("h2gdl3", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl3")
         .label("\u6c22\u6c14\u6269\u6563\u5c42 3\uff08x \u65b9\u5411\u901a\u9053\uff09");
    model.component("comp1").physics("fc").feature("h2gdl3").selection().named("geom1_ext5_dom");
    model.component("comp1").physics("fc").feature("h2gdl3")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("h2gdl3").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("h2gdl3")
         .set("kappag", new String[]{"perm_gas_BPP", "0", "0", "0", "0", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").create("h2gdl4", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl4")
         .label("\u6c22\u6c14\u6269\u6563\u5c42 4\uff08y \u65b9\u5411\u901a\u9053\uff09");
    model.component("comp1").physics("fc").feature("h2gdl4").selection().named("geom1_ext6_dom");
    model.component("comp1").physics("fc").feature("h2gdl4")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("h2gdl4").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("h2gdl4")
         .set("kappag", new String[]{"0", "0", "0", "0", "perm_gas_BPP", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").create("o2gdl3", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl3")
         .label("\u6c27\u6c14\u6269\u6563\u5c42 3\uff08x \u65b9\u5411\u901a\u9053\uff09");
    model.component("comp1").physics("fc").feature("o2gdl3").selection().named("geom1_ext11_dom");
    model.component("comp1").physics("fc").feature("o2gdl3")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("o2gdl3").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("o2gdl3")
         .set("kappag", new String[]{"perm_gas_BPP", "0", "0", "0", "0", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").create("o2gdl4", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl4")
         .label("\u6c27\u6c14\u6269\u6563\u5c42 4\uff08y \u65b9\u5411\u901a\u9053\uff09");
    model.component("comp1").physics("fc").feature("o2gdl4").selection().named("geom1_ext12_dom");
    model.component("comp1").physics("fc").feature("o2gdl4")
         .set("sigmas", new String[]{"sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff", "0", "0", "0", "sigma_BPP_eff"});
    model.component("comp1").physics("fc").feature("o2gdl4").set("epsg", "eps_gas_BPP");
    model.component("comp1").physics("fc").feature("o2gdl4")
         .set("kappag", new String[]{"0", "0", "0", "0", "perm_gas_BPP", "0", "0", "0", "perm_gas_BPP"});
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("geom1_boxsel7");
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().named("geom1_boxsel8");
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_stack");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("T_hum", "T_in");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("pA_hum", "1[atm]+p_in_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("pinit", "p_in_an");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T_in");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("pA_hum", "1[atm]+p_in_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("pinit", "p_in_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_boxsel5");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("geom1_boxsel6");
    model.component("comp1").physics("fc").prop("MassConsistentStabilization")
         .set("StabilizationO2GasFlowChannels", false);
    model.component("comp1").physics("fc").prop("MassConsistentStabilization")
         .set("StabilizationH2GasFlowChannels", false);
    model.component("comp1").physics("dl").selection().named("geom1_unisel10");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "eps_l_BPP");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"perm_cool_BPP", "0", "0", "0", "perm_cool_BPP", "0", "0", "0", "perm_cool_BPP"});
    model.component("comp1").physics("dl").create("inl1", "Inlet", 2);
    model.component("comp1").physics("dl").feature("inl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("dl").feature("inl1").set("U0in", "v_cool_in");
    model.component("comp1").physics("dl").create("out1", "Outlet", 2);
    model.component("comp1").physics("dl").feature("out1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("dl").feature("out1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").label("\u56fa\u4f53 1 - \u819c");
    model.component("comp1").physics("ht").feature("solid1").selection().named("geom1_unisel7");
    model.component("comp1").physics("ht").feature("solid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid2").label("\u56fa\u4f53 2 - GDL");
    model.component("comp1").physics("ht").feature("solid2").selection().named("geom1_unisel15");
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"kappa_GDL_IP", "0", "0", "0", "kappa_GDL_IP", "0", "0", "0", "kappa_GDL_TP"});
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("solid3", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid3").label("\u56fa\u4f53 3 - \u7aef\u5757");
    model.component("comp1").physics("ht").feature("solid3").selection().named("geom1_unisel11");
    model.component("comp1").physics("ht").create("solid4", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid4").label("\u56fa\u4f53 4 - \u6c14\u4f53\u6b67\u7ba1");
    model.component("comp1").physics("ht").feature("solid4").selection().named("geom1_unisel16");
    model.component("comp1").physics("ht").feature("solid4").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid4")
         .set("k", new String[]{"kappa_BPP_eff", "0", "0", "0", "kappa_BPP_eff", "0", "0", "0", "kappa_BPP_eff"});
    model.component("comp1").physics("ht").feature("solid4").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid4").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("poro", "eps_gas_BPP");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("k_b", new String[]{"kappa_BPP_eff", "0", "0", "0", "kappa_BPP_eff", "0", "0", "0", "kappa_BPP_eff"});
    model.component("comp1").physics("ht").feature("porous1").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_adjsel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "2.5e-3");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_unisel7");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").named("geom1_intsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").named("geom1_intsel2");
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view().create("view8", "geom1");
    model.component("comp1").view("view8").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view8").camera().set("zscale", 50);
    model.component("comp1").view("view8").hideEntities().create("hide1");
    model.component("comp1").view("view8").hideEntities("hide1").named("geom1_unisel11");

    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_ext13_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe2");
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("geom1_ext7_dom");
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe3");
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().named("geom1_unisel10");
    model.component("comp1").mesh("mesh1").feature("swe4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe4");
    model.component("comp1").mesh("mesh1").create("swe5", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe5").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe5");

    model.study("std1").create("cdi2", "CurrentDistributionInitialization");
    model.study("std1").feature().move("cdi2", 1);
    model.study("std1").feature("cdi2").set("initType", "secondary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", false);
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").setSolveFor("/physics/dl", false);
    model.study("std1").feature("stat3").set("useparam", true);
    model.study("std1").feature("stat3").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat3").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat3").setIndex("pname", "E_cell_avg", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "range(E_cell_avg_start,-0.1,0.55)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v3").set("control", "user");
    model.sol("sol1").feature("v3").feature("comp1_fc_mu0").set("solvefor", false);
    model.sol("sol1").feature("v3").feature("comp1_fc_phil").set("solvefor", false);
    model.sol("sol1").feature("v3").feature("comp1_fc_phis").set("solvefor", false);
    model.sol("sol1").feature("v3").feature("comp1_fc_wH2O_H2").set("solvefor", false);
    model.sol("sol1").feature("v3").feature("comp1_fc_wH2O_O2").set("solvefor", false);
    model.sol("sol1").feature("v3").feature("comp1_fc_wN2_O2").set("solvefor", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"fc.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"fc.Isx", "fc.Isy", "fc.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"fc.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"fc.Ilx", "fc.Ily", "fc.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2, \u6d41\u7ebf (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y", "fc.tfluxH2z"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "fc.xH2");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2, \u8868\u9762 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xH2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 4, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, O2, \u6d41\u7ebf (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "fc.xO2");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 4, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, O2, \u8868\u9762 (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 4, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy", "fc.tfluxH2Oz"});
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").feature("str1").create("col", "Color");
    model.result("pg7").feature("str1").feature("col").set("expr", "fc.xH2O");
    model.result("pg7").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 4, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 4, 0);
    model.result("pg9").label("\u6469\u5c14\u5206\u6570, N2, \u6d41\u7ebf (fc)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y", "fc.tfluxN2z"});
    model.result("pg9").feature("str1").set("posmethod", "start");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").create("col", "Color");
    model.result("pg9").feature("str1").feature("col").set("expr", "fc.xN2");
    model.result("pg9").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg9").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 4, 0);
    model.result("pg10").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").setIndex("looplevel", 4, 0);
    model.result("pg11").label("\u538b\u529b (fc)");
    model.result("pg11").create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").set("expr", new String[]{"fc.p"});
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"fc.u", "fc.v", "fc.w"});
    model.result("pg11").feature("str1").set("posmethod", "start");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset1");
    model.result("pg12").setIndex("looplevel", 4, 0);
    model.result("pg12").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg12").create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").set("expr", new String[]{"fc.aw"});
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u901f\u5ea6 (dl)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").feature().create("str1", "Streamline");
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg13").feature("str1").set("posmethod", "start");
    model.result("pg13").feature("str1").set("pointtype", "arrow");
    model.result("pg13").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg13").feature("str1").set("smooth", "internal");
    model.result("pg13").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("data", "parent");
    model.result("pg13").feature("str1").selection().geom("geom1", 2);
    model.result("pg13").feature("str1").selection()
         .set(33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 45, 46, 51, 52, 53, 54, 55, 58, 59, 64, 65, 66, 67, 68, 71, 72, 77, 78, 79, 80, 81, 84, 85, 90, 91, 92, 93, 94, 97, 98, 103, 104, 105, 108, 113, 114, 115, 118, 123, 124, 125, 128, 133, 134, 135, 138, 143, 144, 145, 148, 153, 154, 155, 156, 157, 158, 159, 160, 163, 164, 169, 170, 171, 172, 173, 176, 177, 182, 183, 184, 185, 186, 189, 190, 195, 196, 197, 198, 199, 202, 203, 208, 209, 210, 211, 212, 215, 216, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 250, 253, 256, 259, 262, 268, 274, 280, 286, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 446, 447, 448, 449, 456, 457, 458, 459, 466, 467, 468, 469, 476, 477, 478, 479, 486, 487, 488, 489, 492, 493, 494, 495, 498, 499, 500, 501, 504, 505, 506, 507, 510, 511, 512, 513, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 542, 543, 544, 545, 546, 547, 550, 556, 562, 568, 574, 577, 581, 585, 589, 593, 597);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg13").feature("str1").feature().create("col1", "Color");
    model.result("pg13").feature("str1").feature("col1").set("expr", "dl.U");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").label("\u538b\u529b (dl)");
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u8868\u9762");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("expr", "p");
    model.result("pg14").feature("surf1").set("smooth", "internal");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u6e29\u5ea6 (ht)");
    model.result("pg15").feature().create("vol1", "Volume");
    model.result("pg15").feature("vol1").set("showsolutionparams", "on");
    model.result("pg15").feature("vol1").set("solutionparams", "parent");
    model.result("pg15").feature("vol1").set("expr", "T");
    model.result("pg15").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg15").feature("vol1").set("smooth", "internal");
    model.result("pg15").feature("vol1").set("showsolutionparams", "on");
    model.result("pg15").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").active(false);
    model.result("pg1").feature("arwv1").active(false);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "fc.phis");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").active(false);
    model.result("pg2").feature("arwv1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("str1").active(false);
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"fc.tfluxH2x", "fc.tfluxH2y", "fc.tfluxH2z"});
    model.result("pg3").feature("strmsl1").set("descr", "\u603b\u901a\u91cf");
    model.result("pg3").feature("strmsl1").set("xnumber", "0");
    model.result("pg3").feature("strmsl1").set("ynumber", "0");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1")
         .set("zcoord", "range(D_cc+D_bpp*0.75,D_cell,D_cc+D_bpp*0.75+N_cells*D_cell)");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.1);
    model.result("pg3").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg3").feature("strmsl1").set("arrowdistr", "equalinvtime");
    model.result("pg3").feature("strmsl1").set("arrowlength", "proportional");
    model.result("pg3").feature("strmsl1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "fc.xH2");
    model.result("pg3").feature("strmsl1").feature("col1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg3").feature("strmsl1").feature("col1").set("titletype", "auto");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("str1").active(false);
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg5").feature("strmsl1").set("descr", "\u603b\u901a\u91cf");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1")
         .set("zcoord", "range(D_cc+D_bpp/2+D_cell-D_bpp*0.25,D_cell,D_stack-D_bpp*0.75-D_cc)");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.1);
    model.result("pg5").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg5").feature("strmsl1").set("arrowdistr", "equalinvtime");
    model.result("pg5").feature("strmsl1").set("arrowlength", "proportional");
    model.result("pg5").run();
    model.result("pg5").feature("strmsl1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "fc.xO2");
    model.result("pg5").feature("strmsl1").feature("col1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg5").feature("strmsl1").feature("col1").set("titletype", "auto");
    model.result("pg5").run();
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").feature("mslc1").active(false);
    model.result("pg12").run();
    model.result("pg12").label("\u6c27\u6c14 GDE \u4e2d\u7684\u6c34\u6d3b\u5ea6");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "fc.aw");
    model.result("pg12").feature("surf1").set("descr", "\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09");
    model.result("pg12").feature("surf1").create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().named("geom1_intsel2");
    model.result("pg12").run();
    model.result("pg12").set("edges", false);
    model.result("pg12").run();
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").run();
    model.result("pg16").label("MEA \u4e2d\u7684\u6e29\u5ea6");
    model.result("pg16").set("edges", false);
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "T");
    model.result("pg16").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg16").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg16").feature("surf1").create("sel1", "Selection");
    model.result("pg16").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg16").feature("surf1").feature("sel1").selection().named("geom1_unisel15");
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("surf2", "surf1");
    model.result("pg16").run();
    model.result("pg16").feature("surf2").set("titletype", "none");
    model.result("pg16").feature("surf2").set("inheritplot", "surf1");
    model.result("pg16").run();
    model.result("pg16").feature("surf2").feature("sel1").selection().named("geom1_unisel7");
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").label("\u51b7\u5374\u901a\u9053\u4e2d\u7684\u6e29\u5ea6");
    model.result("pg17").set("titletype", "label");
    model.result("pg17").set("edges", false);
    model.result("pg17").set("showlegendsunit", true);
    model.result("pg17").create("strmsl1", "StreamlineMultislice");
    model.result("pg17").feature("strmsl1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg17").feature("strmsl1").set("descr", "\u603b\u8fbe\u897f\u901f\u5ea6\u573a");
    model.result("pg17").feature("strmsl1").set("xnumber", "0");
    model.result("pg17").feature("strmsl1").set("ynumber", "0");
    model.result("pg17").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg17").feature("strmsl1")
         .set("zcoord", "range(D_cc+D_bpp*0.5,D_cell,D_cc+D_bpp*0.5+N_cells*D_cell)");
    model.result("pg17").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg17").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg17").feature("strmsl1").set("arrowdistr", "equalinvtime");
    model.result("pg17").feature("strmsl1").create("col1", "Color");
    model.result("pg17").run();
    model.result("pg17").feature("strmsl1").feature("col1").set("expr", "T");
    model.result("pg17").feature("strmsl1").feature("col1").set("descr", "\u6e29\u5ea6");
    model.result("pg17").feature("strmsl1").feature("col1").set("colortable", "HeatCameraLight");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "W_plate/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.95*L_plate", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "D_cc", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "W_plate/2", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.95*L_plate", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", "D_cc+D_cell*N_cells+D_bpp", 1, 2);
    model.result().create("pg18", "PlotGroup1D");
    model.result("pg18").run();
    model.result("pg18")
         .label("\u671d\u5411\u51b7\u5374\u51fa\u53e3\u7684\u7535\u6c60\u5806\u4e2d\u90e8\u6e29\u5ea6");
    model.result("pg18").set("titletype", "label");
    model.result("pg18").create("lngr1", "LineGraph");
    model.result("pg18").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg18").feature("lngr1").set("linewidth", "preference");
    model.result("pg18").run();
    model.result("pg18").set("data", "cln1");
    model.result("pg18").run();
    model.result("pg18").feature("lngr1").set("expr", "T");
    model.result("pg18").feature("lngr1").set("descr", "\u6e29\u5ea6");
    model.result("pg18").feature("lngr1").set("xdata", "expr");
    model.result("pg18").feature("lngr1").set("xdataexpr", "z");
    model.result("pg18").feature("lngr1").set("legend", true);
    model.result("pg18").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg8").run();
    model.result("pg8").set("edges", false);
    model.result("pg8").run();
    model.result("pg10").run();
    model.result("pg10").set("edges", false);
    model.result("pg10").run();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().remove("pg9");
    model.result().remove("pg11");
    model.result().remove("pg13");
    model.result().remove("pg14");
    model.result("pg8").run();
    model.result("pg15").run();
    model.result("pg15").set("edges", false);
    model.result("pg15").run();
    model.result("pg15").feature("vol1").create("tran1", "Transparency");
    model.result("pg15").run();
    model.result("pg17").run();
    model.result("pg15").run();
    model.result("pg15").feature().copy("strmsl1", "pg17/strmsl1");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").feature("strmsl1").feature("col1").active(false);
    model.result("pg15").run();
    model.result("pg15").feature("strmsl1").set("color", "black");
    model.result("pg15").run();
    model.result("pg15").run();

    model.title("\u71c3\u6599\u7535\u6c60\u5806\u51b7\u5374");

    model
         .description("\u672c\u6559\u7a0b\u5bf9\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c (PEM) \u71c3\u6599\u7535\u6c60\u5806\u7684\u70ed\u7ba1\u7406\u8fdb\u884c\u5efa\u6a21\u3002\u5bf9\u7535\u6c60\u5806\u7684\u6240\u6709\u7535\u6c60\u5355\u5143\u6765\u8bf4\uff0c\u4ee5\u76f8\u4f3c\u7684\u6e29\u5ea6\u5206\u5e03\u8fdb\u884c\u64cd\u4f5c\u975e\u5e38\u91cd\u8981\uff0c\u56e0\u4e3a\u4e0d\u5747\u5300\u7684\u6e29\u5ea6\u5206\u5e03\u53ef\u80fd\u4f1a\u5bfc\u81f4\u4e0d\u5747\u5300\u7684\u6c34\u84b8\u6c14\u51dd\u7ed3\u548c\u7535\u6c60\u5355\u5143\u4e4b\u95f4\u51fa\u73b0\u8f83\u5927\u7684\u6027\u80fd\u5dee\u5f02\u3002\n\n\u8be5\u7535\u6c60\u5806\u7531\u4e94\u4e2a\u7535\u6c60\u5355\u5143\u7ec4\u6210\uff0c\u5b83\u4eec\u4e0e\u643a\u5e26\u6db2\u4f53\u51b7\u5374\u6db2\u7684\u53cc\u6781\u677f\u4ea4\u9519\u5728\u4e00\u8d77\u3002\u672c\u6a21\u578b\u6c42\u89e3\u6e29\u5ea6\u3001\u7535\u6781\u548c\u7535\u89e3\u8d28\u76f8\u7535\u4f4d\u3001\u53cd\u5e94\u7269\u8d28\u5728\u6bcf\u4e2a\u5355\u72ec\u6c14\u5ba4\u4e2d\u7684\u8d28\u91cf\u4f20\u9012\uff0c\u4ee5\u53ca\u6c14\u4f53\u548c\u6db2\u4f53\u6d41\u52a8\u5ba4\u4e2d\u7684\u6d41\u4f53\u538b\u529b\u548c\u76f8\u5e94\u7684\u901f\u5ea6\u3002\n\n\u672c\u4f8b\u6ca1\u6709\u5728\u51e0\u4f55\u4e2d\u660e\u786e\u89e3\u6790\u53cc\u6781\u677f\u7684\u6d41\u9053\uff0c\u800c\u662f\u4f7f\u7528\u5404\u5411\u5f02\u6027\u6e17\u900f\u7387\u6765\u5b9a\u4e49\u7535\u6c60\u5806\u4e2d\u7684\u6d41\u4f53\u6d41\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("stack_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
