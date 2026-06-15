/*
 * nonisothermal_pem_fuel_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:08 by COMSOL 6.3.0.290. */
public class nonisothermal_pem_fuel_cell {

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
    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp").field("velocity").field("ua");
    model.component("comp1").physics("fp").field("velocity").component(new String[]{"ua", "va", "wa"});
    model.component("comp1").physics("fp").field("pressure").field("pa");
    model.component("comp1").physics().create("fp2", "FreeAndPorousMediaFlow", "geom1");
    model.component("comp1").physics("fp2").field("velocity").field("uc");
    model.component("comp1").physics("fp2").field("velocity").component(new String[]{"uc", "vc", "wc"});
    model.component("comp1").physics("fp2").field("pressure").field("pc");
    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").field("velocity").field("u_cool");
    model.component("comp1").physics("spf").field("velocity").component(new String[]{"u_cool", "v_cool", "w_cool"});
    model.component("comp1").physics("spf").field("pressure").field("p_cool");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").insertFile("nonisothermal_pem_fuel_cell_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("adjsel2");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("E_cell", "1[V]", "\u7535\u6c60\u7535\u538b\uff08\u968f\u201c\u8f85\u52a9\u626b\u63cf\u201d\u53d8\u5316\uff09");
    model.param("par2").set("T_in", "70[degC]", "\u5165\u53e3\u6e29\u5ea6");
    model.param("par2").set("sigmas_GDL_IP", "5000[S/m]", "\u9762\u5185 GDL \u7535\u5bfc\u7387");
    model.param("par2").set("sigmas_GDL_TP", "200[S/m]", "\u7a7f\u5c42 GDL \u7535\u5bfc\u7387");
    model.param("par2").set("L_CL", "10[um]", "\u50ac\u5316\u5c42\u539a\u5ea6");
    model.param("par2")
         .set("i0_H2_ref", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("i0_O2_ref", "1e-4[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("a_CL", "5e7[m^2/m^3]", "\u6bd4\u9762\u79ef\uff0c\u50ac\u5316\u5c42");
    model.param("par2")
         .set("kappag_GDL", "5e-12[m^2]", "\u6c14\u4f53\u6e17\u900f\u7387\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("alphaa_O2", "3", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2")
         .set("epsg_GDL", "1-epss_GDL", "\u6c14\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2")
         .set("epss_GDL", "0.4", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6c14\u4f53\u6269\u6563\u5c42");
    model.param("par2").set("RH_an", "85[%]", "\u9633\u6781\u76f8\u5bf9\u6e7f\u5ea6");
    model.param("par2").set("RH_cath", "85[%]", "\u9634\u6781\u76f8\u5bf9\u6e7f\u5ea6");
    model.param("par2").set("kappa_GDL_TP", "0.3[W/m/K]", "\u7a7f\u5c42 GDL \u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("kappa_GDL_IP", "3[W/m/K]", "\u9762\u5185 GDL \u5bfc\u70ed\u7cfb\u6570");
    model.param("par2").set("v_in_an", "0.1[m/s]", "\u5165\u53e3\u901f\u5ea6\uff0c\u9633\u6781");
    model.param("par2").set("v_in_cath", "0.5[m/s]", "\u5165\u53e3\u901f\u5ea6\uff0c\u9634\u6781");
    model.param("par2").set("v_in_cool", "50[mm/s]", "\u5165\u53e3\u901f\u5ea6\uff0c\u51b7\u5374\u6d41\u4f53");
    model.param("par2")
         .set("Rc", "1e-7[ohm*m^2]", "\u63a5\u89e6\u7535\u963b\uff0c\u7535\u5b50\u5bfc\u7535\u76f8\u7684\u5185\u90e8\u8fb9\u754c");

    model.component("comp1").physics("fc").selection().named("geom1_unisel1");
    model.component("comp1").physics("fc").prop("MembraneTransport").set("H2O_mem", true);

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
    model.component("comp1").material("mat1").selection().named("geom1_unisel4");
    model.component("comp1").material("mat2").label("\u51b7\u5374\u6db2");
    model.component("comp1").material("mat2").selection().named("geom1_boxsel9");
    model.component("comp1").material("mat3").selection().named("geom1_boxsel13");

    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_boxsel13");
    model.component("comp1").physics("fc").feature("mem1").feature("init1").set("T0", "T_in");
    model.component("comp1").physics("fc").feature("mem1").feature("wadh2side1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("fc").feature("mem1").feature("wado2side1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("fc").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("h2fch1").selection().named("geom1_boxsel6");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_boxsel15");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_TP"});
    model.component("comp1").physics("fc").feature("h2gdl1").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("geom1_difsel1_dom");
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_boxsel14");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_IP", "0", "0", "0", "sigmas_GDL_TP"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "epsg_GDL");
    model.component("comp1").physics("fc").create("cc1", "CurrentCollector", 3);
    model.component("comp1").physics("fc").feature("cc1").selection().named("geom1_unisel4");
    model.component("comp1").physics("fc").feature("cc1").set("sigmas_mat", "from_mat");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_cell");
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().named("geom1_boxsel12");
    model.component("comp1").physics("fc").feature("ecph1")
         .create("ieccr1", "InternalElectrodeContactResistance", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("ieccr1").selection().named("geom1_adjsel2");
    model.component("comp1").physics("fc").feature("ecph1").feature("ieccr1").set("Rc", "Rc");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedMixture");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("RH_hum", "RH_an");
    model.component("comp1").physics("fc").feature("h2gasph1").feature("init1").set("T_hum", "T_in");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2in1").selection().named("geom1_boxsel7");
    model.component("comp1").physics("fc").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("fc").feature("h2gasph1").feature("h2out1").selection().named("geom1_boxsel8");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("RH_hum", "RH_cath");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T_in");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("geom1_boxsel17");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_H2_ref");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("geom1_boxsel16");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "L_CL");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_O2_ref");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("alphaa", "alphaa_O2");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "a_CL");
    model.component("comp1").physics("fp")
         .label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8 - \u9633\u6781");
    model.component("comp1").physics("fp").selection().named("geom1_unisel6");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");

    model.component("comp1").multiphysics().create("rfh1", "ReactingFlowH2GasPhase", 3);

    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().named("geom1_boxsel15");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "epsg_GDL");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp").feature("inl1").set("Uavfdf", "v_in_an");
    model.component("comp1").physics("fp").feature("inl1").selection().named("geom1_boxsel7");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().named("geom1_boxsel8");
    model.component("comp1").physics("fp").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("fp").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("fp").feature("wallbc2").selection().named("geom1_adjsel1");
    model.component("comp1").physics("fp").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("fp").feature("sym1").selection().named("geom1_unisel3");
    model.component("comp1").physics("fp2")
         .label("\u81ea\u7531\u548c\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8 - \u9634\u6781");
    model.component("comp1").physics("fp2").selection().named("geom1_unisel7");
    model.component("comp1").physics("fp2").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");

