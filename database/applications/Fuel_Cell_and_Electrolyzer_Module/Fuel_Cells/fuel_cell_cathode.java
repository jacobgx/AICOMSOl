/*
 * fuel_cell_cathode.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:07 by COMSOL 6.3.0.290. */
public class fuel_cell_cathode {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "70[degC]", "\u6e29\u5ea6");
    model.param().set("perm", "1e-13[m^2]", "\u6e17\u900f\u7387");
    model.param()
         .set("eps_l", "0.3", "\u591a\u5b54\u7535\u6781\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("eps_gas", "0.4", "\u591a\u5b54\u7535\u6781\u4e2d\u7684\u6c14\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("sigma_s", "164[S/m]", "\u7535\u6781\u7535\u5bfc\u7387");
    model.param().set("E_cell", "1[V]", "\u7535\u6c60\u7535\u538b");
    model.param()
         .set("i0_ref_H2", "1e4[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9633\u6781");
    model.param()
         .set("i0_ref_O2", "1e-3[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u9634\u6781");
    model.param().set("Av", "1e9[1/m]", "\u6bd4\u9762\u79ef");
    model.param().set("RH", "90[%]", "\u76f8\u5bf9\u6e7f\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u819c");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1.5, 1.5, 0.075});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").label("\u9634\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, 0, 0.075});
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9633\u6781\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{0, 0, -0.075});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.15);
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new double[]{1.5, 1.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("fc").create("h2gde1", "H2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("h2gde1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 3);
    model.component("comp1").physics("fc").feature("o2gde1").selection().named("geom1_blk2_dom");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("MembraneCrossover", "MembraneCrossover", "Membrane crossover");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PolymerElectrolyteWaterTransport", "PolymerElectrolyteWaterTransport", "Polymer electrolyte water transport");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("PolymerElectrolyteWaterAbsorptionDesorption", "PolymerElectrolyteWaterAbsorptionDesorption", "Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat1").label("Nafion\u00ae, EW 1100, Vapor Equilibrated, Protonated");
    model.component("comp1").material("mat1").set("family", "glass");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]", "0", "0", "0", "(0.177+3.7e-3*lambda)[W/m/K]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Ex situ measurements of through-plane thermal conductivities\nin a polymer electrolyte fuel cell,\nO Burheim, P Vie, J Pharoah, S. Kjelstrup, Journal of Power Sources 195 (2010) 249\u2013256");
    model.component("comp1").material("mat1").propertyGroup("def").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat1").propertyGroup("def").descr("lambda", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("source", "file");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importedname", "nafion_1100_conductivity_vapor_eq.csv");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("importeddim", "2D");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnametable", new String[][]{{"sigma_vs_T_and_RH", "1"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 3);
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "sigma_vs_T_and_RH"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/cm"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1", "1"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("sourcetype", "model");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))", "0", "0", "0", "sigma_vs_T_and_RH((T-0[degC])[1/K],min(max(phi,0.1),1))"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Yoshitsugu Sone et al 1996 J. Electrochem. Soc. 143 1254\n(Conductivity data refers to the \"E-form\" values, Figure 5a and 5b)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").label("Membrane crossover");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("Psi_H2", "(2.2e-11*f+2.9e-12)[mol/cm/s/bar]*exp(21[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_H2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041\n");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("Psi_O2", "(1.9e-11*f+1.1e-12)[mol/cm/s/bar]*exp(22[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_O2", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("Psi_N2", "Psi_O2");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .setPropertyInfo("Psi_N2", "Approximated as equal to oxygen value (no reference).");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("lambda", "pewt.lambda");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").descr("lambda", "Water uptake");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("EW", "1100[g/mol]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("MembraneCrossover").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .label("Polymer electrolyte water transport");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("funcname", "lambda");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
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
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport").func("int1")
         .set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("alpha", "max(c0*Dmu/(R_const*T*(1-x0)),1e-9)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("alpha", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("xi", "if(lambda>1,1,lambda)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .setPropertyInfo("xi", "Adam Z. Weber and John Newman 2004 J. Electrochem. Soc. 151 A311\n\nWater uptake (lambda) from  Thomas A. Zawodzinski Jr. et al 1993 J. Electrochem. Soc. 140 1041");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("lambda", "lambda(phi)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("lambda", "Water uptake");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("EW", "1100[g/mol]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("EW", "Polymer electrolyte equivalent weight");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Vm", "EW/2[g/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Vm", "Partial molar volume of dry polymer");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("V0", "18[g/mol]/1000[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("V0", "Water partial molar volume");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("f", "lambda*V0/(Vm+lambda*V0)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("f", "Water volume fraction in polymer");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("Dmu", "1.8e-5[cm^2/s]*f*exp(20[kJ/mol]/R_const*(1/30[degC]-1/T))");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("Dmu", "Water diffusion coefficient");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("c0", "lambda/(V0*lambda+Vm)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("c0", "Water concentration");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .set("x0", "lambda/(lambda+1)");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .descr("x0", "Water mole fraction");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterTransport")
         .addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .label("Polymer electrolyte water absorption-desorption");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .set("k_abs_dsp", "1.04e-7*exp(4.48*max(min(phi,0.85),0.25))[mol/cm^2/s]");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .setPropertyInfo("k_abs_dsp", "Kientiz, Yamada, Nonoyama, Weber,\nJournal of Fuel Cell Science and Technology, Feb 2011, Vol. 8,  Article Number 011013");
    model.component("comp1").material("mat1").propertyGroup("PolymerElectrolyteWaterAbsorptionDesorption")
         .addInput("relativehumidity");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").physics("fc").feature("mem1").set("aw_mem", "RH");
    model.component("comp1").physics("fc").feature("h2gasph1").set("MixtureSpecification", "HumidifiedMixture");
    model.component("comp1").physics("fc").feature("h2gasph1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("h2gasph1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("o2gasph1").set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("o2gasph1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("h2gde1")
         .set("sigmas", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("fc").feature("h2gde1").set("epsl", "eps_l");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1")
         .set("ElectrodeKinetics", "LinButlerVolmer");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_ref_H2");
    model.component("comp1").physics("fc").feature("h2gde1").feature("h2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"sigma_s", "0", "0", "0", "sigma_s", "0", "0", "0", "sigma_s"});
    model.component("comp1").physics("fc").feature("o2gde1").set("epsl", "eps_l");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_ref_O2");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().set(3);
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().set(10);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_cell");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(10, 14);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("geom1_blk2_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").selection().set(10, 11);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size3").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size3").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size3").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

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
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").selection().set(3);
    model.component("comp1").probe("bnd1").set("expr", "fc.nIs");
    model.component("comp1").probe("bnd1").set("descr", "\u6cd5\u5411\u7535\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").probe("bnd1").set("expr", "-fc.nIs");
    model.component("comp1").probe("bnd1").set("unit", "A/cm^2");
    model.component("comp1").probe("bnd1").set("descractive", true);
    model.component("comp1").probe("bnd1").set("descr", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1,-0.1,0.5)", 0);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").label("\u6781\u5316\u66f2\u7ebf");
    model.result("pg3").set("switchxy", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();

    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", true);
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasMixtureDarcy", true);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("RH_hum", "RH");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_wp1_bnd");
    model.component("comp1").physics("fc").feature("o2gde1").set("epsg", "eps_gas");
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("kappag", new String[]{"perm", "0", "0", "0", "perm", "0", "0", "0", "perm"});

    model.result().table().duplicate("tbl2", "tbl1");
    model.result().table("tbl2").label("\u65e0\u9650\u5236\u6c27\u6c14\u76f8\u4f20\u9012");

    model.sol().remove("sol1");
    model.sol().remove("sol2");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 6, 0);
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg4").create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", new String[]{"fc.phis"});
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"fc.Isx", "fc.Isy", "fc.Isz"});
    model.result("pg4").feature("arwv1").set("arrowbase", "center");
    model.result("pg4").feature("arwv1").set("color", "gray");
    model.result("pg4").feature("arwv1").create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg4").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 6, 0);
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", new String[]{"fc.phil"});
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"fc.Ilx", "fc.Ily", "fc.Ilz"});
    model.result("pg5").feature("arwv1").set("arrowbase", "center");
    model.result("pg5").feature("arwv1").set("color", "gray");
    model.result("pg5").feature("arwv1").create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg5").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 6, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, O2, \u6d41\u7ebf (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg6").feature("str1").set("posmethod", "start");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col", "Color");
    model.result("pg6").feature("str1").feature("col").set("expr", "fc.xO2");
    model.result("pg6").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 6, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, O2, \u8868\u9762 (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 6, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy", "fc.tfluxH2Oz"});
    model.result("pg8").feature("str1").set("posmethod", "start");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg8").feature("str1").create("col", "Color");
    model.result("pg8").feature("str1").feature("col").set("expr", "fc.xH2O");
    model.result("pg8").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg8").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 6, 0);
    model.result("pg9").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (fc)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").setIndex("looplevel", 6, 0);
    model.result("pg10").label("\u6469\u5c14\u5206\u6570, N2, \u6d41\u7ebf (fc)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y", "fc.tfluxN2z"});
    model.result("pg10").feature("str1").set("posmethod", "start");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result("pg10").feature("str1").create("col", "Color");
    model.result("pg10").feature("str1").feature("col").set("expr", "fc.xN2");
    model.result("pg10").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg10").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").setIndex("looplevel", 6, 0);
    model.result("pg11").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset1");
    model.result("pg12").setIndex("looplevel", 6, 0);
    model.result("pg12").label("\u538b\u529b (fc)");
    model.result("pg12").create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").set("expr", new String[]{"fc.p"});
    model.result("pg12").create("str1", "Streamline");
    model.result("pg12").feature("str1").set("expr", new String[]{"fc.u", "fc.v", "fc.w"});
    model.result("pg12").feature("str1").set("posmethod", "start");
    model.result("pg12").feature("str1").set("pointtype", "arrow");
    model.result("pg12").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("str1").set("color", "gray");
    model.result("pg4").run();
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1")
         .label("\u63a2\u9488\u8868\u56fe\uff1a\u53d7\u9650\u6c27\u6c14\u76f8\u4f20\u9012");
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53d7\u9650\u6c27\u6c14\u76f8\u4f20\u9012", 0);
    model.result("pg3").feature().duplicate("tblp2", "tblp1");
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2")
         .label("\u63a2\u9488\u8868\u56fe\uff1a\u65e0\u9650\u5236\u6c27\u6c14\u76f8\u4f20\u9012");
    model.result("pg3").feature("tblp2").set("table", "tbl2");
    model.result("pg3").feature("tblp2").setIndex("legends", "\u65e0\u9650\u5236\u6c27\u6c14\u76f8\u4f20\u9012", 0);
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 4, 0);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 4, 0);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", 4, 0);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u9634\u6781\u8fc7\u7535\u4f4d");
    model.result("pg13").setIndex("looplevel", 4, 0);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "fc.eta_o2gder1");
    model.result("pg13").feature("surf1").set("descr", "\u8fc7\u7535\u4f4d");
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u9634\u6781\u5c40\u90e8\u4f53\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg14").setIndex("looplevel", 4, 0);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "fc.iv_o2gder1");
    model.result("pg14").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u6e90");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result("pg15").label("\u9633\u6781\u8fb9\u754c\u5904\u7684\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg15").setIndex("looplevel", 4, 0);
    model.result("pg15").selection().geom("geom1", 2);
    model.result("pg15").selection().geom("geom1", 2);
    model.result("pg15").selection().set(6);
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "fc.nIl");
    model.result("pg15").feature("surf1").set("descr", "\u6cd5\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg15").feature("surf1").set("expr", "abs(fc.nIl)");
    model.result("pg15").feature("surf1").set("descractive", true);
    model.result("pg15").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg15").run();

    model
         .title("\u71c3\u6599\u7535\u6c60\u9634\u6781\u7684\u8d28\u91cf\u4f20\u9012\u548c\u7535\u5316\u5b66\u53cd\u5e94");

    model
         .description("\u672c\u4f8b\u662f\u901a\u7528\u71c3\u6599\u7535\u6c60\u9634\u6781\u7684\u7a33\u6001\u4e09\u7ef4\u6a21\u578b\uff0c\u63cf\u8ff0\u6c27\u3001\u6c34\u548c\u6c2e\u7684\u8d28\u91cf\u5206\u6570\u5206\u5e03\u53ca\u7535\u6d41\u5206\u5e03\u3002");

    model.label("fuel_cell_cathode.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
