/*
 * dissociation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:27 by COMSOL 6.3.0.290. */
public class dissociation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp1").physics("tcs").field("massfraction").field("wA");
    model.component("comp1").physics("tcs").field("massfraction").component(new String[]{"wA", "wB"});
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p_amb", "101325[Pa]", "\u53c2\u8003\u538b\u529b");
    model.param().set("T_amb", "293[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("kf", "0.0254", "[1/s] \u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("wN2O4_in", "0.99[1]", "\u5165\u53e3\u5904 N2O4 \u7684\u8d28\u91cf\u5206\u6570");
    model.param().set("Tf", "500[K]", "\u52a0\u70ed\u6d41\u4f53\u7684\u6e29\u5ea6");
    model.param().set("Ua", "50[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("W0", "0.1[m]", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L0", "4[m]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("v_in", "0.02[m/s]", "\u5165\u53e3\u5904\u7684\u901f\u5ea6");

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
         .set("GasLiquidConstant", new String[][]{{"\u7edd\u5bf9\u71b5", "304.32", "J/mol/K"}, 
         {"\u4e34\u754c\u538b\u7f29\u56e0\u5b50", "0.233", "1"}, 
         {"\u4e34\u754c\u538b\u529b", "1.0031e+07", "Pa"}, 
         {"\u4e34\u754c\u6e29\u5ea6", "431.15", "K"}, 
         {"\u4e34\u754c\u4f53\u79ef", "8.249e-05", "m^3/mol"}, 
         {"\u5076\u6781\u77e9", "0", "C\u00b7m"}, 
         {"\u71c3\u70e7\u70ed\uff08\u9ad8\u70ed\u503c\uff09", "0", "J/mol"}, 
         {"Lennard-Jones \u76f4\u5f84\uff08\u52bf\u80fd\u7279\u5f81\u957f\u5ea6\uff09", "0", "m"}, 
         {"Lennard-Jones \u80fd\u91cf\uff08\u6700\u5c0f\u52bf\u80fd\uff09", "0", "K"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u4e0b\u7684\u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"\u5206\u5b50\u8d28\u91cf", "92.011", "g/mol"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6", "294.3", "K"}, 
         {"\u6807\u51c6\u751f\u6210\u7113", "9163", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u6db2\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u56fa\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u84b8\u6c7d\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u6db2\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u56fa\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u84b8\u6c7d\uff09", "0", "J/mol/K"}, 
         {"\u8303\u5fb7\u534e\u9762\u79ef", "0", "m^2/mol"}, 
         {"\u8303\u5fb7\u534e\u4f53\u79ef", "0", "m^3/mol"}});
    model.thermodynamics().feature("spec1")
         .set("GasLiquidModelOptions", new String[][]{{"\u504f\u5fc3\u56e0\u5b50", "0.85327", "1"}, 
         {"Chao-Seader \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"Chao-Seader \u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"Chao-Seader \u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"COSTALD \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"COSTALD \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}, 
         {"\u5bcc\u52d2\u6269\u6563\u4f53\u79ef", "13.1", "cm^3"}, 
         {"\u7b49\u5f20\u6bd4\u5bb9", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 L", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 M", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 N", "0", "1"}, 
         {"Rackett \u53c2\u6570", "0", "1"}, 
         {"\u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer \u03b4 \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC Q \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC R \u53c2\u6570", "0", "1"}, 
         {"Wilke-Chang \u5173\u8054\u53c2\u6570", "1", "1"}, 
         {"Wilson \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}});
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
         .set("GasLiquidConstant", new String[][]{{"\u7edd\u5bf9\u71b5", "239.92", "J/mol/K"}, 
         {"\u4e34\u754c\u538b\u7f29\u56e0\u5b50", "0.233", "1"}, 
         {"\u4e34\u754c\u538b\u529b", "10132500", "Pa"}, 
         {"\u4e34\u754c\u6e29\u5ea6", "431.15", "K"}, 
         {"\u4e34\u754c\u4f53\u79ef", "8.249e-05", "m^3/mol"}, 
         {"\u5076\u6781\u77e9", "0", "C\u00b7m"}, 
         {"\u71c3\u70e7\u70ed\uff08\u9ad8\u70ed\u503c\uff09", "0", "J/mol"}, 
         {"Lennard-Jones \u76f4\u5f84\uff08\u52bf\u80fd\u7279\u5f81\u957f\u5ea6\uff09", "0", "m"}, 
         {"Lennard-Jones \u80fd\u91cf\uff08\u6700\u5c0f\u52bf\u80fd\uff09", "0", "K"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u4e0b\u7684\u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"\u5206\u5b50\u8d28\u91cf", "46.0055", "g/mol"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6", "294.15", "K"}, 
         {"\u6807\u51c6\u751f\u6210\u7113", "33180", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u6db2\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u56fa\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u84b8\u6c7d\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u6db2\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u56fa\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u84b8\u6c7d\uff09", "0", "J/mol/K"}, 
         {"\u8303\u5fb7\u534e\u9762\u79ef", "222300", "m^2/mol"}, 
         {"\u8303\u5fb7\u534e\u4f53\u79ef", "1.391e-5", "m^3/mol"}});
    model.thermodynamics().feature("spec2")
         .set("GasLiquidModelOptions", new String[][]{{"\u504f\u5fc3\u56e0\u5b50", "0.851088", "1"}, 
         {"Chao-Seader \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"Chao-Seader \u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"Chao-Seader \u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"COSTALD \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"COSTALD \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}, 
         {"\u5bcc\u52d2\u6269\u6563\u4f53\u79ef", "13.1", "cm^3"}, 
         {"\u7b49\u5f20\u6bd4\u5bb9", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 L", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 M", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 N", "0", "1"}, 
         {"Rackett \u53c2\u6570", "0", "1"}, 
         {"\u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer \u03b4 \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC Q \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC R \u53c2\u6570", "0", "1"}, 
         {"Wilke-Chang \u5173\u8054\u53c2\u6570", "1", "1"}, 
         {"Wilson \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}});
    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"N2O4", "10544-72-6", "N2O4", "UserDefined"}, {"NO2", "10102-44-0", "NO2", "UserDefined"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
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
    model.thermodynamics().feature("pp1").label("____ 1");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W0", "L0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(3);

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "N2O4");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "NO2");
    model.component("comp1").physics("chem").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics("chem").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "N2O4", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.5", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "NO2", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.5", 1);
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp1").physics().move("chem", 0);
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "N2O4 <=> 2 NO2");
    model.component("comp1").physics("chem").feature("rch1").set("setKeq0", true);
    model.component("comp1").physics("chem").feature("rch1").set("kf", "kf");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wB", 1, 0);

    model.component("comp1").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 2);

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_amb");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3);
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "MaxwellStefan");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 2);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_N2O4_NO2", 0, 0);
    model.component("comp1").physics("tcs").create("reac1", "ReactionSources", 2);
    model.component("comp1").physics("tcs").feature("reac1").selection().set(1);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wA_src", "root.comp1.chem.Rw_N2O4", 0);
    model.component("comp1").physics("tcs").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcs").feature("in1").selection().set(2);
    model.component("comp1").physics("tcs").feature("in1").setIndex("wbnd", "wN2O4_in", 0);
    model.component("comp1").physics("tcs").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcs").feature("out1").selection().set(3);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 50);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 5);

    model.component("comp1").multiphysics("nirf1").set("Heat_physics", "none");
    model.component("comp1").multiphysics("nirf1").set("temperature", "Tf");

    model.study("std1").label("\u7814\u7a76 - \u7b49\u6e29\u6a21\u578b");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "aveop1(w*spf.rho*wB)/aveop1(w*spf.rho)", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6\uff0c\u7b49\u6e29");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "spf.U");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "10");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u8d28\u91cf\u5206\u6570\uff0cB\uff0c\u7b49\u6e29");
    model.result("pg2").set("title", "\u8d28\u91cf\u5206\u6570");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("expr", "wB");
    model.result("pg2").feature("mslc1").set("descr", "\u8d28\u91cf\u5206\u6570 wB");
    model.result("pg2").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg2").feature("mslc1").set("colortabletype", "discrete");
    model.result("pg2").feature("mslc1").set("bandcount", 25);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nirf1", true);
    model.study("std2").label("\u7814\u7a76 - \u975e\u7b49\u6e29\u6a21\u578b");

    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(3);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_amb");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "Ua*(Tf-T)");

    model.component("comp1").multiphysics("nirf1").set("Heat_physics", "ht");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6\uff0c\u975e\u7b49\u6e29");
    model.result("pg3").set("data", "rev2");
    model.result("pg3").set("view", "view2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u8d28\u91cf\u5206\u6570 B\uff0c\u975e\u7b49\u6e29");
    model.result("pg4").set("data", "rev2");
    model.result("pg4").run();
    model.result("pg4").set("view", "view2");
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6");
    model.result("pg5").set("title", "\u6e29\u5ea6");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").set("expr", "T");
    model.result("pg5").feature("mslc1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").feature("mslc1").set("colortable", "HeatCameraLight");
    model.result("pg5").run();

    model.title("\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u7684\u89e3\u79bb\u53cd\u5e94");

    model
         .description("\u672c\u4f8b\u5bf9\u7ba1\u5f0f\u53cd\u5e94\u5668\u8fdb\u884c\u5efa\u6a21\uff0c\u53cd\u5e94\u5668\u4e2d\u53d1\u751f\u7269\u8d28\u7684\u53ef\u9006\u89e3\u79bb\u53cd\u5e94\uff0c\u5373 A <-> 2B\uff0c\u5206\u6790\u6c14\u76f8\u5206\u5b50\u6570\u6cbf\u53cd\u5e94\u9014\u5f84\u589e\u52a0\uff0c\u5bfc\u81f4\u6c14\u4f53\u6df7\u5408\u7269\u4f53\u79ef\u81a8\u80c0\u548c\u6c14\u4f53\u52a0\u901f\uff0c\u7814\u7a76\u4e86\u7b49\u6e29\u548c\u975e\u7b49\u6e29\u4e24\u79cd\u53cd\u5e94\u5668\u6761\u4ef6\u3002");

    model.label("dissociation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
