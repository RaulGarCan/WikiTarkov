package com.raulgarcan.wikitarkov.data;

import com.google.gson.Gson;
import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.pojo.enums.Caliber;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        /*
        Para asignar los nombres a las municiones se escribirán todos en orden en arrays separados
        por calibres y se asociarán en el orden almacenados en el archivo json
         */

        //peticionAPI();
        almacenarPenPerTier();

        String json = leerJSON("ammo_data");
        Data data = parsearJSON(json);
        System.out.println(data.getAmmo());
        System.out.println(data.getAmmo().size());

        ArrayList<ArrayList<Ammo>> matrizClasificada = new ArrayList<>();
        for (Caliber c : Caliber.values()) {
            matrizClasificada.add(clasificarPorCalibre(data, c));
        }
        String[] caliberNames = {
                "7.62x25mm Tokarev",
                "9x18mm Makarov",
                "9x19mm Parabellum",
                "9x21mm Gyurza",
                ".357 Magnum",
                ".45 ACP",
                "4.6x30mm HK",
                "5.7x28mm FN",
                "5.45x39mm",
                "5.56x45mm NATO",
                "6.8x51mm",
                ".300 Blackout",
                "7.62x39mm",
                "7.62x51mm NATO",
                "7.62x54mmR",
                ".338 Lapua Magnum",
                "9x39mm",
                ".366 TKM",
                "12.7x55mm STs-130",
                "20/70",
                "23x75mm",
                "40x46mm",
        };
        String[][] ammoLongName = {
                {
                        caliberNames[0]+" LRNPC",
                        caliberNames[0]+" LRN",
                        caliberNames[0]+" FMJ43",
                        caliberNames[0]+" AKBS",
                        caliberNames[0]+" P gl",
                        caliberNames[0]+" PT gzh",
                        caliberNames[0]+" Pst gzh"
                },
                {
                        caliberNames[1]+ " SP8 gzh",
                        caliberNames[1]+ " SP7 gzh",
                        caliberNames[1]+ " PSV",
                        caliberNames[1]+ " P gzh",
                        caliberNames[1]+ " PSO gzh",
                        caliberNames[1]+ " PS gs PPO",
                        caliberNames[1]+ " PRS gs",
                        caliberNames[1]+ " PPe gzh",
                        caliberNames[1]+ " PPT gzh",
                        caliberNames[1]+ " Pst gzh",
                        caliberNames[1]+ " RG028 gzh",
                        caliberNames[1]+ " BZhT gzh",
                        caliberNames[1]+ " PstM gzh",
                        caliberNames[1]+ " PBM gzh",
                },
                {
                        caliberNames[2]+" RIP",
                        caliberNames[2]+ " QuakeMaker",
                        caliberNames[2]+ " PSO gzh",
                        caliberNames[2]+ " Luger CCI",
                        caliberNames[2]+ " Green Tracer",
                        caliberNames[2]+ " FMJ M882",
                        caliberNames[2]+ " Pst gzh",
                        caliberNames[2]+ " AP 6.3",
                        caliberNames[2]+ " PBP gzh",
                },
                {
                        caliberNames[3]+ " PE gzh",
                        caliberNames[3]+ " P gzh",
                        caliberNames[3]+ " PS gzh",
                        caliberNames[3]+ " 7U4",
                        caliberNames[3]+ " BT gzh",
                        caliberNames[3]+ " 7N42 \"Zubilo\"",
                },
                {
                        caliberNames[4]+ " SP",
                        caliberNames[4]+ " HP",
                        caliberNames[4]+ " JHP",
                        caliberNames[4]+ " FMJ",
                },
                {
                        caliberNames[5]+ " RIP",
                        caliberNames[5]+ " Hydra-Shock",
                        caliberNames[5]+ " Lasermatch FMJ",
                        caliberNames[5]+ " Match FMJ",
                        caliberNames[5]+ " AP",
                },
                {
                        caliberNames[6]+ " Action SX",
                        caliberNames[6]+ " Subsonic SX",
                        caliberNames[6]+ " JSP SX",
                        caliberNames[6]+ " FMJ SX",
                        caliberNames[6]+ " AP SX",
                },
                {
                        caliberNames[7]+ " R37.F",
                        caliberNames[7]+ " R37.X",
                        caliberNames[7]+ " SS198LF",
                        caliberNames[7]+ " SS197SR",
                        caliberNames[7]+ " SB193",
                        caliberNames[7]+ " L191",
                        caliberNames[7]+ " SS190",
                },
                {
                        caliberNames[8]+ " HP",
                        caliberNames[8]+ " PRS gs",
                        caliberNames[8]+ " SP",
                        caliberNames[8]+ " US gs",
                        caliberNames[8]+ " T gs",
                        caliberNames[8]+ " FMJ",
                        caliberNames[8]+ " PS gs",
                        caliberNames[8]+ " PP gs",
                        caliberNames[8]+ " BT gs",
                        caliberNames[8]+ " 7N40",
                        caliberNames[8]+ " BP gs",
                        caliberNames[8]+ " BS gs",
                        caliberNames[8]+ " PPBS gs \"Igolnik\"",
                },
                {
                        caliberNames[9]+ " Warmageddon",
                        caliberNames[9]+ " HP",
                        caliberNames[9]+ " MK 255 Mod 0 (RRLP)",
                        caliberNames[9]+ " M856",
                        caliberNames[9]+ " FMJ",
                        caliberNames[9]+ " M855",
                        caliberNames[9]+ " 318 Mod 0 (SOST)",
                        caliberNames[9]+ " M856A1",
                        caliberNames[9]+ " M855A1",
                        caliberNames[9]+ " M995",
                        caliberNames[9]+ " SSA AP",
                },
                {
                        caliberNames[10]+ " SIG FMJ",
                        caliberNames[10]+ " SIG Hybrid",
                },
                {
                        caliberNames[11]+ " Whisper",
                        caliberNames[11]+ " V-Max",
                        caliberNames[11]+ " BCP FMJ",
                        caliberNames[11]+ " M62 Tracer",
                        caliberNames[11]+ " CBJ",
                        caliberNames[11]+ " AP",
                },
                {
                        caliberNames[12]+ " HP",
                        caliberNames[12]+ " SP",
                        caliberNames[12]+ " FMJ",
                        caliberNames[12]+ " US gzh",
                        caliberNames[12]+ " T-45M1 gzh",
                        caliberNames[12]+ " PS gzh",
                        caliberNames[12]+ " PP gzh",
                        caliberNames[12]+ " BP gzh",
                        caliberNames[12]+ " MAI AP",
                },
                {
                        caliberNames[13]+ " Ultra Nosler",
                        caliberNames[13]+ " TCW SP",
                        caliberNames[13]+ " BCP FMJ",
                        caliberNames[13]+ " M80",
                        caliberNames[13]+ " M62 Tracer",
                        caliberNames[13]+ " M61",
                        caliberNames[13]+ " M993",
                },
                {
                        caliberNames[14]+ " HP BT",
                        caliberNames[14]+ " SP BT",
                        caliberNames[14]+ " FMJ",
                        caliberNames[14]+ " T-46M gzh",
                        caliberNames[14]+ " LPS gzh",
                        caliberNames[14]+ " PS gzh",
                        caliberNames[14]+ " BT gzh",
                        caliberNames[14]+ " SNB gzh",
                        caliberNames[14]+ " BS gzh",
                },
                {
                        caliberNames[15]+ " TAC-X",
                        caliberNames[15]+ " UCW",
                        caliberNames[15]+ " FMJ",
                        caliberNames[15]+ " AP",
                },
                {
                        caliberNames[16]+ " FMJ",
                        caliberNames[16]+ " SP-5 gs",
                        caliberNames[16]+ " SPP gs",
                        caliberNames[16]+ " PAB-9 gs",
                        caliberNames[16]+ " SP-6 gs",
                        caliberNames[16]+ " BP gs",
                },
                {
                        caliberNames[17]+ " Geksa",
                        caliberNames[17]+ " FMJ",
                        caliberNames[17]+ " EKO",
                        caliberNames[17]+ " AP-M",
                },
                {
                        caliberNames[18]+ " PS12A",
                        caliberNames[18]+ " PS12",
                        caliberNames[18]+ " PS12B",
                },
                {
                        caliberNames[19]+ " 5.6mm buckshot",
                        caliberNames[19]+ " 6.2mm buckshot",
                        caliberNames[19]+ " 7.5mm buckshot",
                        caliberNames[19]+ " 7.3mm buckshot",
                        caliberNames[19]+ " Devastator slug",
                        caliberNames[19]+ " \"Poleva-3\" slug",
                        caliberNames[19]+ " Star slug",
                        caliberNames[19]+ "\"Poleva-6u\" slug",
                },
                {
                        caliberNames[20]+ " Zvezda flashbang round",
                        caliberNames[20]+ " Shrapnel-25 buckshot",
                        caliberNames[20]+ " Shrapnel-10 buckshot",
                        caliberNames[20]+ " Barrikada slug",
                },
                {
                        caliberNames[21]+ " M381 (HE) grenade",
                        caliberNames[21]+ " M386 (HE) grenade",
                        caliberNames[21]+ " M406 (HE) grenade",
                        caliberNames[21]+ " M433 (HEDP) grenade",
                        caliberNames[21]+ " M441 (HE) grenade",
                        caliberNames[21]+ " M576 (MP-APERS) grenade",
                }
        };
        String[][] ammoShortName = {
                {
                        "LRNPC",
                        "LRN",
                        "FMJ43",
                        "AKBS",
                        "P gl",
                        "PT gzh",
                        "Pst gzh"
                },
                {
                        "SP8",
                        "SP7",
                        "PSV",
                        "P",
                        "PSO",
                        "PS PPO",
                        "PRS",
                        "PPe",
                        "PPT",
                        "Pst",
                        "RG028",
                        "BZhT",
                        "PstM",
                        "PBM",
                },
                {
                        "RIP",
                        "QuakeMaker",
                        "PSO",
                        "Luger CCI",
                        "GT",
                        "M882",
                        "Pst",
                        "AP 6.3",
                        "PBP",
                },
                {
                        "PE",
                        "P",
                        "PS",
                        "7U4",
                        "BT",
                        "7N42",
                },
                {
                        "SP",
                        "HP",
                        "JHP",
                        "FMJ",
                },
                {
                        "RIP",
                        "HydraShock",
                        "Lasermatch",
                        "FMJ",
                        "AP",
                },
                {
                        "Action SX",
                        "Subsonic SX",
                        "JSP SX",
                        "FMJ SX",
                        "AP SX",
                },
                {
                        "R37.F",
                        "R37.X",
                        "SS198LF",
                        "SS197SR",
                        "SB193",
                        "L191",
                        "SS190",
                },
                {
                        "HP",
                        "PRS",
                        "SP",
                        "US",
                        "T",
                        "FMJ",
                        "PS",
                        "PP",
                        "BT",
                        "7N40",
                        "BP",
                        "BS",
                        "PPBS",
                },
                {
                        "Warmageddon",
                        "HP",
                        "RRLP",
                        "M856",
                        "FMJ",
                        "M855",
                        "SOST",
                        "M856A1",
                        "M855A1",
                        "M995",
                        "SSA AP",
                },
                {
                        "FMJ",
                        "Hybrid",
                },
                {
                        "Whisper",
                        "V-Max",
                        "BCP FMJ",
                        "M62",
                        "CBJ",
                        "AP",
                },
                {
                        "HP",
                        "SP",
                        "FMJ",
                        "US",
                        "T-45M1",
                        "PS",
                        "PP",
                        "BP",
                        "MAI AP",
                },
                {
                        "Ultra Nosler",
                        "TCW SP",
                        "BCP FMJ",
                        "M80",
                        "M62",
                        "M61",
                        "M993",
                },
                {
                        "HP BT",
                        "SP BT",
                        "FMJ",
                        "T-46M",
                        "LPS",
                        "PS",
                        "BT",
                        "SNB",
                        "BS",
                },
                {
                        "TAC-X",
                        "UCW",
                        "FMJ",
                        "AP",
                },
                {
                        "FMJ",
                        "SP-5",
                        "SPP",
                        "PAB-9",
                        "SP-6",
                        "BP",
                },
                {
                        "Geksa",
                        "FMJ",
                        "EKO",
                        "AP-M",
                },
                {
                        "PS12A",
                        "PS12",
                        "PS12B",
                },
                {
                        "5.6mm",
                        "6.2mm",
                        "7.5mm",
                        "7.3mm",
                        "Devastator",
                        "Poleva-3",
                        "Star",
                        "Poleva-6u",
                },
                {
                        "Zvezda",
                        "Shrap-25",
                        "Shrap-10",
                        "Barrikada",
                },
                {
                        "M381",
                        "M386",
                        "M406",
                        "M433",
                        "M441",
                        "M576",
                }
        };
        int ammoNameCounter = 0;
        for (ArrayList<Ammo> tmp : matrizClasificada) {
            tmp.sort(new Comparator<Ammo>() {
                @Override
                public int compare(Ammo o1, Ammo o2) {
                    return Integer.compare(o1.getPenetrationPower(), o2.getPenetrationPower());
                }
            });
            ArrayList<AmmoDef> ammoTmp = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Ammo ammo = tmp.get(i);
                String caliberName = ammo.getCaliber().replaceAll("Caliber", "");
                if (caliberName.equalsIgnoreCase("12g")) {
                    Ammo[] ammoShotguns = tmp.toArray(Ammo[]::new);
                    AmmoDef[] ammoShotgunsDef = ponerNombresYPen12g(ammoShotguns);
                    //guardarJSON(new Gson().toJson(ammoShotguns), "Shotgun" + caliberName);
                    guardarJSON(new Gson().toJson(ammoShotgunsDef), "def/" + ammoShotgunsDef[0].getCaliber());
                    if (i == 0) {
                        System.out.println("Escopetas: " + caliberName);
                    }
                    continue;
                }
                if (i == 0) {
                    System.out.println(caliberName);
                }
                Caliber caliber = Caliber.getCaliberByName(caliberName);
                //System.out.println(caliber);
                String dataPen = leerJSONPen(caliber.getFileName());
                //System.out.println(dataPen);
                PenPerTier[] penPerTier = new Gson().fromJson(dataPen, PenPerTier[].class);
                ammoTmp.add(new AmmoDef(ammo, penPerTier[i]));
            }
            AmmoDef[] result = ammoTmp.toArray(AmmoDef[]::new);
            ponerNombresAmmoDef(result,ammoLongName[ammoNameCounter],ammoShortName[ammoNameCounter]);
            try {
                guardarJSON(new Gson().toJson(result), "def/" + result[0].getCaliber());
                ammoNameCounter++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }

    }
    private static AmmoDef[] ponerNombresYPen12g(Ammo[] ammo12g){
        ArrayList<AmmoDef> result = new ArrayList<>();
        String caliberName = "12/70";
        String[] longNames = {
                caliberName+" 5.25mm buckshot",
                caliberName+" RIP",
                caliberName+" 8.5mm Magnum buckshot",
                caliberName+" 7mm bukshot",
                caliberName+" 6.5mm buckshot",
                caliberName+" SuperFormance HP slug",
                caliberName+" Grizzly 40 slug",
                caliberName+" Copper Sabot Premier HP slug",
                caliberName+" lead slug",
                caliberName+" \"Poleva-3\" slug",
                caliberName+" Dual Sabot slug",
                caliberName+" \"Poleva-6u\" slug",
                caliberName+" FTX Custom Lite slug",
                caliberName+" Piranha",
                caliberName+" makeshift .50 BMG slug",
                caliberName+" AP-20 armor-piercing slug",
                caliberName+" Flechette",
        };
        String[] shortNames = {
                "5.25mm",
                "RIP",
                "Magnum",
                "7mm bukshot",
                "6.5mm",
                "SFormance",
                "Grizzly 40",
                "CSP",
                "Slug",
                "Poleva-3",
                "DualSabot",
                "Poleva-6u",
                "FTX",
                "Piranha",
                ".50 BMG",
                "AP-20",
                "Flechette",
        };
        PenPerTier[] g12 = {
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(0,0,0,0,0,0),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(0,0,0,0,0,0),
                new PenPerTier(6,2,0,0,0,0),
                new PenPerTier(6,3,1,0,0,0),
                new PenPerTier(6,4,1,0,0,0),
                new PenPerTier(6,5,1,0,0,0),
                new PenPerTier(6,5,2,0,0,0),
                new PenPerTier(6,6,2,0,0,0),
                new PenPerTier(6,6,2,0,0,0),
                new PenPerTier(6,6,5,4,4,4),
                new PenPerTier(6,6,5,3,1,0),
                new PenPerTier(6,6,6,5,4,3),
                new PenPerTier(6,6,6,5,5,5)
        };
        String penPath = "./src/main/java/com/example/cache/pen";
        guardarJSON(new Gson().toJson(g12), "g12", penPath);
        for(int i=0; i < ammo12g.length; i++){
            AmmoDef ammoDef = new AmmoDef(ammo12g[i],g12[i]);
            ammoDef.setLongName(longNames[i]);
            ammoDef.setShortName(shortNames[i]);
            result.add(ammoDef);
        }
        return result.toArray(AmmoDef[]::new);
    }

    private static void ponerNombresAmmoDef(AmmoDef[] ammoDef, String[] longNameAmmo, String[] shortNameAmmo) {
        for (int i = 0; i < ammoDef.length; i++) {
            ammoDef[i].setLongName(longNameAmmo[i]);
            ammoDef[i].setShortName(shortNameAmmo[i]);
        }
    }

    private static ArrayList<Ammo> clasificarPorCalibre(Data data, Caliber caliber) {
        ArrayList<Ammo> result = new ArrayList<>();
        for (Ammo a : data.getAmmo()) {
            if (a.getCaliber().replaceAll("Caliber", "").equalsIgnoreCase(caliber.getInternalName())) {
                result.add(a);
            }
        }
        return result;
    }

    private static void almacenarPenPerTier() {
        String penPath = "./src/main/java/com/example/cache/pen";

        PenPerTier[] mm762x25 = {
                new PenPerTier(5, 0, 0, 0, 0, 0),
                new PenPerTier(5, 0, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 3, 0, 0, 0, 0),
                new PenPerTier(6, 4, 0, 0, 0, 0),
                new PenPerTier(6, 6, 4, 1, 0, 0)
        };

        guardarJSON(new Gson().toJson(mm762x25), "mm762x25", penPath);

        PenPerTier[] mm9x18 = {
                new PenPerTier(0, 0, 0, 0, 0, 0),
                new PenPerTier(0, 0, 0, 0, 0, 0),
                new PenPerTier(0, 0, 0, 0, 0, 0),
                new PenPerTier(2, 0, 0, 0, 0, 0),
                new PenPerTier(2, 0, 0, 0, 0, 0),
                new PenPerTier(3, 0, 0, 0, 0, 0),
                new PenPerTier(3, 0, 0, 0, 0, 0),
                new PenPerTier(4, 0, 0, 0, 0, 0),
                new PenPerTier(5, 1, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 5, 1, 0, 0, 0),
                new PenPerTier(6, 6, 4, 0, 0, 0),
                new PenPerTier(6, 6, 5, 1, 0, 0),
        };

        guardarJSON(new Gson().toJson(mm9x18), "mm9x18", penPath);

        PenPerTier[] mm9x19 = {
                new PenPerTier(0, 0, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 3, 1, 0, 0, 0),
                new PenPerTier(6, 5, 2, 0, 0, 0),
                new PenPerTier(6, 6, 2, 0, 0, 0),
                new PenPerTier(6, 6, 6, 4, 2, 1),
                new PenPerTier(6, 6, 6, 5, 4, 3)
        };

        guardarJSON(new Gson().toJson(mm9x19), "mm9x19", penPath);

        PenPerTier[] mm9x21 = {
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 3, 0, 0, 0, 0),
                new PenPerTier(6, 6, 3, 1, 0, 0),
                new PenPerTier(6, 6, 5, 3, 1, 0),
                new PenPerTier(6, 6, 6, 4, 3, 1),
                new PenPerTier(6, 6, 6, 6, 4, 3)
        };

        guardarJSON(new Gson().toJson(mm9x21), "mm9x21", penPath);

        PenPerTier[] magnum357 = {
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 3, 0, 0, 0, 0),
                new PenPerTier(6, 6, 2, 0, 0, 0),
                new PenPerTier(6, 6, 6, 2, 1, 0)
        };

        guardarJSON(new Gson().toJson(magnum357), "magnum357", penPath);

        PenPerTier[] acp45 = {
                new PenPerTier(1, 0, 0, 0, 0, 0),
                new PenPerTier(6, 3, 0, 0, 0, 0),
                new PenPerTier(6, 5, 1, 0, 0, 0),
                new PenPerTier(6, 6, 3, 1, 0, 0),
                new PenPerTier(6, 6, 6, 5, 4, 2)
        };

        guardarJSON(new Gson().toJson(acp45), "acp45", penPath);

        PenPerTier[] mm46x30 = {
                new PenPerTier(6, 5, 1, 0, 0, 0),
                new PenPerTier(6, 6, 3, 0, 0, 0),
                new PenPerTier(6, 6, 6, 4, 2, 1),
                new PenPerTier(6, 6, 6, 6, 4, 3),
                new PenPerTier(6, 6, 6, 6, 6, 5)
        };

        guardarJSON(new Gson().toJson(mm46x30), "mm46x30", penPath);

        PenPerTier[] mm57x28 = {
                new PenPerTier(4, 0, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 4, 1, 0, 0, 0),
                new PenPerTier(6, 6, 4, 1, 0, 0),
                new PenPerTier(6, 6, 5, 2, 1, 0),
                new PenPerTier(6, 6, 6, 3, 2, 2),
                new PenPerTier(6, 6, 6, 5, 4, 3)
        };

        guardarJSON(new Gson().toJson(mm57x28), "mm57x28", penPath);

        PenPerTier[] mm545x39 = {
                new PenPerTier(5, 0, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 2, 0, 0, 0, 0),
                new PenPerTier(6, 5, 1, 0, 0, 0),
                new PenPerTier(6, 6, 1, 0, 0, 0),
                new PenPerTier(6, 6, 3, 2, 0, 0),
                new PenPerTier(6, 6, 5, 3, 1, 0),
                new PenPerTier(6, 6, 6, 4, 3, 1),
                new PenPerTier(6, 6, 6, 5, 3, 2),
                new PenPerTier(6, 6, 6, 6, 4, 3),
                new PenPerTier(6, 6, 6, 6, 5, 4),
                new PenPerTier(6, 6, 6, 6, 6, 5),
                new PenPerTier(6, 6, 6, 6, 6, 6)
        };

        guardarJSON(new Gson().toJson(mm545x39), "mm545x39", penPath);

        PenPerTier[] mm556x45 = {
                new PenPerTier(1, 0, 0, 0, 0, 0),
                new PenPerTier(4, 0, 0, 0, 0, 0),
                new PenPerTier(6, 1, 0, 0, 0, 0),
                new PenPerTier(6, 5, 1, 0, 0, 0),
                new PenPerTier(6, 6, 4, 1, 0, 0),
                new PenPerTier(6, 6, 5, 3, 2, 0),
                new PenPerTier(6, 6, 6, 4, 2, 1),
                new PenPerTier(6, 6, 6, 5, 3, 2),
                new PenPerTier(6, 6, 6, 6, 5, 4),
                new PenPerTier(6, 6, 6, 6, 6, 5),
                new PenPerTier(6, 6, 6, 6, 6, 5)
        };

        guardarJSON(new Gson().toJson(mm556x45), "mm556x45", penPath);

        PenPerTier[] mm68x51 = {
                new PenPerTier(6, 6, 6, 5, 4, 2),
                new PenPerTier(6, 6, 6, 6, 5, 5)
        };

        guardarJSON(new Gson().toJson(mm68x51), "mm68x51", penPath);

        PenPerTier[] blackout300 = {
                new PenPerTier(6, 4, 2, 1, 0, 0),
                new PenPerTier(6, 6, 4, 3, 1, 0),
                new PenPerTier(6, 6, 6, 3, 2, 0),
                new PenPerTier(6, 6, 6, 5, 4, 2),
                new PenPerTier(6, 6, 6, 6, 5, 3),
                new PenPerTier(6, 6, 6, 6, 5, 4)
        };

        guardarJSON(new Gson().toJson(blackout300), "blackout300", penPath);

        PenPerTier[] mm762x39 = {
                new PenPerTier(6, 4, 1, 0, 0, 0),
                new PenPerTier(6, 6, 2, 0, 0, 0),
                new PenPerTier(6, 6, 4, 1, 0, 0),
                new PenPerTier(6, 6, 5, 3, 1, 0),
                new PenPerTier(6, 6, 6, 3, 1, 0),
                new PenPerTier(6, 6, 6, 5, 3, 2),
                new PenPerTier(6, 6, 6, 6, 4, 3),
                new PenPerTier(6, 6, 6, 6, 5, 4),
                new PenPerTier(6, 6, 6, 6, 6, 5)
        };

        guardarJSON(new Gson().toJson(mm762x39), "mm762x39", penPath);

        PenPerTier[] mm762x51 = {
                new PenPerTier(6, 4, 0, 0, 0, 0),
                new PenPerTier(6, 6, 6, 3, 2, 0),
                new PenPerTier(6, 6, 6, 4, 3, 2),
                new PenPerTier(6, 6, 6, 6, 5, 4),
                new PenPerTier(6, 6, 6, 6, 5, 5),
                new PenPerTier(6, 6, 6, 6, 6, 6),
                new PenPerTier(6, 6, 6, 6, 6, 6)
        };

        guardarJSON(new Gson().toJson(mm762x51), "mm762x51", penPath);

        PenPerTier[] mm762x54 = {
                new PenPerTier(6, 6, 3, 1, 0, 0),
                new PenPerTier(6, 6, 5, 4, 2, 1),
                new PenPerTier(6, 6, 6, 5, 3, 2),
                new PenPerTier(6, 6, 6, 6, 4, 3),
                new PenPerTier(6, 6, 6, 6, 4, 3),
                new PenPerTier(6, 6, 6, 6, 5, 5),
                new PenPerTier(6, 6, 6, 6, 6, 5),
                new PenPerTier(6, 6, 6, 6, 6, 6),
                new PenPerTier(6, 6, 6, 6, 6, 6)
        };

        guardarJSON(new Gson().toJson(mm762x54), "mm762x54", penPath);

        PenPerTier[] lapua338 = {
                new PenPerTier(6, 5, 3, 1, 0, 0),
                new PenPerTier(6, 6, 6, 5, 4, 2),
                new PenPerTier(6, 6, 6, 6, 5, 5),
                new PenPerTier(6, 6, 6, 6, 6, 6)
        };

        guardarJSON(new Gson().toJson(lapua338), "lapua338", penPath);

        PenPerTier[] mm9x39 = {
                new PenPerTier(6, 5, 2, 0, 0, 0),
                new PenPerTier(6, 6, 5, 2, 1, 0),
                new PenPerTier(6, 6, 6, 5, 3, 2),
                new PenPerTier(6, 6, 6, 6, 5, 4),
                new PenPerTier(6, 6, 6, 6, 5, 5),
                new PenPerTier(6, 6, 6, 6, 6, 5)
        };

        guardarJSON(new Gson().toJson(mm9x39), "mm9x39", penPath);

        PenPerTier[] tkm366 = {
                new PenPerTier(6, 3, 0, 0, 0, 0),
                new PenPerTier(6, 6, 4, 1, 0, 0),
                new PenPerTier(6, 6, 6, 3, 1, 0),
                new PenPerTier(6, 6, 6, 6, 5, 4)
        };

        guardarJSON(new Gson().toJson(tkm366), "tkm366", penPath);

        PenPerTier[] mm127x55 = {
                new PenPerTier(6, 0, 0, 0, 0, 0),
                new PenPerTier(6, 6, 5, 2, 1, 0),
                new PenPerTier(6, 6, 6, 6, 5, 4)
        };

        guardarJSON(new Gson().toJson(mm127x55), "mm127x55", penPath);

        /*
        Las penetraciones de las escopetas serán añadidas manualmente dado que no siguen reglas como las demás
        Esto incluye los calibres 12g

        12g

        525mm buckshot [3,3,3,3,3,3]
        rip [0,0,0,0,0,0]
        85mm magnum buckshot [3,3,3,3,3,3]
        7mm buckshot [3,3,3,3,3,3]
        65mm express buckshot [3,3,3,3,3,3]
        superformance hp slug [0,0,0,0,0,0]
        grizzly 40 slug [6,2,0,0,0,0]
        copper sabot premier hp slug [6,3,1,0,0,0]
        lead slug [6,4,1,0,0,0]
        poleva-3 slug [6,5,1,0,0,0]
        dual sabot slug [6,5,2,0,0,0]
        poleva-6u slug [6,6,2,0,0,0]
        ftx custom lite slug [6,6,2,0,0,0]
        piranha [6,6,5,4,4,4]
        makeshift 50 bmg slug [6,6,5,3,1,0]
        ap-20 armor-piercing slug [6,6,6,5,4,3]
        flechette [6,6,6,5,5,5]
         */

        PenPerTier[] g20 = {
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(3,3,3,3,3,3),
                new PenPerTier(1,0,0,0,0,0),
                new PenPerTier(6,2,0,0,0,0),
                new PenPerTier(6,5,1,0,0,0),
                new PenPerTier(6,5,1,0,0,0)
        };

        guardarJSON(new Gson().toJson(g20), "g20", penPath);

        PenPerTier[] mm23x75 = {
                new PenPerTier(0, 0, 0, 0, 0, 0),
                new PenPerTier(6, 4, 3, 3, 3, 3),
                new PenPerTier(6, 4, 3, 3, 3, 3),
                new PenPerTier(6, 6, 6, 6, 4, 4)
        };

        guardarJSON(new Gson().toJson(mm23x75), "mm23x75", penPath);

        PenPerTier[] mm40x46 = {
                new PenPerTier(5, 3, 3, 3, 3, 3),
                new PenPerTier(5, 3, 3, 3, 3, 3),
                new PenPerTier(5, 3, 3, 3, 3, 3),
                new PenPerTier(5, 3, 3, 3, 3, 3),
                new PenPerTier(5, 3, 3, 3, 3, 3),
                new PenPerTier(5, 3, 3, 3, 3, 3)
        };

        guardarJSON(new Gson().toJson(mm40x46), "mm40x46", penPath);

    }

    /*public static void peticionAPI() {
        HttpClient client = HttpClient.newBuilder().build();
        //String query = "{\"query\": \"{ ammo {item{ name } } }\"}";
        String query = "{\"query\": \"{ammo {caliber damage penetrationPower armorDamage accuracyModifier recoilModifier lightBleedModifier heavyBleedModifier fragmentationChance initialSpeed tracer tracerColor ammoType projectileCount}}\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tarkov.dev/graphql"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String jsonString = response.body();
        System.out.println(jsonString);
        guardarJSON(jsonString, "ammo_data");
    }
     */

    public static void guardarJSON(String data, String fileName) {
        String ruta = "./src/main/java/com/example/cache/" + fileName + ".json";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarJSON(String data, String fileName, String path) {
        String ruta = path + "/" + fileName + ".json";
        if (new File(ruta).exists()) {
            return;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Data parsearJSON(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Data.class);
    }

    public static String leerJSON(String fileName) {
        String ruta = "./src/com/raulgarcan/wikitarkov/data/cache/def/";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta + fileName + ".json"));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String leerJSONPen(String fileName) {
        String ruta = "./src/main/java/com/example/cache/pen/";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta + fileName + ".json"));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String leerJsonAndroid(String fileName){
        FirebaseHelper helper = new FirebaseHelper(null);
        String ruta = helper.getPathFiles();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ruta+"/cache/data/" + fileName + ".json"));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}