/*
 * chlorine_global_model.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:04 by COMSOL 6.3.0.290. */
public class chlorine_global_model {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Global_Modeling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Rad", "L"});

    model.param().set("Rad", "10[cm]");
    model.param().set("L", "8.5[cm]");
    model.param().set("Pabs", "25[W]");
    model.param().set("Qfeed", "10");
    model.param().set("p0", "0.01[Torr]");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("ev1", "0.07[V]", "\u7b2c\u4e00\u632f\u52a8\u80fd\u7ea7\u7684\u80fd\u91cf");
    model.component("comp1").variable("var1")
         .set("ev2", "0.14[V]", "\u7b2c\u4e8c\u632f\u52a8\u80fd\u7ea7\u7684\u80fd\u91cf");
    model.component("comp1").variable("var1")
         .set("ev3", "0.21[V]", "\u7b2c\u4e09\u632f\u52a8\u80fd\u7ea7\u7684\u80fd\u91cf");
    model.component("comp1").variable("var1").set("eionCl2", "11.5[V]", "Cl2 \u7535\u79bb\u80fd");
    model.component("comp1").variable("var1").set("eionCl", "14.25[V]", "Cl \u7535\u79bb\u80fd");
    model.component("comp1").variable("var1").set("eCl52", "10.17[V]", "Cl52 \u6fc0\u53d1\u80fd");
    model.component("comp1").variable("var1").set("eCl12", "1.35[V]", "Cl12 \u6fc0\u53d1\u80fd");
    model.component("comp1").variable("var1").set("eatt", "2.36[V]", "Cl- \u80fd\u91cf");
    model.component("comp1").variable("var1").set("ediss", "4[V]", "Cl2 \u79bb\u89e3\u80fd");
    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("sp", "1250*(1-exp(-0.091*p0))+400*exp(-0.337*p0)", "Th \u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("Th", "(300+sp*(log10(Pabs/40)/log10(40)))", "\u6c14\u4f53\u6e29\u5ea6");
    model.component("comp1").variable("var2")
         .set("Lambda_diff", "((pi/L)^2+(2.405/Rad)^2)^-0.5", "\u6709\u6548\u6269\u6563\u957f\u5ea6");
    model.component("comp1").variable("var2")
         .set("alpha_s_b", "abs(a1*erf(a2*rho+a3)*exp(-a4/rho^1.35) / ( exp( (gamma-1)/(2*gamma) - 0.49 ) ))", "\u9798\u5c42\u7535\u8d1f\u6027\u4e0e\u672c\u4f53\u7535\u8d1f\u6027\u4e4b\u6bd4");
    model.component("comp1").variable("var2")
         .set("rho", "abs(alpha+a5*(   exp(a6*(gamma-50))-1)  )", "alpha_s_b \u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("gamma", "(e_const*plas.Te/k_B_const)/Th", "Te \u548c Th \u4e4b\u6bd4");
    model.component("comp1").variable("var2").set("alpha", "plas.n_wCl_1m/plas.ne", "\u7535\u8d1f\u6027");
    model.component("comp1").variable("var2")
         .set("alpha_s", "abs(alpha_s_b)*alpha", "\u9798\u5c42\u7535\u8d1f\u6027");
    model.component("comp1").variable("var2").set("a1", "0.607", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2").set("a2", "5.555", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2").set("a3", "-11.16", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2").set("a4", "1.634", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2").set("a5", "12e-3", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2").set("a6", "-107e-3", "alpha_s_b \u548c rho \u7684\u6570\u636e");
    model.component("comp1").variable("var2")
         .set("Epsilon_p", "0.5*plas.Te*(1+alpha_s) / ( 1+alpha_s*gamma )", "\u7b49\u79bb\u5b50\u4f53\u7535\u4f4d");
    model.component("comp1").variable("var2")
         .set("Epsilon_s", "-plas.Te*log(  abs(4*uB_avg*(1+alpha_s) / (veth*(1+alpha_s*(vi/veth)^2)) ) )", "\u9798\u5c42\u7535\u4f4d");
    model.component("comp1").variable("var2")
         .set("Epsilon_e", "2*plas.Te", "\u6bcf\u5931\u53bb\u4e00\u4e2a\u7535\u5b50\u7684\u5e73\u5747\u52a8\u80fd\u635f\u5931");
    model.component("comp1").variable("var2")
         .set("veth", "sqrt(8*e_const*plas.Te/(pi*me))", "\u7535\u5b50\u5e73\u5747\u70ed\u901f\u5ea6");
    model.component("comp1").variable("var2")
         .set("vi", "sqrt(8*k_B_const*Th/(pi*mCl))", "Cl- \u5e73\u5747\u70ed\u901f\u5ea6");
    model.component("comp1").variable("var2")
         .set("hR", "0.8*(4+Rad/Lambda_ion)^(-0.5)/(1+alpha0)", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("alpha0", "alpha*3/2", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("hL", "0.86*(3+L/2/Lambda_ion)^(-0.5)/(1+alpha0)", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("hcCl2", "1/( gamma^0.5  + gamma^0.5*NCl2_s^0.5*plas.n_wCl2_1p*(plas.n_wCl_1m)^(-3/2)  )", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("NCl2_s", "15*vpCl2/(56*Krec*Lambda_ion)", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("Krec", "5e-14*(300/Th)^0.5", "\u4e92\u4e2d\u6027\u5316\u7684\u901f\u7387\u7cfb\u6570");
    model.component("comp1").variable("var2")
         .set("hcCl", "1/( gamma^0.5  + gamma^0.5*NCl_s^0.5*plas.n_wCl_1p*(plas.n_wCl_1m)^(-3/2)  )", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("NCl_s", "15*vpCl/(56*Krec*Lambda_ion)", "\u6821\u6b63\u56e0\u5b50\u7684\u8f85\u52a9\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var2")
         .set("hRCl2", "sqrt( hR^2 + hcCl2^2  )", "Cl2+ \u7684\u5f84\u5411\u6821\u6b63\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("hRCl", "sqrt( hR^2 + hcCl^2  )", "Cl+ \u7684\u5f84\u5411\u6821\u6b63\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("hLCl2", "sqrt( hL^2 + hcCl2^2  )", "Cl2+ \u7684\u7eb5\u5411\u6821\u6b63\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("hLCl", "sqrt( hL^2 + hcCl^2  )", "Cl+ \u7684\u7eb5\u5411\u6821\u6b63\u56e0\u5b50");
    model.component("comp1").variable("var2")
         .set("gammaCl_steel", "10^( -1.22 -1.34*exp(-1.48*plas.n_wCl/plas.n_wCl2) )", "Cl \u590d\u5408\u7cfb\u6570");
    model.component("comp1").variable("var2").set("mCl", "plas.M_wCl/N_A_const", "Cl \u8d28\u91cf");
    model.component("comp1").variable("var2").set("mCl2", "plas.M_wCl2/N_A_const", "Cl2 \u8d28\u91cf");
    model.component("comp1").variable("var2")
         .set("vpCl", "sqrt( 8*k_B_const*Th / (pi*mCl) )", "Cl \u5e73\u5747\u70ed\u901f\u5ea6");
    model.component("comp1").variable("var2")
         .set("vpCl2", "sqrt( 8*k_B_const*Th / (pi*mCl2) )", "Cl2 \u5e73\u5747\u70ed\u901f\u5ea6");
    model.component("comp1").variable("var2").set("me", "9.1e-31[kg]", "\u7535\u5b50\u8d28\u91cf");
    model.component("comp1").variable("var2")
         .set("Lambda_ion", "k_B_const*Th/(p0*sigma_ion)", "\u5e73\u5747\u81ea\u7531\u7a0b");
    model.component("comp1").variable("var2").set("sigma_ion", "7e-19[m^2]", "\u6709\u6548\u6563\u5c04\u622a\u9762");
    model.component("comp1").variable("var2")
         .set("uB_avg", "(plas.n_wCl2_1p*plas.vBohm_wCl2_1p + plas.n_wCl_1p*plas.vBohm_wCl_1p) / (plas.n_wCl_1p+plas.n_wCl2_1p)", "Bohm \u901f\u5ea6\u52a0\u6743\u5e73\u5747");

    model.component("comp1").physics("plas").prop("DiffusionModel").set("DiffusionModel", "GlobalModel");
    model.component("comp1").physics("plas").prop("Reactor").set("ReactorType", "ConstantPressure");
    model.component("comp1").physics("plas").feature("pes1").set("Tgm", "Th");
    model.component("comp1").physics("plas").feature("pes1").set("pAgm", "p0");
    model.component("comp1").physics("plas").feature("pes1").set("sccmmfr", "Qfeed");
    model.component("comp1").physics("plas").feature("pes1").set("Pgm", "Pabs");
    model.component("comp1").physics("plas").feature("pes1").set("EpsilonE", "Epsilon_e");
    model.component("comp1").physics("plas").feature("pes1").set("EpsilonI", "Epsilon_p+Epsilon_s");
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir1").set("formula", "e+Cl2=>Cl+Cl+e");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir1").set("de", "ediss");
    model.component("comp1").physics("plas").feature("eir1")
         .set("kf", "1.04e-13*plas.Te^(-0.29)*exp(-8.84/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir2", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir2").set("formula", "e+Cl2=>2e+Cl2+");
    model.component("comp1").physics("plas").feature("eir2").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir2").set("de", "eionCl2");
    model.component("comp1").physics("plas").feature("eir2")
         .set("kf", "5.12e-14*plas.Te^(0.48)*exp(-12.34/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir3", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir3").set("formula", "e+Cl2=>2e+Cl+Cl+");
    model.component("comp1").physics("plas").feature("eir3").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir3").set("de", "eionCl");
    model.component("comp1").physics("plas").feature("eir3")
         .set("kf", "2.14e-13*plas.Te^(-0.07)*exp(-25.26/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir4", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir4").set("formula", "e+Cl2=>3e+2Cl+");
    model.component("comp1").physics("plas").feature("eir4").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir4").set("de", "2*eionCl");
    model.component("comp1").physics("plas").feature("eir4")
         .set("kf", "2.27e-16*plas.Te^(1.92)*exp(-21.26/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir5", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir5").set("formula", "e+Cl2=>Cl+Cl-");
    model.component("comp1").physics("plas").feature("eir5").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir5")
         .set("kf", "(3.43e-15*plas.Te^(-1.18)*exp(-3.98/plas.Te) + 3.05e-16*plas.Te^(-1.33)*exp(-0.11/(plas.Te+0.014)))*N_A_const");
    model.component("comp1").physics("plas").create("eir6", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir6").set("formula", "e+Cl2v1=>Cl+Cl-");
    model.component("comp1").physics("plas").feature("eir6").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir6")
         .set("kf", "(14.06e-15*plas.Te^(-1.18)*exp(-3.98/plas.Te) + 12.51e-16*plas.Te^(-1.33)*exp(-0.11/(plas.Te+0.014)))*N_A_const");
    model.component("comp1").physics("plas").create("eir7", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir7").set("formula", "e+Cl2v2=>Cl+Cl-");
    model.component("comp1").physics("plas").feature("eir7").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir7")
         .set("kf", "(30.18e-15*plas.Te^(-1.18)*exp(-3.98/plas.Te) + 26.84e-16*plas.Te^(-1.33)*exp(-0.11/(plas.Te+0.014)))*N_A_const");
    model.component("comp1").physics("plas").create("eir8", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir8").set("formula", "e+Cl2v3=>Cl+Cl-");
    model.component("comp1").physics("plas").feature("eir8").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir8")
         .set("kf", "(46.31e-15*plas.Te^(-1.18)*exp(-3.98/plas.Te) + 41.18e-16*plas.Te^(-1.33)*exp(-0.11/(plas.Te+0.014)))*N_A_const");
    model.component("comp1").physics("plas").create("eir9", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir9").set("formula", "e+Cl2=>Cl++Cl-+e");
    model.component("comp1").physics("plas").feature("eir9").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir9").set("de", "eionCl");
    model.component("comp1").physics("plas").feature("eir9")
         .set("kf", "2.94e-16*plas.Te^(0.19)*exp(-18.79/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir10", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir10").set("formula", "e+Cl2=>e+Cl2v1");
    model.component("comp1").physics("plas").feature("eir10").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir10").set("de", "ev1");
    model.component("comp1").physics("plas").feature("eir10")
         .set("kf", "3.99e-12*plas.Te^(-1.5)*exp(-7.51/plas.Te-0.0001/plas.Te^2)*N_A_const");
    model.component("comp1").physics("plas").create("eir11", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir11").set("formula", "e+Cl2=>e+Cl2v2");
    model.component("comp1").physics("plas").feature("eir11").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir11").set("de", "ev2");
    model.component("comp1").physics("plas").feature("eir11")
         .set("kf", "(3.28e-17*plas.Te^(-1.12)*exp(-0.37/plas.Te) + 2.86e-17*exp(-(log(plas.Te)+0.99)^2/(2*1.06^2)))*N_A_const");
    model.component("comp1").physics("plas").create("eir12", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir12").set("formula", "e+Cl2=>e+Cl2v3");
    model.component("comp1").physics("plas").feature("eir12").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir12").set("de", "ev3");
    model.component("comp1").physics("plas").feature("eir12")
         .set("kf", "(1.3e-17*plas.Te^(-1.24)*exp(-0.41/plas.Te) + 6.08e-18*exp(-(log(plas.Te)+0.94)^2/(2*1.02^2)))*N_A_const");
    model.component("comp1").physics("plas").create("eir13", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir13").set("formula", "e+Cl2v1=>e+Cl2v2");
    model.component("comp1").physics("plas").feature("eir13").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir13").set("de", "ev2-ev1");
    model.component("comp1").physics("plas").feature("eir13")
         .set("kf", "(3e-16*plas.Te^(-1.0)*exp(-0.37/plas.Te) + 4.61e-16*exp(-(log(plas.Te)+1.04)^2/(2*1.10^2)))*N_A_const");
    model.component("comp1").physics("plas").create("eir14", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir14").set("formula", "e+Cl2v2=>e+Cl2v3");
    model.component("comp1").physics("plas").feature("eir14").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir14").set("de", "ev3-ev2");
    model.component("comp1").physics("plas").feature("eir14")
         .set("kf", "(3e-16*plas.Te^(-1.0)*exp(-0.37/plas.Te) + 4.61e-16*exp(-(log(plas.Te)+1.04)^2/(2*1.10^2)))*N_A_const");
    model.component("comp1").physics("plas").create("eir15", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir15").set("formula", "e+Cl2v1=>e+Cl2v3");
    model.component("comp1").physics("plas").feature("eir15").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir15").set("de", "ev3-ev1");
    model.component("comp1").physics("plas").feature("eir15")
         .set("kf", "(1.25e-16*plas.Te^(-1.13)*exp(-0.36/plas.Te) + 1.06e-16*exp(-(log(plas.Te)+1.01)^2/(2*1.06^2)))*N_A_const");
    model.component("comp1").physics("plas").create("eir16", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir16").set("formula", "e+Cl2+=>2Cl");
    model.component("comp1").physics("plas").feature("eir16").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir16").set("de", "-eionCl2");
    model.component("comp1").physics("plas").feature("eir16").set("kf", "9e-14*plas.Te^(-0.5)*N_A_const");
    model.component("comp1").physics("plas").create("eir17", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir17").set("formula", "e+Cl=>e+Cl12");
    model.component("comp1").physics("plas").feature("eir17").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir17").set("de", "eCl12");
    model.component("comp1").physics("plas").feature("eir17")
         .set("kf", "4.55e-14*plas.Te^(-0.46)*exp(-2.01/plas.Te-0.001/plas.Te^2)*N_A_const");
    model.component("comp1").physics("plas").create("eir18", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir18").set("formula", "e+Cl=>e+Cl52");
    model.component("comp1").physics("plas").feature("eir18").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir18").set("de", "eCl52");
    model.component("comp1").physics("plas").feature("eir18")
         .set("kf", "(7.03e-17*plas.Te^(0.55)*exp(-2.15/plas.Te-1.5/plas.Te^2-2.05/plas.Te^3))*N_A_const");
    model.component("comp1").physics("plas").create("eir19", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir19").set("formula", "e+Cl=>Cl++2e");
    model.component("comp1").physics("plas").feature("eir19").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir19").set("de", "eionCl");
    model.component("comp1").physics("plas").feature("eir19")
         .set("kf", "3.17e-14*plas.Te^(0.53)*exp(-13.29/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir20", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir20").set("formula", "e+Cl12=>Cl++2e");
    model.component("comp1").physics("plas").feature("eir20").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir20").set("de", "eionCl-eCl12");
    model.component("comp1").physics("plas").feature("eir20")
         .set("kf", "3.17e-14*plas.Te^(0.53)*exp(-13.29/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir21", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir21").set("formula", "e+Cl52=>Cl++2e");
    model.component("comp1").physics("plas").feature("eir21").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir21").set("de", "eionCl-eCl52");
    model.component("comp1").physics("plas").feature("eir21")
         .set("kf", "(4.33e-14*plas.Te^(0.55)*exp(-0.15/plas.Te-0.85/plas.Te^2))*N_A_const");
    model.component("comp1").physics("plas").create("eir22", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir22").set("formula", "e+Cl-=>Cl+2e");
    model.component("comp1").physics("plas").feature("eir22").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir22").set("de", "eatt");
    model.component("comp1").physics("plas").feature("eir22")
         .set("kf", "9.02e-15*plas.Te^(0.92)*exp(-4.88/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir23", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir23").set("formula", "e+Cl-=>Cl++3e");
    model.component("comp1").physics("plas").feature("eir23").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir23").set("de", "eatt+eionCl");
    model.component("comp1").physics("plas").feature("eir23")
         .set("kf", "3.62e-15*plas.Te^(0.72)*exp(-25.38/plas.Te)*N_A_const");
    model.component("comp1").physics("plas").create("eir24", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir24").set("formula", "e+Cl2v1=>e+Cl2");
    model.component("comp1").physics("plas").feature("eir24").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir24").set("de", "-ev1");
    model.component("comp1").physics("plas").feature("eir24")
         .set("kf", "3.99e-12*plas.Te^(-1.5)*exp(-7.51/plas.Te-0.0001/plas.Te^2)*N_A_const*exp(ev1/plas.Te)");
    model.component("comp1").physics("plas").create("eir25", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir25").set("formula", "e+Cl2v2=>e+Cl2");
    model.component("comp1").physics("plas").feature("eir25").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir25").set("de", "-ev2");
    model.component("comp1").physics("plas").feature("eir25")
         .set("kf", "(3.28e-17*plas.Te^(-1.12)*exp(-0.37/plas.Te) + 2.86e-17*exp(-(log(plas.Te)+0.99)^2/(2*1.06^2)))*N_A_const*exp(ev2/plas.Te)");
    model.component("comp1").physics("plas").create("eir26", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir26").set("formula", "e+Cl2v3=>e+Cl2");
    model.component("comp1").physics("plas").feature("eir26").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir26").set("de", "-ev3");
    model.component("comp1").physics("plas").feature("eir26")
         .set("kf", "(1.3e-17*plas.Te^(-1.24)*exp(-0.41/plas.Te) + 6.08e-18*exp(-(log(plas.Te)+0.94)^2/(2*1.02^2)))*N_A_const*exp(ev3/plas.Te)");
    model.component("comp1").physics("plas").create("eir27", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir27").set("formula", "e+Cl2v2=>e+Cl2v1");
    model.component("comp1").physics("plas").feature("eir27").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir27").set("de", "-(ev2-ev1)");
    model.component("comp1").physics("plas").feature("eir27")
         .set("kf", "(3e-16*plas.Te^(-1.0)*exp(-0.37/plas.Te) + 4.61e-16*exp(-(log(plas.Te)+1.04)^2/(2*1.10^2)))*N_A_const*exp((ev2-ev1)/plas.Te)");
    model.component("comp1").physics("plas").create("eir28", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir28").set("formula", "e+Cl2v3=>e+Cl2v2");
    model.component("comp1").physics("plas").feature("eir28").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir28").set("de", "-(ev3-ev2)");
    model.component("comp1").physics("plas").feature("eir28")
         .set("kf", "(3e-16*plas.Te^(-1.0)*exp(-0.37/plas.Te) + 4.61e-16*exp(-(log(plas.Te)+1.04)^2/(2*1.10^2)))*N_A_const*exp((ev3-ev2)/plas.Te)");
    model.component("comp1").physics("plas").create("eir29", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir29").set("formula", "e+Cl2v3=>e+Cl2v1");
    model.component("comp1").physics("plas").feature("eir29").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir29").set("de", "-(ev3-ev1)");
    model.component("comp1").physics("plas").feature("eir29")
         .set("kf", "(1.25e-16*plas.Te^(-1.13)*exp(-0.36/plas.Te) + 1.06e-16*exp(-(log(plas.Te)+1.01)^2/(2*1.06^2)))*N_A_const*exp((ev3-ev1)/plas.Te)");
    model.component("comp1").physics("plas").create("eir30", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir30").set("formula", "e+Cl12=>e+Cl");
    model.component("comp1").physics("plas").feature("eir30").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir30").set("de", "-eCl12");
    model.component("comp1").physics("plas").feature("eir30")
         .set("kf", "4.55e-14*plas.Te^(-0.46)*exp(-2.01/plas.Te-0.001/plas.Te^2)*N_A_const*exp((eCl12)/plas.Te)");
    model.component("comp1").physics("plas").create("eir31", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir31").set("formula", "e+Cl52=>e+Cl");
    model.component("comp1").physics("plas").feature("eir31").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir31").set("de", "-eCl52");
    model.component("comp1").physics("plas").feature("eir31")
         .set("kf", "(7.03e-17*plas.Te^(0.55)*exp(-2.15/plas.Te-1.5/plas.Te^2-2.05/plas.Te^3))*N_A_const*exp((eCl52)/plas.Te)");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Cl52=>Cl");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "1e5");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Cl2++Cl-=>3Cl");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", "5e-14*(300/Th)^0.5*N_A_const");
    model.component("comp1").physics("plas").create("rxn3", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn3").set("formula", "Cl2++Cl-=>Cl+Cl2");
    model.component("comp1").physics("plas").feature("rxn3").set("kf", "5e-14[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").create("rxn4", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn4").set("formula", "Cl++Cl-=>2Cl");
    model.component("comp1").physics("plas").feature("rxn4").set("kf", "5e-14*(300/Th)^0.5*N_A_const");
    model.component("comp1").physics("plas").create("rxn5", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn5").set("formula", "Cl2+Cl+=>Cl+Cl2+");
    model.component("comp1").physics("plas").feature("rxn5").set("kf", "5.4e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").create("rxn6", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn6").set("formula", "Cl2v1+Cl+=>Cl+Cl2+");
    model.component("comp1").physics("plas").feature("rxn6").set("kf", "5.4e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").create("rxn7", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn7").set("formula", "Cl2v2+Cl+=>Cl+Cl2+");
    model.component("comp1").physics("plas").feature("rxn7").set("kf", "5.4e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").create("rxn8", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn8").set("formula", "Cl2v3+Cl+=>Cl+Cl2+");
    model.component("comp1").physics("plas").feature("rxn8").set("kf", "5.4e-16[m^3/s]*N_A_const");
    model.component("comp1").physics("plas").create("rxn9", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn9").set("formula", "2Cl+Cl2=>2Cl2");
    model.component("comp1").physics("plas").feature("rxn9").set("kf", "3.5e-45*exp(810/Th)*N_A_const");
    model.component("comp1").physics("plas").create("rxn10", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn10").set("formula", "2Cl+Cl=>Cl2+Cl");
    model.component("comp1").physics("plas").feature("rxn10").set("kf", "8.75e-46*exp(810/Th)*N_A_const");
    model.component("comp1").physics("plas").create("rxn11", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn11").set("formula", "Cl+Cl2v3=>Cl+Cl2v2");
    model.component("comp1").physics("plas").feature("rxn11").set("kf", "1.3e-17*(Th/300)^0.5*N_A_const");
    model.component("comp1").physics("plas").create("rxn12", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn12").set("formula", "Cl+Cl2v2=>Cl+Cl2v1");
    model.component("comp1").physics("plas").feature("rxn12").set("kf", "1.3e-17*(Th/300)^0.5*N_A_const");
    model.component("comp1").physics("plas").create("rxn13", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn13").set("formula", "Cl+Cl2v1=>Cl+Cl2");
    model.component("comp1").physics("plas").feature("rxn13").set("kf", "1.3e-17*(Th/300)^0.5*N_A_const");
    model.component("comp1").physics("plas").create("eir32", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir32").set("formula", "e+Cl2=>e+Cl2");
    model.component("comp1").physics("plas").feature("eir32").set("mratio", "7.720e-6");
    model.component("comp1").physics("plas").feature("eir32").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir32").set("eedf", "Maxwellian");
    model.component("comp1").physics("plas").feature("eir32")
         .set("xdata", new String[]{"0", "1.9830e-2", "2.3910e-2", "2.7970e-2", "3.2040e-2", "3.6030e-2", "4.0110e-2", "4.3860e-2", "4.7410e-2", "5.1750e-2", 
         "5.7050e-2", "5.8780e-2", "6.3530e-2", "6.8100e-2", "7.2160e-2", "7.8120e-2", "8.5270e-2", "8.9610e-2", "9.4010e-2", "1.0160e-1", 
         "1.1000e-1", "1.2250e-1", "1.3110e-1", "1.4190e-1", "1.5190e-1", "1.6580e-1", "1.7770e-1", "1.8640e-1", "1.9210e-1", "2.0000e-1", 
         "2.1000e-1", "2.2000e-1", "2.3000e-1", "2.4000e-1", "2.5000e-1", "3.3590e-1", "4.6550e-1", "5.8580e-1", "7.6390e-1", "1.0410e+0", 
         "1.4050e+0", "2.0030e+0", "3.2540e+0", "5.0620e+0", "6.3140e+0", "8.4560e+0", "1.0930e+1", "1.5280e+1", "2.1780e+1", "2.9170e+1"});
    model.component("comp1").physics("plas").feature("eir32")
         .set("ydata", new String[]{"0", "4.0290e-19", "3.8530e-19", "3.7200e-19", "3.4280e-19", "3.0920e-19", "2.7250e-19", "2.2910e-19", "1.8860e-19", "1.5500e-19", 
         "1.2610e-19", "1.0730e-19", "9.0200e-20", "7.6740e-20", "7.3380e-20", "8.3280e-20", "9.5620e-20", "1.0480e-19", "1.0120e-19", "9.4510e-20", 
         "9.0200e-20", "9.1260e-20", "9.9030e-20", "9.9030e-20", "8.7090e-20", "7.7640e-20", "6.4660e-20", "5.6320e-20", "4.5000e-20", "3.8000e-20", 
         "2.8000e-20", "2.0000e-20", "1.7000e-20", "1.5500e-20", "1.4500e-20", "1.5310e-20", "1.8860e-20", "2.4480e-20", "3.3750e-20", "4.9020e-20", 
         "6.7650e-20", "8.9360e-20", "1.1690e-19", "1.3680e-19", "1.5310e-19", "1.8690e-19", "2.0220e-19", "1.9020e-19", "1.5710e-19", "1.2760e-19"});
    model.component("comp1").physics("plas").create("eir33", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir33").set("formula", "e+Cl=>e+Cl");
    model.component("comp1").physics("plas").feature("eir33").set("mratio", "1.510e-5");
    model.component("comp1").physics("plas").feature("eir33").set("SpecifyReactionUsing", "UseCrossSectionData");
    model.component("comp1").physics("plas").feature("eir33").set("eedf", "Maxwellian");
    model.component("comp1").physics("plas").feature("eir33")
         .set("xdata", new double[]{0, 0, 0.125, 0.25, 0.5, 1, 1.5, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 25});
    model.component("comp1").physics("plas").feature("eir33")
         .set("ydata", new String[]{"0", "6E-20", "6E-20", "4E-20", "2.2E-20", "2E-20", "2.2E-20", "3E-20", "5E-20", "8E-20", 
         "1.2E-19", "1.6E-19", "2.1E-19", "2.3E-19", "2.28E-19", "2.15E-19", "1.95E-19", "1.75E-19", "1.57E-19", "1.4E-19", 
         "1.25E-19", "1.15E-19", "1.05E-19", "9.8E-20", "9.1E-20", "8.5E-20", "8E-20", "7.5E-20", "7.2E-20"});
    model.component("comp1").physics("plas").feature("Cl2").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Cl2").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("plas").feature("Cl2").set("xfeed", 1);
    model.component("comp1").physics("plas").feature("Cl").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("plas").feature("Cl2_1p").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("plas").feature("Cl2_1p").set("n0", "1E16[1/m^3]");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("plas").feature("Cl_1p").set("n0", "1E14[1/m^3]");
    model.component("comp1").physics("plas").feature("Cl_1p").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("plas").feature("Cl_1m").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("plas").feature("Cl_1m").set("n0", "1E14[1/m^3]");
    model.component("comp1").physics("plas").feature("Cl2v1").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("plas").feature("Cl2v2").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("plas").feature("Cl2v3").set("PresetSpeciesData", "Cl2");
    model.component("comp1").physics("plas").feature("Cl12").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("plas").feature("Cl52").set("PresetSpeciesData", "Cl");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Cl2+=>Cl2");
    model.component("comp1").physics("plas").feature("sr1").set("SpecifyReactionUsing", "UseBohm");
    model.component("comp1").physics("plas").feature("sr1").set("hl", "hRCl2");
    model.component("comp1").physics("plas").feature("sr1").selection().set(4);
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Cl=>0.5Cl2");
    model.component("comp1").physics("plas").feature("sr2").set("SpecifyReactionUsing", "UseStickingDiffusion");
    model.component("comp1").physics("plas").feature("sr2").set("gammaf", "gammaCl_steel");
    model.component("comp1").physics("plas").feature("sr2").set("LambdaDiff", "Lambda_diff");
    model.component("comp1").physics("plas").feature("sr2").selection().all();
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr3").set("formula", "Cl+=>Cl");
    model.component("comp1").physics("plas").feature("sr3").set("SpecifyReactionUsing", "UseBohm");
    model.component("comp1").physics("plas").feature("sr3").set("hl", "hRCl");
    model.component("comp1").physics("plas").feature("sr3").selection().set(4);
    model.component("comp1").physics("plas").create("sr4", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr4").set("formula", "Cl2v1=>Cl2");
    model.component("comp1").physics("plas").feature("sr4").set("SpecifyReactionUsing", "UseStickingDiffusion");
    model.component("comp1").physics("plas").feature("sr4").set("LambdaDiff", "Lambda_diff");
    model.component("comp1").physics("plas").feature("sr4").selection().all();
    model.component("comp1").physics("plas").feature().duplicate("sr5", "sr4");
    model.component("comp1").physics("plas").feature("sr5").set("formula", "Cl2v2=>Cl2");
    model.component("comp1").physics("plas").feature().duplicate("sr6", "sr5");
    model.component("comp1").physics("plas").feature("sr6").set("formula", "Cl2v3=>Cl2");
    model.component("comp1").physics("plas").feature().duplicate("sr7", "sr6");
    model.component("comp1").physics("plas").feature("sr7").set("formula", "Cl12=>Cl");
    model.component("comp1").physics("plas").feature().duplicate("sr8", "sr7");
    model.component("comp1").physics("plas").feature("sr8").set("formula", "Cl52=>Cl");
    model.component("comp1").physics("plas").create("sr9", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr9").set("formula", "Cl2+=>Cl2");
    model.component("comp1").physics("plas").feature("sr9").set("SpecifyReactionUsing", "UseBohm");
    model.component("comp1").physics("plas").feature("sr9").set("hl", "hLCl2");
    model.component("comp1").physics("plas").feature("sr9").selection().set(2, 3);
    model.component("comp1").physics("plas").create("sr10", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr10").set("formula", "Cl+=>Cl");
    model.component("comp1").physics("plas").feature("sr10").set("SpecifyReactionUsing", "UseBohm");
    model.component("comp1").physics("plas").feature("sr10").set("hl", "hLCl");
    model.component("comp1").physics("plas").feature("sr10").selection().set(2, 3);

    model.study("std1").label("\u6821\u6b63\u529f\u7387\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std1").feature("time").set("tlist", "0 10^{range(-12,0.1,2)}");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Rad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Rad", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Pabs", 0);
    model.study("std1").feature("param").setIndex("plistarr", "25 50 100 200 300", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.param().set("Rad", "18.5[cm]");
    model.param().set("L", "20[cm]");
    model.param().set("Qfeed", "100");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/plas", true);
    model.study("std2").feature("time").set("tlist", "0 10^{range(-12,0.1,2)}");
    model.study("std2").label("Malyshev \u548c Donnelly \u529f\u7387\u626b\u63cf");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Rad", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Rad", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Pabs", 0);
    model.study("std2").feature("param").setIndex("plistarr", "50 100 200 400 650", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol8").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std2");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol9");
    model.batch("p2").run("compute");

    model.param().set("Rad", "10[cm]");
    model.param().set("L", "8.5[cm]");
    model.param().set("Pabs", "300[W]");
    model.param().set("Qfeed", "10");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/plas", true);
    model.study("std3").feature("time").set("tlist", "0 10^{range(-12,0.1,2)}");
    model.study("std3").label("Corr \u538b\u529b\u626b\u63cf");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "Rad", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "Rad", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "p0", 0);
    model.study("std3").feature("param").setIndex("plistarr", "1e-3 2e-3 5e-3 10e-3 30e-3 100e-3", 0);
    model.study("std3").feature("param").setIndex("punit", "Torr", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol15").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol16");
    model.sol("sol16").study("std3");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol16");
    model.batch("p3").run("compute");

    model.param().set("Rad", "15[cm]");
    model.param().set("L", "14[cm]");
    model.param().set("Qfeed", "20");

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/plas", true);
    model.study("std4").feature("time").set("tlist", "0 10^{range(-12,0.1,2)}");
    model.study("std4").label("Efremov \u538b\u529b\u626b\u63cf");
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "Rad", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "Rad", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "p0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "1e-3 2e-3 5e-3 10e-3 30e-3 100e-3", 0);
    model.study("std4").feature("param").setIndex("punit", "Torr", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol23").feature("t1").feature("fc1").set("jtech", "minimal");

    model.study("std4").createAutoSequences("all");

    model.sol().create("sol24");
    model.sol("sol24").study("std4");
    model.sol("sol24").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p4").feature("so1").set("psol", "sol24");
    model.batch("p4").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("Corr \u529f\u7387\u626b\u63cf");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5438\u6536\u529f\u7387 (W)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6570\u5bc6\u5ea6 (m<sup>-3</sup>)");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "n-/ne");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 325);
    model.result("pg1").set("ymin", "1e14");
    model.result("pg1").set("ymax", "1e21");
    model.result("pg1").set("yminsec", 0);
    model.result("pg1").set("ymaxsec", 20);
    model.result("pg1").set("ylog", true);
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "plas.n_wCl", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "plas.ne", 1);
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "Cl", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "ne", 1);
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob2").setIndex("expr", "alpha", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "", 0);
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg1").feature("glob2").set("legendmethod", "manual");
    model.result("pg1").feature("glob2").setIndex("legends", "n-/ne", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Malyshev \u548c Donnelly \u529f\u7387\u626b\u63cf");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5438\u6536\u529f\u7387 (W)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6570\u5bc6\u5ea6 (m<sup>-3</sup>)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 700);
    model.result("pg2").set("ymin", "1e15");
    model.result("pg2").set("ymax", "1e21");
    model.result("pg2").set("ylog", true);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "plas.n_wCl", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "plas.ne", 1);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "Cl", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "ne", 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Corr \u548c Efremov \u538b\u529b\u626b\u63cf");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u538b\u529b\uff08\u6258\uff09");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6570\u5bc6\u5ea6 (m<sup>-3</sup>)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", "8e-4");
    model.result("pg3").set("xmax", "125e-3");
    model.result("pg3").set("ymin", "1e16");
    model.result("pg3").set("ymax", "1e21");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset6");
    model.result("pg3").feature("glob1").setIndex("looplevelinput", "last", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "plas.n_wCl", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "p0");
    model.result("pg3").feature("glob1").set("xdataunit", "Torr");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "Cl", 0);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("data", "dset8");
    model.result("pg3").feature("glob2").setIndex("looplevelinput", "last", 0);
    model.result("pg3").feature("glob2").setIndex("expr", "plas.ne", 0);
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg3").feature("glob2").set("xdata", "expr");
    model.result("pg3").feature("glob2").set("xdataexpr", "p0");
    model.result("pg3").feature("glob2").set("xdataunit", "Torr");
    model.result("pg3").feature("glob2").set("legendmethod", "manual");
    model.result("pg3").feature("glob2").setIndex("legends", "ne", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Efremov \u538b\u529b\u626b\u63cf");
    model.result("pg4").set("data", "dset8");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u538b\u529b\uff08\u6258\uff09");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Te (V)");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", "8e-4");
    model.result("pg4").set("xmax", "125e-3");
    model.result("pg4").set("ymin", 1);
    model.result("pg4").set("ymax", 5);
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "plas.Te", 0);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "p0");
    model.result("pg4").feature("glob1").set("xdataunit", "Torr");
    model.result("pg4").run();

    model.title("\u6c2f\u653e\u7535\u5168\u5c40\u6a21\u578b");

    model
         .description("\u542b\u6c2f\u7684\u7b49\u79bb\u5b50\u4f53\u653e\u7535\u901a\u5e38\u5728\u5fae\u7535\u5b50\u5236\u9020\u4e2d\u7528\u4e8e\u534a\u5bfc\u4f53\u548c\u91d1\u5c5e\u7684\u8680\u523b\u3002\n\n\u672c\u6559\u5b66\u6a21\u578b\u4f7f\u7528\u5168\u5c40\uff08\u4f53\u79ef\u5e73\u5747\uff09\u6269\u6563\u6a21\u578b\u7814\u7a76\u6c2f\u7b49\u79bb\u5b50\u4f53\u653e\u7535\u3002\u5728\u8fd0\u884c\u7a7a\u95f4\u76f8\u5173\u6a21\u578b\u7684\u4eff\u771f\u65f6\uff0c\u4f7f\u7528\u5168\u5c40\u6269\u6563\u6a21\u578b\u8017\u65f6\u975e\u5e38\u77ed\u3002\u56e0\u6b64\uff0c\u8fd9\u79cd\u6a21\u578b\u6210\u4e3a\u7814\u7a76\u5927\u578b\u53cd\u5e94\u96c6\u548c\u6269\u5c55\u53c2\u6570\u533a\u57df\u7684\u7406\u60f3\u9009\u62e9\u3002\n\n\u8fd9\u4e00\u201c\u6c2f\u653e\u7535\u201d\u6a21\u578b\u7814\u7a76 50 \u5230 600\u00a0W \u7684\u5438\u6536\u529f\u7387\uff0c\u5de5\u4f5c\u538b\u529b\u4e3a 1 \u5230 100\u00a0mTorr\u3002\u7535\u5b50\u5bc6\u5ea6\u3001\u7535\u5b50\u6e29\u5ea6\u4ee5\u53ca\u6c2f\u539f\u5b50\u5bc6\u5ea6\u7b49\u591a\u4e2a\u76f8\u5173\u7269\u7406\u91cf\u7684\u6a21\u62df\u7ed3\u679c\u4e0e\u6587\u732e\u4e2d\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u4e2d\u6267\u884c\u7684\u6d4b\u91cf\u7ed3\u679c\u975e\u5e38\u4e00\u81f4\u3002");

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
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();

    model.label("chlorine_global_model.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
