/*
 * inas_nanowire_traps_hysteresis_density_gradient.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:55 by COMSOL 6.3.0.290. */
public class inas_nanowire_traps_hysteresis_density_gradient {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Transistors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");

    model.param().label("\u53c2\u6570 1 - \u7eb3\u7c73\u7ebf FET");
    model.param().set("R0", "23[nm]");
    model.param().descr("R0", "\u7eb3\u7c73\u7ebf\u534a\u5f84");
    model.param().set("L0", "1.6[um]");
    model.param().descr("L0", "\u7eb3\u7c73\u7ebf FET \u901a\u9053\u957f\u5ea6");
    model.param().set("mun0", "4e4[cm^2/V/s]");
    model.param().descr("mun0", "\u7535\u5b50\u8fc1\u79fb\u7387");
    model.param().set("mup0", "5e2[cm^2/V/s]");
    model.param().descr("mup0", "\u7a7a\u7a74\u8fc1\u79fb\u7387");
    model.param().set("epsr0", "15.15");
    model.param().descr("epsr0", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("Eg00", "0.354[V]");
    model.param().descr("Eg00", "\u5e26\u9699");
    model.param().set("chi00", "4.9[V]");
    model.param().descr("chi00", "\u7535\u5b50\u4eb2\u548c\u80fd");
    model.param().set("Nc0", "8.7e16[cm^-3]");
    model.param().descr("Nc0", "\u5bfc\u5e26\u6709\u6548\u6001\u5bc6\u5ea6");
    model.param().set("Nv0", "6.6e18[cm^-3]");
    model.param().descr("Nv0", "\u4ef7\u5e26\u6709\u6548\u6001\u5bc6\u5ea6");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("d_ox0", "100[nm]");
    model.param().descr("d_ox0", "\u6805\u6781\u6c27\u5316\u7269\u539a\u5ea6");
    model.param().set("d_ox", "d_ox0*acosh((d_ox0+R0)/R0)");
    model.param().descr("d_ox", "\u6805\u6781\u6c27\u5316\u7269\u6709\u6548\u539a\u5ea6");
    model.param().set("epsr_ox", "2.12");
    model.param()
         .descr("epsr_ox", "\u6805\u6781\u6c27\u5316\u7269\u7684\u6709\u6548\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("G_s", "50[uS]");
    model.param().descr("G_s", "\u4e32\u8054\u7535\u5bfc\u7387");
    model.param("default").setShowInParamSel(false);
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u8868\u9762\u9677\u9631");
    model.param("par2").set("Ds_tot", "4e13[cm^-2]");
    model.param("par2").descr("Ds_tot", "\u603b\u9677\u9631\u5bc6\u5ea6");
    model.param("par2").set("Ds_f", "1.6e12[cm^-2]");
    model.param("par2").descr("Ds_f", "\u5feb\u901f\u9677\u9631\u5bc6\u5ea6");
    model.param("par2").set("Ds_s", "Ds_tot-Ds_f");
    model.param("par2").descr("Ds_s", "\u6162\u901f\u9677\u9631\u5bc6\u5ea6");
    model.param("par2").set("Ea0_f", "0[mV]");
    model.param("par2").descr("Ea0_f", "\u5feb\u901f\u9677\u9631\u53d1\u5c04\u52bf\u5792\u53c2\u6570");
    model.param("par2").set("Ea0_s", "700[mV]");
    model.param("par2").descr("Ea0_s", "\u6162\u901f\u9677\u9631\u53d1\u5c04\u52bf\u5792\u53c2\u6570");
    model.param("par2").set("gamma0", "1e6[1/s]");
    model.param("par2").descr("gamma0", "\u6355\u83b7\u7387\u5e38\u6570");
    model.param("par2").set("Et0_a", "-400[mV]");
    model.param("par2")
         .descr("Et0_a", "\u53d7\u4e3b\u9677\u9631\u80fd\u7ea7\u5206\u5e03\u4e2d\u5fc3\u4f4e\u4e8e Ec\uff08\u8d1f\u503c\u8868\u793a\u9ad8\u4e8e Ec\uff0c\u5373\u5728\u5bfc\u5e26\u5185\uff09");
    model.param("par2").set("Et0_d", "300[mV]");
    model.param("par2")
         .descr("Et0_d", "\u65bd\u4e3b\u9677\u9631\u80fd\u7ea7\u5206\u5e03\u4e2d\u5fc3\u4f4e\u4e8e Ec");
    model.param("par2").set("sig0", "200[mV]");
    model.param("par2").descr("sig0", "\u9677\u9631\u80fd\u7ea7\u5206\u5e03\u5bbd\u5ea6");
    model.param("par2").set("gD0", "2");
    model.param("par2").descr("gD0", "\u7b80\u5e76\u56e0\u5b50");
    model.param("par2").setShowInParamSel(false);

    model.func().create("tri1", "Triangle");
    model.func("tri1").set("lower", 0);
    model.func("tri1").set("upper", 1);
    model.func("tri1").set("smoothactive", false);

    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u504f\u7f6e\u6761\u4ef6");
    model.param("par3").set("dVdt", "44[uV/s]");
    model.param("par3").descr("dVdt", "\u7535\u538b\u659c\u7387");
    model.param("par3").set("V0", "6[V]");
    model.param("par3")
         .descr("V0", "\u4e09\u89d2\u7535\u538b\u659c\u5761\u7684\u521d\u59cb\u548c\u6700\u7ec8\u7535\u538b");
    model.param("par3").set("V1", "-4[V]");
    model.param("par3")
         .descr("V1", "\u4e09\u89d2\u7535\u538b\u659c\u5761\u7684\u5cf0\u503c\uff08\u6216\u8c37\u503c\uff09\u7535\u538b");
    model.param("par3").set("t", "0[s]");
    model.param("par3").descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param("par3").set("t_max", "2*abs(V1-V0)/dVdt");
    model.param("par3").descr("t_max", "\u7535\u538b\u659c\u5761\u6301\u7eed\u65f6\u95f4");
    model.param("par3").set("Vg", "V0+(V1-V0)*tri1(t/t_max)");
    model.param("par3").descr("Vg", "\u6805\u6781\u7535\u538b");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "R0", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor_material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mun", new String[]{"mun0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("mup", new String[]{"mup0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epsr0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"Eg00"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", new String[]{"chi00"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nc", new String[]{"Nc0"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nv", new String[]{"Nv0"});

    model.component("comp1").physics("semi").prop("d").set("d", "L0");
    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2DG");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").feature("smm1")
         .set("meDG", new String[]{"me_const*0.03", "0", "0", "0", "me_const*0.03", "0", "0", "0", "me_const*0.03"});
    model.component("comp1").physics("semi").feature("smm1")
         .set("mhDG", new String[]{"me_const*0.4", "0", "0", "0", "me_const*0.4", "0", "0", "0", "me_const*0.4"});
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").create("gc1", "GateContact", 0);
    model.component("comp1").physics("semi").feature("gc1").selection().set(2);
    model.component("comp1").physics("semi").feature("gc1").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", "epsr_ox");
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "d_ox");
    model.component("comp1").physics("semi").feature("gc1").set("DGexteriorBC", "barrier");
    model.component("comp1").physics("semi").feature("gc1")
         .set("meOx", new String[]{"me_const*0.5", "0", "0", "0", "me_const*0.5", "0", "0", "0", "me_const*0.5"});
    model.component("comp1").physics("semi").feature("gc1")
         .set("meOxStar", new String[]{"me_const*0.22", "0", "0", "0", "me_const*0.22", "0", "0", "0", "me_const*0.22"});
    model.component("comp1").physics("semi").feature("gc1").set("Phi_nOx", "3[V]");
    model.component("comp1").physics("semi").feature("gc1").set("Phi_pOx", "3[V]");
    model.component("comp1").physics("semi").create("tasr1", "TrapAssistedSurfaceRecombination", 0);
    model.component("comp1").physics("semi").feature("tasr1").selection().set(2);
    model.component("comp1").physics("semi").feature("tasr1").set("IncludeTraps", "ExplicitTraps");
    model.component("comp1").physics("semi").feature("tasr1")
         .set("SpecifyDiscreteContinuous", "SpecifyContinuousAndOrDiscreteLevels");
    model.component("comp1").physics("semi").feature("tasr1").set("SpecifyTrapSpecies", true);
    model.component("comp1").physics("semi").feature("tasr1").set("TrappingFormulationDG", "Trap_occupancy");
    model.component("comp1").physics("semi").feature("tasr1").create("ctb1", "ContinuousEnergyLevelsBoundary", 0);
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .label("\u8fde\u7eed\u80fd\u7ea7 - \u5feb\u901f\u53d7\u4f53");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("TrapType", "Acceptor");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Nt_b", "Ds_f");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("DistributionCenterPoint", "FromConduction");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Et0in", "Et0_a");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("gswid", "sig0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("gsd_d", "gD0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Edisc", 100);
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("Vxd_min_in", "semi.tasr1.Ec0t-Et0_a-3*sig0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("Vxd_max_in", "semi.tasr1.Ec0t-Et0_a+3*sig0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("ElectronCaptureProbabilitySelect", "userdef");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1")
         .set("HoleCaptureProbabilitySelect", "userdef");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Cpin", "0[cm^3/s]");
    model.component("comp1").physics("semi").feature("tasr1").feature().duplicate("ctb2", "ctb1");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2")
         .label("\u8fde\u7eed\u80fd\u7ea7 - \u5feb\u901f\u65bd\u4e3b");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2").set("TrapType", "Donor");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2").set("Et0in", "Et0_d");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2")
         .set("Vxd_min_in", "semi.tasr1.Ec0t-Et0_d-3*sig0");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2")
         .set("Vxd_max_in", "semi.tasr1.Ec0t-Et0_d+3*sig0");
    model.component("comp1").physics("semi").feature("tasr1").feature().duplicate("ctb3", "ctb1");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb3")
         .label("\u8fde\u7eed\u80fd\u7ea7 - \u6162\u901f\u53d7\u4e3b");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb3").set("Nt_b", "Ds_s");
    model.component("comp1").physics("semi").feature("tasr1").feature().duplicate("ctb4", "ctb2");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb4")
         .label("\u8fde\u7eed\u80fd\u7ea7 - \u6162\u901f\u65bd\u4e3b");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb4").set("Nt_b", "Ds_s");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u6355\u83b7\u6982\u7387");
    model.component("comp1").variable("var1").set("dE_fa", "semi.tasr1.ctb1.Vxd-semi.tasr1.Ec0t");
    model.component("comp1").variable("var1")
         .descr("dE_fa", "\u76f8\u5bf9\u4e8e\u5bfc\u5e26\u8fb9\u7684\u5feb\u901f\u53d7\u4e3b\u9677\u9631\u80fd\u7ea7");
    model.component("comp1").variable("var1").set("dE_fd", "semi.tasr1.ctb2.Vxd-semi.tasr1.Ec0t");
    model.component("comp1").variable("var1")
         .descr("dE_fd", "\u76f8\u5bf9\u4e8e\u5bfc\u5e26\u8fb9\u7684\u5feb\u901f\u65bd\u4e3b\u9677\u9631\u80fd\u7ea7");
    model.component("comp1").variable("var1").set("dE_sa", "semi.tasr1.ctb3.Vxd-semi.tasr1.Ec0t");
    model.component("comp1").variable("var1")
         .descr("dE_sa", "\u76f8\u5bf9\u4e8e\u5bfc\u5e26\u8fb9\u7684\u6162\u901f\u53d7\u4e3b\u9677\u9631\u80fd\u7ea7");
    model.component("comp1").variable("var1").set("dE_sd", "semi.tasr1.ctb4.Vxd-semi.tasr1.Ec0t");
    model.component("comp1").variable("var1")
         .descr("dE_sd", "\u76f8\u5bf9\u4e8e\u5bfc\u5e26\u8fb9\u7684\u6162\u901f\u65bd\u4e3b\u9677\u9631\u80fd\u7ea7");
    model.component("comp1").variable("var1").set("Eb_fa", "dE_fa+max(-dE_fa,Ea0_f)");
    model.component("comp1").variable("var1")
         .descr("Eb_fa", "\u5feb\u901f\u53d7\u4e3b\u9677\u9631\u6355\u83b7\u52bf\u5792");
    model.component("comp1").variable("var1").set("Eb_fd", "dE_fd+max(-dE_fd,Ea0_f)");
    model.component("comp1").variable("var1")
         .descr("Eb_fd", "\u5feb\u901f\u65bd\u4e3b\u9677\u9631\u6355\u83b7\u52bf\u5792");
    model.component("comp1").variable("var1").set("Eb_sa", "dE_sa+max(-dE_sa,Ea0_s)");
    model.component("comp1").variable("var1")
         .descr("Eb_sa", "\u6162\u901f\u53d7\u4e3b\u9677\u9631\u6355\u83b7\u52bf\u5792");
    model.component("comp1").variable("var1").set("Eb_sd", "dE_sd+max(-dE_sd,Ea0_s)");
    model.component("comp1").variable("var1")
         .descr("Eb_sd", "\u6162\u901f\u65bd\u4e3b\u9677\u9631\u6355\u83b7\u52bf\u5792");
    model.component("comp1").variable("var1").set("Nfac", "10");
    model.component("comp1").variable("var1").descr("Nfac", "\u7535\u5b50\u901a\u91cf\u8865\u507f\u56e0\u5b50");
    model.component("comp1").variable("var1").set("Cn_fa", "gamma0/Nc0*exp(-Eb_fa*e_const/k_B_const/T0)*Nfac");
    model.component("comp1").variable("var1")
         .descr("Cn_fa", "\u5feb\u901f\u53d7\u4e3b\u9677\u9631\u7535\u5b50\u6355\u83b7\u6982\u7387");
    model.component("comp1").variable("var1").set("Cn_fd", "gamma0/Nc0*exp(-Eb_fd*e_const/k_B_const/T0)*Nfac");
    model.component("comp1").variable("var1")
         .descr("Cn_fd", "\u5feb\u901f\u65bd\u4e3b\u9677\u9631\u7535\u5b50\u6355\u83b7\u6982\u7387");
    model.component("comp1").variable("var1").set("Cn_sa", "gamma0/Nc0*exp(-Eb_sa*e_const/k_B_const/T0)*Nfac");
    model.component("comp1").variable("var1")
         .descr("Cn_sa", "\u6162\u901f\u53d7\u4e3b\u9677\u9631\u7535\u5b50\u6355\u83b7\u6982\u7387");
    model.component("comp1").variable("var1").set("Cn_sd", "gamma0/Nc0*exp(-Eb_sd*e_const/k_B_const/T0)*Nfac");
    model.component("comp1").variable("var1")
         .descr("Cn_sd", "\u6162\u901f\u65bd\u4e3b\u9677\u9631\u7535\u5b50\u6355\u83b7\u6982\u7387");

    model.component("comp1").physics("semi").feature("tasr1").feature("ctb1").set("Cnin", "Cn_fa");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb2").set("Cnin", "Cn_fd");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb3").set("Cnin", "Cn_sa");
    model.component("comp1").physics("semi").feature("tasr1").feature("ctb4").set("Cnin", "Cn_sd");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1")
         .label("\u79ef\u5206 1 - \u5728\u6805\u6781\u4e0a\u8bbf\u95ee\u9677\u9631\u53d8\u91cf");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 2 - \u5728\u5706\u67f1\u4f53\u4e0a\u79ef\u5206");
    model.component("comp1").cpl("intop2").selection().all();

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf 2 - \u7535\u5bfc\u7387");
    model.component("comp1").variable("var2")
         .set("NAm", "-intop1(semi.tasr1.ctb1.rho_tr+semi.tasr1.ctb3.rho_tr)/e_const");
    model.component("comp1").variable("var2")
         .descr("NAm", "NA-\uff1a\u7535\u79bb\u53d7\u4e3b\u9677\u9631\u5bc6\u5ea6");
    model.component("comp1").variable("var2")
         .set("NDp", "intop1(semi.tasr1.ctb2.rho_tr+semi.tasr1.ctb4.rho_tr)/e_const");
    model.component("comp1").variable("var2")
         .descr("NDp", "ND+\uff1a\u7535\u79bb\u65bd\u4e3b\u9677\u9631\u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("G_0", "intop2(mun0*semi.N)*e_const*Ds_tot/(NDp+30*NAm)/L0");
    model.component("comp1").variable("var2").descr("G_0", "\u901a\u9053\u7535\u5bfc\u7387");
    model.component("comp1").variable("var2").set("G", "1/(1/G_0+1/G_s)");
    model.component("comp1").variable("var2").descr("G", "\u603b\u4f20\u5bfc\u7387");

    model.study("std1")
         .label("\u7814\u7a76 1 - \u4e24\u4e2a\u5feb\u901f\u548c\u4e00\u4e2a\u6162\u901f\u6805\u6781\u7535\u538b\u659c\u5761");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "dVdt", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V/s", 0);
    model.study("std1").feature("param").setIndex("pname", "dVdt", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "44[uV/s] 27[mV/s] 27[mV/s]", 0);
    model.study("std1").feature("param").setIndex("punit", "mV/s", 0);
    model.study("std1").feature("param").setIndex("pname", "V0", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("pname", "V0", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("plistarr", "6 -4 6", 1);
    model.study("std1").feature("param").setIndex("pname", "V1", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "V", 2);
    model.study("std1").feature("param").setIndex("pname", "V1", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "V", 2);
    model.study("std1").feature("param").setIndex("plistarr", "-4 6 -4", 2);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,1)*t_max");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("data", "parent");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u91cf\u5b50\u52bf (semi)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg4").feature().create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").label("\u91cf\u5b50\u52bf\uff0c\u7535\u5b50");
    model.result("pg4").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr1").set("expr", "semi.VnDG");
    model.result("pg4").feature("lngr1").set("smooth", "internal");
    model.result("pg4").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr1").set("data", "parent");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature().create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").label("\u91cf\u5b50\u52bf\uff0c\u7a7a\u7a74");
    model.result("pg4").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr2").set("expr", "semi.VpDG");
    model.result("pg4").feature("lngr2").set("smooth", "internal");
    model.result("pg4").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr2").set("data", "parent");
    model.result("pg4").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr2").selection().set(1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("xdataexpr", "R");
    model.result("pg5").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg5").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg5").feature("lngr1").set("linecolor", "red");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg5").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg5").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("xdataexpr", "R");
    model.result("pg5").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg5").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg5").feature("lngr2").set("linecolor", "blue");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg5").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg5").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("legendpos", "uppermiddle");
    model.result("pg5")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg5").set("ylog", true);
    model.result("pg5").feature("lngr1").label("P \u578b");
    model.result("pg5").feature("lngr2").label("N \u578b");
    model.result("pg5").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("G-Vg \u66f2\u7ebf");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "G", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "uS", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "Vg");
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("Ef-Ec vs. Vg");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(2);
    model.result("pg6").feature("ptgr1").set("expr", "semi.Efn-semi.Ec");
    model.result("pg6").feature("ptgr1").set("descractive", true);
    model.result("pg6").feature("ptgr1").set("descr", "Efn-Ec");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "Vg");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("autopoint", false);
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("semie", "SemiconductorEquilibrium");
    model.study("std2").feature("semie").set("plotgroup", "Default");
    model.study("std2").feature("semie").set("ftplistmethod", "manual");
    model.study("std2").feature("semie").set("solnum", "auto");
    model.study("std2").feature("semie").set("notsolnum", "auto");
    model.study("std2").feature("semie").set("outputmap", new String[]{});
    model.study("std2").feature("semie").set("ngenAUX", "1");
    model.study("std2").feature("semie").set("goalngenAUX", "1");
    model.study("std2").feature("semie").set("ngenAUX", "1");
    model.study("std2").feature("semie").set("goalngenAUX", "1");
    model.study("std2").feature("semie").setSolveFor("/physics/semi", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5e73\u8861");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("semie").set("useparam", true);
    model.study("std2").feature("semie").setIndex("pname", "dVdt", 0);
    model.study("std2").feature("semie").setIndex("plistarr", "", 0);
    model.study("std2").feature("semie").setIndex("punit", "V/s", 0);
    model.study("std2").feature("semie").setIndex("pname", "dVdt", 0);
    model.study("std2").feature("semie").setIndex("plistarr", "", 0);
    model.study("std2").feature("semie").setIndex("punit", "V/s", 0);
    model.study("std2").feature("semie").setIndex("pname", "Vg", 0);
    model.study("std2").feature("semie").setIndex("plistarr", "range(-4,1,6)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg6").run();
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("data", "dset4");
    model.result("pg6").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg6").feature("ptgr2").set("linecolor", "black");
    model.result("pg6").feature("ptgr2").set("legendprefix", "\u5e73\u8861");
    model.result("pg6").run();

    model
         .title("InAs \u7eb3\u7c73\u7ebf\u573a\u6548\u5e94\u6676\u4f53\u7ba1\u4e2d\u7684\u8868\u9762\u9677\u9631\u8bf1\u5bfc\u6ede\u540e - \u5bc6\u5ea6\u68af\u5ea6\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790 InAs \u7eb3\u7c73\u7ebf\u573a\u6548\u5e94\u6676\u4f53\u7ba1\u7684\u7535\u5bfc-\u6805\u6781-\u7535\u538b (G-Vg) \u66f2\u7ebf\u7684\u6ede\u540e\u73b0\u8c61\uff0c\u5176\u4e2d\u4f7f\u7528\u5bc6\u5ea6\u68af\u5ea6\u7406\u8bba\u5c06\u91cf\u5b50\u9650\u5236\u6548\u5e94\u6dfb\u52a0\u5230\u4f20\u7edf\u7684\u6f02\u79fb-\u6269\u6563\u516c\u5f0f\u4e2d\uff0c\u800c\u6ca1\u6709\u5927\u5e45\u589e\u52a0\u8ba1\u7b97\u6210\u672c\u3002\u8fd9\u79cd\u6ede\u540e\u73b0\u8c61\u662f\u7531\u5feb\u901f\u548c\u6162\u901f\u534a\u5bfc\u4f53-\u6c27\u5316\u7269\u754c\u9762\u9677\u9631\u7684\u52a8\u6001\u5145\u7535\u6548\u5e94\u5f15\u8d77\u7684\uff0c\u8fd9\u4e9b\u9677\u9631\u5177\u6709\u8fde\u7eed\u7684\u80fd\u91cf\u5206\u5e03\uff0c\u4e14\u5305\u542b\u65bd\u4e3b\u548c\u53d7\u4e3b\u4e24\u79cd\u7c7b\u578b\u3002\u672c\u4f8b\u4f7f\u7528\u70ed\u6fc0\u6d3b\u6a21\u578b\u6765\u6a21\u62df\u6355\u83b7\u6982\u7387\uff0c\u5176\u4e2d\u52bf\u5792\u9ad8\u5ea6\u968f\u9677\u9631\u80fd\u7ea7\u53d1\u751f\u53d8\u5316\u3002\u5728\u5404\u79cd\u7535\u538b\u659c\u5347\u6761\u4ef6\u4e0b\u8ba1\u7b97\u51fa\u7684 G-Vg \u66f2\u7ebf\u7684\u5b9a\u6027\u884c\u4e3a\u548c\u6570\u91cf\u7ea7\u4e0e\u6587\u732e\u4e2d\u7684\u4eff\u771f\u548c\u5b9e\u9a8c\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("inas_nanowire_traps_hysteresis_density_gradient.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
