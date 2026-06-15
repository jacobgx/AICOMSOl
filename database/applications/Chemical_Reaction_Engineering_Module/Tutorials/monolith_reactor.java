/*
 * monolith_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:30 by COMSOL 6.3.0.290. */
public class monolith_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("spf", "StationaryPlugflow");
    model.study("std1").feature("spf").set("solnum", "auto");
    model.study("std1").feature("spf").set("notsolnum", "auto");
    model.study("std1").feature("spf").set("outputmap", new String[]{});
    model.study("std1").feature("spf").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m1", "0.086[kg/s]", "Mass flow rate of exhaust gas, case 1 (low load)");
    model.param().set("T1", "523[K]", "Temperature at SCR inlet case 1 (low load)");
    model.param().set("y1", "300e-6", "Molar fraction of NO in, case 1 (low load)");
    model.param().set("m2", "0.11[kg/s]", "Mass flow rate of exhaust gas, case 2 (intermediate load)");
    model.param().set("T2", "623[K]", "Temperature at SCR inlet case 2 (intermediate load)");
    model.param().set("y2", "800e-6", "Molar fraction of NO in, case 2 (intermediate load)");
    model.param().set("m3", "0.16[kg/s]", "Mass flow rate of exhaust gas, case 3 (high load)");
    model.param().set("T3", "703[K]", "Temperature at SCR inlet case 3 (high load)");
    model.param().set("y3", "1300e-6", "Molar fraction of NO in, case 3 (high load)");
    model.param().label("Parameters: Engine Load Cases");
    model.param().create("par2");
    model.param("par2").label("Cases");
    model.param("par2").set("m", "m2");
    model.param("par2").descr("m", "Mass flow rate (case 2 is default)");
    model.param("par2").set("y", "y2");
    model.param("par2").descr("y", "NO molar fraction (case 2 is default)");
    model.param("par2").set("T", "T2");
    model.param("par2").descr("T", "Inlet temperature (case 2 is default)");
    model.param("par2").paramCase().create("case1");
    model.param("par2").paramCase("case1").label("Low Load");
    model.param("par2").paramCase("case1").set("m", "m1");
    model.param("par2").paramCase("case1").set("y", "y1");
    model.param("par2").paramCase("case1").set("T", "T1");
    model.param("par2").paramCase().create("case2");
    model.param("par2").paramCase("case2").label("Intermediate Load");
    model.param("par2").paramCase().create("case3");
    model.param("par2").paramCase("case3").label("High Load");
    model.param("par2").paramCase("case3").set("m", "m3");
    model.param("par2").paramCase("case3").set("y", "y3");
    model.param("par2").paramCase("case3").set("T", "T3");
    model.param().create("par3");
    model.param("par3").label("Parameters: Temperature and Monolith Parameters");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("T_gas_in", "T", "Temperature of inlet exhaust gas");
    model.param("par3").set("d_cat", "0.32[m]", "Monolith diameter");
    model.param("par3").set("l_SCR", "0.4[m]", "SCR monolith length");
    model.param("par3").set("l_ASC", "0.06[m]", "ASC monolith length");
    model.param("par3").set("V_SCR", "pi*(d_cat/2)^2*l_SCR", "SCR monolith volume");
    model.param("par3").set("V_ASC", "pi*(d_cat/2)^2*l_ASC", "ASC monolith volume");
    model.param().create("par4");
    model.param("par4").label("Parameters: Flow and Composition");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("m_exhaust", "m", "Engine outlet exhaust flow rate at T_gas_in");
    model.param("par4").set("yNO", "y", "Molar fraction of NO at reactor inlet");
    model.param("par4").set("NO2ratio", "0.1", "NO2 to NO ratio in NOx at reactor inlet");
    model.param("par4").set("yNO2", "yNO*NO2ratio", "Molar fraction of NO2 at reactor inlet");
    model.param("par4").set("yO2", "0.07", "Molar fraction of O2 at reactor inlet");
    model.param("par4").set("yH2O", "0.1", "Molar fraction of H2O at reactor inlet");
    model.param("par4").set("yN2", "1-yNO-yO2-yH2O-yNO2", "Molar fraction of N2 at reactor inlet");
    model.param("par4")
         .set("n_exhaust", "m_exhaust/(MN2*yN2+MO2*yO2+MH2O*yH2O+MNO*yNO+MNO2*yNO2)", "Molar flow rate of exhaust gas entering the reactor");
    model.param("par4")
         .set("v_exhaust", "n_exhaust*R_const*T_gas_in/1[atm]", "Volumetric flow rate of exhaust gas entering the reactor");
    model.param("par4").set("FH2O_in", "yH2O*n_exhaust", "Molar flow rate of H2O entering the reactor");
    model.param("par4").set("FN2_in", "yN2*n_exhaust", "Molar flow rate of N2 entering the reactor");
    model.param("par4").set("FO2_in", "yO2*n_exhaust", "Molar flow rate of O2 entering the reactor");
    model.param("par4").set("FNO_in", "yNO*n_exhaust", "Molar flow rate of NO entering the reactor");
    model.param("par4").set("FNO2_in", "NO2ratio*FNO_in", "Molar flow rate of NO2 entering the reactor");
    model.param("par4").set("cH2O_in", "FH2O_in/v_exhaust", "Concentration of H2O in gas entering the reactor");
    model.param("par4").set("cN2_in", "FN2_in/v_exhaust", "Concentration of N2 in gas entering the reactor");
    model.param("par4").set("cO2_in", "FO2_in/v_exhaust", "Concentration of O2 in gas entering the reactor");
    model.param("par4").set("cNO_in", "FNO_in/v_exhaust", "Concentration of NO in gas entering the reactor");
    model.param("par4").set("cNO2_in", "FNO2_in/v_exhaust", "Concentration of NO2 in gas entering the reactor");
    model.param("par4").set("cNOx_in", "cNO_in+cNO2_in", "Concentration of NOx in gas entering the reactor");
    model.param("par4").set("ANR", "1.3", "Ammonia to NOx ratio in gas entering the reactor");
    model.param("par4").set("FNH3_in", "ANR*(FNO_in+FNO2_in)", "Molar flow rate of NH3entering the reactor");
    model.param("par4").set("cNH3_in", "FNH3_in/v_tot_in", "Concentration of NH3 in gas entering the reactor");
    model.param("par4").set("mNH3_in", "FNH3_in*MNH3", "Mass flow rate of NH3 entering the reactor");
    model.param("par4").set("m_tot_in", "m_exhaust + mNH3_in", "Total mass flow rate of gas entering the reactor");
    model.param("par4")
         .set("v_NH3_in", "FNH3_in*R_const*T_gas_in/1[atm]", "Volumetric flow rate of NH3entering the reactor");
    model.param("par4")
         .set("v_tot_in", "v_exhaust + v_NH3_in", "Total volumetric flow rate of gas entering the reactor");
    model.param("par4")
         .set("mNOX_in", "(cNO_in*MNO+cNO2_in*MNO2)*v_tot_in", "Mass flow rate of NOx entering the reactor");
    model.param("par4").set("MN2", "0.028013[kg/mol]", "Molar mass of N2");
    model.param("par4").set("MH2O", "0.018015[kg/mol]", "Molar mass of H2O");
    model.param("par4").set("MO2", "0.031999[kg/mol]", "Molar mass of O2");
    model.param("par4").set("MNO", "0.030006[kg/mol]", "Molar mass of NO");
    model.param("par4").set("MNO2", "0.046006[kg/mol]", "Molar mass of NO2");
    model.param("par4").set("MNH3", "0.017031[kg/mol]", "Molar mass of NH3");
    model.param().create("par5");
    model.param("par5").label("Parameters: Reaction Kinetics");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5").set("E1_SCR", "65000[J/mol]", "Apparent activation energy for reaction (1) Standard SCR");
    model.param("par5")
         .set("A1_SCR", "3.0556E8[m^6/s/mol^2]", "Apparent frequency factor for reaction (1) Standard SCR");
    model.param("par5").set("E2_SCR", "25830[J/mol]", "Apparent activation energy for reaction (2) Fast SCR");
    model.param("par5")
         .set("A2_SCR", "2.6111E7[m^6/s/mol^2]", "Apparent frequency factor for reaction  (2) Fast SCR");
    model.param("par5").set("E3_SCR", "69000[J/mol]", "Apparent activation energy for reaction (3) NO2 SCR");
    model.param("par5")
         .set("A3_SCR", "8.4722E8[m^3/(s*mol)]", "Apparent frequency factor for reaction  (3) NO2 SCR");
    model.param("par5")
         .set("E4", "12400[J/mol]", "Apparent forward activation energy for reaction (4) Oxidation of NO to NO2");
    model.param("par5")
         .set("A4", "188.89[m^6/s/mol^2]", "Apparent forward frequency factor for reaction (4) Oxidation of NO to NO2");
    model.param("par5")
         .set("E5_SCR", "1.2317E5[J/mol]", "Apparent activation energy for reaction (5) Oxidation of NH3 to N2");
    model.param("par5")
         .set("A5_SCR", "1.4444E9[m^3/(s*mol)]", "Apparent frequency factor for reaction  (5) Oxidation of NH3 to N2");
    model.param("par5")
         .set("E6_SCR", "1.75E5[J/mol]", "Apparent activation energy for reaction (6) Undesired NO formation");
    model.param("par5")
         .set("A6_SCR", "8E19[m^3/(s*mol)]", "Apparent frequency factor for reaction  (6) Undesired NO formation");
    model.param("par5")
         .set("E1_ASC", "96138[J/mol]", "Apparent activation energy for reaction (1) Desired NH3 oxidation in ASC");
    model.param("par5")
         .set("A1_ASC", "2.2222E9[m^3/(s*mol)]", "Apparent frequency factor for reaction (1) Desired NH3 oxidation in ASC");
    model.param("par5")
         .set("E2_ASC", "1.125E5[J/mol]", "Apparent activation energy for reaction (2) Undesired NH3 oxidation in ASC");
    model.param("par5")
         .set("A2_ASC", "1.6E17[m^3/(s*mol)]", "Apparent frequency factor for reaction (2) Undesired NH3 oxidation in ASC");
    model.param("par5")
         .set("E4_ASC", "95657[J/mol]", "Apparent activation energy for reaction (4) Undesired NO2 formation in ASC");
    model.param("par5")
         .set("A4_ASC", "6.667E8[m^3/(s*mol)]", "Apparent frequency factor for reaction (4) Undesired NO2 formation in ASC");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("Variables: Reaction Kinetics SCR");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("K_NH3", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/R_const*(1/523[K]-1/re.T))", "Equilibrium constant of NH3 adsorption");
    model.component("comp1").variable("var1")
         .set("G", "re.T*(1+K1*re.c_NO+K2*re.c_H2O)^2*(1+K3*re.c_NH3)^2*(1+K4*re.c_O2)^2[1/(K)]", "Inhibition effects from adsorbed species");
    model.component("comp1").variable("var1")
         .set("K1", "21*exp(-(-7.8e3[J/mol])/(R_const*re.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var1")
         .set("K2", "1.6*exp(-(-7.8e3[J/mol])/(R_const*re.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var1")
         .set("K3", "105*exp(-(3.0e4[J/mol])/(R_const*re.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var1")
         .set("K4", "21*exp(-(0[J/mol])/(R_const*re.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("Variables: Reaction Kinetics ASC");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("K_NH3_ASC", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/R_const*(1/523[K]-1/re2.T))", "Equilibrium constant of NH3 adsorption");
    model.component("comp1").variable("var2")
         .set("G_ASC", "re2.T*(1+K1_ASC*re2.c_NO+K2_ASC*re2.c_H2O)^2*(1+K3_ASC*re2.c_NH3)^2*(1+K4_ASC*re2.c_O2)^2[1/(K)]", "Inhibition effects from adsorbed species");
    model.component("comp1").variable("var2")
         .set("K1_ASC", "21*exp(-(-7.8e3[J/mol])/(R_const*re2.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var2")
         .set("K2_ASC", "1.6*exp(-(-7.8e3[J/mol])/(R_const*re2.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var2")
         .set("K3_ASC", "105*exp(-(3.0e4[J/mol])/(R_const*re2.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable("var2")
         .set("K4_ASC", "21*exp(-(0[J/mol])/(R_const*re2.T))[m^3/mol]", "Inhibition term");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("Variables: Postprocessing");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("X_NOx_SCR", "(FNO_in+FNO2_in-re.F_NO-re.F_NO2)/(FNO_in+FNO2_in)", "NOx Conversion in SCR");
    model.component("comp1").variable("var3")
         .set("X_NOx_tot", "(FNO_in+FNO2_in-re2.F_NO-re2.F_NO2)/(FNO_in+FNO2_in)", "Total NOx Conversion in System (SCR + ASC)");
    model.component("comp1").variable("var3")
         .set("X_NH3_SCR", "(FNH3_in-re.F_NH3)/FNH3_in", "NH3 Conversion in SCR");
    model.component("comp1").variable("var3")
         .set("X_NH3_tot", "(FNH3_in-re2.F_NH3)/FNH3_in", "NH3 Conversion in SCR");
    model.component("comp1").variable("var3")
         .set("yNH3_SCR", "re.c_NH3/re.csum", "Molar fraction of NH3 in SCR catalytic bed");
    model.component("comp1").variable("var3")
         .set("yNOx_SCR", "(re.c_NO+re.c_NO2)/re.csum", "Molar fraction of NOx in SCR catalytic bed");
    model.component("comp1").variable("var3").set("yNH3_ASC", "re2.c_NH3/re2.csum", "Molar fraction of NH3 in ASC");
    model.component("comp1").variable("var3")
         .set("yNOx_ASC", "(re2.c_NO+re2.c_NO2)/re2.csum", "Molar fraction of NOx in ASC");

    model.thermodynamics().feature().create("spec1", "UserDefinedSpecies");
    model.thermodynamics().feature("spec1").set("Species", "N2O4");
    model.thermodynamics().feature("spec1").set("SpeciesState", "GasLiquid");
    model.thermodynamics().feature("spec1").set("CAS", "10544-72-6");
    model.thermodynamics().feature("spec1").set("ChemicalFormula", "N2O4");
    model.thermodynamics().feature("spec1").set("StructureFormula", "");
    model.thermodynamics().feature("spec1")
         .set("IdealGas_HeatCapacityCp", new String[][]{{"151.4994", "22.8199439173", "0.248101835092", "-0.000129016784375", "-2.07976512868e-07", "223.9986"}, 
         {"223.9986", "17.3772455746", "0.320995572054", "-0.000454437286832", "2.7628321384e-07", "310.9977"}, 
         {"310.9977", "7.55672923384", "0.415727941528", "-0.000759045229188", "6.02767881686e-07", "325.4976"}, 
         {"325.4976", "23.1617866623", "0.271901466969", "-0.000317178762095", "1.50264322937e-07", "557.4951"}, 
         {"557.4951", "41.600968157", "0.172676299817", "-0.000139194835147", "4.38454940552e-08", "803.9924"}, 
         {"803.9924", "59.0032311688", "0.107741868801", "-5.84298540152e-05", "1.036052532e-08", "1499.985"}});
    model.thermodynamics().feature("spec1")
         .set("Saturated_Liquid_Density", new String[][]{{"261.9", "23723.3913174", "-54.5872209623", "0.161934291432", "-0.000237308754858", "343.1466"}});
    model.thermodynamics().feature("spec1")
         .set("Vapor_ThermalConductivity", new String[][]{{"380.0038", "29.7807267369", "-0.219234326434", "0.000540095868453", "-4.44593410339e-07", "400"}});
    model.thermodynamics().feature("spec1")
         .set("VaporViscosity", new String[][]{{"300.003", "1.63115957629e-05", "-1.40930105836e-07", "6.48383663156e-10", "-6.82053838304e-13", "335.00235"}, 
         {"335.00235", "-9.55153890719e-06", "9.06784306719e-08", "-4.29802526723e-11", "5.86563034883e-15", "558.69014735"}, 
         {"558.69014735", "-1.14075878119e-05", "1.00644861511e-07", "-6.08191762446e-11", "1.65089321605e-14", "999.99"}});
    model.thermodynamics().feature("spec1")
         .set("GasLiquidConstant", new String[][]{{"Absolute entropy", "304.32", "J/mol/K"}, 
         {"Critical compressibility factor", "0.233", "1"}, 
         {"Critical pressure", "1.0031e+07", "Pa"}, 
         {"Critical temperature", "431.15", "K"}, 
         {"Critical volume", "8.249e-05", "m^3/mol"}, 
         {"Dipole moment", "0", "C\u00b7m"}, 
         {"Heat of combustion (high heating value)", "0", "J/mol"}, 
         {"Lennard-Jones diameter (potential characteristic length)", "0", "m"}, 
         {"Lennard-Jones energy (potential energy minimum)", "0", "K"}, 
         {"Liquid volume at normal boiling point", "0", "m^3/mol"}, 
         {"Molecular mass", "92.011", "g/mol"}, 
         {"Normal boiling point temperature", "294.3", "K"}, 
         {"Standard enthalpy of formation", "9163", "J/mol"}, 
         {"Standard molar enthalpy of formation (ion solution)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Liquid)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Solid)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Vapor)", "0", "J/mol"}, 
         {"Standard molar entropy (ion solution)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Liquid)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Solid)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Vapor)", "0", "J/mol/K"}, 
         {"van der Waals area", "0", "m^2/mol"}, 
         {"van der Waals volume", "0", "m^3/mol"}});
    model.thermodynamics().feature("spec1")
         .set("GasLiquidModelOptions", new String[][]{{"Acentric factor", "0.85327", "1"}, 
         {"Chao-Seader acentric factor", "0", "1"}, 
         {"Chao-Seader liquid volume", "0", "m^3/mol"}, 
         {"Chao-Seader solubility parameter", "0", "J^0.5/m^1.5"}, 
         {"COSTALD acentric factor", "0", "1"}, 
         {"COSTALD volume parameter", "0", "m^3/mol"}, 
         {"Fuller diffusion volume", "13.1", "cm^3"}, 
         {"Parachor", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) parameter L", "0", "1"}, 
         {"Peng-Robinson (Twu) parameter M", "0", "1"}, 
         {"Peng-Robinson (Twu) parameter N", "0", "1"}, 
         {"Rackett parameter", "0", "1"}, 
         {"Solubility parameter", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer delta parameter", "0", "1"}, 
         {"UNIQUAC Q parameter", "0", "1"}, 
         {"UNIQUAC R parameter", "0", "1"}, 
         {"Wilke-Chang association parameter", "1", "1"}, 
         {"Wilson volume parameter", "0", "m^3/mol"}});
    model.thermodynamics().feature().create("spec2", "UserDefinedSpecies");
    model.thermodynamics().feature("spec2").set("Species", "NO2");
    model.thermodynamics().feature("spec2").set("SpeciesState", "GasLiquid");
    model.thermodynamics().feature("spec2").set("CAS", "10102-44-0");
    model.thermodynamics().feature("spec2").set("ChemicalFormula", "NO2");
    model.thermodynamics().feature("spec2").set("StructureFormula", "");
    model.thermodynamics().feature("spec2")
         .set("IdealGas_HeatCapacityCp", new String[][]{{"165.9993", "35.8254425805", "-0.0567293232053", "0.000356001767705", "-4.78004544541e-07", "223.9986"}, 
         {"223.9986", "32.4036320949", "-0.0109012177757", "0.00015141073262", "-1.73551696657e-07", "310.9977"}, 
         {"310.9977", "27.9650785955", "0.0319147274807", "1.37378647707e-05", "-2.59912614739e-08", "470.49"}, 
         {"470.49", "24.2998355133", "0.055285232749", "-3.5934194648e-05", "9.20001143717e-09", "963.4907"}});
    model.thermodynamics().feature("spec2")
         .set("Saturated_Liquid_Density", new String[][]{{"261.9026", "47780.8048854", "-96.0025964011", "0.26232127372", "-0.000423346367284", "327.9932"}, 
         {"327.9932", "103808.217268", "-608.459065462", "1.82472094865", "-0.00201118383356", "373.7483"}, 
         {"373.7483", "350445.755442", "-2588.16737322", "7.121623457", "-0.00673531012243", "392.3892"}, 
         {"392.3892", "1568690.00637", "-11902.2176906", "30.8583886824", "-0.0268996140205", "407.6409"}, 
         {"407.6409", "2741379.02684", "-20532.5270178", "52.0297415255", "-0.0442117081071", "414.4194"}, 
         {"414.4194", "23901405.4952", "-173710.875824", "421.651310414", "-0.341512436861", "421.1979"}, 
         {"421.1979", "-24025981.4299", "167653.980875", "-388.81060475", "0.299881956897", "424.5872"}, 
         {"424.5872", "1171039730.24", "-8276305.55206", "19498.6460424", "-15.3132882369", "426.2818"}, 
         {"426.2818", "-3800925140.26", "26714385.1401", "-62584.8271252", "48.872322602", "427.9764"}, 
         {"427.9764", "17346687004.3", "-121524715.039", "283787.3097", "-220.90278402", "429.6711"}, 
         {"429.6711", "-22629305820.2", "157591051.588", "-365815.995308", "283.051135623", "431.3657"}});
    model.thermodynamics().feature("spec2")
         .set("Vapor_ThermalConductivity", new String[][]{{"420.0042", "-15.8305048878", "0.111940151375", "-0.000264111354853", "2.08523312366e-07", "425.804058"}, 
         {"425.804058", "0.142692610769", "-0.000598918170081", "1.86430549483e-07", "1.62236779811e-09", "483.75782068"}, 
         {"483.75782068", "0.568403779574", "-0.00323894475326", "5.64376161541e-06", "-2.13800625176e-09", "530.260979312"}, 
         {"530.260979312", "4.28090087709", "-0.0242427367105", "4.52540531072e-05", "-2.70378782249e-08", "605.722503968"}, 
         {"605.722503968", "3.54280559853", "-0.0205871257316", "3.92189281862e-05", "-2.37167067416e-08", "651.762146706"}, 
         {"651.762146706", "-8.52192261186", "0.034945686068", "-4.59851789874e-05", "1.98595721224e-08", "744.87380587"}, 
         {"744.87380587", "-6.69104780557", "0.0275717867906", "-3.60856507461e-05", "1.54295025977e-08", "791.067898284"}});
    model.thermodynamics().feature("spec2")
         .set("VaporViscosity", new String[][]{{"300.003", "0.000239189260846", "-2.40914442054e-06", "8.25332107135e-09", "-9.09949292819e-12", "307.0029"}, 
         {"307.0029", "-1.89810139051e-05", "1.13668309412e-07", "3.57676542572e-11", "-1.77152219467e-13", "335.0023"}, 
         {"335.0023", "-3.28500933225e-05", "2.37868167897e-07", "-3.34975646566e-10", "1.91744054602e-13", "488.9995"}, 
         {"488.9995", "-2.44211701796e-05", "1.86156930097e-07", "-2.29226585881e-10", "1.19658731621e-13", "579.9978"}, 
         {"579.9978", "-1.03829719748e-05", "1.13545284649e-07", "-1.04033618859e-10", "4.77084776588e-14", "684.9958"}, 
         {"684.9958", "1.95668473445e-06", "5.95026611349e-08", "-2.51387942283e-11", "9.31660010734e-15", "999.99"}});
    model.thermodynamics().feature("spec2")
         .set("GasLiquidConstant", new String[][]{{"Absolute entropy", "239.92", "J/mol/K"}, 
         {"Critical compressibility factor", "0.233", "1"}, 
         {"Critical pressure", "10132500", "Pa"}, 
         {"Critical temperature", "431.15", "K"}, 
         {"Critical volume", "8.249e-05", "m^3/mol"}, 
         {"Dipole moment", "0", "C\u00b7m"}, 
         {"Heat of combustion (high heating value)", "0", "J/mol"}, 
         {"Lennard-Jones diameter (potential characteristic length)", "0", "m"}, 
         {"Lennard-Jones energy (potential energy minimum)", "0", "K"}, 
         {"Liquid volume at normal boiling point", "0", "m^3/mol"}, 
         {"Molecular mass", "46.0055", "g/mol"}, 
         {"Normal boiling point temperature", "294.15", "K"}, 
         {"Standard enthalpy of formation", "33180", "J/mol"}, 
         {"Standard molar enthalpy of formation (ion solution)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Liquid)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Solid)", "0", "J/mol"}, 
         {"Standard molar enthalpy of formation (Vapor)", "0", "J/mol"}, 
         {"Standard molar entropy (ion solution)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Liquid)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Solid)", "0", "J/mol/K"}, 
         {"Standard molar entropy (Vapor)", "0", "J/mol/K"}, 
         {"van der Waals area", "222300", "m^2/mol"}, 
         {"van der Waals volume", "1.391e-5", "m^3/mol"}});
    model.thermodynamics().feature("spec2")
         .set("GasLiquidModelOptions", new String[][]{{"Acentric factor", "0.851088", "1"}, 
         {"Chao-Seader acentric factor", "0", "1"}, 
         {"Chao-Seader liquid volume", "0", "m^3/mol"}, 
         {"Chao-Seader solubility parameter", "0", "J^0.5/m^1.5"}, 
         {"COSTALD acentric factor", "0", "1"}, 
         {"COSTALD volume parameter", "0", "m^3/mol"}, 
         {"Fuller diffusion volume", "13.1", "cm^3"}, 
         {"Parachor", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) parameter L", "0", "1"}, 
         {"Peng-Robinson (Twu) parameter M", "0", "1"}, 
         {"Peng-Robinson (Twu) parameter N", "0", "1"}, 
         {"Rackett parameter", "0", "1"}, 
         {"Solubility parameter", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer delta parameter", "0", "1"}, 
         {"UNIQUAC Q parameter", "0", "1"}, 
         {"UNIQUAC R parameter", "0", "1"}, 
         {"Wilke-Chang association parameter", "1", "1"}, 
         {"Wilson volume parameter", "0", "m^3/mol"}});
    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"N2O4", "10544-72-6", "N2O4", "UserDefined"}, {"NO2", "10102-44-0", "NO2", "UserDefined"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("Gas System 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "Built-in property package");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").label("Gas System 1");
    model.thermodynamics().feature().remove("spec1");
    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"NO2", "10102-44-0", "NO2", "UserDefined"}, 
         {"ammonia", "7664-41-7", "H3N", "COMSOL"}, 
         {"nitrogen", "7727-37-9", "N2", "COMSOL"}, 
         {"nitrogen oxide", "10102-43-9", "NO", "COMSOL"}, 
         {"oxygen", "7782-44-7", "O2", "COMSOL"}, 
         {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("Gas System 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "Built-in property package");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "4NH3+4NO+O2=>4N2+6H2O");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1")
         .set("r", "re.kf_1*re.c_NO*re.c_O2*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 3);
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "A1_SCR");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "E1_SCR");
    model.component("comp1").physics("re").feature("rch1")
         .label("(1) Standard SCR: 4 NH3 + 4 NO + O2 => 4 N2 + 6 H2O");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "2 NH3 + NO + NO2 => 2 N2 + 3 H2O");
    model.component("comp1").physics("re").feature("rch2").label("(2) Fast SCR: 2 NH3 + NO + NO2 => 2 N2 + 3 H2O");
    model.component("comp1").physics("re").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch2")
         .set("r", "re.kf_2*re.c_NO2*re.c_NO*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch2").set("Af", "A2_SCR");
    model.component("comp1").physics("re").feature("rch2").set("Ef", "E2_SCR");
    model.component("comp1").physics("re").feature("rch2").set("bulkFwdOrder", 3);
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "8 NH3 + 6 NO2 => 7 N2 + 12 H2O");
    model.component("comp1").physics("re").feature("rch3").label("(3) NO2 SCR: 8 NH3 + 6 NO2 => 7 N2 + 12 H2O");
    model.component("comp1").physics("re").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch3").set("r", "re.kf_3*re.c_NO2*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch3").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch3").set("Af", "A3_SCR");
    model.component("comp1").physics("re").feature("rch3").set("Ef", "E3_SCR");
    model.component("comp1").physics("re").feature("rch3").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re").feature("rch4").set("setKeq0", true);
    model.component("comp1").physics("re").feature("rch4").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch4").set("Af", "A4");
    model.component("comp1").physics("re").feature("rch4").set("Ef", "E4");
    model.component("comp1").physics("re").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "water", 0, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "nitrogen", 1, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "ammonia", 2, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching")
         .setIndex("PackageSpecies", "nitrogen oxide", 3, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "NO2", 4, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "oxygen", 5, 0);
    model.component("comp1").physics("re").feature("rch4").label("(4) Equilibrium: 2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re").feature("rch5")
         .label("(5) Undesired Oxidation: 4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re").feature("rch5").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch5").set("r", "re.kf_5*(re.c_NH3*re.c_O2)/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch5").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch5").set("Af", "A5_SCR");
    model.component("comp1").physics("re").feature("rch5").set("Ef", "E5_SCR");
    model.component("comp1").physics("re").feature("rch5").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re").feature("rch6")
         .label("(6) Undesired NO Formation: 4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re").feature("rch6").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch6").set("r", "re.kf_6*re.c_O2*re.c_NH3/G");
    model.component("comp1").physics("re").feature("rch6").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch6").set("Af", "A6_SCR");
    model.component("comp1").physics("re").feature("rch6").set("Ef", "E6_SCR");
    model.component("comp1").physics("re").feature("rch6").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").label("Selective Catalytic Reduction Catalyst (SCR)");
    model.component("comp1").physics("re").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re").feature("inits1").set("T0plug", "T_gas_in");
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FH2O_in", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FN2_in", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNH3_in", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNO_in", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNO2_in", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FO2_in", 5, 0);
    model.component("comp1").physics().create("re2", "ReactionEng");

    model.study("std1").feature("spf").setSolveFor("/physics/re2", true);

    model.component("comp1").physics("re2").label("Ammonia Slip Catalyst (ASC)");
    model.component("comp1").physics("re2").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re2").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re2").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch1").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch1")
         .label("(1) Desired NH3 Oxidation: 4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch1")
         .set("r", "re2.kf_1*re2.c_NH3*re2.c_O2/(1+K_NH3_ASC*re2.c_NH3)");
    model.component("comp1").physics("re2").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch1").set("Af", "A1_ASC");
    model.component("comp1").physics("re2").feature("rch1").set("Ef", "E1_ASC");
    model.component("comp1").physics("re2").feature("rch1").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch2").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re2").feature("rch2")
         .label("(2) Undesired NO Formation: 4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re2").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch2").set("r", "re2.kf_2*re2.c_O2*re2.c_NH3/G_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch2").set("Af", "A2_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("Ef", "E2_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch3").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re2").feature("rch3").label("(3) Equilibrium: 2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re2").feature("rch3").set("setKeq0", true);
    model.component("comp1").physics("re2").feature("rch3").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch3").set("Af", "A4");
    model.component("comp1").physics("re2").feature("rch3").set("Ef", "E4");
    model.component("comp1").physics("re2").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch4").set("formula", "4 NH3 + 7 O2 => 4 NO2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch4")
         .label("(4) Undesired NO2 Formation: 4 NH3 + 7 O2 => 4 NO2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch4").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch4").set("r", "re2.kf_4*re2.c_NH3*re2.c_O2/G_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch4").set("Af", "A4_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("Ef", "E4_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "water", 0, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "nitrogen", 1, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "ammonia", 2, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching")
         .setIndex("PackageSpecies", "nitrogen oxide", 3, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "oxygen", 5, 0);
    model.component("comp1").physics("re2").feature("inits1").set("T0plug", "re.T");
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_H2O", 0, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_N2", 1, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NH3", 2, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NO", 3, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NO2", 4, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_O2", 5, 0);

    model.study("std1").feature("spf").set("tlist", "0 V_SCR");
    model.study("std1").feature("spf").setSolveFor("/physics/re2", false);
    model.study("std1").feature("spf").set("useparam", true);
    model.study("std1").feature("spf").setIndex("pname", "A1_ASC", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "", 0);
    model.study("std1").feature("spf").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std1").feature("spf").setIndex("pname", "A1_ASC", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "", 0);
    model.study("std1").feature("spf").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std1").feature("spf").setIndex("pname", "T_gas_in", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "range(523,25,825)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "init");

    model.study("std1").label("Study 1: SCR Temperature Operating Window, ANR = 1.3, Case 2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.F_NH3", "re.F_NO", "re.F_O2", "re.F_N2", "re.F_H2O", "re.F_NO2"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"Molar flow rate", "Molar flow rate", "Molar flow rate", "Molar flow rate", "Molar flow rate", "Molar flow rate"});
    model.result("pg1").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg1").label("Molar Flow Rate (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"Temperature"});
    model.result("pg2").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg2").label("Temperature (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("Conversion Along Reactor Axis");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg1").feature("glob1").set("colorcycle", "long");
    model.result("pg1").feature("glob1").set("linemarker", "cycle");
    model.result("pg1").feature("glob1").set("markers", 1);
    model.result("pg1").feature("glob1").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("titletype", "none");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Temperature Increase Along Reactor Axis");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "re.T-T_gas_in", 0);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Temperature Increase (K)");
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("Temperature Operating Window");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "X_NOx_SCR", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg3").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("Temperature Operating Window");
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("SCR Temperature Operating Window, ANR = 1.3, Case 2");

    model.study().create("std2");
    model.study("std2").create("spf", "StationaryPlugflow");
    model.study("std2").feature("spf").set("plotgroup", "Default");
    model.study("std2").feature("spf").set("solnum", "auto");
    model.study("std2").feature("spf").set("notsolnum", "auto");
    model.study("std2").feature("spf").set("outputmap", new String[]{});
    model.study("std2").feature("spf").setSolveFor("/physics/re", true);
    model.study("std2").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std2").feature().remove("spf");
    model.study("std2").feature().copy("spf", "std1/spf");
    model.study("std2").feature("spf").setIndex("pname", "A1_ASC", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "", 1);
    model.study("std2").feature("spf").setIndex("punit", "m^3/(s*mol)", 1);
    model.study("std2").feature("spf").setIndex("pname", "A1_ASC", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "", 1);
    model.study("std2").feature("spf").setIndex("punit", "m^3/(s*mol)", 1);
    model.study("std2").feature("spf").setIndex("pname", "ANR", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "range(1,0.1,1.6)", 1);
    model.study("std2").feature("spf").set("sweeptype", "filled");
    model.study("std2").feature("spf").set("tlist", "range(0, V_SCR/10, V_SCR)");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("Study 2: SCR Temperature Operating Window, ANR Effect, Case 2");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("scalemethod", "init");
    model.sol("sol2").feature("pf1").set("tout", "tlist");

    model.result().setOnlyPlotWhenRequested(true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("Temperature Operating Window and ANR Effect, NOx Conversion");
    model.result().evaluationGroup("eg2").set("data", "none");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevel", new int[]{1}, 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("looplevel", new int[]{3}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev4", "gev3");
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("looplevel", new int[]{4}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev5", "gev4");
    model.result().evaluationGroup("eg2").feature("gev5").setIndex("looplevel", new int[]{5}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev6", "gev5");
    model.result().evaluationGroup("eg2").feature("gev6").setIndex("looplevel", new int[]{6}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev7", "gev6");
    model.result().evaluationGroup("eg2").feature("gev7").setIndex("looplevel", new int[]{7}, 1);
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg4").feature("tblp1").label("ANR = 1.0");
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("autoplotlabel", true);
    model.result("pg4").feature("tblp1").set("autoheaders", false);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").feature("tblp2").label("ANR = 1.1");
    model.result("pg4").feature("tblp2").set("plotcolumns", new int[]{8});
    model.result("pg4").feature().duplicate("tblp3", "tblp2");
    model.result("pg4").feature("tblp3").label("ANR = 1.2");
    model.result("pg4").feature("tblp3").set("plotcolumns", new int[]{12});
    model.result("pg4").feature().duplicate("tblp4", "tblp3");
    model.result("pg4").feature("tblp4").label("ANR = 1.3");
    model.result("pg4").feature("tblp4").set("plotcolumns", new int[]{16});
    model.result("pg4").feature().duplicate("tblp5", "tblp4");
    model.result("pg4").feature("tblp5").label("ANR = 1.4");
    model.result("pg4").feature("tblp5").set("plotcolumns", new int[]{20});
    model.result("pg4").feature().duplicate("tblp6", "tblp5");
    model.result("pg4").feature("tblp6").label("ANR = 1.5");
    model.result("pg4").feature("tblp6").set("plotcolumns", new int[]{24});
    model.result("pg4").feature().duplicate("tblp7", "tblp6");
    model.result("pg4").feature("tblp7").label("ANR = 1.6");
    model.result("pg4").feature("tblp7").set("plotcolumns", new int[]{28});
    model.result("pg4").label("NOx Conversion in SCR");
    model.result("pg4").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").label("Temperature Operating Window and ANR Effect, NH3 Conversion");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev3").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev4").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev5").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev6").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev7").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").label("NH3 Conversion in SCR");
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp2").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp3").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp4").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp5").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp6").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp7").set("evaluationgroup", "eg3");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").label("SCR Temperature Operating Window and ANR Effect, Case 2");

    model.result().setOnlyPlotWhenRequested(false);

    model.study().create("std3");
    model.study("std3").create("spf", "StationaryPlugflow");
    model.study("std3").feature("spf").set("plotgroup", "Default");
    model.study("std3").feature("spf").set("solnum", "auto");
    model.study("std3").feature("spf").set("notsolnum", "auto");
    model.study("std3").feature("spf").set("outputmap", new String[]{});
    model.study("std3").feature("spf").setSolveFor("/physics/re", true);
    model.study("std3").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std3").feature("spf").label("Stationary Plug Flow, SCR");
    model.study("std3").feature("spf").set("tlist", "0 V_SCR");
    model.study("std3").feature("spf").setSolveFor("/physics/re2", false);
    model.study("std3").feature().duplicate("spf1", "spf");
    model.study("std3").feature("spf1").label("Stationary Plug Flow, ASC");
    model.study("std3").feature("spf1").set("tlist", "V_SCR V_SCR+V_ASC");
    model.study("std3").feature("spf1").setSolveFor("/physics/re2", true);
    model.study("std3").feature("spf1").setSolveFor("/physics/re", false);
    model.study("std3").label("Study 3: Single Channel Model, Influence of ANR, Case 2");
    model.study("std3").setStoreSolution(true);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.study("std3").feature("spf1").set("useinitsol", true);
    model.study("std3").feature("spf1").set("initmethod", "init");
    model.study("std3").feature("spf1").set("solnum", "last");
    model.study("std3").feature("spf1").set("usesol", true);
    model.study("std3").feature("spf1").set("notsolnum", "last");

    model.sol("sol3").feature("v1").set("scalemethod", "init");
    model.sol("sol3").feature("v2").set("scalemethod", "init");

    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "A1_ASC", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std3").feature("param").setIndex("pname", "A1_ASC", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std3").feature("param").setIndex("pname", "ANR", 0);
    model.study("std3").feature("param").setIndex("plistarr", "1 1.3 1.6", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol5");

    model.sol().create("sol6");
    model.sol("sol6").study("std3");
    model.sol("sol6").label("Parametric Solutions 2");

    model.batch("p1").feature("so2").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Molar Fraction of NH3 and NOx");
    model.result("pg6").set("data", "dset6");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").label("NH3 SCR");
    model.result("pg6").feature("glob1").set("expr", new String[]{});
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").set("expr", new String[]{"yNH3_SCR"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"Molar fraction of NH3 in SCR catalytic bed"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg6").feature("glob1").setIndex("expr", "yNH3_SCR", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "ppm", 0);
    model.result("pg6").feature("glob1").set("linestyle", "cycle");
    model.result("pg6").feature("glob1").set("linecolor", "blue");
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").label("NH3");
    model.result("pg6").feature("glob2").set("data", "dset5");
    model.result("pg6").feature("glob2").setIndex("expr", "yNH3_ASC", 0);
    model.result("pg6").feature("glob2").set("linestyle", "cyclereset");
    model.result("pg6").feature("glob2").set("legend", true);
    model.result("pg6").feature("glob2").set("autoplotlabel", true);
    model.result("pg6").feature("glob2").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("glob3", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob3").label("NOx SCR");
    model.result("pg6").feature("glob3").setIndex("expr", "yNOx_SCR", 0);
    model.result("pg6").feature("glob3").set("linestyle", "cyclereset");
    model.result("pg6").feature("glob3").set("linecolor", "magenta");
    model.result("pg6").feature().duplicate("glob4", "glob3");
    model.result("pg6").run();
    model.result("pg6").feature("glob4").label("NOx");
    model.result("pg6").feature("glob4").set("data", "dset5");
    model.result("pg6").feature("glob4").setIndex("expr", "yNOx_ASC", 0);
    model.result("pg6").feature("glob4").set("legend", true);
    model.result("pg6").feature("glob4").set("autoplotlabel", true);
    model.result("pg6").feature("glob4").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Molar Fraction of NH3 (ppm)");
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").setIndex("plotonsecyaxis", false, 1, 1);
    model.result("pg6").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg6").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg6").set("yseclabelactive", true);
    model.result("pg6").set("yseclabel", "Molar Fraction of NOx (ppm)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("Conversion of NH3 and NOx");
    model.result("pg7").set("ylabel", "Conversion");
    model.result("pg7").set("twoyaxes", false);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"X_NH3_SCR"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"NH3 Conversion in SCR"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"ppm"});
    model.result("pg7").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("expr", "X_NH3_tot", 0);
    model.result("pg7").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob3").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg7").feature("glob3").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob4").setIndex("expr", "X_NOx_tot", 0);
    model.result("pg7").feature("glob4").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg6").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").label("Single Channel Model, Influence of ANR, Case 2");

    model.study().create("std4");
    model.study("std4").create("spf", "StationaryPlugflow");
    model.study("std4").feature("spf").set("plotgroup", "Default");
    model.study("std4").feature("spf").set("solnum", "auto");
    model.study("std4").feature("spf").set("notsolnum", "auto");
    model.study("std4").feature("spf").set("outputmap", new String[]{});
    model.study("std4").feature("spf").setSolveFor("/physics/re", true);
    model.study("std4").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std4").feature().remove("spf");
    model.study("std4").feature().copy("param", "std3/param");
    model.study("std4").feature().copy("spf", "std3/spf");
    model.study("std4").feature().copy("spf1", "std3/spf1");
    model.study("std4").label("Study 4: Single Channel Model, ANR = 1.3, All Cases");
    model.study("std4").setStoreSolution(true);
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("param").set("sweeptype", "switch");
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "par2", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol13").feature("v1").set("scalemethod", "init");
    model.sol("sol13").feature("v2").set("scalemethod", "init");

    model.study("std4").feature("spf1").set("initstudy", "std4");
    model.study("std4").feature("spf1").set("initsoluse", "sol14");
    model.study("std4").feature("spf1").set("notstudy", "std4");
    model.study("std4").feature("spf1").set("notsoluse", "sol14");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol15");
    model.sol("sol15").study("std4");
    model.sol("sol15").label("Parametric Solutions 3");

    model.batch("p2").feature("so1").set("psol", "sol15");

    model.sol().create("sol16");
    model.sol("sol16").study("std4");
    model.sol("sol16").label("Parametric Solutions 4");

    model.batch("p2").feature("so2").set("psol", "sol16");
    model.batch("p2").run("compute");

    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");

    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").label("Molar Fraction of NH3 and NOx, All Cases");
    model.result("pg8").set("data", "dset10");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("data", "dset9");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").run();
    model.result("pg8").feature("glob4").set("data", "dset9");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("Conversion of NH3 and NOx, All Cases");
    model.result("pg9").set("twoyaxes", false);
    model.result("pg9").set("ylabel", "Conversion");
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "X_NH3_SCR", 0);
    model.result("pg9").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob2").setIndex("expr", "X_NH3_tot", 0);
    model.result("pg9").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob3").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg9").feature("glob3").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob4").setIndex("expr", "X_NOx_tot", 0);
    model.result("pg9").feature("glob4").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");

    model.nodeGroup("grp3").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10").label("Temperature Increase Along Reactor Axis, All Cases");
    model.result("pg10").set("ylabel", "Temperature Increase (K)");
    model.result("pg10").run();
    model.result("pg10").feature().remove("glob3");
    model.result("pg10").feature().remove("glob4");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("glob1").label("Temperature Increase SCR");
    model.result("pg10").feature("glob1").setIndex("expr", "re.T-T", 0);
    model.result("pg10").feature("glob1").set("linestyle", "solid");
    model.result("pg10").feature("glob1").set("linecolor", "cycle");
    model.result("pg10").run();
    model.result("pg10").feature("glob2").label("Temperature Increase ASC");
    model.result("pg10").feature("glob2").setIndex("expr", "re2.T-T", 0);
    model.result("pg10").feature("glob2").set("linestyle", "solid");
    model.result("pg10").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").run();
    model.result("pg8").run();

    model.nodeGroup("grp3").remove("plotgroup", "pg10", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg9", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg8", false);

    model.result("pg10").run();
    model.result("pg8").run();

    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").add("plotgroup", "pg8");
    model.nodeGroup("grp4").add("plotgroup", "pg9");
    model.nodeGroup("grp4").add("plotgroup", "pg10");
    model.nodeGroup("grp4").label("Single Channel Model, ANR = 1.3, All Cases");

    model.result("pg9").run();

    model
         .title("\u53cc\u5e8a\u5e73\u63a8\u6d41\u53cd\u5e94\u5668\u4e2d NOx \u548c\u6c28\u7684\u8f6c\u5316\u52a8\u529b\u5b66\u5206\u6790");

    model
         .description("\u672c\u4f8b\u63a2\u8ba8\u4e86\u91cd\u578b\u67f4\u6cb9\u8f66\u6392\u6c14\u7cfb\u7edf\u4e2d NOx \u548c\u6c28\u7684\u50ac\u5316\u8f6c\u5316\u52a8\u529b\u5b66\u3002\u5728\u7b2c\u4e00\u4e2a\u9009\u62e9\u6027\u50ac\u5316\u8fd8\u539f (SCR) \u5e8a\u4e2d\uff0cNOx \u901a\u8fc7\u6c28\u8fdb\u884c\u9009\u62e9\u6027\u8fd8\u539f\uff0c\u968f\u540e\u5728\u8be5\u5355\u4f53\u50ac\u5316\u5242\u7684\u4e0b\u6e38\uff0c\u5269\u4f59\u7684\u6c28\u5728\u6c28\u9003\u9038\u50ac\u5316\u5242 (ASC) \u4e2d\u88ab\u6c27\u5316\u3002\u5f53\u5e9f\u6c14\u6d41\u7ecf\u4e32\u8054\u653e\u7f6e\u7684\u5355\u4f53\u50ac\u5316\u5242\u901a\u9053\u65f6\uff0c\u4f1a\u53d1\u751f\u8fd9\u4e9b\u53cd\u5e94\u3002\u7814\u7a76\u7684\u91cd\u70b9\u5728\u4e8e\u6e29\u5ea6\u3001\u6c14\u4f53\u7ec4\u6210\u548c\u6d41\u7387\u5bf9\u8f6c\u5316\u7387\u7684\u5f71\u54cd\u3002\u672c\u6a21\u578b\u901a\u8fc7\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u4e2d\u7684\u5e73\u63a8\u6d41\u53cd\u5e94\u5668\u8fdb\u884c\u8bbe\u7f6e\u3002");

    model.label("monolith_kinetics.mph");

    model.result("pg9").run();

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("T_amb", "283[K]", "Ambient temperature");
    model.param("par3").set("kappa_axial", "3e-8[m^2]", "Permeability of monoliths, axial direction");
    model.param("par3").set("kappa_radial", "3e-12[m^2]", "Permeability of monoliths, radial direction");
    model.param("par3").set("por", "0.75", "Gas phase volume fraction of monolith");
    model.param("par3").set("ks_radial", "0.65[W/m/K]", "Solid phase thermal conductivity, radial direction");
    model.param("par3").set("ks_axial", "1.25[W/m/K]", "Solid phase thermal conductivity, axial direction");
    model.param("par3").set("h_conv", "10[W/(m^2*K)]", "Heat transfer coefficient");

    model.component("comp1").physics("re").feature("N2").set("sType", "solvent");
    model.component("comp1").physics("re2").feature("N2").set("sType", "solvent");
    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "2Daxi");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "DilutedSpeciesInPorousMedia");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "LaminarFlow");
    model.component("comp1").physics("re").feature("sync1").set("energybalance", "PorousMediaHeatTransfer");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);
    model.component("comp2").geom("geom1").axisymmetric(true);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(2Daxi)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std5"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");
    model.component("comp2").physics("chem").prop("mixture").set("updatechem", "0");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p", "1[atm]");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"N2"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "6");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "6");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventTag", "N2");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "4 NH3 + 4 NO + O2 => 4 N2 + 6 H2O");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch1")
         .set("r", "chem.kf_1*chem.c_NO*chem.c_O2*chem.c_NH3/(1+K_NH3*chem.c_NH3)");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch1").set("Af", "A1_SCR");
    model.component("comp2").physics("chem").feature("rch1").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch1").set("Ef", "E1_SCR");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "3");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch1")
         .label("(1) Standard SCR: 4 NH3 + 4 NO + O2 => 4 N2 + 6 H2O");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "2 NH3 + NO + NO2 => 2 N2 + 3 H2O");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch2")
         .set("r", "chem.kf_2*chem.c_NO2*chem.c_NO*chem.c_NH3/(1+K_NH3*chem.c_NH3)");
    model.component("comp2").physics("chem").feature("rch2").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch2").set("Af", "A2_SCR");
    model.component("comp2").physics("chem").feature("rch2").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch2").set("Ef", "E2_SCR");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", "3");
    model.component("comp2").physics("chem").feature("rch2").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch2").label("(2) Fast SCR: 2 NH3 + NO + NO2 => 2 N2 + 3 H2O");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch3", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch3").set("rSequenceNo", "3");
    model.component("comp2").physics("chem").feature("rch3").set("formula", "8 NH3 + 6 NO2 => 7 N2 + 12 H2O");
    model.component("comp2").physics("chem").feature("rch3").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch3").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch3")
         .set("r", "chem.kf_3*chem.c_NO2*chem.c_NH3/(1+K_NH3*chem.c_NH3)");
    model.component("comp2").physics("chem").feature("rch3").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch3").set("Af", "A3_SCR");
    model.component("comp2").physics("chem").feature("rch3").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch3").set("Ef", "E3_SCR");
    model.component("comp2").physics("chem").feature("rch3").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch3").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch3").label("(3) NO2 SCR: 8 NH3 + 6 NO2 => 7 N2 + 12 H2O");
    model.component("comp2").physics("chem").feature("rch3").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch4", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch4").set("rSequenceNo", "4");
    model.component("comp2").physics("chem").feature("rch4").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp2").physics("chem").feature("rch4").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch4").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch4").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch4").set("Af", "A4");
    model.component("comp2").physics("chem").feature("rch4").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch4").set("Ef", "E4");
    model.component("comp2").physics("chem").feature("rch4").set("bulkFwdOrder", "3");
    model.component("comp2").physics("chem").feature("rch4").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch4").set("setKeq0", "1");
    model.component("comp2").physics("chem").feature("rch4").set("kr", "1");
    model.component("comp2").physics("chem").feature("rch4").set("Ar", "1");
    model.component("comp2").physics("chem").feature("rch4").set("nr", "0");
    model.component("comp2").physics("chem").feature("rch4").set("Er", "0");
    model.component("comp2").physics("chem").feature("rch4").set("bulkRevOrder", "2");
    model.component("comp2").physics("chem").feature("rch4").set("surfRevOrder", "0");
    model.component("comp2").physics("chem").feature("rch4").label("(4) Equilibrium: 2 NO + O2 <=> 2 NO2");
    model.component("comp2").physics("chem").feature("rch4").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch5", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch5").set("rSequenceNo", "5");
    model.component("comp2").physics("chem").feature("rch5").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp2").physics("chem").feature("rch5").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch5").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch5").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch5")
         .set("r", "chem.kf_5*(chem.c_NH3*chem.c_O2)/(1+K_NH3*chem.c_NH3)");
    model.component("comp2").physics("chem").feature("rch5").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch5").set("Af", "A5_SCR");
    model.component("comp2").physics("chem").feature("rch5").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch5").set("Ef", "E5_SCR");
    model.component("comp2").physics("chem").feature("rch5").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch5").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch5")
         .label("(5) Undesired Oxidation: 4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp2").physics("chem").feature("rch5").set("rClass", "volumetric");
    model.component("comp2").physics("chem").create("rch6", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch6").set("rSequenceNo", "6");
    model.component("comp2").physics("chem").feature("rch6").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp2").physics("chem").feature("rch6").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch6").set("useArrhenius", "1");
    model.component("comp2").physics("chem").feature("rch6").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem").feature("rch6").set("r", "chem.kf_6*chem.c_O2*chem.c_NH3/G");
    model.component("comp2").physics("chem").feature("rch6").set("kf", "1");
    model.component("comp2").physics("chem").feature("rch6").set("Af", "A6_SCR");
    model.component("comp2").physics("chem").feature("rch6").set("nf", "0");
    model.component("comp2").physics("chem").feature("rch6").set("Ef", "E6_SCR");
    model.component("comp2").physics("chem").feature("rch6").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch6").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch6")
         .label("(6) Undesired NO Formation: 4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp2").physics("chem").feature("rch6").set("rClass", "volumetric");
    model.component("comp2").physics("chem").feature("NH3").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("NH3").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("NH3").set("specLabel", "NH3");
    model.component("comp2").physics("chem").feature("NH3").set("speciesNameInput", "NH3");
    model.component("comp2").physics("chem").feature("NH3").set("specName", "NH3");
    model.component("comp2").physics("chem").feature("NH3").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("NH3").set("chemicalFormula", "NH3");
    model.component("comp2").physics("chem").feature("NH3").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("NH3").set("M", "17.03079[g/mol]");
    model.component("comp2").physics("chem").feature("NH3").set("z", "0");
    model.component("comp2").physics("chem").feature("NH3").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("NH3").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("NH3").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("NH3").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("NH3").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("NH3").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("NH3").set("cLock", "0");
    model.component("comp2").physics("chem").feature("NH3").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("NH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("NH3").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("NO").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("NO").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("NO").set("specLabel", "NO");
    model.component("comp2").physics("chem").feature("NO").set("speciesNameInput", "NO");
    model.component("comp2").physics("chem").feature("NO").set("specName", "NO");
    model.component("comp2").physics("chem").feature("NO").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("NO").set("chemicalFormula", "NO");
    model.component("comp2").physics("chem").feature("NO").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("NO").set("M", "30.00625[g/mol]");
    model.component("comp2").physics("chem").feature("NO").set("z", "0");
    model.component("comp2").physics("chem").feature("NO").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("NO").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("NO").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("NO").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("NO").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("NO").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("NO").set("cLock", "0");
    model.component("comp2").physics("chem").feature("NO").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("NO").set("dependent", "0");
    model.component("comp2").physics("chem").feature("NO").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("O2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("O2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("O2").set("specLabel", "O2");
    model.component("comp2").physics("chem").feature("O2").set("speciesNameInput", "O2");
    model.component("comp2").physics("chem").feature("O2").set("specName", "O2");
    model.component("comp2").physics("chem").feature("O2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("O2").set("chemicalFormula", "O2");
    model.component("comp2").physics("chem").feature("O2").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("O2").set("M", "31.9988[g/mol]");
    model.component("comp2").physics("chem").feature("O2").set("z", "0");
    model.component("comp2").physics("chem").feature("O2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("O2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("O2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("O2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("O2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("O2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("O2").set("cLock", "0");
    model.component("comp2").physics("chem").feature("O2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("O2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("O2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("N2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("N2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("N2").set("specLabel", "N2");
    model.component("comp2").physics("chem").feature("N2").set("speciesNameInput", "N2");
    model.component("comp2").physics("chem").feature("N2").set("specName", "N2");
    model.component("comp2").physics("chem").feature("N2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("N2").set("chemicalFormula", "N2");
    model.component("comp2").physics("chem").feature("N2").set("sType", "solvent");
    model.component("comp2").physics("chem").feature("N2").set("M", "28.0137[g/mol]");
    model.component("comp2").physics("chem").feature("N2").set("z", "0");
    model.component("comp2").physics("chem").feature("N2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("N2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("N2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("N2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("N2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("N2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("N2").set("cLock", "1");
    model.component("comp2").physics("chem").feature("N2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("N2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("N2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("H2O").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H2O").set("specLabel", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("speciesNameInput", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("specName", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H2O").set("chemicalFormula", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("H2O").set("M", "18.01536[g/mol]");
    model.component("comp2").physics("chem").feature("H2O").set("z", "0");
    model.component("comp2").physics("chem").feature("H2O").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H2O").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H2O").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H2O").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("H2O").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H2O").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H2O").set("cLock", "0");
    model.component("comp2").physics("chem").feature("H2O").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("NO2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("NO2").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("NO2").set("specLabel", "NO2");
    model.component("comp2").physics("chem").feature("NO2").set("speciesNameInput", "NO2");
    model.component("comp2").physics("chem").feature("NO2").set("specName", "NO2");
    model.component("comp2").physics("chem").feature("NO2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("NO2").set("chemicalFormula", "NO2");
    model.component("comp2").physics("chem").feature("NO2").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("NO2").set("M", "46.00565[g/mol]");
    model.component("comp2").physics("chem").feature("NO2").set("z", "0");
    model.component("comp2").physics("chem").feature("NO2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("NO2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("NO2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("NO2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("NO2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("NO2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("NO2").set("cLock", "0");
    model.component("comp2").physics("chem").feature("NO2").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("NO2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("NO2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "6");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "6");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "1");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"water", "nitrogen", "ammonia", "nitrogen oxide", "NO2", "oxygen"});
    model.component("comp2").physics("chem").prop("mixture").set("FullyCoupled", "1");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("mixture").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("heatCapacitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("thermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("dynamicViscositySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NH3").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("NO").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("O2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("O2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("N2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("N2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("N2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("N2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("N2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("N2").set("speciesEnthalpy", "Thermodynamics");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp2").physics("chem").feature("N2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("H2O").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("H2O").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("NO2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("NO2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").prop("mixture").set("updatechem", "1");

    model.thermodynamics().feature("pp1").set("physicsID", "chem");
    model.thermodynamics().feature("pp1")
         .set("FunctionList", new String[][]{{"M", "sigma", "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", "M", "sigma", 
         "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", 
         "M", "sigma", "epsilonkb", "mu", "rho", "hF", "h", "sF", "Cp", "gamma", 
         "D", "gF0", "gF0", "gF0", "gF0", "gF0", "gF0", "k", "eta"}, 
         {"water", "water", "water", "water", "nitrogen", "nitrogen", "nitrogen", "nitrogen", "ammonia", "ammonia", 
         "ammonia", "ammonia", "nitrogen oxide", "nitrogen oxide", "nitrogen oxide", "nitrogen oxide", "NO2", "NO2", "NO2", "NO2", 
         "oxygen", "oxygen", "oxygen", "oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", 
         "water:ammonia:nitrogen oxide:NO2:oxygen:nitrogen", "water", "nitrogen", "ammonia", "nitrogen oxide", "NO2", "oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen"}, 
         {"CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", 
         "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", 
         "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", 
         "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mass", "mole", "mass", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mass", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole"}});
    model.thermodynamics().feature("pp1").set("Create", "0");

    model.component("comp2").physics("tds").field("concentration")
         .component(new String[]{"cH2O", "cNH3", "cNO", "cNO2", "cO2"});
    model.component("comp2").physics("tds").feature("init1").set("initc", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("tds").feature().create("reac1", "Reactions");
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").feature("reac1").set("ReactingVolumeType", "PoreVolume");
    model.component("comp2").physics("tds").feature().create("in1", "Inflow");
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 0);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 1);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 2);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 3);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 4);
    model.component("comp2").physics("tds").feature().create("out1", "Outflow");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cH2O", "UserDefined", "cNH3", "cNO", "cNO2", "cO2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "0", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cH2O", "0", "cNH3", "cNO", "cNO2", "cO2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("csurf", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_H2O", "root.comp2.chem.z_NH3", "root.comp2.chem.z_NO", "root.comp2.chem.z_NO2", "root.comp2.chem.z_O2"});
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("Dchem_cH2O_src", "userdef");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("Dchem_cNH3_src", "userdef");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("Dchem_cNO_src", "userdef");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("Dchem_cNO2_src", "userdef");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("Dchem_cO2_src", "userdef");
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cH2O_src", "root.comp2.chem.R_H2O", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cNH3_src", "root.comp2.chem.R_NH3", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cNO_src", "root.comp2.chem.R_NO", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cNO2_src", "root.comp2.chem.R_NO2", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cO2_src", "root.comp2.chem.R_O2", 0);
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp2").physics("spf").feature().create("inl1", "InletBoundary");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "Velocity");
    model.component("comp2").physics("spf").feature("inl1").set("ComponentWise", "NormalInflowVelocity");
    model.component("comp2").physics("spf").feature().create("out1", "OutletBoundary");
    model.component("comp2").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .setIndex("fluidType", "gasLiquid", 0);
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").setIndex("gamma_mat", "userdef", 0);
    model.component("comp2").physics("ht").feature().create("temp1", "TemperatureBoundary");
    model.component("comp2").physics("ht").feature("temp1").set("T0", "T_gas_in");
    model.component("comp2").physics("ht").feature().create("ofl1", "ConvectiveOutflow");
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "298.15[K]");
    model.component("comp2").physics("ht").feature().create("hs1", "HeatSource");
    model.component("comp2").physics("ht").feature("hs1").selection().all();
    model.component("comp2").physics("ht").feature("hs1").set("Q0_src", "root.comp2.chem.Qtot");

    model.component("comp2").multiphysics().create("rfd1", "ReactingFlowDS", 2);
    model.component("comp2").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("rfd1").set("Species_physics", "tds");
    model.component("comp2").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp2").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("nitf1").set("Heat_physics", "ht");

    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tds");
    model.component("comp1").physics("re").feature("sync1").set("energybalance", "ht");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp2.T");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp2.u");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp2.chem.Cptot");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("molarmass_mat", "root.comp2.chem.M_NH3");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("gamma_not_IG_mat", "root.comp2.chem.gamma");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("k_mat", "root.comp2.chem.krr");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp2").physics("ht").feature("hs1").setIndex("Q0_src", "userdef", 0);
    model.component("comp2").physics("ht").feature("hs1").set("Q0", "(1-comp2.ht.porous.pm.theta)*comp2.chem.Qtot");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp2.chem.Cptot");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "spf");
    model.component("comp2").physics("spf").feature("fp1").set("mu_mat", "root.comp2.chem.eta");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.spf.pA");
    model.component("comp2").physics("spf").feature("fp1").set("rho_mat", "root.comp2.nitf1.rho");

    model.study("std5").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std5").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std5").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std5").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("spf").setSolveFor("/physics/chem", false);
    model.study("std1").feature("spf").setSolveFor("/physics/tds", false);
    model.study("std1").feature("spf").setSolveFor("/physics/spf", false);
    model.study("std1").feature("spf").setSolveFor("/physics/ht", false);
    model.study("std1").feature("spf").setSolveFor("/multiphysics/rfd1", false);
    model.study("std1").feature("spf").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").feature("spf").setSolveFor("/physics/chem", false);
    model.study("std2").feature("spf").setSolveFor("/physics/tds", false);
    model.study("std2").feature("spf").setSolveFor("/physics/spf", false);
    model.study("std2").feature("spf").setSolveFor("/physics/ht", false);
    model.study("std2").feature("spf").setSolveFor("/multiphysics/rfd1", false);
    model.study("std2").feature("spf").setSolveFor("/multiphysics/nitf1", false);
    model.study("std3").feature("param").setSolveFor("/physics/chem", false);
    model.study("std3").feature("param").setSolveFor("/physics/tds", false);
    model.study("std3").feature("param").setSolveFor("/physics/spf", false);
    model.study("std3").feature("param").setSolveFor("/physics/ht", false);
    model.study("std3").feature("param").setSolveFor("/multiphysics/rfd1", false);
    model.study("std3").feature("param").setSolveFor("/multiphysics/nitf1", false);
    model.study("std3").feature("spf").setSolveFor("/physics/chem", false);
    model.study("std3").feature("spf").setSolveFor("/physics/tds", false);
    model.study("std3").feature("spf").setSolveFor("/physics/spf", false);
    model.study("std3").feature("spf").setSolveFor("/physics/ht", false);
    model.study("std3").feature("spf").setSolveFor("/multiphysics/rfd1", false);
    model.study("std3").feature("spf").setSolveFor("/multiphysics/nitf1", false);
    model.study("std3").feature("spf1").setSolveFor("/physics/chem", false);
    model.study("std3").feature("spf1").setSolveFor("/physics/tds", false);
    model.study("std3").feature("spf1").setSolveFor("/physics/spf", false);
    model.study("std3").feature("spf1").setSolveFor("/physics/ht", false);
    model.study("std3").feature("spf1").setSolveFor("/multiphysics/rfd1", false);
    model.study("std3").feature("spf1").setSolveFor("/multiphysics/nitf1", false);
    model.study("std4").feature("param").setSolveFor("/physics/chem", false);
    model.study("std4").feature("param").setSolveFor("/physics/tds", false);
    model.study("std4").feature("param").setSolveFor("/physics/spf", false);
    model.study("std4").feature("param").setSolveFor("/physics/ht", false);
    model.study("std4").feature("param").setSolveFor("/multiphysics/rfd1", false);
    model.study("std4").feature("param").setSolveFor("/multiphysics/nitf1", false);
    model.study("std4").feature("spf").setSolveFor("/physics/chem", false);
    model.study("std4").feature("spf").setSolveFor("/physics/tds", false);
    model.study("std4").feature("spf").setSolveFor("/physics/spf", false);
    model.study("std4").feature("spf").setSolveFor("/physics/ht", false);
    model.study("std4").feature("spf").setSolveFor("/multiphysics/rfd1", false);
    model.study("std4").feature("spf").setSolveFor("/multiphysics/nitf1", false);
    model.study("std4").feature("spf1").setSolveFor("/physics/chem", false);
    model.study("std4").feature("spf1").setSolveFor("/physics/tds", false);
    model.study("std4").feature("spf1").setSolveFor("/physics/spf", false);
    model.study("std4").feature("spf1").setSolveFor("/physics/ht", false);
    model.study("std4").feature("spf1").setSolveFor("/multiphysics/rfd1", false);
    model.study("std4").feature("spf1").setSolveFor("/multiphysics/nitf1", false);

    model.component("comp2").label("\u6574\u4f53\u5f0f\u53cd\u5e94\u5668\u6a21\u578b");

    model.component("comp2").geom("geom1").insertFile("monolith_reactor_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom1").run("sel8");

    model.component("comp2").variable().create("var4");
    model.component("comp2").variable("var4").selection().geom("geom1", 2);
    model.component("comp2").variable("var4").selection().named("geom1_sel1");

//    To import content from file, use:
//    model.component("comp2").variable("var4").loadFile("FILENAME");
    model.component("comp2").variable("var4")
         .set("K_NH3", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/(R_const*chem.T))", "Equilibrium constant of NH3 adsorption");
    model.component("comp2").variable("var4")
         .set("G", "chem.T*(1+K1*chem.c_NO+K2*chem.c_H2O)^2*(1+K3*chem.c_NH3)^2*(1+K4*chem.c_O2)^2[1/(K)]", "Inhibition effects from adsorbed species");
    model.component("comp2").variable("var4")
         .set("K1", "21*exp(-(-7.8e3[J/mol])/(R_const*chem.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var4")
         .set("K2", "1.6*exp(-(-7.8e3[J/mol])/(R_const*chem.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var4")
         .set("K3", "105*exp(-(3.0e4[J/mol])/(R_const*chem.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var4")
         .set("K4", "21*exp(-(0[J/mol])/(R_const*chem.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable().create("var5");
    model.component("comp2").variable("var5").selection().geom("geom1", 2);
    model.component("comp2").variable("var5").selection().named("geom1_sel2");

//    To import content from file, use:
//    model.component("comp2").variable("var5").loadFile("FILENAME");
    model.component("comp2").variable("var5")
         .set("K_NH3_ASC", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/(R_const*chem2.T))", "Equilibrium constant of NH3 adsorption");
    model.component("comp2").variable("var5")
         .set("G_ASC", "chem2.T*(1+K1_ASC*chem2.c_NO+K2_ASC*chem2.c_H2O)^2*(1+K3_ASC*chem2.c_NH3)^2*(1+K4_ASC*chem2.c_O2)^2[1/(K)]", "Inhibition effects from adsorbed species");
    model.component("comp2").variable("var5")
         .set("K1_ASC", "21*exp(-(-7.8e3[J/mol])/(R_const*chem2.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var5")
         .set("K2_ASC", "1.6*exp(-(-7.8e3[J/mol])/(R_const*chem2.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var5")
         .set("K3_ASC", "105*exp(-(3.0e4[J/mol])/(R_const*chem2.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable("var5")
         .set("K4_ASC", "21*exp(-(0[J/mol])/(R_const*chem2.T))[m^3/mol]", "Inhibition term");
    model.component("comp2").variable().create("var6");

//    To import content from file, use:
//    model.component("comp2").variable("var6").loadFile("FILENAME");
    model.component("comp2").variable("var6").set("X_NH3", "(cNH3_in-cNH3)/cNH3_in", "NH3 Conversion");
    model.component("comp2").variable("var6")
         .set("X_NOx", "(cNO_in+cNO2_in-cNO-cNO2)/(cNO_in+cNO2_in)", "NOx Conversion");
    model.component("comp2").variable("var6").set("yNH3", "cNH3/(cH2O+cNH3+cNO+cNO2+cO2)", "Molar Fraction NH3");
    model.component("comp2").variable("var6").set("yNO", "cNO/(cH2O+cNH3+cNO+cNO2+cO2)", "Molar Fraction NO");
    model.component("comp2").variable("var6").set("yNO2", "cNO2/(cH2O+cNH3+cNO+cNO2+cO2)", "Molar Fraction NO2");
    model.component("comp2").variable("var6").set("yNOx", "yNO+yNO2", "Molar Fraction NOx");

    model.component("comp1").physics("re2").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re2").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re2").feature("sync1").set("massbalance", "none");
    model.component("comp1").physics("re2").feature("sync1").set("momentumbalance", "none");
    model.component("comp1").physics("re2").feature("sync1").set("energybalance", "none");
    model.component("comp1").physics("re2").feature("sync1").set("study", "none");
    model.component("comp1").physics("re2").prop("synchronize").set("synchronize", "1");
    model.component("comp2").physics().create("chem2", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem2", 1);
    model.component("comp2").physics("chem2").prop("mixture").set("updatechem", "0");
    model.component("comp2").physics("chem2").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem2").prop("TPFeatureInput").set("p_src", "userdef");
    model.component("comp2").physics("chem2").prop("TPFeatureInput").set("p", "1[atm]");
    model.component("comp2").physics("chem2").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem2").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem2").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem2").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem2").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem2").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"N2"});
    model.component("comp2").physics("chem2").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem2").prop("ActiveSpecies").set("SumActiveSpecies", "6");
    model.component("comp2").physics("chem2").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "6");
    model.component("comp2").physics("chem2").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem2").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem2").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem2").prop("solventIsSet").set("solventIsSet", "1");
    model.component("comp2").physics("chem2").prop("solventIsSet").set("solventTag", "N2");
    model.component("comp2").physics("chem2").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem2").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem2").feature("rch1").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem2").feature("rch1").set("useArrhenius", "1");
    model.component("comp2").physics("chem2").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem2").feature("rch1")
         .set("r", "chem2.kf_1*chem2.c_NH3*chem2.c_O2/(1+K_NH3_ASC*chem2.c_NH3)");
    model.component("comp2").physics("chem2").feature("rch1").set("kf", "1");
    model.component("comp2").physics("chem2").feature("rch1").set("Af", "A1_ASC");
    model.component("comp2").physics("chem2").feature("rch1").set("nf", "0");
    model.component("comp2").physics("chem2").feature("rch1").set("Ef", "E1_ASC");
    model.component("comp2").physics("chem2").feature("rch1").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem2").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem2").feature("rch1")
         .label("(1) Desired NH3 Oxidation: 4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch1").set("rClass", "volumetric");
    model.component("comp2").physics("chem2").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem2").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem2").feature("rch2").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem2").feature("rch2").set("useArrhenius", "1");
    model.component("comp2").physics("chem2").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem2").feature("rch2").set("r", "chem2.kf_2*chem2.c_O2*chem2.c_NH3/G_ASC");
    model.component("comp2").physics("chem2").feature("rch2").set("kf", "1");
    model.component("comp2").physics("chem2").feature("rch2").set("Af", "A2_ASC");
    model.component("comp2").physics("chem2").feature("rch2").set("nf", "0");
    model.component("comp2").physics("chem2").feature("rch2").set("Ef", "E2_ASC");
    model.component("comp2").physics("chem2").feature("rch2").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem2").feature("rch2").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem2").feature("rch2")
         .label("(2) Undesired NO Formation: 4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch2").set("rClass", "volumetric");
    model.component("comp2").physics("chem2").create("rch3", "ReactionChem");
    model.component("comp2").physics("chem2").feature("rch3").set("rSequenceNo", "3");
    model.component("comp2").physics("chem2").feature("rch3").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp2").physics("chem2").feature("rch3").set("updatechem", "0");
    model.component("comp2").physics("chem2").feature("rch3").set("useArrhenius", "1");
    model.component("comp2").physics("chem2").feature("rch3").set("kf", "1");
    model.component("comp2").physics("chem2").feature("rch3").set("Af", "A4");
    model.component("comp2").physics("chem2").feature("rch3").set("nf", "0");
    model.component("comp2").physics("chem2").feature("rch3").set("Ef", "E4");
    model.component("comp2").physics("chem2").feature("rch3").set("bulkFwdOrder", "3");
    model.component("comp2").physics("chem2").feature("rch3").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem2").feature("rch3").set("setKeq0", "1");
    model.component("comp2").physics("chem2").feature("rch3").set("kr", "1");
    model.component("comp2").physics("chem2").feature("rch3").set("Ar", "1");
    model.component("comp2").physics("chem2").feature("rch3").set("nr", "0");
    model.component("comp2").physics("chem2").feature("rch3").set("Er", "0");
    model.component("comp2").physics("chem2").feature("rch3").set("bulkRevOrder", "2");
    model.component("comp2").physics("chem2").feature("rch3").set("surfRevOrder", "0");
    model.component("comp2").physics("chem2").feature("rch3").label("(3) Equilibrium: 2 NO + O2 <=> 2 NO2");
    model.component("comp2").physics("chem2").feature("rch3").set("rClass", "volumetric");
    model.component("comp2").physics("chem2").create("rch4", "ReactionChem");
    model.component("comp2").physics("chem2").feature("rch4").set("rSequenceNo", "4");
    model.component("comp2").physics("chem2").feature("rch4").set("formula", "4 NH3 + 7 O2 => 4 NO2 + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch4").set("updatechem", "0");
    model.component("comp2").physics("chem2").feature("rch4").set("useArrhenius", "1");
    model.component("comp2").physics("chem2").feature("rch4").set("ReactionExpression", "UserDefined");
    model.component("comp2").physics("chem2").feature("rch4").set("r", "chem2.kf_4*chem2.c_NH3*chem2.c_O2/G_ASC");
    model.component("comp2").physics("chem2").feature("rch4").set("kf", "1");
    model.component("comp2").physics("chem2").feature("rch4").set("Af", "A4_ASC");
    model.component("comp2").physics("chem2").feature("rch4").set("nf", "0");
    model.component("comp2").physics("chem2").feature("rch4").set("Ef", "E4_ASC");
    model.component("comp2").physics("chem2").feature("rch4").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem2").feature("rch4").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem2").feature("rch4")
         .label("(4) Undesired NO2 Formation: 4 NH3 + 7 O2 => 4 NO2 + 6 H2O");
    model.component("comp2").physics("chem2").feature("rch4").set("rClass", "volumetric");
    model.component("comp2").physics("chem2").feature("NH3").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("NH3").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("NH3").set("specLabel", "NH3");
    model.component("comp2").physics("chem2").feature("NH3").set("speciesNameInput", "NH3");
    model.component("comp2").physics("chem2").feature("NH3").set("specName", "NH3");
    model.component("comp2").physics("chem2").feature("NH3").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("NH3").set("chemicalFormula", "NH3");
    model.component("comp2").physics("chem2").feature("NH3").set("sType", "volumetric");
    model.component("comp2").physics("chem2").feature("NH3").set("M", "17.03079[g/mol]");
    model.component("comp2").physics("chem2").feature("NH3").set("z", "0");
    model.component("comp2").physics("chem2").feature("NH3").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("NH3").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("NH3").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("NH3").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("NH3").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("NH3").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("NH3").set("cLock", "0");
    model.component("comp2").physics("chem2").feature("NH3").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("NH3").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("NH3").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem2").feature("O2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("O2").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("O2").set("specLabel", "O2");
    model.component("comp2").physics("chem2").feature("O2").set("speciesNameInput", "O2");
    model.component("comp2").physics("chem2").feature("O2").set("specName", "O2");
    model.component("comp2").physics("chem2").feature("O2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("O2").set("chemicalFormula", "O2");
    model.component("comp2").physics("chem2").feature("O2").set("sType", "volumetric");
    model.component("comp2").physics("chem2").feature("O2").set("M", "31.9988[g/mol]");
    model.component("comp2").physics("chem2").feature("O2").set("z", "0");
    model.component("comp2").physics("chem2").feature("O2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("O2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("O2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("O2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("O2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("O2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("O2").set("cLock", "0");
    model.component("comp2").physics("chem2").feature("O2").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("O2").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("O2").set("SpeciesrateSelection", "Automatic");

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp2").physics("chem2").feature("N2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("N2").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("N2").set("specLabel", "N2");
    model.component("comp2").physics("chem2").feature("N2").set("speciesNameInput", "N2");
    model.component("comp2").physics("chem2").feature("N2").set("specName", "N2");
    model.component("comp2").physics("chem2").feature("N2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("N2").set("chemicalFormula", "N2");
    model.component("comp2").physics("chem2").feature("N2").set("sType", "solvent");
    model.component("comp2").physics("chem2").feature("N2").set("M", "28.0137[g/mol]");
    model.component("comp2").physics("chem2").feature("N2").set("z", "0");
    model.component("comp2").physics("chem2").feature("N2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("N2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("N2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("N2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("N2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("N2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("N2").set("cLock", "1");
    model.component("comp2").physics("chem2").feature("N2").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("N2").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("N2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem2").feature("H2O").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("H2O").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("H2O").set("specLabel", "H2O");
    model.component("comp2").physics("chem2").feature("H2O").set("speciesNameInput", "H2O");
    model.component("comp2").physics("chem2").feature("H2O").set("specName", "H2O");
    model.component("comp2").physics("chem2").feature("H2O").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("H2O").set("chemicalFormula", "H2O");
    model.component("comp2").physics("chem2").feature("H2O").set("sType", "volumetric");
    model.component("comp2").physics("chem2").feature("H2O").set("M", "18.01536[g/mol]");
    model.component("comp2").physics("chem2").feature("H2O").set("z", "0");
    model.component("comp2").physics("chem2").feature("H2O").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("H2O").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("H2O").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("H2O").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("H2O").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("H2O").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("H2O").set("cLock", "0");
    model.component("comp2").physics("chem2").feature("H2O").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("H2O").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("H2O").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem2").feature("NO").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("NO").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("NO").set("specLabel", "NO");
    model.component("comp2").physics("chem2").feature("NO").set("speciesNameInput", "NO");
    model.component("comp2").physics("chem2").feature("NO").set("specName", "NO");
    model.component("comp2").physics("chem2").feature("NO").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("NO").set("chemicalFormula", "NO");
    model.component("comp2").physics("chem2").feature("NO").set("sType", "volumetric");
    model.component("comp2").physics("chem2").feature("NO").set("M", "30.00625[g/mol]");
    model.component("comp2").physics("chem2").feature("NO").set("z", "0");
    model.component("comp2").physics("chem2").feature("NO").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("NO").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("NO").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("NO").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("NO").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("NO").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("NO").set("cLock", "0");
    model.component("comp2").physics("chem2").feature("NO").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("NO").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("NO").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem2").feature("NO2").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem2").feature("NO2").set("sisDef", "1");
    model.component("comp2").physics("chem2").feature("NO2").set("specLabel", "NO2");
    model.component("comp2").physics("chem2").feature("NO2").set("speciesNameInput", "NO2");
    model.component("comp2").physics("chem2").feature("NO2").set("specName", "NO2");
    model.component("comp2").physics("chem2").feature("NO2").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem2").feature("NO2").set("chemicalFormula", "NO2");
    model.component("comp2").physics("chem2").feature("NO2").set("sType", "volumetric");
    model.component("comp2").physics("chem2").feature("NO2").set("M", "46.00565[g/mol]");
    model.component("comp2").physics("chem2").feature("NO2").set("z", "0");
    model.component("comp2").physics("chem2").feature("NO2").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem2").feature("NO2").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem2").feature("NO2").set("mu", "0[C*m]");
    model.component("comp2").physics("chem2").feature("NO2").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem2").feature("NO2").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem2").feature("NO2").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem2").feature("NO2").set("cLock", "0");
    model.component("comp2").physics("chem2").feature("NO2").set("Dependent", "0");
    model.component("comp2").physics("chem2").feature("NO2").set("dependent", "0");
    model.component("comp2").physics("chem2").feature("NO2").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem2").prop("simpropChem").set("rSequenceNo", "4");
    model.component("comp2").physics("chem2").prop("simpropChem").set("sSequenceNo", "6");
    model.component("comp2").physics("chem2").prop("mixture").set("hasPropertyPackage", "1");
    model.component("comp2").physics("chem2").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp2").physics("chem2").prop("mixture").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"water", "nitrogen", "ammonia", "nitrogen oxide", "NO2", "oxygen"});
    model.component("comp2").physics("chem2").prop("mixture").set("FullyCoupled", "1");
    model.component("comp2").physics("chem2").prop("mixture").set("gasDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").prop("mixture").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").prop("calcTransport").set("heatCapacitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").prop("calcTransport").set("thermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").prop("calcTransport").set("dynamicViscositySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NH3").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").feature("O2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("O2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").feature("N2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("N2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").feature("H2O").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("H2O").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").feature("NO").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").feature("NO2").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem2").feature("NO2").set("Thermodynamics", "1");
    model.component("comp2").physics("chem2").prop("mixture").set("updatechem", "1");

    model.thermodynamics().feature("pp1").set("physicsID", "chem2");
    model.thermodynamics().feature("pp1")
         .set("FunctionList", new String[][]{{"M", "sigma", "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", "M", "sigma", 
         "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", 
         "M", "sigma", "epsilonkb", "mu", "rho", "hF", "h", "sF", "Cp", "gamma", 
         "D", "gF0", "gF0", "gF0", "gF0", "gF0", "gF0", "k", "eta"}, 
         {"water", "water", "water", "water", "nitrogen", "nitrogen", "nitrogen", "nitrogen", "ammonia", "ammonia", 
         "ammonia", "ammonia", "nitrogen oxide", "nitrogen oxide", "nitrogen oxide", "nitrogen oxide", "NO2", "NO2", "NO2", "NO2", 
         "oxygen", "oxygen", "oxygen", "oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", 
         "water:ammonia:nitrogen oxide:NO2:oxygen:nitrogen", "water", "nitrogen", "ammonia", "nitrogen oxide", "NO2", "oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen", "water:nitrogen:ammonia:nitrogen oxide:NO2:oxygen"}, 
         {"CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", 
         "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", 
         "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", 
         "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mass", "mole", "mass", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mass", "mole", "mole", "mole", 
         "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole"}});
    model.thermodynamics().feature("pp1").set("Create", "0");

    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cH2O", "0", "cNH3", "cNO", "cNO2", "cO2"});
    model.component("comp1").physics("re2").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re2").feature("sync1").set("chemTag", "chem2");

    model.thermodynamics().feature("pp1").feature().create("singlephase1", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase1").label("\u5bc6\u5ea6 3");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("funcname", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("property", "Density");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("propertydescr", "\u5bc6\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("unit", "kg/m^3");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase1").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ammonia", "1", "\u8d28\u91cf\u5206\u6570 ammonia"}, 
         {"massfraction_nitrogen", "1", "\u8d28\u91cf\u5206\u6570 nitrogen"}, 
         {"massfraction_nitrogen_oxide", "1", "\u8d28\u91cf\u5206\u6570 nitrogen oxide"}, 
         {"massfraction_NO2", "1", "\u8d28\u91cf\u5206\u6570 NO2"}, 
         {"massfraction_oxygen", "1", "\u8d28\u91cf\u5206\u6570 oxygen"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ammonia", "0.16", "0.16"}, 
         {"massfraction_nitrogen", "0.16", "0.16"}, 
         {"massfraction_nitrogen_oxide", "0.16", "0.16"}, 
         {"massfraction_NO2", "0.16", "0.16"}, 
         {"massfraction_oxygen", "0.16", "0.16"}, 
         {"massfraction_water", "0.16", "0.16"}});
    model.thermodynamics().feature("pp1").feature("singlephase1")
         .set("derivatives", new String[]{"Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dtemperature", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dpressure", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_ammonia", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_nitrogen", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_nitrogen_oxide", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_NO2", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_oxygen", "Density_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas11_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase2", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase2").label("\u70ed\u5bb9 (Cp) 3");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("funcname", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("property", "HeatCapacityCp");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("propertydescr", "\u70ed\u5bb9 (Cp)");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("unit", "J/kg/K");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase2").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ammonia", "1", "\u8d28\u91cf\u5206\u6570 ammonia"}, 
         {"massfraction_nitrogen", "1", "\u8d28\u91cf\u5206\u6570 nitrogen"}, 
         {"massfraction_nitrogen_oxide", "1", "\u8d28\u91cf\u5206\u6570 nitrogen oxide"}, 
         {"massfraction_NO2", "1", "\u8d28\u91cf\u5206\u6570 NO2"}, 
         {"massfraction_oxygen", "1", "\u8d28\u91cf\u5206\u6570 oxygen"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ammonia", "0.16", "0.16"}, 
         {"massfraction_nitrogen", "0.16", "0.16"}, 
         {"massfraction_nitrogen_oxide", "0.16", "0.16"}, 
         {"massfraction_NO2", "0.16", "0.16"}, 
         {"massfraction_oxygen", "0.16", "0.16"}, 
         {"massfraction_water", "0.16", "0.16"}});
    model.thermodynamics().feature("pp1").feature("singlephase2")
         .set("derivatives", new String[]{"HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dtemperature", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dpressure", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_ammonia", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_nitrogen", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_nitrogen_oxide", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_NO2", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_oxygen", "HeatCapacityCp_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas12_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase2").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase3", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase3").label("\u70ed\u5bb9\u6bd4 (Cp/Cv) 3");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("funcname", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("property", "HeatCapacityRatioCpCv");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("propertydescr", "\u70ed\u5bb9\u6bd4 (Cp/Cv)");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase3").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ammonia", "1", "\u8d28\u91cf\u5206\u6570 ammonia"}, 
         {"massfraction_nitrogen", "1", "\u8d28\u91cf\u5206\u6570 nitrogen"}, 
         {"massfraction_nitrogen_oxide", "1", "\u8d28\u91cf\u5206\u6570 nitrogen oxide"}, 
         {"massfraction_NO2", "1", "\u8d28\u91cf\u5206\u6570 NO2"}, 
         {"massfraction_oxygen", "1", "\u8d28\u91cf\u5206\u6570 oxygen"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ammonia", "0.16", "0.16"}, 
         {"massfraction_nitrogen", "0.16", "0.16"}, 
         {"massfraction_nitrogen_oxide", "0.16", "0.16"}, 
         {"massfraction_NO2", "0.16", "0.16"}, 
         {"massfraction_oxygen", "0.16", "0.16"}, 
         {"massfraction_water", "0.16", "0.16"}});
    model.thermodynamics().feature("pp1").feature("singlephase3")
         .set("derivatives", new String[]{"HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dtemperature", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dpressure", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_ammonia", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_nitrogen", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_nitrogen_oxide", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_NO2", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_oxygen", "HeatCapacityRatioCpCv_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas13_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase3").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase4", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase4").label("\u5bfc\u70ed\u7cfb\u6570 3");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("funcname", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("property", "ThermalConductivity");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("propertydescr", "\u5bfc\u70ed\u7cfb\u6570");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("unit", "W/m/K");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase4").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ammonia", "1", "\u8d28\u91cf\u5206\u6570 ammonia"}, 
         {"massfraction_nitrogen", "1", "\u8d28\u91cf\u5206\u6570 nitrogen"}, 
         {"massfraction_nitrogen_oxide", "1", "\u8d28\u91cf\u5206\u6570 nitrogen oxide"}, 
         {"massfraction_NO2", "1", "\u8d28\u91cf\u5206\u6570 NO2"}, 
         {"massfraction_oxygen", "1", "\u8d28\u91cf\u5206\u6570 oxygen"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ammonia", "0.16", "0.16"}, 
         {"massfraction_nitrogen", "0.16", "0.16"}, 
         {"massfraction_nitrogen_oxide", "0.16", "0.16"}, 
         {"massfraction_NO2", "0.16", "0.16"}, 
         {"massfraction_oxygen", "0.16", "0.16"}, 
         {"massfraction_water", "0.16", "0.16"}});
    model.thermodynamics().feature("pp1").feature("singlephase4")
         .set("derivatives", new String[]{"ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dtemperature", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dpressure", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_ammonia", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_nitrogen", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_nitrogen_oxide", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_NO2", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_oxygen", "ThermalConductivity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas14_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase4").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase5", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase5").label("\u9ecf\u5ea6 3");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("funcname", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("property", "Viscosity");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("propertydescr", "\u9ecf\u5ea6");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("unit", "Pa*s");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase5").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, 
         {"pressure", "Pa", "\u538b\u529b"}, 
         {"massfraction_ammonia", "1", "\u8d28\u91cf\u5206\u6570 ammonia"}, 
         {"massfraction_nitrogen", "1", "\u8d28\u91cf\u5206\u6570 nitrogen"}, 
         {"massfraction_nitrogen_oxide", "1", "\u8d28\u91cf\u5206\u6570 nitrogen oxide"}, 
         {"massfraction_NO2", "1", "\u8d28\u91cf\u5206\u6570 NO2"}, 
         {"massfraction_oxygen", "1", "\u8d28\u91cf\u5206\u6570 oxygen"}, 
         {"massfraction_water", "1", "\u8d28\u91cf\u5206\u6570 water"}});
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, 
         {"pressure", "101325", "101325"}, 
         {"massfraction_ammonia", "0.16", "0.16"}, 
         {"massfraction_nitrogen", "0.16", "0.16"}, 
         {"massfraction_nitrogen_oxide", "0.16", "0.16"}, 
         {"massfraction_NO2", "0.16", "0.16"}, 
         {"massfraction_oxygen", "0.16", "0.16"}, 
         {"massfraction_water", "0.16", "0.16"}});

    return model;
  }

  public static Model run6(Model model) {
    model.thermodynamics().feature("pp1").feature("singlephase5")
         .set("derivatives", new String[]{"Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dtemperature", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dpressure", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_ammonia", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_nitrogen", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_nitrogen_oxide", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_NO2", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_oxygen", "Viscosity_ammonia_nitrogen_nitrogen_oxide_NO2_oxygen_water_Gas15_Dmassfraction_water"});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase5").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase6", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .label("\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 11");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("IgnoreComposition", "true");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("funcname", "DiffusionCoeffient_ammonia_in_nitrogen_Vapor11");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("property", "BinaryDiffusionCoeffientAtInfiniteDilution[ammonia,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("propertydescr", "\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 [ammonia,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("unit", "m^2/s");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase6").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase6")
         .set("derivatives", new String[]{"DiffusionCoeffient_ammonia_in_nitrogen_Vapor11_Dtemperature", "DiffusionCoeffient_ammonia_in_nitrogen_Vapor11_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase6").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase7", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .label("\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 12");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("IgnoreComposition", "true");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("funcname", "DiffusionCoeffient_nitrogen_oxide_in_nitrogen_Vapor12");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("property", "BinaryDiffusionCoeffientAtInfiniteDilution[nitrogen oxide,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("propertydescr", "\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 [nitrogen oxide,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("unit", "m^2/s");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase7").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase7")
         .set("derivatives", new String[]{"DiffusionCoeffient_nitrogen_oxide_in_nitrogen_Vapor12_Dtemperature", "DiffusionCoeffient_nitrogen_oxide_in_nitrogen_Vapor12_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase7").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase8", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .label("\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 13");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("IgnoreComposition", "true");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("funcname", "DiffusionCoeffient_NO2_in_nitrogen_Vapor13");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("property", "BinaryDiffusionCoeffientAtInfiniteDilution[NO2,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("propertydescr", "\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 [NO2,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("unit", "m^2/s");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase8").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase8")
         .set("derivatives", new String[]{"DiffusionCoeffient_NO2_in_nitrogen_Vapor13_Dtemperature", "DiffusionCoeffient_NO2_in_nitrogen_Vapor13_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase8").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase9", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .label("\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 14");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("IgnoreComposition", "true");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("funcname", "DiffusionCoeffient_oxygen_in_nitrogen_Vapor14");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("property", "BinaryDiffusionCoeffientAtInfiniteDilution[oxygen,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("propertydescr", "\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 [oxygen,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("unit", "m^2/s");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase9").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase9")
         .set("derivatives", new String[]{"DiffusionCoeffient_oxygen_in_nitrogen_Vapor14_Dtemperature", "DiffusionCoeffient_oxygen_in_nitrogen_Vapor14_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase9").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature().create("singlephase10", "OnePhaseProperty");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .label("\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 15");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("IgnoreComposition", "true");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("funcname", "DiffusionCoeffient_water_in_nitrogen_Vapor15");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("property", "BinaryDiffusionCoeffientAtInfiniteDilution[water,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("propertydescr", "\u65e0\u9650\u7a00\u91ca\u65f6\u7684\u6269\u6563\u7cfb\u6570 [water,nitrogen]");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("unit", "m^2/s");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("prop_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("compounds", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("phase", "Gas");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("comp_basis", "mass");
    model.thermodynamics().feature("pp1").feature("singlephase10").set("include_derivatives", "yes");
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("args", new String[][]{{"temperature", "K", "\u6e29\u5ea6"}, {"pressure", "Pa", "\u538b\u529b"}});
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("plotargs", new String[][]{{"temperature", "298.15", "373.15"}, {"pressure", "101325", "101325"}});
    model.thermodynamics().feature("pp1").feature("singlephase10")
         .set("derivatives", new String[]{"DiffusionCoeffient_water_in_nitrogen_Vapor15_Dtemperature", "DiffusionCoeffient_water_in_nitrogen_Vapor15_Dpressure"});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("SecondDerivatives", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase10").set("SecondDerivativeIndices", new String[]{});
    model.thermodynamics().feature("pp1").feature("singlephase1").tag("mat_singlephase1");
    model.thermodynamics().feature("pp1").feature("mat_singlephase1").set("funcname", "Densitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase2").tag("mat_singlephase2");
    model.thermodynamics().feature("pp1").feature("mat_singlephase2").set("funcname", "HeatCapacityCppp1");
    model.thermodynamics().feature("pp1").feature("singlephase3").tag("mat_singlephase3");
    model.thermodynamics().feature("pp1").feature("mat_singlephase3").set("funcname", "HeatCapacityRatioCpCvpp1");
    model.thermodynamics().feature("pp1").feature("singlephase4").tag("mat_singlephase4");
    model.thermodynamics().feature("pp1").feature("mat_singlephase4").set("funcname", "ThermalConductivitypp1");
    model.thermodynamics().feature("pp1").feature("singlephase5").tag("mat_singlephase5");
    model.thermodynamics().feature("pp1").feature("mat_singlephase5").set("funcname", "Viscositypp1");
    model.thermodynamics().feature("pp1").feature("singlephase6").tag("mat_singlephase6");
    model.thermodynamics().feature("pp1").feature("mat_singlephase6")
         .set("funcname", "DiffusionCoefficient_ammonia_nitrogen_pp1");
    model.thermodynamics().feature("pp1").feature("singlephase7").tag("mat_singlephase7");
    model.thermodynamics().feature("pp1").feature("mat_singlephase7")
         .set("funcname", "DiffusionCoefficient_nitrogen_oxide_nitrogen_pp1");
    model.thermodynamics().feature("pp1").feature("singlephase8").tag("mat_singlephase8");
    model.thermodynamics().feature("pp1").feature("mat_singlephase8")
         .set("funcname", "DiffusionCoefficient_NO2_nitrogen_pp1");
    model.thermodynamics().feature("pp1").feature("singlephase9").tag("mat_singlephase9");
    model.thermodynamics().feature("pp1").feature("mat_singlephase9")
         .set("funcname", "DiffusionCoefficient_oxygen_nitrogen_pp1");
    model.thermodynamics().feature("pp1").feature("singlephase10").tag("mat_singlephase10");
    model.thermodynamics().feature("pp1").feature("mat_singlephase10")
         .set("funcname", "DiffusionCoefficient_water_nitrogen_pp1");
    model.thermodynamics()
         .createMaterial("Global", "pp1", "Gas", new String[]{"ammonia", "nitrogen", "nitrogen oxide", "NO2", "oxygen", "water"}, new String[]{"0", "1", "0", "0", "0", "0"}, new String[]{"density", "heatcapacitycp", "heatcapacityratiocpcv", "thermalconductivity", "viscosity", "binarydiffusioncoeffientatinfinitedilution"}, new String[][]{{"density", "Densitypp1"}, 
         {"heatcapacitycp", "HeatCapacityCppp1"}, 
         {"heatcapacityratiocpcv", "HeatCapacityRatioCpCvpp1"}, 
         {"thermalconductivity", "ThermalConductivitypp1"}, 
         {"viscosity", "Viscositypp1"}, 
         {"binarydiffusioncoeffientatinfinitedilution[ammonia,nitrogen]", "DiffusionCoefficient_ammonia_nitrogen_pp1"}, 
         {"binarydiffusioncoeffientatinfinitedilution[nitrogen oxide,nitrogen]", "DiffusionCoefficient_nitrogen_oxide_nitrogen_pp1"}, 
         {"binarydiffusioncoeffientatinfinitedilution[no2,nitrogen]", "DiffusionCoefficient_NO2_nitrogen_pp1"}, 
         {"binarydiffusioncoeffientatinfinitedilution[oxygen,nitrogen]", "DiffusionCoefficient_oxygen_nitrogen_pp1"}, 
         {"binarydiffusioncoeffientatinfinitedilution[water,nitrogen]", "DiffusionCoefficient_water_nitrogen_pp1"}}, "Identical", new String[][]{{"0", "273", "733", "20", "101325", "201325", "15"}, 
         {"60", "273", "373", "20", "101325", "201325", "15"}, 
         {"68", "273", "373", "20", "101325", "201325", "15"}, 
         {"48", "273", "373", "20", "101325", "201325", "15"}, 
         {"52", "273", "373", "20", "101325", "201325", "15"}, 
         {"76", "273", "373", "20", "101325", "201325", "15"}}, new String[]{"mass", "mass"});

    model.material("pp1mat1").label("\u6c14\u4f53\uff1a\u6c2e\u6c14");
    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.material("mat1").propertyGroup("def").set("thermalconductivity", "");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"2970[kg/m^3]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"975[J/kg/K]"});
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"35[W/m/K]"});
    model.material("mat1").label("\u56fa\u4f53\uff1a\u6574\u88c5\u53cd\u5e94\u5668\u6750\u6599");
    model.component("comp2").material().create("pmat1", "PorousMedia");
    model.component("comp2").material("pmat1").selection().set(3, 5, 14);
    model.component("comp2").material("pmat1").feature().create("fluid1", "Fluid", "comp2");
    model.component("comp2").material("pmat1").feature().create("solid1", "Solid", "comp2");
    model.component("comp2").material("pmat1").feature("solid1").set("link", "mat1");
    model.component("comp2").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp2").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp2").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp2").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp2").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp2").material("mat2").label("Structural steel");
    model.component("comp2").material("mat2").set("family", "custom");
    model.component("comp2").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp2").material("mat2").set("diffuse", "custom");
    model.component("comp2").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("ambient", "custom");
    model.component("comp2").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp2").material("mat2").set("noise", true);
    model.component("comp2").material("mat2").set("fresnel", 0.9);
    model.component("comp2").material("mat2").set("roughness", 0.3);
    model.component("comp2").material("mat2").set("diffusewrap", 0);
    model.component("comp2").material("mat2").set("reflectance", 0);
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp2").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp2").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp2").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp2").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp2").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp2").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp2").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp2").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp2").material("mat2").selection().named("geom1_sel4");
    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");

    return model;
  }

  public static Model run7(Model model) {
    model.component("comp2").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat3").label("Air");
    model.component("comp2").material("mat3").set("family", "air");
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat3").materialType("nonSolid");
    model.component("comp2").material("mat3").selection().named("geom1_sel8");
    model.component("comp2").material().create("matlnk1", "Link");
    model.component("comp2").material("matlnk1").selection().named("geom1_sel7");

    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "cN2_in", 1, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cH2O", 0, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "cN2_in", 1, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cNH3", 2, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cNO", 3, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cNO2", 4, 0);
    model.component("comp2").physics("chem2").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cO2", 5, 0);

    model.component("comp2").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp2").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2", "sel7"});
    model.component("comp2").geom("geom1").run("unisel1");
    model.component("comp2").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp2").geom("geom1").feature("unisel2")
         .set("input", new String[]{"sel1", "sel2", "sel3", "sel4", "sel7", "sel8"});
    model.component("comp2").geom("geom1").run();

    model.component("comp2").physics("tds").selection().named("geom1_unisel1");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DiffusionCoefficientSource", "mat");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1").set("FluidMaterialList", "pp1mat1");
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cH2O", new String[]{"pp1mat1.df5.D11*0.0001", "0", "0", "0", "pp1mat1.df5.D11*0.0001", "0", "0", "0", "pp1mat1.df5.D11"});
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cNH3", new String[]{"pp1mat1.df1.D11*0.0001", "0", "0", "0", "pp1mat1.df1.D11*0.0001", "0", "0", "0", "pp1mat1.df1.D11"});
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cNO", new String[]{"pp1mat1.df2.D11*0.0001", "0", "0", "0", "pp1mat1.df2.D11*0.0001", "0", "0", "0", "pp1mat1.df2.D11"});
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cNO2", new String[]{"pp1mat1.df3.D11*0.0001", "0", "0", "0", "pp1mat1.df3.D11*0.0001", "0", "0", "0", "pp1mat1.df3.D11"});
    model.component("comp2").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cO2", new String[]{"pp1mat1.df4.D11*0.0001", "0", "0", "0", "pp1mat1.df4.D11*0.0001", "0", "0", "0", "pp1mat1.df4.D11"});
    model.component("comp2").physics("tds").feature("reac1").selection().named("geom1_sel1");
    model.component("comp2").physics("tds").create("reac2", "Reactions", 2);
    model.component("comp2").physics("tds").feature("reac2").selection().named("geom1_sel2");
    model.component("comp2").physics("tds").feature("reac2").setIndex("R_cH2O_src", "root.comp2.chem2.R_H2O", 0);
    model.component("comp2").physics("tds").feature("reac2").setIndex("R_cNH3_src", "root.comp2.chem2.R_NH3", 0);
    model.component("comp2").physics("tds").feature("reac2").setIndex("R_cNO_src", "root.comp2.chem2.R_NO", 0);
    model.component("comp2").physics("tds").feature("reac2").setIndex("R_cNO2_src", "root.comp2.chem2.R_NO2", 0);
    model.component("comp2").physics("tds").feature("reac2").setIndex("R_cO2_src", "root.comp2.chem2.R_O2", 0);
    model.component("comp2").physics("tds").feature("reac2").set("ReactingVolumeType", "PoreVolume");
    model.component("comp2").physics("tds").feature().move("reac2", 7);
    model.component("comp2").physics("tds").feature("in1").selection().named("geom1_sel5");
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "cH2O_in", 0);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "cNH3_in", 1);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "cNO_in", 2);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "cNO2_in", 3);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "cO2_in", 4);
    model.component("comp2").physics("tds").feature("out1").selection().named("geom1_sel6");
    model.component("comp2").physics("tds").create("cdm1", "Fluid", 2);
    model.component("comp2").physics("tds").feature("cdm1").selection().named("geom1_sel7");
    model.component("comp2").physics("tds").feature("cdm1").set("DiffusionMaterialList", "pp1mat1");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cH2O_mat", "df5");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cNH3_mat", "df1");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cNO_mat", "df2");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cNO2_mat", "df3");
    model.component("comp2").physics("tds").feature("cdm1").set("D_cO2_mat", "df4");
    model.component("comp2").physics("ht").selection().named("geom1_unisel2");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("k_mat", "from_mat");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "from_mat");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("Cp_mat", "from_mat");
    model.component("comp2").physics("ht").feature("porous1").feature("fluid1").set("gamma_not_IG_mat", "from_mat");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1").set("k_sp_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous1").feature("pm1")
         .set("k_sp", new String[]{"ks_radial", "0", "0", "0", "ks_radial", "0", "0", "0", "ks_axial"});
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "T_gas_in");
    model.component("comp2").physics("ht").feature("temp1").selection().set(18);
    model.component("comp2").physics("ht").feature("ofl1").selection().named("geom1_sel6");
    model.component("comp2").physics("ht").feature("hs1").selection().named("geom1_sel1");
    model.component("comp2").physics("ht").feature("hs1").setIndex("materialType", "from_mat", 0);
    model.component("comp2").physics("ht").create("hs2", "HeatSource", 2);
    model.component("comp2").physics("ht").feature("hs2").selection().named("geom1_sel2");
    model.component("comp2").physics("ht").feature("hs2").setIndex("materialType", "from_mat", 0);
    model.component("comp2").physics("ht").feature("hs2").set("Q0", "(1-comp2.ht.porous.pm.theta)*comp2.chem2.Qtot");
    model.component("comp2").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp2").physics("ht").feature("ifl1").selection().named("geom1_sel5");
    model.component("comp2").physics("ht").feature("ifl1").set("Tustr", "T_gas_in");
    model.component("comp2").physics("ht").create("fluid1", "FluidHeatTransferModel", 2);
    model.component("comp2").physics("ht").feature("fluid1").selection().named("geom1_sel7");
    model.component("comp2").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp2").physics("ht").create("fluid2", "FluidHeatTransferModel", 2);
    model.component("comp2").physics("ht").feature("fluid2").selection().named("geom1_sel8");
    model.component("comp2").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp2").selection().set(17, 48, 51);
    model.component("comp2").physics("ht").feature("temp2").set("T0", "T_gas_in");

    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp2").cpl("aveop1").selection().set(6, 45);

    model.component("comp2").physics("ht").create("temp3", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp3").selection().set(14, 16, 45, 50);
    model.component("comp2").physics("ht").feature("temp3").set("T0", "aveop1(T)");
    model.component("comp2").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp2").physics("ht").feature("solid1").selection().named("geom1_sel4");
    model.component("comp2").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht").feature("hf1").selection().set(31, 32, 33, 34, 70);
    model.component("comp2").physics("ht").feature("hf1").setIndex("materialType", "from_mat", 0);
    model.component("comp2").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht").feature("hf1").set("h", "h_conv");
    model.component("comp2").physics("ht").feature("hf1").set("Text", "T_amb");
    model.component("comp2").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp2").physics("ht").feature("hf2").selection().set(15);
    model.component("comp2").physics("ht").feature("hf2").setIndex("materialType", "solid", 0);
    model.component("comp2").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht").feature("hf2").set("h", "1[W/(m^2*K)]");
    model.component("comp2").physics("ht").feature("hf2").set("Text", "T_amb");
    model.component("comp2").physics("ht").create("porous2", "PorousMediumHeatTransferModel", 2);
    model.component("comp2").physics("ht").feature("porous2").feature("fluid1").set("gamma_not_IG_mat", "from_mat");
    model.component("comp2").physics("ht").feature("porous2").selection().set(14);
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("poro_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("poro", 0.5);
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("k_b_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous2").feature("pm1")
         .set("k_b", new double[]{0.1, 0, 0, 0, 0.1, 0, 0, 0, 0.1});
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("rho_b_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("rho_b", "0.63[g/cm^3]");
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("Cp_b_mat", "userdef");
    model.component("comp2").physics("ht").feature("porous2").feature("pm1").set("Cp_b", "1.1[J/g/degC]");
    model.component("comp2").physics("spf").selection().named("geom1_unisel1");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp2").physics("spf").feature("fp1").set("mu_mat", "from_mat");
    model.component("comp2").physics("spf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp2").physics("spf").feature("inl1").selection().named("geom1_sel5");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp2").physics("spf").feature("inl1").set("mfr", "m_tot_in");
    model.component("comp2").physics("spf").feature("out1").selection().named("geom1_sel6");
    model.component("comp2").physics("spf").create("porous1", "PorousMedium", 2);
    model.component("comp2").physics("spf").feature("porous1").selection().set(3, 5);
    model.component("comp2").physics("spf").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp2").physics("spf").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_radial", "0", "0", "0", "kappa_radial", "0", "0", "0", "kappa_axial"});

    model.component("comp2").mesh("mesh1").automatic(false);
    model.component("comp2").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp2").mesh("mesh1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature().move("size1", 1);
    model.component("comp2").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("size1").selection().set(4);
    model.component("comp2").mesh("mesh1").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("size1").set("hauto", 3);
    model.component("comp2").mesh("mesh1").create("map1", "Map");
    model.component("comp2").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("map1").selection().named("geom1_sel2");
    model.component("comp2").mesh("mesh1").feature("map1").selection().set(3, 5);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").selection().set(6, 8, 10, 12);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").selection().set(9, 47);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 80);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 10);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis3").selection().set(5, 44);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 3);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis3").set("symmetric", true);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp2").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").selection().set(7);
    model.component("comp2").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp2").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("bl1").selection().set(3, 4, 5, 14);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").selection().set(8, 10, 44, 46, 47);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp2").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp2").mesh("mesh1").feature().move("bl1", 4);
    model.component("comp2").mesh("mesh1").run();

    model.study("std5").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std5").feature("stat").setSolveFor("/physics/chem2", false);
    model.study("std5").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std5").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/rfd1", false);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std5").create("stat2", "Stationary");
    model.study("std5").create("param", "Parametric");
    model.study("std5").feature("param").set("sweeptype", "switch");
    model.study("std5").feature("param").setIndex("switchname", "default", 0);
    model.study("std5").feature("param").setIndex("switchcase", "all", 0);
    model.study("std5").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std5").feature("param").setIndex("switchname", "default", 0);
    model.study("std5").feature("param").setIndex("switchcase", "all", 0);
    model.study("std5").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std5").feature("param").setIndex("switchname", "par2", 0);
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol25");
    model.sol("sol25").study("std5");
    model.sol("sol25").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p3").feature("so1").set("psol", "sol25");
    model.batch("p3").run("compute");

    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset11");
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "\u8868\u9762: \u6d53\u5ea6 (mol/m<sup>3</sup>) \u9762\u7bad\u5934: \u603b\u901a\u91cf");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").set("showlegendstitle", true);
    model.result("pg11").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, H2O, NH3, NO, NO2 (tds)");
    model.result("pg11").set("plotarrayenable", true);
    model.result("pg11").set("arrayshape", "linear");
    model.result("pg11").set("legendpos", "rightdouble");
    model.result("pg11").set("arrayaxis", "x");
    model.result("pg11").set("relpadding", 0.5);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cH2O"});
    model.result("pg11").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg11").feature("surf1").label("\u8868\u9762, H2O");
    model.result("pg11").feature("surf1").set("legendtitle", "H2O");
    model.result("pg11").feature("surf1").set("manualindexing", true);
    model.result("pg11").feature("surf1").set("arrayindex", 0);
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("expr", new String[]{"tds.tflux_cH2Or", "tds.tflux_cH2Oz"});
    model.result("pg11").feature("arws1").set("xnumber", 7);
    model.result("pg11").feature("arws1").set("ynumber", 7);
    model.result("pg11").feature("arws1").set("color", "black");
    model.result("pg11").feature("arws1").create("sel1", "Selection");
    model.result("pg11").feature("arws1").feature("sel1").selection().set(3, 4, 5);
    model.result("pg11").feature("arws1").label("\u603b\u901a\u91cf, H2O");
    model.result("pg11").feature("arws1").set("manualindexing", true);
    model.result("pg11").feature("arws1").set("arrayindex", 0);
    model.result("pg11").create("ann1", "Annotation");
    model.result("pg11").feature("ann1").set("showpoint", false);
    model.result("pg11").feature("ann1").set("text", "H2O");
    model.result("pg11").feature("ann1").label("H2O");
    model.result("pg11").feature("ann1").set("posxexpr", -2.7755575615628914E-17);
    model.result("pg11").feature("ann1").set("posyexpr", -0.20900000000000002);
    model.result("pg11").feature("ann1").set("manualindexing", true);
    model.result("pg11").feature("ann1").set("arrayindex", 0);
    model.result("pg11").create("surf2", "Surface");
    model.result("pg11").feature("surf2").set("expr", new String[]{"cNH3"});
    model.result("pg11").feature("surf2").set("colortable", "Baptisia");
    model.result("pg11").feature("surf2").label("\u8868\u9762, NH3");
    model.result("pg11").feature("surf2").set("legendtitle", "NH3");
    model.result("pg11").feature("surf2").set("manualindexing", true);
    model.result("pg11").feature("surf2").set("arrayindex", 1);
    model.result("pg11").create("arws2", "ArrowSurface");
    model.result("pg11").feature("arws2").set("expr", new String[]{"tds.tflux_cNH3r", "tds.tflux_cNH3z"});

    return model;
  }

  public static Model run8(Model model) {
    model.result("pg11").feature("arws2").set("xnumber", 7);
    model.result("pg11").feature("arws2").set("ynumber", 7);
    model.result("pg11").feature("arws2").set("color", "black");
    model.result("pg11").feature("arws2").create("sel1", "Selection");
    model.result("pg11").feature("arws2").feature("sel1").selection().set(3, 4, 5);
    model.result("pg11").feature("arws2").label("\u603b\u901a\u91cf, NH3");
    model.result("pg11").feature("arws2").set("manualindexing", true);
    model.result("pg11").feature("arws2").set("arrayindex", 1);
    model.result("pg11").create("ann2", "Annotation");
    model.result("pg11").feature("ann2").set("showpoint", false);
    model.result("pg11").feature("ann2").set("text", "NH3");
    model.result("pg11").feature("ann2").label("NH3");
    model.result("pg11").feature("ann2").set("posxexpr", -2.7755575615628914E-17);
    model.result("pg11").feature("ann2").set("posyexpr", -0.20900000000000002);
    model.result("pg11").feature("ann2").set("manualindexing", true);
    model.result("pg11").feature("ann2").set("arrayindex", 1);
    model.result("pg11").create("surf3", "Surface");
    model.result("pg11").feature("surf3").set("expr", new String[]{"cNO"});
    model.result("pg11").feature("surf3").set("colortable", "Arctium");
    model.result("pg11").feature("surf3").label("\u8868\u9762, NO");
    model.result("pg11").feature("surf3").set("legendtitle", "NO");
    model.result("pg11").feature("surf3").set("manualindexing", true);
    model.result("pg11").feature("surf3").set("arrayindex", 2);
    model.result("pg11").create("arws3", "ArrowSurface");
    model.result("pg11").feature("arws3").set("expr", new String[]{"tds.tflux_cNOr", "tds.tflux_cNOz"});
    model.result("pg11").feature("arws3").set("xnumber", 7);
    model.result("pg11").feature("arws3").set("ynumber", 7);
    model.result("pg11").feature("arws3").set("color", "black");
    model.result("pg11").feature("arws3").create("sel1", "Selection");
    model.result("pg11").feature("arws3").feature("sel1").selection().set(3, 4, 5);
    model.result("pg11").feature("arws3").label("\u603b\u901a\u91cf, NO");
    model.result("pg11").feature("arws3").set("manualindexing", true);
    model.result("pg11").feature("arws3").set("arrayindex", 2);
    model.result("pg11").create("ann3", "Annotation");
    model.result("pg11").feature("ann3").set("showpoint", false);
    model.result("pg11").feature("ann3").set("text", "NO");
    model.result("pg11").feature("ann3").label("NO");
    model.result("pg11").feature("ann3").set("posxexpr", -2.7755575615628914E-17);
    model.result("pg11").feature("ann3").set("posyexpr", -0.20900000000000002);
    model.result("pg11").feature("ann3").set("manualindexing", true);
    model.result("pg11").feature("ann3").set("arrayindex", 2);
    model.result("pg11").create("surf4", "Surface");
    model.result("pg11").feature("surf4").set("expr", new String[]{"cNO2"});
    model.result("pg11").feature("surf4").set("colortable", "Algae");
    model.result("pg11").feature("surf4").label("\u8868\u9762, NO2");
    model.result("pg11").feature("surf4").set("legendtitle", "NO2");
    model.result("pg11").feature("surf4").set("manualindexing", true);
    model.result("pg11").feature("surf4").set("arrayindex", 3);
    model.result("pg11").create("arws4", "ArrowSurface");
    model.result("pg11").feature("arws4").set("expr", new String[]{"tds.tflux_cNO2r", "tds.tflux_cNO2z"});
    model.result("pg11").feature("arws4").set("xnumber", 7);
    model.result("pg11").feature("arws4").set("ynumber", 7);
    model.result("pg11").feature("arws4").set("color", "black");
    model.result("pg11").feature("arws4").create("sel1", "Selection");
    model.result("pg11").feature("arws4").feature("sel1").selection().set(3, 4, 5);
    model.result("pg11").feature("arws4").label("\u603b\u901a\u91cf, NO2");
    model.result("pg11").feature("arws4").set("manualindexing", true);
    model.result("pg11").feature("arws4").set("arrayindex", 3);
    model.result("pg11").create("ann4", "Annotation");
    model.result("pg11").feature("ann4").set("showpoint", false);
    model.result("pg11").feature("ann4").set("text", "NO2");
    model.result("pg11").feature("ann4").label("NO2");
    model.result("pg11").feature("ann4").set("posxexpr", -2.7755575615628914E-17);
    model.result("pg11").feature("ann4").set("posyexpr", -0.20900000000000002);
    model.result("pg11").feature("ann4").set("manualindexing", true);
    model.result("pg11").feature("ann4").set("arrayindex", 3);
    model.result("pg11").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, H2O, NH3, NO, NO2 (tds)");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset13");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").set("titletype", "none");
    model.result("pg11").feature("surf1").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().remove("surf1");
    model.result("pg11").feature().remove("arws1");
    model.result("pg11").feature().remove("ann1");
    model.result("pg11").feature().remove("arws2");
    model.result("pg11").feature().remove("arws3");
    model.result("pg11").feature().remove("arws4");
    model.result("pg11").feature("surf2").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").set("expr", "yNH3");
    model.result("pg11").feature("surf2").set("unit", "ppm");
    model.result("pg11").feature("surf2").set("colortable", "Cynanthus");
    model.result("pg11").feature("surf2").set("colortabletype", "discrete");
    model.result("pg11").feature("surf2").set("bandcount", 20);
    model.result("pg11").feature("surf2").create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().set(5);
    model.result("pg11").feature("surf2").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("surf5", "surf2");
    model.result("pg11").feature("surf5").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("surf5").feature("sel1").selection().set(3);
    model.result("pg11").feature("surf3").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf3").set("expr", "yNO");
    model.result("pg11").feature("surf3").set("unit", "ppm");
    model.result("pg11").feature("surf3").set("colortable", "Baptisia");
    model.result("pg11").feature("surf3").set("colortabletype", "discrete");
    model.result("pg11").feature("surf3").set("bandcount", 20);
    model.result("pg11").feature("surf3").create("sel1", "Selection");
    model.result("pg11").feature("surf3").feature("sel1").selection().set(5);
    model.result("pg11").feature("surf3").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("surf6", "surf3");
    model.result("pg11").feature("surf6").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("surf6").feature("sel1").selection().set(3);
    model.result("pg11").feature("surf4").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf4").set("expr", "yNO2");
    model.result("pg11").feature("surf4").set("unit", "ppm");
    model.result("pg11").feature("surf4").set("colortable", "Arctium");
    model.result("pg11").feature("surf4").set("colortabletype", "discrete");
    model.result("pg11").feature("surf4").set("bandcount", 20);
    model.result("pg11").feature("surf4").create("sel1", "Selection");
    model.result("pg11").feature("surf4").feature("sel1").selection().set(5);
    model.result("pg11").feature("surf4").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("surf7", "surf4");
    model.result("pg11").feature("surf7").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf5").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().move("surf5", 2);
    model.result("pg11").feature("surf6").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().move("surf6", 5);
    model.result("pg11").run();
    model.result("pg11").feature("surf7").feature("sel1").selection().set(3);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").setIndex("looplevel", 2, 0);
    model.result("pg11").run();
    model.result("pg11").setIndex("looplevel", 3, 0);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").run();
    model.result("pg12").set("data", "dset13");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("showlegendstitle", true);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").set("plotarrayenable", true);
    model.result().dataset().create("surf1", "Surface");
    model.result("pg12").run();
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("arraydim", "1");
    model.result("pg12").feature("surf1").set("expr", "spf.U");
    model.result("pg12").feature("surf1").set("colortable", "Metasepia");
    model.result("pg12").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg12").feature("surf1").set("colortabletype", "discrete");
    model.result("pg12").feature("surf1").set("bandcount", 5);
    model.result("pg12").create("ann1", "Annotation");
    model.result("pg12").feature("ann1").set("arraydim", "1");
    model.result("pg12").feature("ann1").set("posyexpr", -0.21);
    model.result("pg12").feature("ann1").set("showpoint", false);
    model.result("pg12").feature("ann1").set("manualindexing", true);
    model.result("pg12").create("surf2", "Surface");
    model.result("pg12").feature("surf2").set("arraydim", "1");
    model.result("pg12").feature("surf2").set("expr", "p");
    model.result("pg12").feature("surf2").set("colortable", "Agama");
    model.result("pg12").feature("surf2").set("colortabletype", "discrete");
    model.result("pg12").create("ann2", "Annotation");
    model.result("pg12").feature("ann2").set("arraydim", "1");
    model.result("pg12").feature("ann2").set("posyexpr", -0.21);
    model.result("pg12").feature("ann2").set("showpoint", false);
    model.result("pg12").feature("ann2").set("manualindexing", true);
    model.result("pg12").feature("ann2").set("arrayindex", 1);
    model.result("pg12").create("surf3", "Surface");
    model.result("pg12").feature("surf3").set("arraydim", "1");
    model.result("pg12").feature("surf3").set("expr", "T");
    model.result("pg12").feature("surf3").set("unit", "\u00b0C");
    model.result("pg12").feature("surf3").set("colortable", "Plasma");
    model.result("pg12").feature("surf3").set("colortabletype", "discrete");
    model.result("pg12").feature("surf3").create("sel1", "Selection");
    model.result("pg12").feature("surf3").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg12").create("ann3", "Annotation");
    model.result("pg12").feature("ann3").set("arraydim", "1");
    model.result("pg12").feature("ann3").set("posyexpr", -0.21);
    model.result("pg12").feature("ann3").set("showpoint", false);
    model.result("pg12").feature("ann3").set("manualindexing", true);
    model.result("pg12").feature("ann3").set("arrayindex", 2);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").set("data", "dset13");
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").selection().set(5, 7, 9);
    model.result("pg13").feature("lngr1").set("expr", "X_NH3");
    model.result("pg13").feature("lngr1").set("descractive", true);
    model.result("pg13").feature("lngr1").set("xdata", "expr");
    model.result("pg13").feature("lngr1").set("xdataexpr", "(0.4-z)*pi*d_cat^2/4");
    model.result("pg13").feature("lngr1").set("xdatadescractive", true);
    model.result("pg13").feature("lngr1").set("linestyle", "cycle");
    model.result("pg13").feature("lngr1").set("linecolor", "blue");
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("autoplotlabel", true);
    model.result("pg13").feature().duplicate("lngr2", "lngr1");
    model.result("pg13").run();
    model.result("pg13").feature("lngr2").set("expr", "X_NOx");
    model.result("pg13").feature("lngr2").set("linestyle", "cyclereset");
    model.result("pg13").feature("lngr2").set("linecolor", "magenta");
    model.result("pg13").run();
    model.result("pg13").feature().duplicate("lngr3", "lngr1");
    model.result("pg13").feature().duplicate("lngr4", "lngr2");
    model.result("pg13").run();
    model.result("pg13").feature("lngr3").selection().set(44, 46, 47);
    model.result("pg13").feature("lngr3").set("linestyle", "cyclereset");
    model.result("pg13").feature("lngr3").set("linecolor", "black");
    model.result("pg13").run();
    model.result("pg13").feature("lngr4").selection().set(44, 46, 47);
    model.result("pg13").feature("lngr4").set("linecolor", "red");
    model.result("pg13").run();
    model.result("pg13").set("legendlayout", "outside");
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").set("data", "dset13");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("legendpos", "lowerleft");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").selection().set(5, 7, 9);
    model.result("pg14").feature("lngr1").set("expr", "T-T_gas_in");
    model.result("pg14").feature("lngr1").set("xdata", "reversedarc");
    model.result("pg14").feature("lngr1").set("legend", true);
    model.result("pg14").feature().duplicate("lngr2", "lngr1");
    model.result("pg14").run();
    model.result("pg14").feature("lngr2").selection().set(44, 46, 47);
    model.result("pg14").feature("lngr2").set("linestyle", "dashed");
    model.result("pg14").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg14").run();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").set("data", "dset13");
    model.result().numerical("av1").selection().set(10);
    model.result().numerical("av1").setIndex("expr", "yNH3", 0);
    model.result().numerical("av1").setIndex("unit", "ppm", 0);
    model.result().numerical("av1").setIndex("expr", "yNOx", 1);
    model.result().numerical("av1").setIndex("unit", "ppm", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().numerical().duplicate("av2", "av1");
    model.result().numerical("av2").selection().set(6);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u5e73\u5747\u503c 2");
    model.result().numerical("av2").set("table", "tbl2");
    model.result().numerical("av2").setResult();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").run();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset13");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 200);
    model.result("pg15").run();
    model.result("pg15").set("data", "rev1");
    model.result("pg15").setIndex("looplevel", 1, 0);
    model.result("pg15").set("titletype", "none");
    model.result("pg15").set("view", "view4");
    model.result("pg15").set("edges", false);
    model.result("pg15").set("showlegends", false);
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", "1");
    model.result("pg15").feature("surf1").create("sel1", "Selection");
    model.result("pg15").feature("surf1").feature("sel1").selection().named("geom1_sel4");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf1").feature("mtrl1").set("family", "tungsten");
    model.result("pg15").create("surf2", "Surface");
    model.result("pg15").feature("surf2").set("expr", "T");
    model.result("pg15").feature("surf2").set("colortable", "HeatCameraLight");
    model.result("pg15").feature("surf2").create("sel1", "Selection");
    model.result("pg15").feature("surf2").feature("sel1").selection().named("geom1_sel8");
    model.result("pg15").run();
    model.result("pg15").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf2").feature("mtrl1").set("material", "mat3");
    model.result("pg15").feature("surf2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg15").create("surf3", "Surface");
    model.result("pg15").feature("surf3").set("expr", "T");
    model.result("pg15").feature("surf3").set("colortable", "HeatCameraLight");
    model.result("pg15").feature("surf3").create("sel1", "Selection");
    model.result("pg15").feature("surf3").feature("sel1").selection().named("geom1_sel3");
    model.result("pg15").run();
    model.result("pg15").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf3").feature("mtrl1").set("family", "rock");
    model.result("pg15").feature("surf3").feature("mtrl1").set("useplotcolors", true);
    model.result("pg15").create("surf4", "Surface");
    model.result("pg15").feature("surf4").set("expr", "cNO+cNO2");
    model.result("pg15").feature("surf4").set("colortable", "Cerinthe");
    model.result("pg15").feature("surf4").create("sel1", "Selection");
    model.result("pg15").feature("surf4").feature("sel1").selection().set(3, 5);
    model.result("pg15").feature("surf4").feature("sel1").set("evalendcap", false);
    model.result("pg15").run();
    model.result("pg15").feature("surf4").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf4").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf4").feature("mtrl1").set("family", "rock");
    model.result("pg15").feature("surf4").feature("mtrl1").set("useplotcolors", true);
    model.result("pg15").run();
    model.result("pg15").create("con1", "Contour");
    model.result("pg15").feature("con1").set("expr", "cNO+cNO2");
    model.result("pg15").feature("con1").set("number", 15);
    model.result("pg15").feature("con1").set("colortable", "Prionace");
    model.result("pg15").feature("con1").set("colortabletrans", "reverse");
    model.result("pg15").feature("con1").create("sel1", "Selection");
    model.result("pg15").feature("con1").feature("sel1").selection().set(5);
    model.result("pg15").feature("con1").feature("sel1").set("evalmantle", false);
    model.result("pg15").create("surf5", "Surface");
    model.result("pg15").feature("surf5").set("expr", "1");
    model.result("pg15").feature("surf5").create("sel1", "Selection");
    model.result("pg15").feature("surf5").feature("sel1").selection().set(3, 5);
    model.result("pg15").feature("surf5").feature("sel1").set("evalendcap", false);
    model.result("pg15").feature("surf5").feature("sel1").set("evalstartcap", false);
    model.result("pg15").run();
    model.result("pg15").feature("surf5").create("mtrl1", "MaterialAppearance");
    model.result("pg15").run();
    model.result("pg15").feature("surf5").feature("mtrl1").set("appearance", "custom");
    model.result("pg15").feature("surf5").feature("mtrl1").set("family", "custom");
    model.result("pg15").feature("surf5").feature("mtrl1")
         .set("customspecular", new double[]{0.9803921580314636, 0.9411764740943909, 0.9019607901573181});
    model.result("pg15").feature("surf5").feature("mtrl1").set("diffuse", "custom");
    model.result("pg15").feature("surf5").feature("mtrl1").set("ambient", "custom");
    model.result("pg15").feature("surf5").feature("mtrl1")
         .set("customambient", new double[]{0.9019607901573181, 0.8823529481887817, 0.800000011920929});
    model.result("pg15").feature("surf5").feature("mtrl1").set("basis", true);
    model.result("pg15").feature("surf5").feature("mtrl1").set("scale", 2);
    model.result("pg15").feature("surf5").feature("mtrl1").setIndex("origin", 1, 0);
    model.result("pg15").feature("surf5").feature("mtrl1").set("specifybasisy", true);
    model.result("pg15").feature("surf5").feature("mtrl1").set("noise", true);
    model.result("pg15").feature("surf5").feature("mtrl1").set("normalnoisetype", "1");
    model.result("pg15").feature("surf5").feature("mtrl1").set("noisescale", 50);
    model.result("pg15").feature("surf5").feature("mtrl1").set("noisefreq", 50);
    model.result("pg15").feature("surf5").feature("mtrl1").set("normalnoisebrush", "1");
    model.result("pg15").feature("surf5").feature("mtrl1").set("roughness", 1);
    model.result("pg15").feature("surf5").feature("mtrl1").set("diffusewrap", 0);
    model.result("pg15").feature("surf5").feature("mtrl1").set("reflectance", 0.05);
    model.result("pg15").run();
    model.result("pg15").feature().duplicate("surf6", "surf5");
    model.result("pg15").run();
    model.result("pg15").feature("surf6").set("expr", "cNH3");
    model.result("pg15").run();
    model.result("pg15").feature("surf6").feature("sel1").set("evalmantle", false);
    model.result("pg15").feature("surf6").feature("sel1").set("evalendcap", true);
    model.result("pg15").run();
    model.result("pg15").feature("surf6").feature("mtrl1").set("useplotcolors", true);
    model.result("pg15").run();
    model.result("pg15").feature("surf6").set("colortable", "Kyanite");
    model.result("pg15").run();
    model.result("pg11").run();

    model.nodeGroup().create("grp5", "Results");
    model.nodeGroup("grp5").set("type", "plotgroup");
    model.nodeGroup().move("grp5", 4);
    model.nodeGroup("grp5").add("plotgroup", "pg11");
    model.nodeGroup("grp5").add("plotgroup", "pg12");
    model.nodeGroup("grp5").add("plotgroup", "pg13");
    model.nodeGroup("grp5").add("plotgroup", "pg14");
    model.nodeGroup("grp5").add("plotgroup", "pg15");

    model.result("pg15").run();
    model.result().duplicate("pg16", "pg15");

    model.nodeGroup("grp5").add("plotgroup", "pg16");

    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").feature().remove("surf2");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").feature().remove("con1");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").feature("surf3").feature("mtrl1").set("useplotcolors", false);
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").feature("surf4").feature("mtrl1").set("useplotcolors", false);
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").feature("surf6").feature("mtrl1").set("useplotcolors", false);
    model.result("pg16").run();
    model.result("pg16").feature("surf6").feature("sel1").set("evalstartcap", true);
    model.result("pg16").run();
    model.result("pg16").run();
    model.result().remove("pg16");
    model.result("pg15").run();

    model
         .title("\u53cc\u5e8a\u6574\u4f53\u5f0f\u53cd\u5e94\u5668\u4e2d\u6c2e\u6c27\u5316\u7269\u548c\u6c28\u7684\u8f6c\u5316");

    model
         .description("\u5728\u91cd\u578b\u67f4\u6cb9\u5361\u8f66\u7684\u6392\u6c14\u7cfb\u7edf\u4e2d\uff0c\u5e9f\u6c14\u6d41\u7ecf\u6574\u4f53\u5f0f\u53cd\u5e94\u5668\u901a\u9053\u65f6\u4f1a\u4fc3\u4f7f\u6c2e\u6c27\u5316\u7269\u53d1\u751f\u8fd8\u539f\u53cd\u5e94\u3002\u672c\u4f8b\u7814\u7a76\u8be5\u53cd\u5e94\u5668\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u51e0\u4f55\uff0c\u5176\u4e2d\u6db5\u76d6\u8d28\u91cf\u4f20\u9012\u3001\u4f20\u70ed\u548c\u6d41\u4f53\u6d41\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();

    model.label("monolith_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    model = run7(model);
    run8(model);
  }

}