    model.component("comp1").multiphysics().create("rfo1", "ReactingFlowO2GasPhase", 3);
    model.component("comp1").multiphysics("rfo1").set("Fluid_physics", "fp2");

    model.component("comp1").physics("fp2").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp2").feature("porous1").selection().named("geom1_boxsel14");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("epsilon_p", "epsg_GDL");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappag_GDL", "0", "0", "0", "kappag_GDL", "0", "0", "0", "kappag_GDL"});
    model.component("comp1").physics("fp2").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp2").feature("inl1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("fp2").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp2").feature("inl1").set("Uavfdf", "v_in_cath");
    model.component("comp1").physics("fp2").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp2").feature("out1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("fp2").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("fp2").feature("wallbc2").selection().named("geom1_adjsel1");
    model.component("comp1").physics("fp2").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("fp2").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("fp2").feature("sym1").selection().named("geom1_unisel3");
    model.component("comp1").physics("ht").feature("fluid1").label("\u6d41\u4f53 - \u9633\u6781\u6c14\u4f53");
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_boxsel6");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.fc.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "root.comp1.fc.Cp_mix");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").create("fluid2", "FluidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("fluid2").label("\u6d41\u4f53 - \u9634\u6781\u6c14\u4f53");
    model.component("comp1").physics("ht").feature("fluid2").selection().named("geom1_difsel1_dom");
    model.component("comp1").physics("ht").feature("fluid2").set("k_mat", "root.comp1.fc.kgas_mix_tensorxx");
    model.component("comp1").physics("ht").feature("fluid2").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid2").set("rho_mat", "root.comp1.fc.rho");
    model.component("comp1").physics("ht").feature("fluid2").set("Cp_mat", "root.comp1.fc.Cp_mix");
    model.component("comp1").physics("ht").feature("fluid2").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").create("fluid3", "FluidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("fluid3").label("\u6d41\u4f53 - \u51b7\u5374");
    model.component("comp1").physics("ht").feature("fluid3").selection().named("geom1_boxsel9");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid2").label("\u56fa\u4f53 - GDL");
    model.component("comp1").physics("ht").feature("solid2").selection().named("geom1_unisel8");
    model.component("comp1").physics("ht").feature("solid2").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2")
         .set("k", new String[]{"kappa_GDL_IP", "0", "0", "0", "kappa_GDL_IP", "0", "0", "0", "kappa_GDL_TP"});
    model.component("comp1").physics("ht").feature("solid2").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("solid3", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid3").selection().named("geom1_boxsel13");
    model.component("comp1").physics("ht").feature("solid3").label("\u56fa\u4f53 - \u819c");
    model.component("comp1").physics("ht").feature("solid3").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("solid3").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_unisel9");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_unisel10");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("geom1_unisel3");
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc1").selection().named("geom1_unisel2");
    model.component("comp1").physics("spf").selection().named("geom1_boxsel9");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_boxsel10");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_in_cool");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_boxsel11");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_unisel3");

    model.component("comp1").multiphysics().create("ech1", "ElectrochemicalHeating", 3);
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1")
         .label("\u975e\u7b49\u6e29\u6d41\u52a8 - \u9633\u6781\u6c14\u4f53");
    model.component("comp1").multiphysics().create("nitf2", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf2")
         .label("\u975e\u7b49\u6e29\u6d41\u52a8 - \u9634\u6781\u6c14\u4f53");
    model.component("comp1").multiphysics("nitf2").set("Fluid_physics", "fp2");
    model.component("comp1").multiphysics().create("nitf3", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf3").label("\u975e\u7b49\u6e29\u6d41\u52a8 - \u51b7\u5374\u6db2");
    model.component("comp1").multiphysics("nitf3").set("Fluid_physics", "spf");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_in");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_boxsel9", "geom1_unisel5"});
    model.component("comp1").selection("uni1").label("\u81ea\u7531\u6d41\u52a8\u57df");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u81ea\u7531\u6d41\u52a8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u8fb9\u754c\u5c42\u8fb9\u754c");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif1")
         .set("subtract", new String[]{"geom1_boxsel1", "geom1_boxsel2", "geom1_boxsel7", "geom1_boxsel8", "geom1_boxsel10", "geom1_boxsel11", "geom1_unisel3"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u626b\u63a0\u57df");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_boxsel13", "geom1_boxsel14", "geom1_boxsel15"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u81ea\u7531\u56db\u9762\u4f53\u7f51\u683c\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"uni2"});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_difsel1_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", "m_th/4");
    model.component("comp1").mesh("mesh1").create("cr1", "CornerRefinement");
    model.component("comp1").mesh("mesh1").feature("cr1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("cr1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("cr1").selection("boundary").named("dif1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "m_th");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "m_th/2");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "m_th/5");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

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
    model.study("std1").feature("cdi").setSolveFor("/physics/fp", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/fp2", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/spf", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfh1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfo1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ech1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nitf2", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/nitf3", true);
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
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fp2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfh1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfo1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ech1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf2", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf3", true);
    model.study("std1").feature("stat").label("\u7a33\u6001 - \u9633\u6781\u6d41");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat").setSolveFor("/physics/fp2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").label("\u7a33\u6001 - \u9634\u6781\u6d41");
    model.study("std1").feature("stat2").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/fp", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").label("\u7a33\u6001 - \u51b7\u5374\u6d41");
    model.study("std1").feature("stat3").setSolveFor("/physics/fc", false);
    model.study("std1").feature("stat3").setSolveFor("/physics/fp", false);
    model.study("std1").feature("stat3").setSolveFor("/physics/fp2", false);
    model.study("std1").feature("stat3").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat3").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("stat3").setSolveFor("/multiphysics/rfo1", false);
    model.study("std1").create("stat4", "Stationary");
    model.study("std1").feature("stat4")
         .label("\u7a33\u6001 - \u9664\u5c42\u6d41\u4e4b\u5916\u7684\u6240\u6709\u7269\u7406\u573a");
    model.study("std1").feature("stat4").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat4").set("useparam", true);
    model.study("std1").feature("stat4").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat4").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat4").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat4").setIndex("pname", "a_CL", 0);
    model.study("std1").feature("stat4").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat4").setIndex("punit", "1/m", 0);
    model.study("std1").feature("stat4").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat4").setIndex("plistarr", "range(1,-0.1,0.5)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s5").feature("se1").set("segstabacc", "none");
    model.sol("sol1").feature("s5").feature("se1").feature("ss2").set("subdamp", "1");
    model.sol("sol1").feature("s5").feature("se1").feature("ss3").set("subdamp", "1");
    model.sol("sol1").feature("s5").feature("se1").feature("ss4").set("subdamp", "1");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
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
    model.result("pg11").label("\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09 (fc)");
    model.result("pg11").create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").set("expr", new String[]{"fc.aw"});
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u901f\u5ea6 (fp)");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("slc1", "Slice");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg12").feature("slc1").label("\u5207\u9762");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("expr", "fp.U");
    model.result("pg12").feature("slc1").set("smooth", "internal");
    model.result("pg12").feature("slc1").set("showsolutionparams", "on");
    model.result("pg12").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(8, 9, 12, 16, 19, 24);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u538b\u529b (fp)");
    model.result("pg13").set("data", "surf1");
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").label("\u8868\u9762");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("expr", "pa");
    model.result("pg13").feature("surf1").set("colortable", "Dipole");
    model.result("pg13").feature("surf1").set("smooth", "internal");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result("pg13").feature("surf1").feature().create("tran1", "Transparency");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").label("\u901f\u5ea6 (fp2)");
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").feature().create("slc1", "Slice");
    model.result("pg14").feature("slc1").label("\u5207\u9762");
    model.result("pg14").feature("slc1").set("showsolutionparams", "on");
    model.result("pg14").feature("slc1").set("expr", "fp2.U");
    model.result("pg14").feature("slc1").set("smooth", "internal");
    model.result("pg14").feature("slc1").set("showsolutionparams", "on");
    model.result("pg14").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 1");
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(33, 34, 40, 43, 44, 46, 48, 50, 51, 52, 53, 54, 56, 57, 59, 61, 63, 64, 65, 66, 67, 69, 70, 72, 74, 76, 77, 78, 79, 80, 82, 83, 85, 87, 89, 90, 91, 92, 93, 95, 96, 98, 100, 102, 103, 104, 105, 106, 108, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 173, 174, 175, 176, 177, 178, 179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191, 192, 193, 194, 195, 196, 197, 198, 199, 201, 202, 203, 204, 205, 206, 207, 208, 209, 211, 212, 213, 214, 215, 216, 217, 218, 219, 221, 222);
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u538b\u529b (fp2)");
    model.result("pg15").set("data", "surf2");
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").feature().create("surf1", "Surface");
    model.result("pg15").feature("surf1").label("\u8868\u9762");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("expr", "pc");
    model.result("pg15").feature("surf1").set("colortable", "Dipole");
    model.result("pg15").feature("surf1").set("smooth", "internal");
    model.result("pg15").feature("surf1").set("showsolutionparams", "on");
    model.result("pg15").feature("surf1").set("data", "parent");
    model.result("pg15").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").label("\u6e29\u5ea6 (ht)");
    model.result("pg16").feature().create("vol1", "Volume");
    model.result("pg16").feature("vol1").set("showsolutionparams", "on");
    model.result("pg16").feature("vol1").set("solutionparams", "parent");
    model.result("pg16").feature("vol1").set("expr", "T");
    model.result("pg16").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg16").feature("vol1").set("smooth", "internal");
    model.result("pg16").feature("vol1").set("showsolutionparams", "on");
    model.result("pg16").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").label("\u901f\u5ea6 (spf)");
    model.result("pg17").set("frametype", "spatial");
    model.result("pg17").feature().create("slc1", "Slice");
    model.result("pg17").feature("slc1").label("\u5207\u9762");
    model.result("pg17").feature("slc1").set("showsolutionparams", "on");
    model.result("pg17").feature("slc1").set("expr", "spf.U");
    model.result("pg17").feature("slc1").set("smooth", "internal");
    model.result("pg17").feature("slc1").set("showsolutionparams", "on");
    model.result("pg17").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("\u5916\u58c1 2");
    model.result().dataset("surf3").set("data", "dset1");
    model.result().dataset("surf3").selection().geom("geom1", 2);
    model.result().dataset("surf3").selection().set(3, 6, 18);
    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").label("\u538b\u529b (spf)");
    model.result("pg18").set("data", "surf3");
    model.result("pg18").set("frametype", "spatial");
    model.result("pg18").feature().create("surf1", "Surface");
    model.result("pg18").feature("surf1").label("\u8868\u9762");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("expr", "p_cool");
    model.result("pg18").feature("surf1").set("colortable", "Dipole");
    model.result("pg18").feature("surf1").set("smooth", "internal");
    model.result("pg18").feature("surf1").set("showsolutionparams", "on");
    model.result("pg18").feature("surf1").set("data", "parent");
    model.result("pg18").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg19", "PlotGroup3D");
    model.result("pg19").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg19").set("showlegendsunit", true);
    model.result("pg19").feature().create("surf1", "Surface");
    model.result("pg19").feature("surf1").label("\u58c1\u6e29");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("solutionparams", "parent");
    model.result("pg19").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg19").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg19").feature("surf1").set("smooth", "internal");
    model.result("pg19").feature("surf1").set("showsolutionparams", "on");
    model.result("pg19").feature("surf1").set("data", "parent");
    model.result("pg19").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg19").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg19").feature("surf1").feature("sel1").selection().set(8, 9, 12, 16, 19, 24);
    model.result("pg19").feature().create("vol1", "Volume");
    model.result("pg19").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg19").feature("vol1").set("showsolutionparams", "on");
    model.result("pg19").feature("vol1").set("solutionparams", "parent");
    model.result("pg19").feature("vol1").set("expr", "nitf1.T");
    model.result("pg19").feature("vol1").set("smooth", "internal");
    model.result("pg19").feature("vol1").set("showsolutionparams", "on");
    model.result("pg19").feature("vol1").set("data", "parent");
    model.result("pg19").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg19").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg19").feature("vol1").feature("sel1").selection().set(2, 3, 4, 6, 7, 9, 10);
    model.result("pg19").feature("vol1").set("inheritplot", "surf1");
    model.result("pg19").feature().create("arwv1", "ArrowVolume");
    model.result("pg19").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg19").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg19").feature("arwv1").set("solutionparams", "parent");
    model.result("pg19").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg19").feature("arwv1").set("xnumber", 30);
    model.result("pg19").feature("arwv1").set("ynumber", 30);
    model.result("pg19").feature("arwv1").set("znumber", 30);
    model.result("pg19").feature("arwv1").set("arrowtype", "cone");
    model.result("pg19").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg19").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg19").feature("arwv1").set("data", "parent");
    model.result("pg19").feature("arwv1").feature().create("col1", "Color");
    model.result("pg19").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg19").feature("arwv1").feature("col1").set("expr", "fp.U");
    model.result("pg19").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg19").feature("arwv1").feature("filt1").set("expr", "fp.U>nitf1.Uave");
    model.result().create("pg20", "PlotGroup3D");
    model.result("pg20").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf2)");
    model.result("pg20").set("showlegendsunit", true);
    model.result("pg20").feature().create("surf1", "Surface");
    model.result("pg20").feature("surf1").label("\u58c1\u6e29");
    model.result("pg20").feature("surf1").set("showsolutionparams", "on");
    model.result("pg20").feature("surf1").set("solutionparams", "parent");
    model.result("pg20").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg20").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg20").feature("surf1").set("smooth", "internal");
    model.result("pg20").feature("surf1").set("showsolutionparams", "on");
    model.result("pg20").feature("surf1").set("data", "parent");
    model.result("pg20").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg20").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg20").feature("surf1").feature("sel1").selection()
         .set(33, 34, 40, 43, 44, 46, 48, 50, 51, 52, 53, 54, 56, 57, 59, 61, 63, 64, 65, 66, 67, 69, 70, 72, 74, 76, 77, 78, 79, 80, 82, 83, 85, 87, 89, 90, 91, 92, 93, 95, 96, 98, 100, 102, 103, 104, 105, 106, 108, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 173, 174, 175, 176, 177, 178, 179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 191, 192, 193, 194, 195, 196, 197, 198, 199, 201, 202, 203, 204, 205, 206, 207, 208, 209, 211, 212, 213, 214, 215, 216, 217, 218, 219, 221, 222);
    model.result("pg20").feature().create("vol1", "Volume");
    model.result("pg20").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg20").feature("vol1").set("showsolutionparams", "on");
    model.result("pg20").feature("vol1").set("solutionparams", "parent");
    model.result("pg20").feature("vol1").set("expr", "nitf2.T");
    model.result("pg20").feature("vol1").set("smooth", "internal");
    model.result("pg20").feature("vol1").set("showsolutionparams", "on");
    model.result("pg20").feature("vol1").set("data", "parent");
    model.result("pg20").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg20").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg20").feature("vol1").feature("sel1").selection().set(2, 3, 4, 6, 7, 9, 10);
    model.result("pg20").feature("vol1").set("inheritplot", "surf1");
    model.result("pg20").feature().create("arwv1", "ArrowVolume");
    model.result("pg20").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg20").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg20").feature("arwv1").set("solutionparams", "parent");
    model.result("pg20").feature("arwv1").set("expr", new String[]{"nitf2.ux", "nitf2.uy", "nitf2.uz"});
    model.result("pg20").feature("arwv1").set("xnumber", 30);
    model.result("pg20").feature("arwv1").set("ynumber", 30);
    model.result("pg20").feature("arwv1").set("znumber", 30);
    model.result("pg20").feature("arwv1").set("arrowtype", "cone");
    model.result("pg20").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg20").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg20").feature("arwv1").set("data", "parent");
    model.result("pg20").feature("arwv1").feature().create("col1", "Color");
    model.result("pg20").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg20").feature("arwv1").feature("col1").set("expr", "fp2.U");
    model.result("pg20").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg20").feature("arwv1").feature("filt1").set("expr", "fp2.U>nitf2.Uave");
    model.result().create("pg21", "PlotGroup3D");
    model.result("pg21").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf3)");
    model.result("pg21").set("showlegendsunit", true);
    model.result("pg21").feature().create("surf1", "Surface");
    model.result("pg21").feature("surf1").label("\u58c1\u6e29");
    model.result("pg21").feature("surf1").set("showsolutionparams", "on");
    model.result("pg21").feature("surf1").set("solutionparams", "parent");
    model.result("pg21").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg21").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg21").feature("surf1").set("smooth", "internal");
    model.result("pg21").feature("surf1").set("showsolutionparams", "on");
    model.result("pg21").feature("surf1").set("data", "parent");
    model.result("pg21").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg21").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg21").feature("surf1").feature("sel1").selection().set(3, 6, 18);
    model.result("pg21").feature().create("vol1", "Volume");
    model.result("pg21").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg21").feature("vol1").set("showsolutionparams", "on");
    model.result("pg21").feature("vol1").set("solutionparams", "parent");
    model.result("pg21").feature("vol1").set("expr", "nitf3.T");
    model.result("pg21").feature("vol1").set("smooth", "internal");
    model.result("pg21").feature("vol1").set("showsolutionparams", "on");
    model.result("pg21").feature("vol1").set("data", "parent");
    model.result("pg21").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg21").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg21").feature("vol1").feature("sel1").selection().set(2, 3, 4, 6, 7, 9, 10);
    model.result("pg21").feature("vol1").set("inheritplot", "surf1");
    model.result("pg21").feature().create("arwv1", "ArrowVolume");
    model.result("pg21").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg21").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg21").feature("arwv1").set("solutionparams", "parent");
    model.result("pg21").feature("arwv1").set("expr", new String[]{"nitf3.ux", "nitf3.uy", "nitf3.uz"});
    model.result("pg21").feature("arwv1").set("xnumber", 30);
    model.result("pg21").feature("arwv1").set("ynumber", 30);
    model.result("pg21").feature("arwv1").set("znumber", 30);
    model.result("pg21").feature("arwv1").set("arrowtype", "cone");
    model.result("pg21").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg21").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg21").feature("arwv1").set("data", "parent");
    model.result("pg21").feature("arwv1").feature().create("col1", "Color");
    model.result("pg21").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg21").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg21").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg21").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf3.Uave");
    model.result("pg1").run();

    model.study("std1").feature("stat4").set("plot", true);
    model.study("std1").feature("stat4").set("plotgroup", "pg16");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg22", "PlotGroup3D");
    model.result("pg22").run();
    model.result("pg22").label("\u819c\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg22").create("surf1", "Surface");
    model.result("pg22").feature("surf1").set("expr", "fc.Ilz");
    model.result("pg22").feature("surf1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg22").feature("surf1").set("unit", "A/cm^2");
    model.result("pg22").run();
    model.result().create("pg23", "PlotGroup3D");
    model.result("pg23").run();
    model.result("pg23").label("\u7535\u6781\u76f8\u7535\u4f4d\uff0c\u9633\u6781\u4fa7");
    model.result("pg23").selection().geom("geom1", 2);
    model.result("pg23").selection().named("geom1_boxsel5_bnd");
    model.result("pg23").create("surf1", "Surface");
    model.result("pg23").feature("surf1").set("expr", "fc.phis");
    model.result("pg23").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg23").run();
    model.result("pg23").run();
    model.result().duplicate("pg24", "pg23");
    model.result("pg24").run();
    model.result("pg24").label("\u7535\u6781\u76f8\u7535\u4f4d\uff0c\u9634\u6781");
    model.result("pg24").selection().named("geom1_csel5_bnd");
    model.result("pg24").run();
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "selection");
    model.result("pg5").feature("str1").set("selnumber", 15);
    model.result("pg5").feature("str1").selection().named("geom1_boxsel1");
    model.result("pg5").feature("str1").set("linetype", "ribbon");
    model.result("pg5").run();
    model.result("pg5").feature("str1").feature("col").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "fc.xO2");
    model.result("pg5").feature("surf1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg5").feature("surf1").set("titletype", "custom");
    model.result("pg5").feature("surf1").set("typeintitle", false);
    model.result("pg5").feature("surf1").set("inheritplot", "str1");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("geom1_adjsel1");
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "1");
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").set("color", "white");
    model.result("pg5").feature("surf2").create("sel1", "Selection");
    model.result("pg5").feature("surf2").feature("sel1").selection().named("geom1_csel1_bnd");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").set("edges", false);
    model.result("pg7").run();
    model.result("pg7").feature("str1").set("titletype", "none");
    model.result("pg7").feature("str1").set("linetype", "ribbon");
    model.result("pg7").run();
    model.result("pg7").feature("str1").feature("col").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "fc.xH2O");
    model.result("pg7").feature("surf1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg7").feature("surf1").set("titletype", "custom");
    model.result("pg7").feature("surf1").set("typeintitle", false);
    model.result("pg7").feature("surf1").set("inheritplot", "str1");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().named("geom1_adjsel1");
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").feature().copy("surf2", "pg5/surf2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").run();
    model.result().create("pg25", "PlotGroup3D");
    model.result("pg25").run();
    model.result("pg25").label("\u51b7\u5374\u901a\u9053\u6e29\u5ea6");
    model.result("pg25").selection().geom("geom1", 3);
    model.result("pg25").selection().named("geom1_boxsel9");
    model.result("pg25").create("vol1", "Volume");
    model.result("pg25").feature("vol1").set("expr", "T");
    model.result("pg25").feature("vol1").set("descr", "\u6e29\u5ea6");
    model.result("pg25").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg25").run();
    model.result().create("pg26", "PlotGroup3D");
    model.result("pg26").run();
    model.result("pg26").label("\u6c34\u6d3b\u5ea6");
    model.result("pg26").create("surf1", "Surface");
    model.result("pg26").feature("surf1").set("expr", "fc.aw");
    model.result("pg26").feature("surf1").set("descr", "\u6c34\u6d3b\u5ea6\uff08\u76f8\u5bf9\u6e7f\u5ea6\uff09");
    model.result("pg26").run();
    model.result("pg5").run();

    model.title("\u975e\u7b49\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60");

    model
         .description("\u672c\u6559\u7a0b\u6a21\u62df\u805a\u5408\u7269\u7535\u89e3\u8d28\u819c (PEM) \u71c3\u6599\u7535\u6c60\u4e2d\u7684\u76f8\u4e92\u8026\u5408\u7535\u5316\u5b66\u53cd\u5e94\u3001\u7535\u8377\u548c\u7269\u8d28\u4f20\u9012\u4ee5\u53ca\u4f20\u70ed\u3002\u5bf9\u4e8e\u6c14\u4f53\u6d41\u573a\uff0c\u6c22\u9633\u6781\u4fa7\u91c7\u7528\u76f4\u901a\u9053\uff0c\u7a7a\u6c14\u9634\u6781\u4fa7\u5219\u91c7\u7528\u7f51\u72b6\u7ed3\u6784\u3002\u7535\u6c60\u901a\u8fc7\u5728\u5355\u72ec\u901a\u9053\u4e2d\u6d41\u52a8\u7684\u51b7\u5374\u6db2\u8fdb\u884c\u51b7\u5374\u3002\u672c\u4f8b\u5728\u4e0a\u4e0b\u8fb9\u754c\u4f7f\u7528\u5468\u671f\u6027\u6e29\u5ea6\u8fb9\u754c\u6761\u4ef6\uff0c\u4ece\u800c\u6a21\u62df\u5806\u53e0\u7535\u6c60\u914d\u7f6e\u3002\u6b64\u5916\uff0c\u6a21\u578b\u4e2d\u8fd8\u5305\u542b\u6c34\u901a\u8fc7\u819c\u7684\u7535\u6e17\u4f20\u8f93\uff08\u66f3\u529b\uff09\u548c\u6e17\u900f\u4f5c\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("nonisothermal_pem_fuel_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
