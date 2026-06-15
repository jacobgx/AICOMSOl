/*
 * gaas_cvd.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:22 by COMSOL 6.3.0.290. */
public class gaas_cvd {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("u_in", "0.4[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("T_in", "300[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T_surf", "900[K]", "\u8868\u9762\u6e29\u5ea6");
    model.param().set("p_0", "4000[Pa]", "\u51fa\u53e3\u538b\u529b");
    model.param().set("c_AsH3_in", "0.16[mol/m^3]", "AsH3 \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("c_GaC6H15_in", "6.415e-3[mol/m^3]", "GaC6H15 \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("c_H2_init", "1.44[mol/m^3]", "H2\uff08\u6eb6\u5242\uff09\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c_AsH3_init", "0.16[mol/m^3]", "AsH3 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c_GaC6H15_init", "6.415e-3[mol/m^3]", "GaC6H15 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("c_Gasurf", "7.2e-6[mol/m^2]", "As \u4e0a Ga \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_Assurf_Ga", "7.3e-6[mol/m^2]", "Ga \u4e0a As \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_GaC2H5surf", "1.4e-9[mol/m^2]", "As \u4e0a GaC2H5 \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_C2H5surf", "2.9e-8[mol/m^2]", "As \u4e0a C2H5 \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_C2H5surf_Ga", "1.7e-8[mol/m^2]", "Ga \u4e0a C2H5 \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_Hsurf", "6.8e-8[mol/m^2]", "As \u4e0a H \u7684\u8868\u9762\u6d53\u5ea6");
    model.param().set("c_Hsurf_Ga", "4e-8[mol/m^2]", "Ga \u4e0a H \u7684\u8868\u9762\u6d53\u5ea6");

    model.component("comp1").physics("re")
         .label("\u53cd\u5e94\u5de5\u7a0b - \u6c14\u76f8\u53cd\u5e94\uff0c\u5b8c\u6574\u96c6\u5408 (re)");
    model.component("comp1").physics("re").create("rgr1", "ReversibleReactionGroup", -1);
    model.component("comp1").physics("re").feature("rgr1").set("chemkin", true);
    model.component("comp1").physics("re").feature("rgr1").set("kinetics", "gaas_cvd_reaction_kinetics.txt");
    model.component("comp1").physics("re").prop("specNumbering").set("specNumbering", "on");
    model.component("comp1").physics("re").feature("rgr1").set("Keq0dep", new String[]{});
    model.component("comp1").physics("re").feature("rgr1")
         .set("chemkinSpeciesLabel", new String[]{"AsH3", "C2H6", "C", "C2H5", "C2H4", "H_1(ads)", "GaH2", "H", "GaC2H6", "H2", 
         "C2H5_1(ads)", "GaAs", "CH4", "Ga_1(ads)", "CH3", "H_Ga_1(ads)", "GaC6H15", "GaC4H10", "As_Ga_1(ads)", "GaC2H5_1(ads)", 
         "C2H5_Ga_1(ads)", "GaC2H6_1(ads)"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
         "21", "22", "23", "24", "25"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5+H2", "C2H5+H", "CH3+H2", "2CH3", "C2H4+H", "2H+H2", "GaC6H15", 
         "GaC2H6", "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", 
         "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H6+H", "2CH3", "CH4+H", "C2H6", "C2H5", "2H2", "GaC2H5_1(ads)+2C2H5", 
         "GaC2H6_1(ads)", "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", 
         "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "3.1E-6", "3.6E7", "2.9E-4", "2000000.0", "150.0", "1000000.0", "1.453E-4", 
         "1.822E-4", "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", 
         "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "3.6", "0.0", "3.12", "0.0", "1.49", "0.0", "0.5", 
         "0.5", "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "35599.9824", "0.0", "36400.00504", "0.0", "41800.000960000005", "0.0", "20900.000480000002", 
         "0.0", "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", 
         "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "5.499999999999999E-4", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "3.5", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "21800.02072", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Keq0dep", new String[]{"1", "1", "1", "0", "1", "1", "1", "1", "1", "1", 
         "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", 
         "1", "1", "1", "1", "1"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("kf", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("kfUnit", new String[]{"m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^6*s^-1*mol^-2", "m^1*s^-1*mol^-0", 
         "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", 
         "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^2*s^-1*mol^-1", "m^2*s^-1*mol^-1"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("krUnit", new String[]{"m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^3*s^-1*mol^-1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-2*s^-1*mol^--1", 
         "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", 
         "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("reactionRate", new String[]{"re.kf_cr1*re.c_GaC6H15", "re.kf_cr2*re.c_GaC4H10", "re.kf_cr3*re.c_GaC2H6", "re.kf_cr4*re.c_C2H5*re.c_H2-re.kr_cr4*re.c_C2H6*re.c_H", "re.kf_cr5*re.c_C2H5*re.c_H", "re.kf_cr6*re.c_CH3*re.c_H2", "re.kf_cr7*re.c_CH3^2", "re.kf_cr8*re.c_C2H4*re.c_H", "re.kf_cr9*re.c_H^2*re.c_H2", "re.kf_cr10*re.c_GaC6H15", 
         "re.kf_cr11*re.c_GaC2H6", "re.kf_cr12*re.csurf_GaC2H6_1_surf", "re.kf_cr13*re.c_GaH2", "re.kf_cr14*re.csurf_GaC2H6_1_surf", "re.kf_cr15*re.c_AsH3", "re.kf_cr16*re.c_C2H5", "re.kf_cr17*re.csurf_C2H5_1_surf", "re.kf_cr18*re.c_C2H5", "re.kf_cr19*re.csurf_C2H5_Ga_1_surf", "re.kf_cr20*re.csurf_C2H5_1_surf", 
         "re.kf_cr21*re.csurf_C2H5_Ga_1_surf", "re.kf_cr22*re.csurf_H_1_surf", "re.kf_cr23*re.csurf_H_Ga_1_surf", "re.kf_cr24*re.csurf_GaC2H5_1_surf*re.csurf_As_Ga_1_surf", "re.kf_cr25*re.csurf_Ga_1_surf*re.csurf_As_Ga_1_surf"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("thirdBody", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Species", new String[]{"AsH3", "C2H6", "C", "C2H5", "C2H4", "H_1_surf", "GaH2", "H", "GaC2H6", "H2", 
         "C2H5_1_surf", "GaAs", "CH4", "Ga_1_surf", "CH3", "H_Ga_1_surf", "GaC6H15", "GaC4H10", "As_Ga_1_surf", "GaC2H5_1_surf", 
         "C2H5_Ga_1_surf", "GaC2H6_1_surf"});
    model.component("comp1").physics("re").feature("rgr1").set("tableRow", "25");
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"156.9063[g/mol]", "127.8452[g/mol]", "29.0611[g/mol]", "99.79208[g/mol]", "28.05312[g/mol]", "71.73896[g/mol]", "2.01596[g/mol]", "30.06908[g/mol]", "1.00798[g/mol]", "15.03454[g/mol]", 
         "16.04252[g/mol]", "77.94553[g/mol]", "144.64459[g/mol]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", 
         "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", 
         "107.4[K]", "107.4[K]", "107.4[K]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("mu", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("sigma", new String[]{"(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", 
         "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", 
         "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp1").physics("re").feature("sg_rgr1").set("tableRow", "13");
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Species", new String[]{"GaC6H15", "GaC4H10", "C2H5", "GaC2H6", "C2H4", "GaH2", "H2", "C2H6", "H", "CH3", 
         "CH4", "AsH3", "GaAs"});
    model.component("comp1").physics("re").create("C", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("C").set("specName", "C");
    model.component("comp1").physics("re").feature("C").set("cLock", true);
    model.component("comp1").physics("re").feature("C").set("specLabel", "C");
    model.component("comp1").physics("re").feature("rgr1")
         .set("chemkinLabel", new String[]{"As_Ga(s)", "Ga(s)", "GaC2H6(s)", "GaC2H5(s)", "C2H5(s)", "H(s)", "C2H5_Ga(s)", "H_Ga(s)"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("specLabel", new String[]{"As_Ga_1(ads)", "Ga_1(ads)", "GaC2H6_1(ads)", "GaC2H5_1(ads)", "C2H5_1(ads)", "H_1(ads)", "C2H5_Ga_1(ads)", "H_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("SurfaceSpeciesGroup", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("sigmak", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp1").physics("re").feature("rgr1").set("gamman", new String[]{"7.75E-5"});
    model.component("comp1").physics("re").feature("inits1").set("gamman0", "7.75E-5");
    model.component("comp1").physics("re").feature("inits1")
         .set("sigmak", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp1").physics("re").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp1").physics("re").prop("ActiveSpecies").set("surface", true);
    model.component("comp1").physics("re").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "14");
    model.component("comp1").physics("re").prop("ActiveSpecies").set("SumActiveSpecies", "22");
    model.component("comp1").physics("re").feature("inits1")
         .set("initialValue", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("inits1")
         .set("VolumetricSpecies", new String[]{"AsH3", "C", "C2H4", "C2H5", "C2H6", "CH3", "CH4", "GaAs", "GaC2H6", "GaC4H10", 
         "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp1").physics("re").feature("inits1")
         .set("F0", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("inits1")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("inits1")
         .set("initialValues", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("inits1")
         .set("sigmak", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp1").physics("re").feature().remove("C");
    model.component("comp1").physics("re").prop("energybalance").set("T", "900[K]");
    model.component("comp1").physics("re").feature("rgr1").set("chemkin", false);
    model.component("comp1").physics("re").feature("rgr1").set("createThisReactiondescr", 9);
    model.component("comp1").physics("re").create("rgr1_1", "ReactionChem");
    model.component("comp1").physics("re").feature("rgr1_1").set("formula", "2H+H2=>2H2");
    model.component("comp1").physics("re").feature("rgr1_1").set("updatechem", "0");
    model.component("comp1").physics("re").feature("rgr1_1").setIndex("useArrhenius", "1", 0);
    model.component("comp1").physics("re").feature("rgr1_1").set("Af", "1000000.0");
    model.component("comp1").physics("re").feature("rgr1_1").set("nf", "0.0");
    model.component("comp1").physics("re").feature("rgr1_1").set("Ef", "0.0");
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "10", "11", 
         "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", 
         "22", "23", "24", "25"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5+H2", "C2H5+H", "CH3+H2", "2CH3", "C2H4+H", "GaC6H15", "GaC2H6", 
         "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", 
         "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H6+H", "2CH3", "CH4+H", "C2H6", "C2H5", "GaC2H5_1(ads)+2C2H5", "GaC2H6_1(ads)", 
         "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", 
         "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "3.1E-6", "3.6E7", "2.9E-4", "2000000.0", "150.0", "1.453E-4", "1.822E-4", 
         "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", 
         "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "3.6", "0.0", "3.12", "0.0", "1.49", "0.5", "0.5", 
         "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "35599.9824", "0.0", "36400.00504", "0.0", "41800.000960000005", "20900.000480000002", "0.0", 
         "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", "134000.00568", 
         "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "5.499999999999999E-4", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "3.5", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "21800.02072", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("rgr1").set("createThisReactiondescr", "8");
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"156.9063[g/mol]", "127.8452[g/mol]", "29.0611[g/mol]", "99.79208[g/mol]", "28.05312[g/mol]", "71.73896[g/mol]", "2.01596[g/mol]", "30.06908[g/mol]", "1.00798[g/mol]", "15.03454[g/mol]", 
         "16.04252[g/mol]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", 
         "1[kg/m^3]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", 
         "107.4[K]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("mu", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("sigma", new String[]{"(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", 
         "(3.458*(10^(-10)))[m]"});
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", 
         "0.015[W/(m*K)]"});
    model.component("comp1").physics("re").feature("sg_rgr1").set("tableRow", "11");
    model.component("comp1").physics("re").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "C2H6", "CH3", "CH4", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", 
         "GaH2"});
    model.component("comp1").physics("re").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp1").physics("re").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp1").physics("re").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp1").physics("re").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp1").physics("re").feature("H").set("dependent", "0");
    model.component("comp1").physics("re").feature("H2").set("dependent", "0");
    model.component("comp1").physics("re").prop("SimpropMass")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "C2H6", "CH3", "CH4", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", 
         "GaH2", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "H", 
         "H2"});
    model.component("comp1").physics("re").prop("SimpropMass")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", 
         "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", 
         "\u53d8\u91cf"});
    model.component("comp1").physics("re").prop("SimpropMass")
         .set("Notes", new String[]{"re.R_AsH3", "re.R_C2H4", "re.R_C2H5", "re.R_C2H6", "re.R_CH3", "re.R_CH4", "re.R_GaAs", "re.R_GaC2H6", "re.R_GaC4H10", "re.R_GaC6H15", 
         "re.R_GaH2", "re.R_GaC2H5_1_surf", "re.R_GaC2H6_1_surf", "re.R_Ga_1_surf", "re.R_As_Ga_1_surf", "re.R_C2H5_1_surf", "re.R_C2H5_Ga_1_surf", "re.R_H_1_surf", "re.R_H_Ga_1_surf", "re.R_H", 
         "re.R_H2"});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {22}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {23}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H2"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6+H"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.1E-6"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.6"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"35599.9824"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {24}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"C2H5+H2"}, {"C2H5+H"}, {"CH3+H2"}, {"2CH3"}, {"C2H4+H"}, {"GaC2H5_1(ads)+As_Ga_1(ads)"}, {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"C2H6+H"}, {"2CH3"}, {"CH4+H"}, {"C2H6"}, {"C2H5"}, {"GaAs+C2H5"}, {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"3.1E-6"}, {"3.6E7"}, {"2.9E-4"}, {"2000000.0"}, {"150.0"}, {"1100000.0"}, {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"3.6"}, {"0.0"}, {"3.12"}, {"0.0"}, {"1.49"}, {"0.0"}, {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"35599.9824"}, {"0.0"}, {"36400.00504"}, {"0.0"}, {"41800.000960000005"}, {"4179.98336"}, {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {25}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"C2H5+H2"}, {"C2H5+H"}, {"CH3+H2"}, {"2CH3"}, {"C2H4+H"}, {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"C2H6+H"}, {"2CH3"}, {"CH4+H"}, {"C2H6"}, {"C2H5"}, {"GaAs"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"3.1E-6"}, {"3.6E7"}, {"2.9E-4"}, {"2000000.0"}, {"150.0"}, {"1100000.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"3.6"}, {"0.0"}, {"3.12"}, {"0.0"}, {"1.49"}, {"0.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"35599.9824"}, {"0.0"}, {"36400.00504"}, {"0.0"}, {"41800.000960000005"}, {"4179.98336"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"C2H5+H2"}, {"C2H5+H"}, {"CH3+H2"}, {"2CH3"}, {"C2H4+H"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"C2H6+H"}, {"2CH3"}, {"CH4+H"}, {"C2H6"}, {"C2H5"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"3.1E-6"}, {"3.6E7"}, {"2.9E-4"}, {"2000000.0"}, {"150.0"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"3.6"}, {"0.0"}, {"3.12"}, {"0.0"}, {"1.49"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"35599.9824"}, {"0.0"}, {"36400.00504"}, {"0.0"}, {"41800.000960000005"}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Ar", new double[][]{{0}, {0}, {0}, {5.499999999999999E-4}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("nr", new double[][]{{0}, {0}, {0}, {3.5}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("rgr1")
         .set("Er", new double[][]{{0}, {0}, {0}, {21800.02072}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re").feature("H2").set("sType", "solvent");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_GaC6H15_init", 7, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_H2_init", 10, 0);

    model.study("std1").label("\u7814\u7a76 (re)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_C2H4", "re.c_C2H5", "re.c_C2H6", "re.c_CH3", "re.c_CH4", "re.c_GaC2H6", "re.c_GaC4H10", "re.c_GaC6H15", "re.c_GaH2", "re.c_H"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u6c14\u76f8\u53cd\u5e94\u6d53\u5ea6\uff0c\u5b8c\u6574\u96c6\u5408 (re)");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_GaC6H15"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6", "re.c_GaH2"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6", "re.c_GaH2", "re.c_C2H4"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6", "re.c_GaH2", "re.c_C2H4", "re.c_C2H5"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6", "re.c_GaH2", "re.c_C2H4", "re.c_C2H5", "re.c_C2H6"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_GaC6H15", "re.c_GaC4H10", "re.c_GaC2H6", "re.c_GaH2", "re.c_C2H4", "re.c_C2H5", "re.c_C2H6", "re.c_CH4"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("linemarker", "cycle");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "GaC6H15", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "GaC4H10", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "GaC2H6", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "GaH2", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "C2H4", 4);
    model.result("pg1").feature("glob1").setIndex("legends", "C2H5", 5);
    model.result("pg1").feature("glob1").setIndex("legends", "C2H6", 6);
    model.result("pg1").feature("glob1").setIndex("legends", "CH4", 7);
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("ylog", true);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("ymin", "1e-8");
    model.result("pg1").set("ymax", "1e-1");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();

    model.component("comp1").physics().copy("re2", "re");
    model.component("comp1").physics("re2")
         .label("\u53cd\u5e94\u5de5\u7a0b - \u6c14\u76f8\u53cd\u5e94\uff0c\u7b80\u5316\u96c6\u5408 (re2)");
    model.component("comp1").physics("re2").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {5}, {6}, {7}, {8}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"C2H5+H"}, {"CH3+H2"}, {"2CH3"}, {"C2H4+H"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"2CH3"}, {"CH4+H"}, {"C2H6"}, {"C2H5"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"3.6E7"}, {"2.9E-4"}, {"2000000.0"}, {"150.0"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"0.0"}, {"3.12"}, {"0.0"}, {"1.49"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"0.0"}, {"36400.00504"}, {"0.0"}, {"41800.000960000005"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {6}, {7}, {8}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"CH3+H2"}, {"2CH3"}, {"C2H4+H"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"CH4+H"}, {"C2H6"}, {"C2H5"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"2.9E-4"}, {"2000000.0"}, {"150.0"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"3.12"}, {"0.0"}, {"1.49"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"36400.00504"}, {"0.0"}, {"41800.000960000005"}});
    model.component("comp1").physics("re2").feature("rgr1").set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {7}, {8}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"2CH3"}, {"C2H4+H"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"C2H6"}, {"C2H5"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"2000000.0"}, {"150.0"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"0.0"}, {"1.49"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, {"195000.00608000002"}, {"195000.00608000002"}, {"0.0"}, {"41800.000960000005"}});
    model.component("comp1").physics("re2").feature("rgr1").set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {8}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}, {"C2H4+H"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}, {"C2H5"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, {"1.0E18"}, {"1.0E18"}, {"150.0"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}, {"1.49"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ef", new double[][]{{195000.00608000002}, {195000.00608000002}, {195000.00608000002}, {41800.000960000005}});
    model.component("comp1").physics("re2").feature("rgr1").set("Ar", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("nr", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("Er", new int[][]{{0}, {0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("rsSequenceNo", new int[][]{{1}, {2}, {3}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, {"GaC4H10"}, {"GaC2H6"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, {"GaC2H6+C2H4"}, {"GaH2+C2H4"}});
    model.component("comp1").physics("re2").feature("rgr1").set("Af", new double[][]{{1.0E15}, {1.0E18}, {1.0E18}});
    model.component("comp1").physics("re2").feature("rgr1").set("nf", new String[][]{{"0.0"}, {"0.0"}, {"0.0"}});
    model.component("comp1").physics("re2").feature("rgr1")
         .set("Ef", new double[][]{{195000.00608000002}, {195000.00608000002}, {195000.00608000002}});
    model.component("comp1").physics("re2").feature("rgr1").set("Ar", new int[][]{{0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("nr", new int[][]{{0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1").set("Er", new int[][]{{0}, {0}, {0}});
    model.component("comp1").physics("re2").feature("rgr1_1").active(false);

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").feature("time").setSolveFor("/physics/re2", true);
    model.study("std2").label("\u7814\u7a76 (re2)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re2.c_C2H4", "re2.c_C2H5", "re2.c_GaC2H6", "re2.c_GaC4H10", "re2.c_GaC6H15", "re2.c_GaH2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").label("\u6d53\u5ea6 (re2)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").run();
    model.result("pg2").label("\u6c14\u76f8\u6d53\u5ea6\uff0c\u7b80\u5316\u96c6\u5408 (re2)");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("expr", new String[]{"re2.c_GaC6H15"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re2.c_GaC6H15", "re2.c_GaC4H10"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re2.c_GaC6H15", "re2.c_GaC4H10", "re2.c_GaC2H6"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re2.c_GaC6H15", "re2.c_GaC4H10", "re2.c_GaC2H6", "re2.c_GaH2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re2.c_GaC6H15", "re2.c_GaC4H10", "re2.c_GaC2H6", "re2.c_GaH2", "re2.c_C2H4"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"re2.c_GaC6H15", "re2.c_GaC4H10", "re2.c_GaC2H6", "re2.c_GaH2", "re2.c_C2H4", "re2.c_C2H5"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "GaC6H15", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "GaC4H10", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "GaC2H6", 2);
    model.result("pg2").feature("glob1").setIndex("legends", "GaH2", 3);
    model.result("pg2").feature("glob1").setIndex("legends", "C2H4", 4);
    model.result("pg2").feature("glob1").setIndex("legends", "C2H5", 5);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymin", "1e-8");
    model.result("pg2").set("ymax", "1e-1");
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);

    model.component("comp2").mesh().create("mesh1");

    model.component("comp2").geom("geom1").create("r1", "Rectangle");
    model.component("comp2").geom("geom1").feature("r1").set("size", new double[]{0.4, 0.1});
    model.component("comp2").geom("geom1").feature("r1").set("base", "center");
    model.component("comp2").geom("geom1").run("r1");
    model.component("comp2").geom("geom1").create("r2", "Rectangle");
    model.component("comp2").geom("geom1").feature("r2").set("pos", new double[]{0, -0.025});
    model.component("comp2").geom("geom1").feature("r2").set("size", new String[]{"1e-3", "0.05"});
    model.component("comp2").geom("geom1").feature("r2").set("rot", -10);
    model.component("comp2").geom("geom1").run("r2");
    model.component("comp2").geom("geom1").create("dif1", "Difference");
    model.component("comp2").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp2").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp2").geom("geom1").run("fin");

    model.component("comp2").physics().create("chem", "Chemistry", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std2").feature("time").setSolveFor("/physics/chem", false);

    model.component("comp2").physics("chem").create("rgr1", "ReversibleReactionGroup", 2);
    model.component("comp2").physics("chem").feature("rgr1").set("chemkin", true);
    model.component("comp2").physics("chem").feature("rgr1").set("kinetics", "gaas_cvd_reaction_kinetics.txt");
    model.component("comp2").physics("chem").prop("specNumbering").set("specNumbering", "on");
    model.component("comp2").physics("chem").feature("rgr1").set("Keq0dep", new String[]{});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("chemkinSpeciesLabel", new String[]{"AsH3", "C2H6", "C", "C2H5", "C2H4", "H_1(ads)", "GaH2", "H", "GaC2H6", "H2", 
         "C2H5_1(ads)", "GaAs", "CH4", "Ga_1(ads)", "CH3", "H_Ga_1(ads)", "GaC6H15", "GaC4H10", "As_Ga_1(ads)", "GaC2H5_1(ads)", 
         "C2H5_Ga_1(ads)", "GaC2H6_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
         "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5+H2", "C2H5+H", "CH3+H2", "2CH3", "C2H4+H", "2H+H2", "GaC6H15", 
         "GaC2H6", "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", 
         "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H6+H", "2CH3", "CH4+H", "C2H6", "C2H5", "2H2", "GaC2H5_1(ads)+2C2H5", 
         "GaC2H6_1(ads)", "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", 
         "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "3.1E-6", "3.6E7", "2.9E-4", "2000000.0", "150.0", "1000000.0", "1.453E-4", 
         "1.822E-4", "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", 
         "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "3.6", "0.0", "3.12", "0.0", "1.49", "0.0", "0.5", 
         "0.5", "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "35599.9824", "0.0", "36400.00504", "0.0", "41800.000960000005", "0.0", "20900.000480000002", 
         "0.0", "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", 
         "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "5.499999999999999E-4", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "3.5", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "21800.02072", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Keq0dep", new String[]{"1", "1", "1", "0", "1", "1", "1", "1", "1", "1", 
         "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", 
         "1", "1", "1", "1", "1"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("kf", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("kfUnit", new String[]{"m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^3*s^-1*mol^-1", "m^6*s^-1*mol^-2", "m^1*s^-1*mol^-0", 
         "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^1*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", 
         "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^0*s^-1*mol^-0", "m^2*s^-1*mol^-1", "m^2*s^-1*mol^-1"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("krUnit", new String[]{"m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^3*s^-1*mol^-1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-3*s^-1*mol^--1", "m^-2*s^-1*mol^--1", 
         "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", 
         "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1", "m^-2*s^-1*mol^--1"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("reactionRate", new String[]{"chem.kf_cr1*chem.c_GaC6H15", "chem.kf_cr2*chem.c_GaC4H10", "chem.kf_cr3*chem.c_GaC2H6", "chem.kf_cr4*chem.c_C2H5*chem.c_H2-chem.kr_cr4*chem.c_C2H6*chem.c_H", "chem.kf_cr5*chem.c_C2H5*chem.c_H", "chem.kf_cr6*chem.c_CH3*chem.c_H2", "chem.kf_cr7*chem.c_CH3^2", "chem.kf_cr8*chem.c_C2H4*chem.c_H", "chem.kf_cr9*chem.c_H^2*chem.c_H2", "chem.kf_cr10*chem.c_GaC6H15", 
         "chem.kf_cr11*chem.c_GaC2H6", "chem.kf_cr12*chem.csurf_GaC2H6_1_surf", "chem.kf_cr13*chem.c_GaH2", "chem.kf_cr14*chem.csurf_GaC2H6_1_surf", "chem.kf_cr15*chem.c_AsH3", "chem.kf_cr16*chem.c_C2H5", "chem.kf_cr17*chem.csurf_C2H5_1_surf", "chem.kf_cr18*chem.c_C2H5", "chem.kf_cr19*chem.csurf_C2H5_Ga_1_surf", "chem.kf_cr20*chem.csurf_C2H5_1_surf", 
         "chem.kf_cr21*chem.csurf_C2H5_Ga_1_surf", "chem.kf_cr22*chem.csurf_H_1_surf", "chem.kf_cr23*chem.csurf_H_Ga_1_surf", "chem.kf_cr24*chem.csurf_GaC2H5_1_surf*chem.csurf_As_Ga_1_surf", "chem.kf_cr25*chem.csurf_Ga_1_surf*chem.csurf_As_Ga_1_surf"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("thirdBody", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("Update", "maximumSpeciesNumber");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Species", new String[]{"AsH3", "C2H6", "C", "C2H5", "C2H4", "H_1_surf", "GaH2", "H", "GaC2H6", "H2", 
         "C2H5_1_surf", "GaAs", "CH4", "Ga_1_surf", "CH3", "H_Ga_1_surf", "GaC6H15", "GaC4H10", "As_Ga_1_surf", "GaC2H5_1_surf", 
         "C2H5_Ga_1_surf", "GaC2H6_1_surf"});
    model.component("comp2").physics("chem").feature("rgr1").set("tableRow", "25");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]", "0.0[kg/mol]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"156.9063[g/mol]", "127.8452[g/mol]", "29.0611[g/mol]", "99.79208[g/mol]", "28.05312[g/mol]", "71.73896[g/mol]", "2.01596[g/mol]", "30.06908[g/mol]", "1.00798[g/mol]", "15.03454[g/mol]", 
         "16.04252[g/mol]", "77.94553[g/mol]", "144.64459[g/mol]"});

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", 
         "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", "107.4[K]", 
         "107.4[K]", "107.4[K]", "107.4[K]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", 
         "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]", "(3.458*(10^(-10)))[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", 
         "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "13");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"GaC6H15", "GaC4H10", "C2H5", "GaC2H6", "C2H4", "GaH2", "H2", "C2H6", "H", "CH3", 
         "CH4", "AsH3", "GaAs"});
    model.component("comp2").physics("chem").create("C", "SpeciesChem", 2);
    model.component("comp2").physics("chem").feature("C").set("specName", "C");
    model.component("comp2").physics("chem").feature("C").set("cLock", true);
    model.component("comp2").physics("chem").feature("C").set("specLabel", "C");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("chemkinLabel", new String[]{"As_Ga(s)", "Ga(s)", "GaC2H6(s)", "GaC2H5(s)", "C2H5(s)", "H(s)", "C2H5_Ga(s)", "H_Ga(s)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("specLabel", new String[]{"As_Ga_1(ads)", "Ga_1(ads)", "GaC2H6_1(ads)", "GaC2H5_1(ads)", "C2H5_1(ads)", "H_1(ads)", "C2H5_Ga_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("SurfaceSpeciesGroup", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("sigmak", new String[]{"1", "1", "1", "1", "1", "1", "1", "1"});
    model.component("comp2").physics("chem").feature("rgr1").set("gamman", new String[]{"7.75E-5"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("Update", "none");
    model.component("comp2").physics("chem").feature().remove("C");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("transport", "gaas_cvd_transp.txt");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13", "14"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C", "C2H4", "C2H5", "C2H6", "CH3", "CH4", "GaAs", "GaC2H6", "GaC4H10", 
         "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "(3.458*(10^(-10)))[m]", "4.163e-10[m]", "4.291e-10[m]", "3.458e-10[m]", "3.458e-10[m]", "3.458e-10[m]", "3.458e-10[m]", "4.72e-10[m]", "5.487e-10[m]", 
         "6.245e-10[m]", "3.664e-10[m]", "1.825e-10[m]", "2.827e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "107.4[K]", "224.7", "184.5", "107.4", "107.4", "107.4", "107.4", "554.8", "516.7", 
         "478.6", "345.3", "2.31", "59.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0.0", "0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", 
         "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", 
         "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"156.9063[g/mol]", "0.0[kg/mol]", "127.8452[g/mol]", "29.0611[g/mol]", "99.79208[g/mol]", "28.05312[g/mol]", "71.73896[g/mol]", "2.01596[g/mol]", "30.06908[g/mol]", "1.00798[g/mol]", 
         "15.03454[g/mol]", "16.04252[g/mol]", "77.94553[g/mol]", "144.64459[g/mol]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("thermo", "gaas_cvd_thermo.txt");
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1").set("TypeSelection", "NASA");
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("speSequence", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
         "11", "12", "13", "14", "s1", "s2", "s3", "s4", "s5", "s6", 
         "s7", "s8"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("Species", new String[]{"AsH3", "C", "C2H4", "C2H5", "C2H6", "CH3", "CH4", "GaAs", "GaC2H6", "GaC4H10", 
         "GaC6H15", "GaH2", "H", "H2", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", 
         "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoTlo", new String[]{"300.0", "300[K]", "200.0", "200.0", "200.0", "200.0", "200.0", "300.0", "300.0", "300.0", 
         "300.0", "300.0", "200.0", "200.0", "300.0", "300.0", "300.0", "300.0", "300.0", "300.0", 
         "200.0", "200.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoTmid", new String[]{"1000.0", "1000[K]", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", 
         "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", "1000.0", 
         "1000.0", "1000.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoThi", new String[]{"5000.0", "1000[K]", "3500.0", "3000.0", "3000.0", "3500.0", "3500.0", "5000.0", "5000.0", "5000.0", 
         "5000.0", "5000.0", "3000.0", "3000.0", "5000.0", "5000.0", "5000.0", "5000.0", "5000.0", "5000.0", 
         "3000.0", "3000.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo1", new String[]{"0", "0", "3.95920148", "4.30646568", "4.29142492", "3.6735904", "5.14987613", "0", "0", "0", 
         "0", "0", "2.5", "2.34433112", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo2", new String[]{"0", "0", "-0.00757052247", "-0.00418658892", "-0.0055015427", "0.00201095175", "-0.0136709788", "0", "0", "0", 
         "0", "0", "7.05332819E-13", "0.00798052075", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo3", new String[]{"0", "0", "5.70990292E-5", "4.97142807E-5", "5.99438288E-5", "5.73021856E-6", "4.91800599E-5", "0", "0", "0", 
         "0", "0", "-1.99591964E-15", "-1.9478151E-5", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo4", new String[]{"0", "0", "-6.91588753E-8", "-5.99126606E-8", "-7.08466285E-8", "-6.87117425E-9", "-4.84743026E-8", "0", "0", "0", 
         "0", "0", "2.30081632E-18", "2.01572094E-8", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo5", new String[]{"0", "0", "2.69884373E-11", "2.30509004E-11", "2.68685771E-11", "2.54385734E-12", "1.66693956E-11", "0", "0", "0", 
         "0", "0", "-9.27732332E-22", "-7.37611761E-12", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo6", new String[]{"0", "0", "5089.77593", "12841.6265", "-11522.2055", "16444.9988", "-10246.6476", "0", "0", "0", 
         "0", "0", "25473.6599", "-917.935173", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaLo7", new String[]{"0", "0", "4.09733096", "4.70720924", "2.66682316", "1.60456433", "-4.64130376", "0", "0", "0", 
         "0", "0", "-0.446682853", "0.683010238", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi1", new String[]{"0", "0", "2.03611116", "1.95465642", "1.0718815", "2.28571772", "0.074851495", "0", "0", "0", 
         "0", "0", "2.50000001", "3.3372792", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi2", new String[]{"0", "0", "0.0146454151", "0.0173972722", "0.0216852677", "0.00723990037", "0.0133909467", "0", "0", "0", 
         "0", "0", "-2.30842973E-11", "-4.94024731E-5", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi3", new String[]{"0", "0", "-6.71077915E-6", "-7.98206668E-6", "-1.00256067E-5", "-2.98714348E-6", "-5.73285809E-6", "0", "0", "0", 
         "0", "0", "1.61561948E-14", "4.99456778E-7", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi4", new String[]{"0", "0", "1.47222923E-9", "1.75217689E-9", "2.21412001E-9", "5.95684644E-10", "1.22292535E-9", "0", "0", "0", 
         "0", "0", "-4.73515235E-18", "-1.79566394E-10", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi5", new String[]{"0", "0", "-1.25706061E-13", "-1.49641576E-13", "-1.9000289E-13", "-4.67154394E-14", "-1.0181523E-13", "0", "0", "0", 
         "0", "0", "4.98197357E-22", "2.00255376E-14", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi6", new String[]{"0", "0", "4939.88614", "12857.52", "-11426.3932", "16775.5843", "-9468.34459", "0", "0", "0", 
         "0", "0", "25473.6599", "-950.158922", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("SpeciesThermoaHi7", new String[]{"0", "0", "10.3053693", "13.4624343", "15.1156107", "8.48007179", "18.437318", "0", "0", "0", 
         "0", "0", "-0.446682914", "-3.20502331", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("hs", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").feature("sthm1")
         .set("Cps", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.0[kg/mol]", "0.028053759999999997", "0.0290617", "0.03006964", "0.01503482", "0.016042760000000003", "0.14464159999999998", "0.09978964", "0.1278434", 
         "0.15690510000000002", "0.07173588", "0.0010079400000000001", "0.0020158800000000003"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.09878169999999999", "0.09978964", "0.06972", "0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("rgr1").set("chemkin", false);
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H5+H"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"2H+H2"}, 
         {"GaC6H15"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"2CH3"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"2H2"}, 
         {"GaC2H5_1(ads)+2C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"3.6E7"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1000000.0"}, 
         {"1.453E-4"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"0.0"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"CH3+H2"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"2H+H2"}, 
         {"GaC6H15"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"CH4+H"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"2H2"}, 
         {"GaC2H5_1(ads)+2C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"2.9E-4"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1000000.0"}, 
         {"1.453E-4"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"3.12"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"36400.00504"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"2CH3"}, 
         {"C2H4+H"}, 
         {"2H+H2"}, 
         {"GaC6H15"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H6"}, 
         {"C2H5"}, 
         {"2H2"}, 
         {"GaC2H5_1(ads)+2C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"2000000.0"}, 
         {"150.0"}, 
         {"1000000.0"}, 
         {"1.453E-4"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"0.0"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"C2H4+H"}, 
         {"2H+H2"}, 
         {"GaC6H15"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"C2H5"}, 
         {"2H2"}, 
         {"GaC2H5_1(ads)+2C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"150.0"}, 
         {"1000000.0"}, 
         {"1.453E-4"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"1.49"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"41800.000960000005"}, 
         {"0.0"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new int[][]{{1}, {2}, {3}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19}, {20}, {21}, {22}, {23}, {24}, {25}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[][]{{"GaC6H15"}, 
         {"GaC4H10"}, 
         {"GaC2H6"}, 
         {"2H+H2"}, 
         {"GaC6H15"}, 
         {"GaC2H6"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaH2"}, 
         {"GaC2H6_1(ads)"}, 
         {"AsH3"}, 
         {"C2H5"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"H_1(ads)"}, 
         {"H_Ga_1(ads)"}, 
         {"GaC2H5_1(ads)+As_Ga_1(ads)"}, 
         {"Ga_1(ads)+As_Ga_1(ads)"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[][]{{"GaC4H10+C2H5"}, 
         {"GaC2H6+C2H4"}, 
         {"GaH2+C2H4"}, 
         {"2H2"}, 
         {"GaC2H5_1(ads)+2C2H5"}, 
         {"GaC2H6_1(ads)"}, 
         {"GaC2H6"}, 
         {"Ga_1(ads)+2H"}, 
         {"Ga_1(ads)+C2H5+H"}, 
         {"As_Ga_1(ads)+3H"}, 
         {"C2H5_1(ads)"}, 
         {"C2H5"}, 
         {"C2H5_Ga_1(ads)"}, 
         {"C2H5"}, 
         {"C2H4+H_1(ads)"}, 
         {"C2H4+H_Ga_1(ads)"}, 
         {"H"}, 
         {"H"}, 
         {"GaAs+C2H5"}, 
         {"GaAs"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[][]{{"1.0E15"}, 
         {"1.0E18"}, 
         {"1.0E18"}, 
         {"1000000.0"}, 
         {"1.453E-4"}, 
         {"1.822E-4"}, 
         {"5.0E8"}, 
         {"2.148E-4"}, 
         {"5.0E11"}, 
         {"1.133E-4"}, 
         {"3.377E-4"}, 
         {"7.9E11"}, 
         {"1.858E-4"}, 
         {"7.9E11"}, 
         {"2.5E10"}, 
         {"2.5E10"}, 
         {"720000.0"}, 
         {"720000.0"}, 
         {"1100000.0"}, 
         {"1100000.0"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[][]{{"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.5"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}, 
         {"0.0"}});

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[][]{{"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"195000.00608000002"}, 
         {"0.0"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"146000.01056"}, 
         {"0.0"}, 
         {"134000.00568"}, 
         {"20900.000480000002"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"0.0"}, 
         {"150999.97424"}, 
         {"134000.00568"}, 
         {"134000.00568"}, 
         {"67400.01416"}, 
         {"67400.01416"}, 
         {"4179.98336"}, 
         {"4179.98336"}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 9);
    model.component("comp2").physics("chem").create("rgr1_1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_1").set("formula", "2H+H2=>2H2");
    model.component("comp2").physics("chem").feature("rgr1_1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_1").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_1").set("Af", "1000000.0");
    model.component("comp2").physics("chem").feature("rgr1_1").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_1").set("Ef", "0.0");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "10", "11", "12", "13", "14", "15", "16", 
         "17", "18", "19", "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaC6H15", "GaC2H6", "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", 
         "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "GaC2H5_1(ads)+2C2H5", "GaC2H6_1(ads)", "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", 
         "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1.453E-4", "1.822E-4", "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", 
         "7.9E11", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.5", "0.5", 
         "0.0", "0.5", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "20900.000480000002", "0.0", "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", 
         "150999.97424", "0.0", "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.09878169999999999", "0.09978964", "0.06972", "0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.0290617", "0.14464159999999998", "0.09978964", "0.1278434", "0.15690510000000002", "0.07173588"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "184.5", "107.4", "554.8", "516.7", "478.6", "345.3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "4.291e-10[m]", "3.458e-10[m]", "4.72e-10[m]", "5.487e-10[m]", "6.245e-10[m]", "3.664e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "8");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 10);
    model.component("comp2").physics("chem").create("rgr1_2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_2").set("formula", "GaC6H15=>GaC2H5_1(ads)+2C2H5");
    model.component("comp2").physics("chem").feature("rgr1_2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_2").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_2").set("Af", "1.453E-4");
    model.component("comp2").physics("chem").feature("rgr1_2").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_2").set("Ef", "20900.000480000002");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "11", "12", "13", "14", "15", "16", "17", 
         "18", "19", "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaC2H6", "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", 
         "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "GaC2H6_1(ads)", "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", 
         "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1.822E-4", "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", 
         "1.858E-4", "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", 
         "0.5", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "0.0", "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", 
         "0.0", "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"2", "3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"GaC2H6_1(ads)", "Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.09978964", "0.06972", "0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5", "6"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.14464159999999998", "0.09978964", "0.1278434", "0.07173588"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Charge", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "107.4", "554.8", "516.7", "345.3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "3.458e-10[m]", "4.72e-10[m]", "5.487e-10[m]", "3.664e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "6");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "GaAs", "GaC2H6", "GaC4H10", "GaH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 11);
    model.component("comp2").physics("chem").create("rgr1_3", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_3").set("formula", "GaC2H6=>GaC2H6_1(ads)");
    model.component("comp2").physics("chem").feature("rgr1_3").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_3").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_3").set("Af", "1.822E-4");
    model.component("comp2").physics("chem").feature("rgr1_3").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_3").set("Ef", "0.0");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "12", "13", "14", "15", "16", "17", "18", 
         "19", "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaC2H6_1(ads)", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", 
         "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "GaC2H6", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", 
         "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "5.0E8", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", 
         "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", "0.5", 
         "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "146000.01056", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", 
         "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.06972", "0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.14464159999999998", "0.1278434", "0.07173588"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "107.4", "516.7", "345.3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]", "3.664e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "5");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "GaAs", "GaC4H10", "GaH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});

    return model;
  }

  public static Model run6(Model model) {
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 12);
    model.component("comp2").physics("chem").create("rgr1_4", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_4").set("formula", "GaC2H6_1(ads)=>GaC2H6");
    model.component("comp2").physics("chem").feature("rgr1_4").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_4").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_4").set("Af", "5.0E8");
    model.component("comp2").physics("chem").feature("rgr1_4").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_4").set("Ef", "146000.01056");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "13", "14", "15", "16", "17", "18", "19", 
         "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaH2", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", 
         "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "Ga_1(ads)+2H", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", 
         "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "2.148E-4", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", 
         "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", 
         "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "0.0", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", 
         "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"3", "4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"Ga_1(ads)", "As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.06972", "0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4", "5"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.14464159999999998", "0.1278434", "0.07173588"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "107.4", "516.7", "345.3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("mu", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]", "3.664e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "5");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "GaAs", "GaC4H10", "GaH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 13);
    model.component("comp2").physics("chem").create("rgr1_5", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_5").set("formula", "GaH2=>Ga_1(ads)+2H");
    model.component("comp2").physics("chem").feature("rgr1_5").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_5").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_5").set("Af", "2.148E-4");
    model.component("comp2").physics("chem").feature("rgr1_5").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_5").set("Ef", "0.0");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "14", "15", "16", "17", "18", "19", "20", 
         "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaC2H6_1(ads)", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", 
         "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "Ga_1(ads)+C2H5+H", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", 
         "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "5.0E11", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", 
         "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "134000.00568", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", 
         "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "4");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 14);
    model.component("comp2").physics("chem").create("rgr1_6", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_6").set("formula", "GaC2H6_1(ads)=>Ga_1(ads)+C2H5+H");
    model.component("comp2").physics("chem").feature("rgr1_6").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_6").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_6").set("Af", "5.0E11");
    model.component("comp2").physics("chem").feature("rgr1_6").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_6").set("Ef", "134000.00568");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "15", "16", "17", "18", "19", "20", "21", 
         "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "AsH3", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", 
         "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "As_Ga_1(ads)+3H", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", 
         "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1.133E-4", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", 
         "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.5", "0.0", "0.5", "0.0", "0.0", "0.0", 
         "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "20900.000480000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", "134000.00568", 
         "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sSequenceNo", new String[]{"4", "5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0749216", "0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurCharge", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "4"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.07794542", "0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"259.8", "224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.145e-10[m]", "4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "4");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"AsH3", "C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");

    return model;
  }

  public static Model run7(Model model) {
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 15);
    model.component("comp2").physics("chem").create("rgr1_7", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_7").set("formula", "AsH3=>As_Ga_1(ads)+3H");
    model.component("comp2").physics("chem").feature("rgr1_7").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_7").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_7").set("Af", "1.133E-4");
    model.component("comp2").physics("chem").feature("rgr1_7").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_7").set("Ef", "20900.000480000002");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "16", "17", "18", "19", "20", "21", "22", 
         "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", 
         "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", 
         "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "3.377E-4", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", "720000.0", 
         "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.0", "0.5", "0.0", "0.0", "0.0", "0.0", 
         "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "0.0", "150999.97424", "0.0", "150999.97424", "134000.00568", "134000.00568", "67400.01416", 
         "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"5", "6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0290617", "0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 16);
    model.component("comp2").physics("chem").create("rgr1_8", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_8").set("formula", "C2H5=>C2H5_1(ads)");
    model.component("comp2").physics("chem").feature("rgr1_8").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_8").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_8").set("Af", "3.377E-4");
    model.component("comp2").physics("chem").feature("rgr1_8").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_8").set("Ef", "0.0");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "17", "18", "19", "20", "21", "22", "23", 
         "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5_1(ads)", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", 
         "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H5", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", 
         "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "7.9E11", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", 
         "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.5", "0.0", "0.0", "0.0", "0.0", "0.0", 
         "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "150999.97424", "0.0", "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", 
         "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 17);
    model.component("comp2").physics("chem").create("rgr1_9", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_9").set("formula", "C2H5_1(ads)=>C2H5");
    model.component("comp2").physics("chem").feature("rgr1_9").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_9").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_9").set("Af", "7.9E11");
    model.component("comp2").physics("chem").feature("rgr1_9").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_9").set("Ef", "150999.97424");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "18", "19", "20", "21", "22", "23", "24", 
         "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", 
         "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H5_Ga_1(ads)", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", 
         "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1.858E-4", "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", 
         "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.5", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", 
         "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "0.0", "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", 
         "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", 
         "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"6", "7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0290617", "0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});

    return model;
  }

  public static Model run8(Model model) {
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 18);
    model.component("comp2").physics("chem").create("rgr1_10", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_10").set("formula", "C2H5=>C2H5_Ga_1(ads)");
    model.component("comp2").physics("chem").feature("rgr1_10").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_10").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_10").set("Af", "1.858E-4");
    model.component("comp2").physics("chem").feature("rgr1_10").set("nf", "0.5");
    model.component("comp2").physics("chem").feature("rgr1_10").set("Ef", "0.0");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "19", "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H5", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "7.9E11", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "150999.97424", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 19);
    model.component("comp2").physics("chem").create("rgr1_11", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_11").set("formula", "C2H5_Ga_1(ads)=>C2H5");
    model.component("comp2").physics("chem").feature("rgr1_11").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_11").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_11").set("Af", "7.9E11");
    model.component("comp2").physics("chem").feature("rgr1_11").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_11").set("Ef", "150999.97424");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "20", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H4+H_1(ads)", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "2.5E10", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "134000.00568", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"7", "8"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurSpecies", new String[]{"H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0010079400000000001", "0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.028053759999999997", "0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("epsilonkb", new String[]{"224.7", "107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"4.163e-10[m]", "3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Species", new String[]{"C2H4", "GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 20);
    model.component("comp2").physics("chem").create("rgr1_12", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_12").set("formula", "C2H5_1(ads)=>C2H4+H_1(ads)");
    model.component("comp2").physics("chem").feature("rgr1_12").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_12").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_12").set("Af", "2.5E10");
    model.component("comp2").physics("chem").feature("rgr1_12").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_12").set("Ef", "134000.00568");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "21", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "C2H5_Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "C2H4+H_Ga_1(ads)", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "2.5E10", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "134000.00568", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});

    return model;
  }

  public static Model run9(Model model) {
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sSequenceNo", new String[]{"8"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurSpecies", new String[]{"H_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("SurMolarWeight", new String[]{"0.0010079400000000001"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("SurCharge", new String[]{"0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "2");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 21);
    model.component("comp2").physics("chem").create("rgr1_13", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_13").set("formula", "C2H5_Ga_1(ads)=>C2H4+H_Ga_1(ads)");
    model.component("comp2").physics("chem").feature("rgr1_13").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_13").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_13").set("Af", "2.5E10");
    model.component("comp2").physics("chem").feature("rgr1_13").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_13").set("Ef", "134000.00568");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "22", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "H_1(ads)", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "H", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "720000.0", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "67400.01416", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ar", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nr", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Er", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "2");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 22);
    model.component("comp2").physics("chem").create("rgr1_14", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_14").set("formula", "H_1(ads)=>H");
    model.component("comp2").physics("chem").feature("rgr1_14").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_14").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_14").set("Af", "720000.0");
    model.component("comp2").physics("chem").feature("rgr1_14").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_14").set("Ef", "67400.01416");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "23", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "H_Ga_1(ads)", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "H", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "720000.0", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "67400.01416", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1").set("Ar", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("nr", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("Er", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "2");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 23);
    model.component("comp2").physics("chem").create("rgr1_15", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_15").set("formula", "H_Ga_1(ads)=>H");
    model.component("comp2").physics("chem").feature("rgr1_15").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_15").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_15").set("Af", "720000.0");
    model.component("comp2").physics("chem").feature("rgr1_15").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_15").set("Ef", "67400.01416");
    model.component("comp2").physics("chem").feature("rgr1")
         .set("rsSequenceNo", new String[]{"1", "2", "3", "24", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "GaC2H5_1(ads)+As_Ga_1(ads)", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "GaAs+C2H5", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1100000.0", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("nf", new String[]{"0.0", "0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "4179.98336", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1").set("Ar", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("nr", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("Er", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");

    return model;
  }

  public static Model run10(Model model) {
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1", "2"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("MolarWeight", new String[]{"0.14464159999999998", "0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("Density", new String[]{"1[kg/m^3]", "1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0", "0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"107.4", "516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0", "0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("sigma", new String[]{"3.458e-10[m]", "5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1")
         .set("k", new String[]{"0.015[W/(m*K)]", "0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "2");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaAs", "GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 24);
    model.component("comp2").physics("chem").create("rgr1_16", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_16")
         .set("formula", "GaC2H5_1(ads)+As_Ga_1(ads)=>GaAs+C2H5");
    model.component("comp2").physics("chem").feature("rgr1_16").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_16").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_16").set("Af", "1100000.0");
    model.component("comp2").physics("chem").feature("rgr1_16").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_16").set("Ef", "4179.98336");
    model.component("comp2").physics("chem").feature("rgr1").set("rsSequenceNo", new String[]{"1", "2", "3", "25"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6", "Ga_1(ads)+As_Ga_1(ads)"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4", "GaAs"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18", "1100000.0"});
    model.component("comp2").physics("chem").feature("rgr1").set("nf", new String[]{"0.0", "0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002", "4179.98336"});
    model.component("comp2").physics("chem").feature("rgr1").set("Ar", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("nr", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("Er", new String[]{"0", "0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("MolarWeight", new String[]{"0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Density", new String[]{"1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sigma", new String[]{"5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("k", new String[]{"0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "1");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaAs").set("dependent", "0");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "0");
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", 25);
    model.component("comp2").physics("chem").create("rgr1_17", "ReactionChem");
    model.component("comp2").physics("chem").feature("rgr1_17").set("formula", "Ga_1(ads)+As_Ga_1(ads)=>GaAs");
    model.component("comp2").physics("chem").feature("rgr1_17").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rgr1_17").setIndex("useArrhenius", "1", 0);
    model.component("comp2").physics("chem").feature("rgr1_17").set("Af", "1100000.0");
    model.component("comp2").physics("chem").feature("rgr1_17").set("nf", "0.0");
    model.component("comp2").physics("chem").feature("rgr1_17").set("Ef", "4179.98336");
    model.component("comp2").physics("chem").feature("rgr1").set("rsSequenceNo", new String[]{"1", "2", "3"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("fwdSpecies", new String[]{"GaC6H15", "GaC4H10", "GaC2H6"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("revSpecies", new String[]{"GaC4H10+C2H5", "GaC2H6+C2H4", "GaH2+C2H4"});
    model.component("comp2").physics("chem").feature("rgr1").set("Af", new String[]{"1.0E15", "1.0E18", "1.0E18"});
    model.component("comp2").physics("chem").feature("rgr1").set("nf", new String[]{"0.0", "0.0", "0.0"});
    model.component("comp2").physics("chem").feature("rgr1")
         .set("Ef", new String[]{"195000.00608000002", "195000.00608000002", "195000.00608000002"});
    model.component("comp2").physics("chem").feature("rgr1").set("Ar", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("nr", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("Er", new String[]{"0", "0", "0"});
    model.component("comp2").physics("chem").feature("rgr1").set("createThisReactiondescr", "3");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("rsSequenceNo", new String[]{"1"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("MolarWeight", new String[]{"0.1278434"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Density", new String[]{"1[kg/m^3]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Charge", new String[]{"0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("epsilonkb", new String[]{"516.7"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("mu", new String[]{"0.0"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("sigma", new String[]{"5.487e-10[m]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("k", new String[]{"0.015[W/(m*K)]"});
    model.component("comp2").physics("chem").feature("sg_rgr1").set("tableRow", "1");
    model.component("comp2").physics("chem").feature("sg_rgr1").set("Species", new String[]{"GaC4H10"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("hasEquilibriumReaction", false);
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("EquilibriumSpeciesVector", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependent", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("dependentReactions", "");
    model.component("comp2").physics("chem").prop("EquilibriumReactionProperty").set("equliSet", "0");
    model.component("comp2").physics("chem").feature("H").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC6H15").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6").set("dependent", "0");
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaH2").set("dependent", "0");
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("AsH3").set("dependent", "0");
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("C2H4").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("GaAs").set("dependent", "0");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Species", new String[]{"AsH3", "C2H4", "C2H5", "GaAs", "GaC2H6", "GaC4H10", "GaC6H15", "GaH2", "H", "H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SpeciesInputType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Notes", new String[]{"chem.R_AsH3", "chem.R_C2H4", "chem.R_C2H5", "chem.R_GaAs", "chem.R_GaC2H6", "chem.R_GaC4H10", "chem.R_GaC6H15", "chem.R_GaH2", "chem.R_H", "chem.R_H2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Unit", new String[]{"mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3", "mol/m^3"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cAsH3", "cC2H4", "cC2H5", "cGaAs", "cGaC2H6", "cGaC4H10", "cGaC6H15", "cGaH2", "cH", "cH2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidReactionRate", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpecies", new String[]{"As_Ga_1(ads)", "C2H5_1(ads)", "C2H5_Ga_1(ads)", "GaC2H5_1(ads)", "GaC2H6_1(ads)", "Ga_1(ads)", "H_1(ads)", "H_Ga_1(ads)"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesType", new String[]{"\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf", "\u53d8\u91cf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceConcentrationInput", new String[]{"UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined", "UserDefined"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("Rssurf", new String[]{"chem.R_As_Ga_1_surf", "chem.R_C2H5_1_surf", "chem.R_C2H5_Ga_1_surf", "chem.R_GaC2H5_1_surf", "chem.R_GaC2H6_1_surf", "chem.R_Ga_1_surf", "chem.R_H_1_surf", "chem.R_H_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf0", new String[]{"cAs_Ga_1_surf", "cC2H5_1_surf", "cC2H5_Ga_1_surf", "cGaC2H5_1_surf", "cGaC2H6_1_surf", "cGa_1_surf", "cH_1_surf", "cH_Ga_1_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpecies", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesType", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesRate", new String[]{});
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "18");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "10");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSolidSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveAqueousSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "8");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", true);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"none", "none", "none", "none", "none", "none", "none", "none", "none", "none", 
         "none", "none", "none", "none", "none", "none", "none", "none"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("Update", "1");
    model.component("comp2").physics("chem").feature("rgr1_17").active(false);
    model.component("comp2").physics("chem").feature("H2").set("sType", "solvent");
    model.component("comp2").physics("chem").feature("GaAs").set("cLock", true);
    model.component("comp2").physics("chem").feature("GaC2H5_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("GaC2H6_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("Ga_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("As_Ga_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("C2H5_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("C2H5_Ga_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("H_1_surf").set("cLock", true);
    model.component("comp2").physics("chem").feature("H_Ga_1_surf").set("cLock", true);
    model.component("comp2").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/tds", false);
    model.study("std2").feature("time").setSolveFor("/physics/tds", false);

    model.component("comp2").physics("tds").prop("AdvancedSettings").set("ConvectiveTerm", "cons");
    model.component("comp2").physics("tds").field("concentration")
         .component(new String[]{"c", "c2", "c3", "c4", "c5", "c6", "c7", "c8"});
    model.component("comp2").physics("tds").field("concentration").component(1, "cGaC4H10");
    model.component("comp2").physics("tds").field("concentration").component(2, "cC2H5");
    model.component("comp2").physics("tds").field("concentration").component(3, "cH");
    model.component("comp2").physics("tds").field("concentration").component(4, "cC2H4");
    model.component("comp2").physics("tds").field("concentration").component(5, "cAsH3");
    model.component("comp2").physics("tds").field("concentration").component(6, "cGaH2");
    model.component("comp2").physics("tds").field("concentration").component(7, "cGaC2H6");
    model.component("comp2").physics("tds").field("concentration").component(8, "cGaC6H15");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cAsH3", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cC2H4", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cC2H5", 2, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", 0, 3, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cGaC2H6", 4, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cGaC4H10", 5, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cGaC6H15", 6, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cGaH2", 7, 0);

    return model;
  }

  public static Model run11(Model model) {
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "cH", 8, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "c_H2_init", 9, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_Assurf_Ga", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_C2H5surf", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_C2H5surf_Ga", 2, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_GaC2H5surf", 3, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").setIndex("csurf", 0, 4, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_Gasurf", 5, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").setIndex("csurf", "c_Hsurf", 6, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("csurf", "c_Hsurf_Ga", 7, 0);
    model.component("comp2").physics("tds").feature("cdm1").set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cGaC4H10_src", "root.comp2.chem.DXX_GaC4H10");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cC2H5_src", "root.comp2.chem.DXX_C2H5");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cH_src", "root.comp2.chem.DXX_H");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cC2H4_src", "root.comp2.chem.DXX_C2H4");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cAsH3_src", "root.comp2.chem.DXX_AsH3");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cGaH2_src", "root.comp2.chem.DXX_GaH2");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cGaC2H6_src", "root.comp2.chem.DXX_GaC2H6");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cGaC6H15_src", "root.comp2.chem.DXX_GaC6H15");
    model.component("comp2").physics("tds").create("reac1", "Reactions", 2);
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").feature("reac1")
         .setIndex("R_cGaC4H10_src", "root.comp2.chem.R_GaC4H10", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cC2H5_src", "root.comp2.chem.R_C2H5", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cH_src", "root.comp2.chem.R_H", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cC2H4_src", "root.comp2.chem.R_C2H4", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cAsH3_src", "root.comp2.chem.R_AsH3", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cGaH2_src", "root.comp2.chem.R_GaH2", 0);
    model.component("comp2").physics("tds").feature("reac1")
         .setIndex("R_cGaC2H6_src", "root.comp2.chem.R_GaC2H6", 0);
    model.component("comp2").physics("tds").feature("reac1")
         .setIndex("R_cGaC6H15_src", "root.comp2.chem.R_GaC6H15", 0);
    model.component("comp2").physics("tds").create("srf1", "SurfaceReactionsFlux", 1);
    model.component("comp2").physics("tds").feature("srf1").selection().set(4, 5, 6, 7);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cGaC4H10_src", "root.comp2.chem.Rsurf_GaC4H10", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cC2H5_src", "root.comp2.chem.Rsurf_C2H5", 0);
    model.component("comp2").physics("tds").feature("srf1").setIndex("J0_cH_src", "root.comp2.chem.Rsurf_H", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cC2H4_src", "root.comp2.chem.Rsurf_C2H4", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cAsH3_src", "root.comp2.chem.Rsurf_AsH3", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cGaH2_src", "root.comp2.chem.Rsurf_GaH2", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cGaC2H6_src", "root.comp2.chem.Rsurf_GaC2H6", 0);
    model.component("comp2").physics("tds").feature("srf1")
         .setIndex("J0_cGaC6H15_src", "root.comp2.chem.Rsurf_GaC6H15", 0);
    model.component("comp2").physics("tds").create("in1", "Inflow", 1);
    model.component("comp2").physics("tds").feature("in1").selection().set(1);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "c_AsH3_in", 4);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "c_GaC6H15_in", 7);
    model.component("comp2").physics("tds").create("out1", "Outflow", 1);
    model.component("comp2").physics("tds").feature("out1").selection().set(8);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "c_AsH3_in", 4);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "c_GaC6H15_in", 7);
    model.component("comp2").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ht", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht", false);

    model.component("comp2").physics("ht").feature("fluid1").set("k_mat", "root.comp2.chem.kxx");
    model.component("comp2").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp2").physics("ht").feature("fluid1").set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("ht").feature("fluid1").set("Cp_mat", "root.comp2.chem.Cptot");
    model.component("comp2").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp2").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp2").physics("ht").feature("hs1").selection().set(1);
    model.component("comp2").physics("ht").feature("hs1").set("Q0_src", "root.comp2.chem.Qtot");
    model.component("comp2").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp1").selection().set(1, 2, 3);
    model.component("comp2").physics("ht").feature("temp1").set("T0", "T_in");
    model.component("comp2").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht").feature("temp2").selection().set(4, 5, 6, 7);
    model.component("comp2").physics("ht").feature("temp2").set("T0", "T_surf");
    model.component("comp2").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp2").physics("ht").feature("ofl1").selection().set(8);
    model.component("comp2").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);

    model.component("comp2").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("pref", "0[atm]");
    model.component("comp2").physics("spf").feature("fp1").set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("spf").feature("fp1").set("mu_mat", "root.comp2.chem.eta");
    model.component("comp2").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp2").physics("spf").feature("inl1").selection().set(1);
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp2").physics("spf").feature("inl1").set("Uavfdf", "u_in");
    model.component("comp2").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp2").physics("spf").feature("out1").selection().set(8);
    model.component("comp2").physics("spf").feature("out1").set("p0", "p_0");
    model.component("comp2").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp2").physics("spf").feature("init1").set("p_init", "p_0");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp2.T");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.spf.pA");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "UserDefined");
    model.component("comp2").physics("chem").prop("mixture").set("rhoGas", "chem.p/R_const/chem.T*chem.M_H2");

    model.component("comp2").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp2").multiphysics().create("rfd1", "ReactingFlowDS", 2);

    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/rfd1", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", false);

    model.component("comp2").mesh("mesh1").autoMeshSize(3);
    model.component("comp2").mesh("mesh1").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/re", false);
    model.study("std3").feature("stat").setSolveFor("/physics/re2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std3").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std3").label("\u7814\u7a76\uff0c\u4e8c\u7ef4\u6a21\u578b");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u6d53\u5ea6, GaC4H10 (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cGaC4H10"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"tds.tflux_cGaC4H10x", "tds.tflux_cGaC4H10y"});
    model.result("pg3").feature("arws1").set("xnumber", 10);
    model.result("pg3").feature("arws1").set("ynumber", 10);
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u6d53\u5ea6, C2H5 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cC2H5"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"tds.tflux_cC2H5x", "tds.tflux_cC2H5y"});
    model.result("pg4").feature("arws1").set("xnumber", 10);
    model.result("pg4").feature("arws1").set("ynumber", 10);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u6d53\u5ea6, H (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cH"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("expr", new String[]{"tds.tflux_cHx", "tds.tflux_cHy"});
    model.result("pg5").feature("arws1").set("xnumber", 10);
    model.result("pg5").feature("arws1").set("ynumber", 10);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").create("sel1", "Selection");
    model.result("pg5").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").label("\u6d53\u5ea6, C2H4 (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cC2H4"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"tds.tflux_cC2H4x", "tds.tflux_cC2H4y"});
    model.result("pg6").feature("arws1").set("xnumber", 10);
    model.result("pg6").feature("arws1").set("ynumber", 10);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").label("\u6d53\u5ea6, AsH3 (tds)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cAsH3"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"tds.tflux_cAsH3x", "tds.tflux_cAsH3y"});
    model.result("pg7").feature("arws1").set("xnumber", 10);
    model.result("pg7").feature("arws1").set("ynumber", 10);
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").feature("arws1").create("sel1", "Selection");
    model.result("pg7").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("\u6d53\u5ea6, GaH2 (tds)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cGaH2"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"tds.tflux_cGaH2x", "tds.tflux_cGaH2y"});
    model.result("pg8").feature("arws1").set("xnumber", 10);
    model.result("pg8").feature("arws1").set("ynumber", 10);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").create("sel1", "Selection");
    model.result("pg8").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").label("\u6d53\u5ea6, GaC2H6 (tds)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cGaC2H6"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"tds.tflux_cGaC2H6x", "tds.tflux_cGaC2H6y"});
    model.result("pg9").feature("arws1").set("xnumber", 10);
    model.result("pg9").feature("arws1").set("ynumber", 10);
    model.result("pg9").feature("arws1").set("color", "black");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").label("\u6d53\u5ea6, GaC6H15 (tds)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"cGaC6H15"});
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("expr", new String[]{"tds.tflux_cGaC6H15x", "tds.tflux_cGaC6H15y"});
    model.result("pg10").feature("arws1").set("xnumber", 10);
    model.result("pg10").feature("arws1").set("ynumber", 10);
    model.result("pg10").feature("arws1").set("color", "black");
    model.result("pg10").feature("arws1").create("sel1", "Selection");
    model.result("pg10").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6e29\u5ea6 (ht)");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "T");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u901f\u5ea6 (spf)");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u8868\u9762");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("expr", "spf.U");
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").label("\u538b\u529b (spf)");
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").feature().create("con1", "Contour");
    model.result("pg13").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg13").feature("con1").set("showsolutionparams", "on");
    model.result("pg13").feature("con1").set("expr", "p");
    model.result("pg13").feature("con1").set("number", 40);
    model.result("pg13").feature("con1").set("levelrounding", false);
    model.result("pg13").feature("con1").set("colortable", "Rainbow");
    model.result("pg13").feature("con1").set("smooth", "internal");
    model.result("pg13").feature("con1").set("showsolutionparams", "on");
    model.result("pg13").feature("con1").set("data", "parent");
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("solutionparams", "parent");
    model.result("pg14").feature("surf1").set("expr", "nitf1.T");
    model.result("pg14").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg14").feature("surf1").set("smooth", "internal");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result("pg14").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg14").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg14").feature().create("arws1", "ArrowSurface");
    model.result("pg14").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg14").feature("arws1").set("showsolutionparams", "on");
    model.result("pg14").feature("arws1").set("solutionparams", "parent");
    model.result("pg14").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg14").feature("arws1").set("xnumber", 30);
    model.result("pg14").feature("arws1").set("ynumber", 30);
    model.result("pg14").feature("arws1").set("arrowtype", "cone");
    model.result("pg14").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg14").feature("arws1").set("showsolutionparams", "on");
    model.result("pg14").feature("arws1").set("data", "parent");
    model.result("pg14").feature("arws1").feature().create("col1", "Color");
    model.result("pg14").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg14").feature("arws1").feature("col1").set("expr", "spf.U");
    model.result("pg14").feature("arws1").feature("col1").set("colortable", "Rainbow");
    model.result("pg14").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg14").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg3").run();
    model.result("pg12").run();
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").set("legendpos", "bottom");
    model.result("pg12").run();
    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("legendpos", "bottom");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").run();
    model.result("pg10").run();
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("legendpos", "bottom");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").run();
    model.result("pg13").run();
    model.result("pg13").feature("con1").set("expr", "p-p_0");
    model.result("pg13").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", -0.2, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.2, 1, 0);
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").run();
    model.result("pg15").label("\u6d53\u5ea6\u5206\u5e03\uff0cGaC6H15 \u548c GaH2");
    model.result("pg15").set("data", "cln1");
    model.result("pg15").create("lngr1", "LineGraph");
    model.result("pg15").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg15").feature("lngr1").set("linewidth", "preference");
    model.result("pg15").feature("lngr1").label("GaC6H15");
    model.result("pg15").feature("lngr1").set("expr", "cGaC6H15");
    model.result("pg15").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccGaC6H15");
    model.result("pg15").feature("lngr1").set("xdataexpr", "x");
    model.result("pg15").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg15").feature("lngr1").set("linewidth", 2);
    model.result("pg15").feature("lngr1").set("legend", true);
    model.result("pg15").feature("lngr1").set("autoplotlabel", true);
    model.result("pg15").feature("lngr1").set("autosolution", false);
    model.result("pg15").run();
    model.result("pg15").create("lngr2", "LineGraph");
    model.result("pg15").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg15").feature("lngr2").set("linewidth", "preference");
    model.result("pg15").feature("lngr2").label("GaH2");
    model.result("pg15").feature("lngr2").set("expr", "cGaH2");
    model.result("pg15").feature("lngr2").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccGaH2");
    model.result("pg15").feature("lngr2").set("xdataexpr", "x");
    model.result("pg15").feature("lngr2").set("xdatadescr", "x \u5750\u6807");
    model.result("pg15").feature("lngr2").set("legend", true);
    model.result("pg15").feature("lngr2").set("autoplotlabel", true);
    model.result("pg15").feature("lngr2").set("autosolution", false);
    model.result("pg15").run();
    model.result("pg15").set("titletype", "none");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u6d53\u5ea6\u5206\u5e03\uff0cAsH3 \u53d8\u5316");
    model.result("pg16").set("data", "cln1");
    model.result("pg16").create("lngr1", "LineGraph");
    model.result("pg16").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg16").feature("lngr1").set("linewidth", "preference");
    model.result("pg16").feature("lngr1").set("expr", "cAsH3");
    model.result("pg16").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccAsH3");
    model.result("pg16").feature("lngr1").set("expr", "cAsH3-c_AsH3_init");
    model.result("pg16").feature("lngr1").set("titletype", "manual");
    model.result("pg16").feature("lngr1")
         .set("title", "\u4e0e\u53cd\u5e94\u5668\u521d\u59cb\u6761\u4ef6\u76f8\u6bd4\uff0c\u7837\u5316\u6c22 AsH<sub>3</sub> \u6d53\u5ea6\u7684\u53d8\u5316");
    model.result("pg16").feature("lngr1").set("xdataexpr", "x");
    model.result("pg16").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg16").feature("lngr1").set("linewidth", 2);
    model.result("pg16").feature("lngr1").set("legend", true);
    model.result("pg16").feature("lngr1").set("legendmethod", "manual");
    model.result("pg16").feature("lngr1").setIndex("legends", "AsH3", 0);
    model.result("pg16").run();
    model.result("pg16").feature("lngr1").set("titletype", "manual");
    model.result().create("pg17", "PlotGroup1D");
    model.result("pg17").run();
    model.result("pg17").label("\u6269\u6563\u7cfb\u6570 vs. \u6e29\u5ea6");
    model.result("pg17").set("data", "cln1");
    model.result("pg17").set("legendpos", "lowerright");
    model.result("pg17").create("lngr1", "LineGraph");
    model.result("pg17").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg17").feature("lngr1").set("linewidth", "preference");
    model.result("pg17").feature("lngr1").label("GaC6H15");
    model.result("pg17").feature("lngr1").set("expr", "tds.Dav_cGaC6H15");
    model.result("pg17").feature("lngr1").set("descr", "\u5e73\u5747\u6269\u6563\u7cfb\u6570");
    model.result("pg17").feature("lngr1").set("xdata", "expr");
    model.result("pg17").feature("lngr1").set("xdataexpr", "T");
    model.result("pg17").feature("lngr1").set("xdatadescr", "\u6e29\u5ea6");
    model.result("pg17").feature("lngr1").set("linewidth", 2);
    model.result("pg17").feature("lngr1").set("legend", true);
    model.result("pg17").feature("lngr1").set("autoplotlabel", true);
    model.result("pg17").feature("lngr1").set("autosolution", false);
    model.result("pg17").run();
    model.result("pg17").run();
    model.result("pg17").create("lngr2", "LineGraph");
    model.result("pg17").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg17").feature("lngr2").set("linewidth", "preference");
    model.result("pg17").feature("lngr2").label("AsH3");
    model.result("pg17").feature("lngr2").set("expr", "tds.Dav_cAsH3");
    model.result("pg17").feature("lngr2").set("descr", "\u5e73\u5747\u6269\u6563\u7cfb\u6570");
    model.result("pg17").feature("lngr2").set("xdata", "expr");
    model.result("pg17").feature("lngr2").set("xdataexpr", "T");
    model.result("pg17").feature("lngr2").set("xdatadescr", "\u6e29\u5ea6");
    model.result("pg17").feature("lngr2").set("linewidth", 2);
    model.result("pg17").feature("lngr2").set("legend", true);
    model.result("pg17").feature("lngr2").set("autoplotlabel", true);
    model.result("pg17").feature("lngr2").set("autosolution", false);
    model.result("pg17").run();
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("manualgrid", true);
    model.result("pg17").set("xspacing", 100);
    model.result("pg17").set("yspacing", "1e-3");
    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup1D");
    model.result("pg18").run();
    model.result("pg18").label("\u70ed\u5bfc\u7387 H2");
    model.result("pg18").set("data", "cln1");
    model.result("pg18").set("legendpos", "lowerright");
    model.result("pg18").create("lngr1", "LineGraph");
    model.result("pg18").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg18").feature("lngr1").set("linewidth", "preference");
    model.result("pg18").feature("lngr1").set("expr", "ht.kmean");
    model.result("pg18").feature("lngr1").set("descr", "\u5e73\u5747\u6709\u6548\u5bfc\u70ed\u7cfb\u6570");
    model.result("pg18").feature("lngr1").set("xdata", "expr");
    model.result("pg18").feature("lngr1").set("xdataexpr", "T");
    model.result("pg18").feature("lngr1").set("xdatadescr", "\u6e29\u5ea6");
    model.result("pg18").feature("lngr1").set("linewidth", 2);
    model.result("pg18").feature("lngr1").set("legend", true);
    model.result("pg18").feature("lngr1").set("legendmethod", "manual");
    model.result("pg18").feature("lngr1").setIndex("legends", "H2", 0);
    model.result("pg18").run();

    return model;
  }

  public static Model run12(Model model) {
    model.result("pg18").set("titletype", "none");
    model.result("pg18").run();
    model.result().create("pg19", "PlotGroup2D");
    model.result("pg19").set("data", "dset3");
    model.result("pg19").set("titletype", "manual");
    model.result("pg19")
         .set("title", "\u8868\u9762: \u6d53\u5ea6 (mol/m<sup>3</sup>) \u9762\u7bad\u5934: \u603b\u901a\u91cf");
    model.result("pg19").set("showlegendsunit", true);
    model.result("pg19").set("showlegendstitle", true);
    model.result("pg19").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, GaC4H10, C2H5, H, C2H4 (tds)");
    model.result("pg19").set("plotarrayenable", true);
    model.result("pg19").set("arrayshape", "linear");
    model.result("pg19").set("legendpos", "rightdouble");
    model.result("pg19").set("arrayaxis", "y");
    model.result("pg19").set("relpadding", 0.5);
    model.result("pg19").create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("expr", new String[]{"cGaC4H10"});
    model.result("pg19").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg19").feature("surf1").label("\u8868\u9762, GaC4H10");
    model.result("pg19").feature("surf1").set("legendtitle", "GaC4H10");
    model.result("pg19").feature("surf1").set("manualindexing", true);
    model.result("pg19").feature("surf1").set("arrayindex", 0);
    model.result("pg19").create("arws1", "ArrowSurface");
    model.result("pg19").feature("arws1").set("expr", new String[]{"tds.tflux_cGaC4H10x", "tds.tflux_cGaC4H10y"});
    model.result("pg19").feature("arws1").set("xnumber", 7);
    model.result("pg19").feature("arws1").set("ynumber", 7);
    model.result("pg19").feature("arws1").set("color", "black");
    model.result("pg19").feature("arws1").create("sel1", "Selection");
    model.result("pg19").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg19").feature("arws1").label("\u603b\u901a\u91cf, GaC4H10");
    model.result("pg19").feature("arws1").set("manualindexing", true);
    model.result("pg19").feature("arws1").set("arrayindex", 0);
    model.result("pg19").create("ann1", "Annotation");
    model.result("pg19").feature("ann1").set("showpoint", false);
    model.result("pg19").feature("ann1").set("text", "GaC4H10");
    model.result("pg19").feature("ann1").label("GaC4H10");
    model.result("pg19").feature("ann1").set("posxexpr", -0.20000000000000004);
    model.result("pg19").feature("ann1").set("posyexpr", -0.05000000000000001);
    model.result("pg19").feature("ann1").set("manualindexing", true);
    model.result("pg19").feature("ann1").set("arrayindex", 0);
    model.result("pg19").create("surf2", "Surface");
    model.result("pg19").feature("surf2").set("expr", new String[]{"cC2H5"});
    model.result("pg19").feature("surf2").set("colortable", "Baptisia");
    model.result("pg19").feature("surf2").label("\u8868\u9762, C2H5");
    model.result("pg19").feature("surf2").set("legendtitle", "C2H5");
    model.result("pg19").feature("surf2").set("manualindexing", true);
    model.result("pg19").feature("surf2").set("arrayindex", 1);
    model.result("pg19").create("arws2", "ArrowSurface");
    model.result("pg19").feature("arws2").set("expr", new String[]{"tds.tflux_cC2H5x", "tds.tflux_cC2H5y"});
    model.result("pg19").feature("arws2").set("xnumber", 7);
    model.result("pg19").feature("arws2").set("ynumber", 7);
    model.result("pg19").feature("arws2").set("color", "black");
    model.result("pg19").feature("arws2").create("sel1", "Selection");
    model.result("pg19").feature("arws2").feature("sel1").selection().set(1);
    model.result("pg19").feature("arws2").label("\u603b\u901a\u91cf, C2H5");
    model.result("pg19").feature("arws2").set("manualindexing", true);
    model.result("pg19").feature("arws2").set("arrayindex", 1);
    model.result("pg19").create("ann2", "Annotation");
    model.result("pg19").feature("ann2").set("showpoint", false);
    model.result("pg19").feature("ann2").set("text", "C2H5");
    model.result("pg19").feature("ann2").label("C2H5");
    model.result("pg19").feature("ann2").set("posxexpr", -0.20000000000000004);
    model.result("pg19").feature("ann2").set("posyexpr", -0.05000000000000001);
    model.result("pg19").feature("ann2").set("manualindexing", true);
    model.result("pg19").feature("ann2").set("arrayindex", 1);
    model.result("pg19").create("surf3", "Surface");
    model.result("pg19").feature("surf3").set("expr", new String[]{"cH"});
    model.result("pg19").feature("surf3").set("colortable", "Arctium");
    model.result("pg19").feature("surf3").label("\u8868\u9762, H");
    model.result("pg19").feature("surf3").set("legendtitle", "H");
    model.result("pg19").feature("surf3").set("manualindexing", true);
    model.result("pg19").feature("surf3").set("arrayindex", 2);
    model.result("pg19").create("arws3", "ArrowSurface");
    model.result("pg19").feature("arws3").set("expr", new String[]{"tds.tflux_cHx", "tds.tflux_cHy"});
    model.result("pg19").feature("arws3").set("xnumber", 7);
    model.result("pg19").feature("arws3").set("ynumber", 7);
    model.result("pg19").feature("arws3").set("color", "black");
    model.result("pg19").feature("arws3").create("sel1", "Selection");
    model.result("pg19").feature("arws3").feature("sel1").selection().set(1);
    model.result("pg19").feature("arws3").label("\u603b\u901a\u91cf, H");
    model.result("pg19").feature("arws3").set("manualindexing", true);
    model.result("pg19").feature("arws3").set("arrayindex", 2);
    model.result("pg19").create("ann3", "Annotation");
    model.result("pg19").feature("ann3").set("showpoint", false);
    model.result("pg19").feature("ann3").set("text", "H");
    model.result("pg19").feature("ann3").label("H");
    model.result("pg19").feature("ann3").set("posxexpr", -0.20000000000000004);
    model.result("pg19").feature("ann3").set("posyexpr", -0.05000000000000001);
    model.result("pg19").feature("ann3").set("manualindexing", true);
    model.result("pg19").feature("ann3").set("arrayindex", 2);
    model.result("pg19").create("surf4", "Surface");
    model.result("pg19").feature("surf4").set("expr", new String[]{"cC2H4"});
    model.result("pg19").feature("surf4").set("colortable", "Algae");
    model.result("pg19").feature("surf4").label("\u8868\u9762, C2H4");
    model.result("pg19").feature("surf4").set("legendtitle", "C2H4");
    model.result("pg19").feature("surf4").set("manualindexing", true);
    model.result("pg19").feature("surf4").set("arrayindex", 3);
    model.result("pg19").create("arws4", "ArrowSurface");
    model.result("pg19").feature("arws4").set("expr", new String[]{"tds.tflux_cC2H4x", "tds.tflux_cC2H4y"});
    model.result("pg19").feature("arws4").set("xnumber", 7);
    model.result("pg19").feature("arws4").set("ynumber", 7);
    model.result("pg19").feature("arws4").set("color", "black");
    model.result("pg19").feature("arws4").create("sel1", "Selection");
    model.result("pg19").feature("arws4").feature("sel1").selection().set(1);
    model.result("pg19").feature("arws4").label("\u603b\u901a\u91cf, C2H4");
    model.result("pg19").feature("arws4").set("manualindexing", true);
    model.result("pg19").feature("arws4").set("arrayindex", 3);
    model.result("pg19").create("ann4", "Annotation");
    model.result("pg19").feature("ann4").set("showpoint", false);
    model.result("pg19").feature("ann4").set("text", "C2H4");
    model.result("pg19").feature("ann4").label("C2H4");
    model.result("pg19").feature("ann4").set("posxexpr", -0.20000000000000004);
    model.result("pg19").feature("ann4").set("posyexpr", -0.05000000000000001);
    model.result("pg19").feature("ann4").set("manualindexing", true);
    model.result("pg19").feature("ann4").set("arrayindex", 3);
    model.result("pg19").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, GaC4H10, C2H5, H, C2H4 (tds)");
    model.result().create("pg20", "PlotGroup2D");
    model.result("pg20").set("data", "dset3");
    model.result("pg20").set("titletype", "manual");
    model.result("pg20")
         .set("title", "\u8868\u9762: \u6d53\u5ea6 (mol/m<sup>3</sup>) \u9762\u7bad\u5934: \u603b\u901a\u91cf");
    model.result("pg20").set("showlegendsunit", true);
    model.result("pg20").set("showlegendstitle", true);
    model.result("pg20").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, AsH3, GaH2, GaC2H6, GaC6H15 (tds)");
    model.result("pg20").set("plotarrayenable", true);
    model.result("pg20").set("arrayshape", "linear");
    model.result("pg20").set("legendpos", "rightdouble");
    model.result("pg20").set("arrayaxis", "y");
    model.result("pg20").set("relpadding", 0.5);
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf1").set("expr", new String[]{"cAsH3"});
    model.result("pg20").feature("surf1").set("colortable", "Cynanthus");
    model.result("pg20").feature("surf1").label("\u8868\u9762, AsH3");
    model.result("pg20").feature("surf1").set("legendtitle", "AsH3");
    model.result("pg20").feature("surf1").set("manualindexing", true);
    model.result("pg20").feature("surf1").set("arrayindex", 0);
    model.result("pg20").create("arws1", "ArrowSurface");
    model.result("pg20").feature("arws1").set("expr", new String[]{"tds.tflux_cAsH3x", "tds.tflux_cAsH3y"});
    model.result("pg20").feature("arws1").set("xnumber", 7);
    model.result("pg20").feature("arws1").set("ynumber", 7);
    model.result("pg20").feature("arws1").set("color", "black");
    model.result("pg20").feature("arws1").create("sel1", "Selection");
    model.result("pg20").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg20").feature("arws1").label("\u603b\u901a\u91cf, AsH3");
    model.result("pg20").feature("arws1").set("manualindexing", true);
    model.result("pg20").feature("arws1").set("arrayindex", 0);
    model.result("pg20").create("ann1", "Annotation");
    model.result("pg20").feature("ann1").set("showpoint", false);
    model.result("pg20").feature("ann1").set("text", "AsH3");
    model.result("pg20").feature("ann1").label("AsH3");
    model.result("pg20").feature("ann1").set("posxexpr", -0.20000000000000004);
    model.result("pg20").feature("ann1").set("posyexpr", -0.05000000000000001);
    model.result("pg20").feature("ann1").set("manualindexing", true);
    model.result("pg20").feature("ann1").set("arrayindex", 0);
    model.result("pg20").create("surf2", "Surface");
    model.result("pg20").feature("surf2").set("expr", new String[]{"cGaH2"});
    model.result("pg20").feature("surf2").set("colortable", "Baptisia");
    model.result("pg20").feature("surf2").label("\u8868\u9762, GaH2");
    model.result("pg20").feature("surf2").set("legendtitle", "GaH2");
    model.result("pg20").feature("surf2").set("manualindexing", true);
    model.result("pg20").feature("surf2").set("arrayindex", 1);
    model.result("pg20").create("arws2", "ArrowSurface");
    model.result("pg20").feature("arws2").set("expr", new String[]{"tds.tflux_cGaH2x", "tds.tflux_cGaH2y"});
    model.result("pg20").feature("arws2").set("xnumber", 7);
    model.result("pg20").feature("arws2").set("ynumber", 7);
    model.result("pg20").feature("arws2").set("color", "black");
    model.result("pg20").feature("arws2").create("sel1", "Selection");
    model.result("pg20").feature("arws2").feature("sel1").selection().set(1);
    model.result("pg20").feature("arws2").label("\u603b\u901a\u91cf, GaH2");
    model.result("pg20").feature("arws2").set("manualindexing", true);
    model.result("pg20").feature("arws2").set("arrayindex", 1);
    model.result("pg20").create("ann2", "Annotation");
    model.result("pg20").feature("ann2").set("showpoint", false);
    model.result("pg20").feature("ann2").set("text", "GaH2");
    model.result("pg20").feature("ann2").label("GaH2");
    model.result("pg20").feature("ann2").set("posxexpr", -0.20000000000000004);
    model.result("pg20").feature("ann2").set("posyexpr", -0.05000000000000001);
    model.result("pg20").feature("ann2").set("manualindexing", true);
    model.result("pg20").feature("ann2").set("arrayindex", 1);
    model.result("pg20").create("surf3", "Surface");
    model.result("pg20").feature("surf3").set("expr", new String[]{"cGaC2H6"});
    model.result("pg20").feature("surf3").set("colortable", "Arctium");
    model.result("pg20").feature("surf3").label("\u8868\u9762, GaC2H6");
    model.result("pg20").feature("surf3").set("legendtitle", "GaC2H6");
    model.result("pg20").feature("surf3").set("manualindexing", true);
    model.result("pg20").feature("surf3").set("arrayindex", 2);
    model.result("pg20").create("arws3", "ArrowSurface");
    model.result("pg20").feature("arws3").set("expr", new String[]{"tds.tflux_cGaC2H6x", "tds.tflux_cGaC2H6y"});
    model.result("pg20").feature("arws3").set("xnumber", 7);
    model.result("pg20").feature("arws3").set("ynumber", 7);
    model.result("pg20").feature("arws3").set("color", "black");
    model.result("pg20").feature("arws3").create("sel1", "Selection");
    model.result("pg20").feature("arws3").feature("sel1").selection().set(1);
    model.result("pg20").feature("arws3").label("\u603b\u901a\u91cf, GaC2H6");
    model.result("pg20").feature("arws3").set("manualindexing", true);
    model.result("pg20").feature("arws3").set("arrayindex", 2);
    model.result("pg20").create("ann3", "Annotation");
    model.result("pg20").feature("ann3").set("showpoint", false);
    model.result("pg20").feature("ann3").set("text", "GaC2H6");
    model.result("pg20").feature("ann3").label("GaC2H6");
    model.result("pg20").feature("ann3").set("posxexpr", -0.20000000000000004);
    model.result("pg20").feature("ann3").set("posyexpr", -0.05000000000000001);
    model.result("pg20").feature("ann3").set("manualindexing", true);
    model.result("pg20").feature("ann3").set("arrayindex", 2);
    model.result("pg20").create("surf4", "Surface");
    model.result("pg20").feature("surf4").set("expr", new String[]{"cGaC6H15"});
    model.result("pg20").feature("surf4").set("colortable", "Algae");
    model.result("pg20").feature("surf4").label("\u8868\u9762, GaC6H15");
    model.result("pg20").feature("surf4").set("legendtitle", "GaC6H15");
    model.result("pg20").feature("surf4").set("manualindexing", true);
    model.result("pg20").feature("surf4").set("arrayindex", 3);
    model.result("pg20").create("arws4", "ArrowSurface");
    model.result("pg20").feature("arws4").set("expr", new String[]{"tds.tflux_cGaC6H15x", "tds.tflux_cGaC6H15y"});
    model.result("pg20").feature("arws4").set("xnumber", 7);
    model.result("pg20").feature("arws4").set("ynumber", 7);
    model.result("pg20").feature("arws4").set("color", "black");
    model.result("pg20").feature("arws4").create("sel1", "Selection");
    model.result("pg20").feature("arws4").feature("sel1").selection().set(1);
    model.result("pg20").feature("arws4").label("\u603b\u901a\u91cf, GaC6H15");
    model.result("pg20").feature("arws4").set("manualindexing", true);
    model.result("pg20").feature("arws4").set("arrayindex", 3);
    model.result("pg20").create("ann4", "Annotation");
    model.result("pg20").feature("ann4").set("showpoint", false);
    model.result("pg20").feature("ann4").set("text", "GaC6H15");
    model.result("pg20").feature("ann4").label("GaC6H15");
    model.result("pg20").feature("ann4").set("posxexpr", -0.20000000000000004);
    model.result("pg20").feature("ann4").set("posyexpr", -0.05000000000000001);
    model.result("pg20").feature("ann4").set("manualindexing", true);
    model.result("pg20").feature("ann4").set("arrayindex", 3);
    model.result("pg20").label("\u7ed8\u56fe\u9635\u5217: \u6d53\u5ea6, AsH3, GaH2, GaC2H6, GaC6H15 (tds)");
    model.result("pg20").run();
    model.result("pg20").feature("surf3").set("arraydim", "1");
    model.result("pg20").run();
    model.result("pg20").feature("surf3").set("colortable", "Amethyst");
    model.result("pg20").feature("surf2").set("arraydim", "1");
    model.result("pg20").run();
    model.result("pg20").feature("surf2").set("colortable", "Garnet");
    model.result("pg19").feature("surf1").set("arraydim", "1");
    model.result("pg19").run();
    model.result("pg19").feature("surf1").set("colortable", "Lichen");
    model.result("pg19").feature("surf2").set("arraydim", "1");
    model.result("pg19").run();
    model.result("pg19").feature("surf2").set("colortable", "Lagoon");
    model.result("pg19").feature("surf3").set("arraydim", "1");
    model.result("pg19").run();
    model.result("pg19").feature("surf3").set("colortable", "Pelagic");
    model.result("pg19").feature("surf4").set("arraydim", "1");
    model.result("pg19").run();
    model.result("pg19").feature("surf4").set("colortable", "Kyanite");
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result().remove("pg9");
    model.result("pg10").run();

    model.title("\u7837\u5316\u9553\u5316\u5b66\u6c14\u76f8\u6c89\u79ef");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u5316\u5b66\u6c14\u76f8\u6c89\u79ef (CVD) \u53cd\u5e94\u5668\u7684\u5efa\u6a21\u3002CVD \u662f\u7535\u5b50\u5de5\u4e1a\u4e2d\u7684\u4e00\u79cd\u91cd\u8981\u6280\u672f\uff0c\u5176\u539f\u7406\u662f\u5206\u5b50\u6216\u5206\u5b50\u788e\u7247\u5728\u57fa\u5e95\u8868\u9762\u88ab\u5438\u6536\u5e76\u53d1\u751f\u5316\u5b66\u53cd\u5e94\uff0c\u7531\u6b64\u4ea7\u751f\u8584\u819c\u3002\u5176\u4e2d\u7ed3\u5408\u4e86\u8be6\u7ec6\u5316\u5b66\u53cd\u5e94\u52a8\u529b\u5b66\u548c CVD \u53cd\u5e94\u5668\u7684\u4f20\u9012\u6a21\u578b\uff0c\u4f7f\u6c89\u79ef\u8fc7\u7a0b\u7684\u6a21\u62df\u66f4\u771f\u5b9e\u3002\u6267\u884c\u4eff\u771f\u540e\uff0c\u65e0\u987b\u8fdb\u884c\u8d39\u65f6\u8d39\u529b\u7684\u6d4b\u8bd5\u8fd0\u884c\uff0c\u800c\u8fd9\u5728\u5e38\u89c4\u7684\u53cd\u5e94\u5668\u8bbe\u8ba1\u4e2d\u662f\u4e0d\u5fc5\u53ef\u5c11\u7684\u6b65\u9aa4\u3002\n\n\u201c\u5316\u5b66\u201d\u63a5\u53e3\u4e2d\u8003\u8651\u4e86\u53cd\u5e94\u52a8\u529b\u5b66\u5e76\u8ba1\u7b97\u4e86\u4f20\u9012\u53c2\u6570\u548c\u70ed\u53c2\u6570\uff0c\u4ece\u800c\u53ef\u4ee5\u4e0e\u5176\u4ed6\u63a5\u53e3\u65e0\u7f1d\u8026\u5408\u3002\u8be5\u6a21\u578b\u8fd8\u5229\u7528\u201c\u53ef\u9006\u53cd\u5e94\u7ec4\u201d\u7279\u5f81\uff0c\u8fdb\u884c\u8868\u9762\u53cd\u5e94\u7684 CHEMKIN \u5bfc\u5165\uff0c\u4ee5\u53ca CVD \u8fc7\u7a0b\u4e2d\u6d89\u53ca\u7684\u672c\u4f53\u53cd\u5e94\u548c\u8868\u9762\u53cd\u5e94\u8fd9\u4e2a\u590d\u6742\u7cfb\u7edf\u7684\u7ec4\u7ec7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("gaas_cvd.mph");

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
    model = run8(model);
    model = run9(model);
    model = run10(model);
    model = run11(model);
    run12(model);
  }

}
